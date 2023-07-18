import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderDetails } from 'src/app/data-types';
// import * as Razorpay from 'razorpay';

import { OrderService } from 'src/app/services/order.service';

declare var Razorpay: any;
@Component({
  selector: 'app-orderform',
  templateUrl: './orderform.component.html',
  styleUrls: ['./orderform.component.css']
})
export class OrderformComponent implements OnInit{
  

  receivedData: any;
  orderDetails:OrderDetails ={
    orderdetailId:0,
    nameOfUser: '',
    order_date:'',
    addressOfCust:'',
    phone:'',
    productId:'',
    quantity:0,
    amount:0,
    transactionId:''
  }

  constructor(private orders:OrderService,
    private router:Router){}
  
  ngOnInit(): void {}

  placeOrderWithDetails(data:Object){
      console.log(data);
      this.receivedData = this.orders.getData();
      console.log(this.receivedData);
      this.orders.createTransaction(this.receivedData.input2*100).subscribe(
        (response) =>{
          console.log(response);
          this.openTransactionPropmt(response,data,this.receivedData);
        },
        (error)=>{
          console.log(error);
        }
      );
  }

  openTransactionPropmt(response:any,data:Object,receivedData:any){
    var option = {
        order_id:response.orderId,
        key:response.key,
        amount:response.amount,
        currency:response.currency,
        name: 'DishShopping',
        description: 'Payment of online shopping',
        image: 'assets/logo.png',
        handler: (result :any )=>{
          if(result!=null && result.razorpay_payment_id!=null){
            this.processResult(result,data,receivedData);
          }else{
            alert("Payment failed..");
          }
        },
        prefil:{
          name:'DS',
          email:'DS@GMAIL.COM',
          contact:'90900909'
        },
        notes:{
          address:'Disha Shopping'
        },
        theme:{
          color: '#f37254'
        }
    };
    var razorPayObject = new Razorpay(option);
    razorPayObject.open();
  }

  processResult(res:any,data:any,receivedData:any){
    console.log("data" +data);
    this.orderDetails.nameOfUser = data.name;
    this.orderDetails.addressOfCust = data.address;
    this.orderDetails.phone = data.phone;
    this.orderDetails.productId = receivedData.input1;
    this.orderDetails.quantity = data.quantity;
    this.orderDetails.amount = receivedData.input2;
    this.orderDetails.transactionId = res.razorpay_payment_id;
    this.orders.placeOrderandSave(this.orderDetails).subscribe(
      (ret)=>{
        this.router.navigate(['/orders']);
        alert(ret.data);
      },
      (error)=>{
        this.router.navigate(['/login']);
      }
    );
  }


}
