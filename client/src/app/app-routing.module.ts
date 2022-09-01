import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import {NotfoundComponent} from './components/notfound/notfound.component';
import {AuthComponent} from './components/auth/auth.component';

const routes: Routes = [
  { path: 'products', component: ProductComponent },
  { path: 'auth', component: AuthComponent},
  { path: '**', component: NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
