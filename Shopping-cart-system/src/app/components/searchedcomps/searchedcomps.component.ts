import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cart, product } from 'src/app/data-types';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-searchedcomps',
  templateUrl: './searchedcomps.component.html',
  styleUrls: ['./searchedcomps.component.css']
})
export class SearchedcompsComponent  implements OnInit {

  productList:undefined | product[];

  receivedData: string = '';

  constructor(private product:ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private cartservice:CartService,
    private orders:OrderService,
    private auth:AuthService
    ){}
 
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.receivedData = params['data'];
      console.log(this.receivedData)
    });
    this.product.productListbyName(this.receivedData).subscribe((result)=>{
    this.productList=result;
    // console.log(result)
    }); 
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
    })
    
  }

  rateTheProduct(productId:number){
    this.router.navigate(['/ratings',productId]);
  }

  orderForm(productId:string,price:number){
    this.router.navigate(['/order-form']);
    const data = {
      input1: productId,
      input2: price
    };

    this.orders.setData(data);
  }
}
