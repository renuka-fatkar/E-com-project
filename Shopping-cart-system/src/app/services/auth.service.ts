import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { Cart } from '../data-types';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient,
    private router:Router){}

  login(data:any){
    this.http.post('http://localhost:8080/user/authenticate',data).subscribe(
      (result:any)=>{
        localStorage.setItem("token",result.token)
        this.router.navigate(['/cart'])
      },
      (error)=>{
        Swal.fire({
          icon: 'warning',
          title: 'Invalid Credentials',
          text: 'Please enter the valid username or password'
        });
      }
    )
  }

  sellerlogin(data:any){
    this.http.post('http://localhost:8080/user/authenticate',data).subscribe(
      (result:any)=>{
        localStorage.setItem("token",result.token);
        this.getUserInfo().subscribe(
          (result)=>{
            this.isAdmin(result.userName).subscribe(
              (result)=>{
                if(result.res==="true"){
                  this.router.navigate(['/allproducts'])
                }
                else{
                  Swal.fire({
                    icon: 'warning',
                    title: 'Invalid Access',
                    text: 'You are not authorized to view this content'
                  });
                }
              }
            )
          }
        );
      },
      (error)=>{
        Swal.fire({
          icon: 'warning',
          title: 'Invalid Credentials',
          text: 'Please enter the valid username or password'
        });
      }
    )
  }

  refreshPage(): void {
    location.reload();
  }
  getHeadersWithAuthorization(): HttpHeaders {
    const token = localStorage.getItem("token"); // Retrieve the JWT token from localStorage

    // Create the HttpHeaders object with the Authorization header
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  // checkTokenExpiredCart(): void {
  //   this.http.get('http://localhost:8080/user/checkIfTokenExpired')
  //     .pipe(
  //       catchError((error: HttpErrorResponse) => {
  //         if (error.status === 403) {
  //           console.log('Token is expired or invalid.');
  //           this.router.navigate(['/login']); 
  //           // Perform any additional actions, such as redirecting to the login page
  //         } else {
  //           console.error('An error occurred:', error.message);
  //         }
  //         return throwError('Something went wrong. Please try again later.');
  //       })
  //     )
  //     .subscribe(() => {
  //       console.log('Token is valid.');
  //       this.router.navigate(['/cart']); 
  //        // Executed if the request is successful
  //     });
  // }

  // checkTokenExpiredOrder(): void {
  //   this.http.get('http://localhost:8080/user/checkIfTokenExpired')
  //     // .pipe(
  //     //   catchError((error: HttpErrorResponse) => {
  //     //     if (error.status === 403) {
  //     //       console.log('Token is expired or invalid.');
  //     //       this.router.navigate(['/login']); 
  //     //       // Perform any additional actions, such as redirecting to the login page
  //     //     } else {
  //     //       console.error('An error occurred:', error.message);
  //     //     }
  //     //     return throwError('Something went wrong. Please try again later.');
  //     //   })
  //     // )
  //     .subscribe((result) => {
  //       console.log('Token is valid.');
  //       this.router.navigate(['/orders']); 
  //        // Executed if the request is successful
  //     });
  // }
  
  isTokenExpired(){
    const token = localStorage.getItem("token");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`http://localhost:8080/user/checkIfTokenExpired`, { headers });
  }

  cart(){
    const headers = this.getHeadersWithAuthorization();
    this.http.get<Cart[]>("http://localhost:8080/cart/viewCart",{headers}).subscribe(
      (result)=>{} );
  }

  getUserInfo(){
    const token = localStorage.getItem("token");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`http://localhost:8080/user/info`, { headers });
  }

  isAdmin(name:string){
    return this.http.get<any>("http://localhost:8080/user/isAdmin/"+name);
  }

}
