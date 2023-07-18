import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart, product } from 'src/app/data-types';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-electronics',
  templateUrl: './electronics.component.html',
  styleUrls: ['./electronics.component.css']
})
export class ElectronicsComponent implements OnInit{

  productList:undefined | product[];
  constructor(private product:ProductService,
    private router: Router,
    private cartservice:CartService,
    private orders:OrderService,
    private auth:AuthService){

  }
 
  ngOnInit(): void {
    this.product.productList('electronics').subscribe((result)=>{
    this.productList=result;
    })
    
  }

  addToCart(productId:number,name:string,price:number,specifications:string,img:string){
    const cart: Cart={
      cartId:0,
      userName:'',
      quantity:1,
      prodId:productId,
      prodName:name,
      price:price,
      specifications:specifications,
      img:img
    };
    this.auth.getUserInfo().subscribe(
      (result)=>{
        cart.userName=result.userName;
        this.cartservice.addToCart(cart).subscribe(
          response => {
          }
        )
    },
    (error)=>{
      alert("You need to Login First")
      this.router.navigate(['/login']);
    })
    
  }

  rateTheProduct(productId:number){
    this.auth.getUserInfo().subscribe(
      (result)=>{
        this.router.navigate(['/ratings',productId]);
    },
    (error)=>{
      alert("You need to Login First")
      this.router.navigate(['/login']);
    })
    
  }

  orderForm(productId:string,price:number){
    this.auth.getUserInfo().subscribe(
      (result)=>{
        this.router.navigate(['/order-form']);
      const data = {
        input1: productId,
        input2: price
    };

    this.orders.setData(data);
    },
    (error)=>{
      alert("You need to Login First")
      this.router.navigate(['/login']);
    })
    
  }

}
