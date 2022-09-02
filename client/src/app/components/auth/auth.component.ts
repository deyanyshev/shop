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

  constructor(private userService: UserService) {
    this.newUser = new User();
  }

  ngOnInit() {
  }

  addUser() {
    this.userService.addUser(this.newUser).subscribe(res => {
      console.log(res);
    })
  }
}
