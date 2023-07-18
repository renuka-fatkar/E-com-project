import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { product } from '../data-types';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient,
    private auth:AuthService) { }

  productList(category:string){
    return this.http.get<product[]>('http://localhost:8080/product/viewProducts/'+category);
  }

  productListbyName(name:string){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.get<product[]>('http://localhost:8080/product/viewProductsby/'+name,{headers});
  }

  // allproductList(){
  //   const headers = this.auth.getHeadersWithAuthorization();
  //   return this.http.get<product[]>('http://localhost:8080/product/viewallProducts',{headers});
  // }

  viewproductbyId(id:number){
    return this.http.get<product>('http://localhost:8080/product/viewProduct/'+id);

  }

  removeProduct(productId:number){
    const headers = this.auth.getHeadersWithAuthorization();
    return this.http.delete<any>("http://localhost:8080/product/removeProduct/"+productId,{headers});
  }
}
