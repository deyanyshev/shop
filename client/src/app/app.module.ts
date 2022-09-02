import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {NgModel, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { UserComponent } from './components/user/user.component';

import { ProductService } from './service/product.service';
import {UserService} from './service/user.service';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { AuthComponent } from './components/auth/auth.component';
import { ProductsComponent } from './components/products/products.component';
import { BasketComponent } from './components/basket/basket.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    UserComponent,
    NotfoundComponent,
    AuthComponent,
    ProductsComponent,
    BasketComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgbModule,
        ReactiveFormsModule
    ],
  providers: [ProductService, UserService],
  bootstrap: [AppComponent, AuthComponent]
})
export class AppModule { }
