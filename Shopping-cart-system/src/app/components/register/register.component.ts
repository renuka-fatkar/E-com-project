import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private register: RegisterService, private router:Router){}
  ngOnInit(): void {  }

  signup(data:object):void{
      this.register.userSignUp(data).subscribe(
        (result)=>{
          this.router.navigate(['/login']);
      },
      (error)=>{
          alert("UserName Already Exists");
      }
      );
  }
}
