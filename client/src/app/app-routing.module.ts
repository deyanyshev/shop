import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NotfoundComponent} from './components/notfound/notfound.component';
import {AuthComponent} from './components/auth/auth.component';
import {ProductsComponent} from "./components/products/products.component";
import {UserComponent} from "./components/user/user.component";
import {BasketComponent} from "./components/basket/basket.component";

const routes: Routes = [
  { path: '', component: ProductsComponent },
  { path: 'auth', component: AuthComponent },
  { path: 'profile', component: UserComponent },
  { path: 'basket', component: BasketComponent },
  { path: '**', component: NotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
