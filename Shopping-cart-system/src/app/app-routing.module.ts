import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { JewelleryComponent } from './components/jewellery/jewellery.component';
import { TodaysdealsComponent } from './components/todaysdeals/todaysdeals.component';
import { ElectronicsComponent } from './components/electronics/electronics.component';
import { DecoresComponent } from './components/decores/decores.component';
import { ClothesComponent } from './components/clothes/clothes.component';
import { CartComponent } from './components/cart/cart.component';
import { OrdersComponent } from './components/orders/orders.component';
import { HelpComponent } from './components/help/help.component';
import { SellerAuthComponent } from './components/seller-auth/seller-auth.component';
import { authGuard } from './auth.guard';
import { AllproductsComponent } from './components/allproducts/allproducts.component';
import { RatingsComponent } from './components/ratings/ratings.component';
import { OrderformComponent } from './components/orderform/orderform.component';
import { ProductFormComponent } from './components/product-form/product-form.component';
import { SearchedcompsComponent } from './components/searchedcomps/searchedcomps.component';

const routes: Routes = [
  {
    path:"login",
    component:LoginComponent,
    canActivate:[authGuard]
  },
  {
    path:"register",
    component:RegisterComponent
  },
  {
    path:"jewellery",
    component:JewelleryComponent
  },
  {
    path:"",
    component:TodaysdealsComponent
  },
  {
    path:"electronics",
    component:ElectronicsComponent
  },
  {
    path:"decores",
    component:DecoresComponent
  },
  {
    path:"clothes",
    component:ClothesComponent
  },
  {
    path:"cart",
    component:CartComponent
  },
  {
    path:"orders",
    component:OrdersComponent
  },
  {
    path:"help",
    component:HelpComponent
  },
  {
    path:"seller-auth",
    component:SellerAuthComponent
  },
  {
    path:"allproducts",
    component:AllproductsComponent
  },
  {
    path:"ratings/:data",
    component:RatingsComponent
  },
  {
    path:"order-form",
    component:OrderformComponent
  },
  {
    path:"productForm",
    component:ProductFormComponent
  },
  {
    path:"searchedcomps/:data",
    component:SearchedcompsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
