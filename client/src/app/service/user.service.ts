import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../models/user";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';
import {Data} from "@angular/router";


@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public isLoggedIn():Observable<boolean> {
    return this.http.get<boolean>('/api/users/check-auth');
  }

  public getUser():Observable<User> {
    return this.http.get<User>('api/users/user');
  }

  public logInUser(user: User):Observable<any> {
    return this.http.post('api/users/auth', user);
  }

  public deleteUser() {
    return this.http.post('api/users/delete', {});
}

  public addUser(user: User) {
    return this.http.post('api/users/add', user);
  }
}
