import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { product } from 'src/app/data-types';
import { AuthService } from 'src/app/services/auth.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-allproducts',
  templateUrl: './allproducts.component.html',
  styleUrls: ['./allproducts.component.css']
})
export class AllproductsComponent implements OnInit{
  
  currentPage = 1;
  pageSize = 5;
  totalItems = 0;
  data: any[] = [];
  clothes:undefined | product[];
  decores:undefined | product[];
  electronics:undefined | product[];
  jewellery:undefined | product[];
  constructor(private product:ProductService,
    private router:Router,
    private http: HttpClient,
    private auth:AuthService){

  }
 
  ngOnInit(): void {
    // this.product.productList('clothes').subscribe((result)=>{
    //   this.clothes=result;
    // });
    // this.product.productList('electronics').subscribe((result)=>{
    //   this.electronics=result;
    // });
    // this.product.productList('jewellery').subscribe((result)=>{
    //   this.jewellery=result;
    // });
    // this.product.productList('decores').subscribe((result)=>{
    //   this.decores=result;
    // });

    this.getData();

  }

  getData() {
    // Call the API with pagination parameters
    const url = 'http://localhost:8080/product/viewallProducts';
    const headers = this.auth.getHeadersWithAuthorization();
    const params = new HttpParams()
      .set('pageNumber', this.currentPage.toString())
      .set('pageSize', this.pageSize.toString());

    this.http.get<product[]>(url, {headers, params }).subscribe((response: any) => {
      // Update component properties with API response
      this.data = response;
      this.totalItems = 55;
      console.log(response);
    });
  }

  onPageChange(page: number) {
    // Update the current page and fetch data for the new page
    this.currentPage = page;
    this.getData();
  }

  addNewProduct(){
    this.router.navigate(['/productForm'])
  }

  remove(productId:number){
    const confirmed = window.confirm('Are you sure you want to delete this product?');
    if(confirmed){
      this.product.removeProduct(productId).subscribe(
        (result)=>{
          
        }
      )
    }
  }

}
