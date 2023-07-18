import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { RegisterComponent } from './components/register/register.component';
import { TodaysdealsComponent } from './components/todaysdeals/todaysdeals.component';
import { ClothesComponent } from './components/clothes/clothes.component';
import { ElectronicsComponent } from './components/electronics/electronics.component';
import { JewelleryComponent } from './components/jewellery/jewellery.component';
import { DecoresComponent } from './components/decores/decores.component';
import { LoginComponent } from './components/login/login.component';
import { CartComponent } from './components/cart/cart.component';
import { OrdersComponent } from './components/orders/orders.component';
import { HelpComponent } from './components/help/help.component';
import { SellerAuthComponent } from './components/seller-auth/seller-auth.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AllproductsComponent } from './components/allproducts/allproducts.component';
import { RatingsComponent } from './components/ratings/ratings.component';
import { OrderformComponent } from './components/orderform/orderform.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductFormComponent } from './components/product-form/product-form.component';
import { SearchedcompsComponent } from './components/searchedcomps/searchedcomps.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    TodaysdealsComponent,
    ClothesComponent,
    ElectronicsComponent,
    JewelleryComponent,
    DecoresComponent,
    LoginComponent,
    CartComponent,
    OrdersComponent,
    HelpComponent,
    SellerAuthComponent,
    AllproductsComponent,
    RatingsComponent,
    OrderformComponent,
    ProductFormComponent,
    SearchedcompsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
