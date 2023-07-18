import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { ProductService } from 'src/app/services/product.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
 
  
  textInput!: string;

  constructor(private router:Router,
    private auth:AuthService,
    private product:ProductService){}
 
  ngOnInit(): void {}
  
  onInputChange(name:string) {
    console.log(name)
    this.router.navigate(['/searchedcomps',name])
  }
  navigateCart(){
    // this.auth.checkTokenExpiredCart();
    // alert("You need to login First")
    this.auth.isTokenExpired().subscribe(
      (result)=>{
        this.router.navigate(['/cart']);  
      },
      (error)=>{
        this.router.navigate(['/login']);
        Swal.fire({
          icon: 'warning',
          title: 'Login Required',
          text: 'Please login',
          confirmButtonColor: '#3085d6',
          confirmButtonText: 'Login',
          showCancelButton: true,
          cancelButtonColor: '#d33',
          cancelButtonText: 'Cancel'
        });
      }
    );   
  }
  refreshPage(): void {
    this.auth.refreshPage();
  }
  

  logout(): void {
    // Remove the token from local storage
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }



  navigateAllProduct(){
    this.auth.isTokenExpired().subscribe(
      (result)=>{
        this.auth.getUserInfo().subscribe(
          (result)=>{
            this.auth.isAdmin(result.userName).subscribe(
              (result)=>{
                if(result.res==="true"){
                  this.router.navigate(['/allproducts'])
                }
                else{
                  Swal.fire({
                    icon: 'warning',
                    title: 'Invalid Access',
                    text: 'You are not authorized to view this',
                    confirmButtonColor: '#3085d6',
                  });
                }
              }
            )
          }
        );
      },
      (error)=>{
        this.router.navigate(['/seller-auth']);
        Swal.fire({
          icon: 'warning',
          title: 'Login Required',
          text: 'Please login',
          confirmButtonColor: '#3085d6',
          confirmButtonText: 'Login',
          showCancelButton: true,
          cancelButtonColor: '#d33',
          cancelButtonText: 'Cancel'
        });
      }
    ); 
    
  }

 

  sellerlogin(){
    this.auth.isTokenExpired().subscribe(
      (result)=>{
        alert("You are already logged in")
        //this.router.navigate(['/orders']);  
      },
      (error)=>{
        this.router.navigate(['/seller-auth']);
      }
    );   
  }  
  

  userlogin(){
    this.auth.isTokenExpired().subscribe(
      (result)=>{
        alert("You are already logged in")
        //this.router.navigate(['/orders']);  
      },
      (error)=>{
        this.router.navigate(['/login']);
      }
    );   
  
  }

  navigateOrder(){
    // this.auth.checkTokenExpiredCart();
    // alert("You need to login First")
    this.auth.isTokenExpired().subscribe(
      (result)=>{
        this.router.navigate(['/orders']);  
      },
      (error)=>{
        this.router.navigate(['/login']);
        alert("PLease login first")
      }
    );   
  }  
}
