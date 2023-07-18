import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart, OrderDetails } from '../data-types';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  
  private sharedData: any;

  constructor(private http:HttpClient) { }

  setData(data: any) {
    this.sharedData = data;
    console.log(this.sharedData);
  }

  getData() {
    return this.sharedData;
  }

  createTransaction(amount:number){
    const token = localStorage.getItem("token");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get('http://localhost:8080/order/createTransaction/'+amount,{ headers });
  }

  placeOrderandSave(data:OrderDetails){
    const token = localStorage.getItem("token");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post<any>('http://localhost:8080/order/placeOrder',data,{ headers });
  }

  getAllOrdersOfUser(name:string){
    const token = localStorage.getItem("token");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<OrderDetails[]>('http://localhost:8080/order/getOrdersByName/'+name,{ headers })
  }

  
}
