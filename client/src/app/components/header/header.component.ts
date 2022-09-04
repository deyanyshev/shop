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
  isLogin = false;

  constructor(private userService: UserService, private apiService: ApiService) { }

  ngOnInit() {
    this.userService.getUser(this.apiService.getCookie('token')).subscribe(res => {
      if (res.login != null) this.isLogin = true;
    })
  }

}
