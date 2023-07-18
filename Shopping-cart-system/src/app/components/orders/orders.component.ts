import { Component, OnInit } from '@angular/core';
import { OrderDetails } from 'src/app/data-types';
import { AuthService } from 'src/app/services/auth.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{

  empty:undefined | string;
  userOrdders : undefined | OrderDetails[];
  constructor(private orders:OrderService,
    private auth:AuthService){}

  ngOnInit(): void {
    this.auth.getUserInfo().subscribe(
      (result)=>{
        this.orders.getAllOrdersOfUser(result.userName).subscribe(
          (result) =>{
            if(result.length === 0){
              this.empty = "No Orders Placed";
            }
            this.userOrdders=result;
          }
        );
      }
    )
  }

}
