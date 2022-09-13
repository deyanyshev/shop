import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../models/user";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';
import {Data} from "@angular/router";
import {Product} from "../models/product";


@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public isLoggedIn():Observable<boolean> {
    return this.http.get<boolean>('api/users/check-auth');
  }

  public getAllUsers():Observable<User[]> {
    return this.http.get<User[]>('api/users/get-all');
  }

  public getUser():Observable<User> {
    return this.http.get<User>('api/users/user');
  }

  public getProducts():Observable<Product[]> {
    return this.http.get<Product[]>('api/users/products');
  }

  public logInUser(user: User):Observable<any> {
    return this.http.post('api/users/auth', user);
  }

  public deleteUser(user: User) {
    return this.http.post('api/users/delete', user);
  }

  public revokeProduct(id: number) {
    return this.http.post(`api/users/revoke-product`, id);
  }

  public addUser(user: User) {
    return this.http.post('api/users/add', user);
  }
}
