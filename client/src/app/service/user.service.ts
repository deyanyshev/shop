import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../models/user";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';


@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUser():Observable<User> {
    return this.http.get('api/users/user/').pipe(map((data:any) => {
      return data;
    }));
  }

  public logInUser(user: User):Observable<any> {
    return this.http.post('api/users/auth', user).pipe(map((data:any) => {
      return data;
    }));
  }

  public deleteUser() {
    return this.http.post('api/users/delete/', {});
}

  public addUser(user: User):Observable<any> {
    return this.http.post('api/users/add', user).pipe(map((data:any) => {
      return data;
    }));
  }
}
