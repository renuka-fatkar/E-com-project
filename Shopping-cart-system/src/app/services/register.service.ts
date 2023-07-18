import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http:HttpClient) { }

  userSignUp(data:any){
    return this.http.post('http://localhost:8080/user/register', data);
  }
}
