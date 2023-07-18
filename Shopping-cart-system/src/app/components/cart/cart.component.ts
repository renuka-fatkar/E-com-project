import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart, product } from 'src/app/data-types';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { Observable, forkJoin } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{

  name="cart";
  cart:Cart[] | undefined;
  bill:undefined | number;
  empty:undefined | string;
  productids: undefined | string;
  

  constructor(private cartservice:CartService,
    private product:ProductService,
    private router:Router,
    private orders:OrderService,
    private auth:AuthService){}

  ngOnInit(): void {
    this.auth.getUserInfo().subscribe(
      (result)=>{
        this.cartservice.viewCartByUserName(result.userName).subscribe(
          (result) =>{
            if(result.length === 0){
              this.empty = "Your Cart is Empty";
            }
            this.cart=result;
          },
          (error)=>{
            console.log("Could not fetch details")
          }
        );
        this.cartservice.calculateBill(result.userName).subscribe((result)=>{
          this.bill=result;
        },
        (error)=>{
          console.log(error);
        });
        this.cartservice.mergeProductId(result.userName).subscribe(
          (ans)=>{
            this.productids = ans.data;
          },
          (error)=>{
            console.log(error);
          }
        )
      }
    )
    
  }

  removeFromCart(cartId:number){
    this.auth.getUserInfo().subscribe(
      (result)=>{
    this.cartservice.removeFromCart(cartId).subscribe(
      () => {
        console.log('Resource deleted successfully.');
        this.cart = this.cart?.filter(item=>item.cartId !== cartId);
        this.cartservice.calculateBill(result.userName).subscribe((result)=>{
          this.bill=result;
        },
        (error)=>{
          console.log(error);
        })
        if(this.cart?.length === 0){
          this.empty = "Your Cart is Empty";
        }
        // Perform any additional actions after successful deletion
      },
      (error) => {
        console.error('An error occurred while deleting the resource:', error);
        // Handle the error appropriately
      }
    );
  });
}


  orderForm(productId:string,price:number){
    this.router.navigate(['/order-form']);
    const data = {
      input1: productId,
      input2: price
    };
    this.orders.setData(data);
  }
  
  orderFormForAll(){
    if (this.productids &&  this.bill) {
      this.orderForm(this.productids, this.bill);
    } else {
      console.log(this.bill);
    }
  
  } 

}
