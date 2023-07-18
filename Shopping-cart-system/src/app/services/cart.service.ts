import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart, MyData } from '../data-types';
import { AuthService } from './auth.service';
import { map } from 'rxjs/internal/operators/map';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http:HttpClient,
    private auth:AuthService) { }

  addToCart(data:Cart){
    const headers = this.auth.getHeadersWithAuthorization();
      return this.http.post('http://localhost:8080/cart/addToCart',data,{headers});
  }



  viewCartByUserName(data:string){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.get<Cart[]>('http://localhost:8080/cart/viewCart/'+data,{headers});
  }

  calculateBill(data:string){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.get<number>('http://localhost:8080/cart/calculateBill'+data,{headers});
  }

  mergeProductId(data:string){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.get<MyData>('http://localhost:8080/cart/mergeProductId'+data,{headers});
  }

  removeFromCart(cartId:number){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.delete('http://localhost:8080/cart/removeFromCart/'+cartId,{headers});
  }
}
