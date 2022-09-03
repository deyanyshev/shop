import {Component, Directive, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from "../../assets/user";


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  newUser: User;
  inputUser: User;

  constructor(private userService: UserService) {
    this.newUser = new User();
    this.inputUser = new User();
  }

  ngOnInit() {
  }

  logIn() {
    this.userService.logInUser(this.inputUser).subscribe(res => {
      if (res == "ok") {
        location.href = '/';
      } else {
        alert(res);
      }
    });
  }

  addUser() {
    this.userService.addUser(this.newUser).subscribe(res => {
      if (res == "ok") {
        location.href = '/';
      } else {
        alert(res);
      }
    });
  }
}
