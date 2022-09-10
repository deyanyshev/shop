import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NotfoundComponent} from './components/notfound/notfound.component';
import {AuthComponent} from './components/auth/auth.component';
import {ProductsComponent} from "./components/products/products.component";
import {UserComponent} from "./components/user/user.component";
import {BasketComponent} from "./components/basket/basket.component";
import {CreatingProductComponent} from "./components/creating-product/creating-product.component";
import {NotAuthGuard} from "./guards/not-auth.guard";
import {AuthGuard} from "./guards/auth.guard";
import {AdminGuard} from "./guards/admin.guard";

const routes: Routes = [
  { path: '', component: ProductsComponent },
  { path: 'auth', component: AuthComponent, canActivate: [NotAuthGuard] },
  { path: 'profile', component: UserComponent, canActivate: [AuthGuard] },
  { path: 'basket', component: BasketComponent, canActivate: [AuthGuard] },
  { path: 'create-product', component: CreatingProductComponent, canActivate: [AdminGuard] },
  { path: '**', component: NotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
