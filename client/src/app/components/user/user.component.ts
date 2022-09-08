import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from "../../models/user";
import {ApiService} from "../../service/api.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.components.css']
})
export class UserComponent implements OnInit {
  user: User;

  constructor(private userService: UserService, private apiService: ApiService) {
    this.user = new User();
  }

  logOut() {
    this.apiService.deleteCookie('token');
    location.href = '/';
  }

  deleteUser() {
    this.userService.deleteUser().subscribe();
    this.apiService.deleteCookie('token');
    location.href = '/';
  }

  ngOnInit(): void {
    this.userService.getUser().subscribe(res => {
      this.user = res;

      if (this.user.login == null) location.href = '/';
    })
  }
}
