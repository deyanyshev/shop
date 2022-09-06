import {Component, Directive, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from "../../models/user";
import {ApiService} from "../../service/api.service";


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  newUser: User;
  inputUser: User;

  constructor(private userService: UserService, private apiService: ApiService) {
    this.newUser = new User();
    this.inputUser = new User();
  }

  ngOnInit() {
  }

  /**
   * Авторизация пользователя
   */
  logIn() {
    this.userService.logInUser(this.inputUser).subscribe(res => {
      if (res.status == "ok") {
        this.apiService.setCookie('token', res.token,5);
        location.href = '/';
      } else {
        alert(res.status);
      }
    });
  }

  /**
   * Добавление нового пользователя
   */
  addUser() {
    this.userService.addUser(this.newUser).subscribe(res => {
      if (res.status == "ok") {
        this.apiService.setCookie('token', res.token,5);
        location.href = '/';
      } else {
        alert(res.status);
      }
    });
  }
}
