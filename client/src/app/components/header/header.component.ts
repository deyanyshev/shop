import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ApiService} from "../../service/api.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  path = window.location.pathname;
  isLogin = false; /** Флаг авторизации пользователя, нужен для рендера верных шаблонов **/

  constructor(private userService: UserService) { }

  ngOnInit() {
    /** Определение флага авторизации пользователя **/
    this.userService.getUser().subscribe(res => {
      if (res.login != null) this.isLogin = true;
    })
  }

}
