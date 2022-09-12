import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {map} from "rxjs/operators";
import {User} from "../../models/user";
import {Role} from "../../models/enums/role"

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  path = window.location.pathname;
  isLoggedIn = false; /** Флаг авторизации пользователя, нужен для рендера верных шаблонов **/
  user: User;
  roles = Role;

  constructor(private userService: UserService) { }

  async ngOnInit() {
    /** Определение флага авторизации пользователя **/
    this.userService.isLoggedIn().pipe(
      map((data:boolean) => {
        this.isLoggedIn = data;
        this.userService.getUser().pipe(
          map(user => {
            this.user = user;
          })
        ).subscribe();
      })
    ).subscribe();
  }

}
