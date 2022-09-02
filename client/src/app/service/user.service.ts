import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from "../assets/user";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public addUser(user: User) {
    return this.http.post('http://localhost:8080/users/add', user);
  }
}
