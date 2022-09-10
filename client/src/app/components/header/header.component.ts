import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ApiService} from "../../service/api.service";
import {map} from "rxjs/operators";
import {bottom} from "@popperjs/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  path = window.location.pathname;
  isLoggedIn = false; /** Флаг авторизации пользователя, нужен для рендера верных шаблонов **/

  constructor(private userService: UserService) { }

  async ngOnInit() {
    /** Определение флага авторизации пользователя **/
    this.userService.isLoggedIn().pipe(
      map((data:boolean) => {
        this.isLoggedIn = data;
      })
    ).subscribe();
  }

}
