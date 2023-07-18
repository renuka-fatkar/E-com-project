import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { product } from 'src/app/data-types';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit{

  constructor(
    private formBuilder: FormBuilder,
    private http:HttpClient,
  private auth:AuthService){}

  productForm!: FormGroup;

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      category: ['', Validators.required],
      specifications: ['', Validators.required],
      img: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.productForm.invalid) {
      return;
    }

    const ratingData = this.productForm.value;
    const newProduct: product = {
      productId:0,
      name:ratingData.name,
      price:ratingData.price,
      category:ratingData.category,
      specifications:ratingData.specifications,
      img:ratingData.img
    };
    const headers = this.auth.getHeadersWithAuthorization();
    this.http.post('http://localhost:8080/product/addProduct', newProduct,{headers}).subscribe(
      () => {
        this.productForm.reset();
      },
      (error) => {
        console.error('Failed to save product:', error);
      }
    );
  }
}
