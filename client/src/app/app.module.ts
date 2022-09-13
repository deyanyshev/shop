import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {NgModel, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProductComponent } from './components/product/product.component';
import { UserComponent } from './components/user/user.component';

import { ProductService } from './service/product.service';
import {UserService} from './service/user.service';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { AuthComponent } from './components/auth/auth.component';
import { ProductsComponent } from './components/products/products.component';
import { BasketComponent } from './components/basket/basket.component';
import {NgbModule, NgbToast, NgbToastModule} from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import {ApiService} from "./service/api.service";
import { CreatingProductComponent } from './components/creating-product/creating-product.component';
import {AuthInterceptor} from "./http-interceptors/auth-interceptor";
import {NotAuthGuard} from "./guards/not-auth.guard";
import {AuthGuard} from "./guards/auth.guard";
import {AdminGuard} from "./guards/admin.guard";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {TypeService} from "./service/type.service";
import {CountryService} from "./service/country.service";
import {FileService} from "./service/file.service";
import {FileSaverModule} from "ngx-filesaver";
import { AdminComponent } from './components/admin/admin.component';
import {SuperAdminGuard} from "./guards/super-admin.guard";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MdbCheckboxModule} from "mdb-angular-ui-kit/checkbox";
import {MdbFormsModule} from "mdb-angular-ui-kit/forms";
import {MdbCollapseModule} from "mdb-angular-ui-kit/collapse";
import {MatSelectModule} from "@angular/material/select";
import {MatCard, MatCardModule} from "@angular/material/card";
import {ToastService} from "./service/toast.service";
import {ToastsContainer} from "./components/toasts-container/toasts-container.component";


@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    UserComponent,
    NotfoundComponent,
    AuthComponent,
    ProductsComponent,
    BasketComponent,
    HeaderComponent,
    CreatingProductComponent,
    AdminComponent,
    ToastsContainer
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
    MatInputModule,
    FileSaverModule,
    MatTableModule,
    MatButtonModule,
    MatSelectModule,
    MatCardModule,
  ],
  providers: [
    ProductService,
    UserService,
    ApiService,
    TypeService,
    CountryService,
    ToastService,
    FileService,
    NotAuthGuard,
    AuthGuard,
    AdminGuard,
    SuperAdminGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent, AuthComponent]
})
export class AppModule { }
