import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../assets/user";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public logInUser(user: User):Observable<string> {
    return this.http.post('http://localhost:8080/users/auth', user).pipe(map((data:any) => {
      return data.status;
    }));
  }

  public addUser(user: User):Observable<string> {
    return this.http.post('http://localhost:8080/users/add', user).pipe(map((data:any) => {
      return data.status;
    }));
  }
}
