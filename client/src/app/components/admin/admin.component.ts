import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../models/user";
import {map} from "rxjs/operators";
import {Role} from "../../models/enums/role";
import {NgForm} from "@angular/forms";

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  newUser: User;
  users: User[];
  displayedColumns: string[] = ['id', 'login', 'name', 'email', 'role', 'actions'];

  constructor(private userService: UserService) {
    this.newUser = new User();
  }

  ngOnInit(): void {
    this.userService.getAllUsers().pipe(
      map(data => {
        this.users = data;
      })
    ).subscribe();
  }

  deleteUser(user:User) {
    this.userService.deleteUser(user).subscribe();
    location.reload();
  }

  addAdmin() {
    this.newUser.role = Role.admin;
    this.userService.addUser(this.newUser).pipe(
      map((res:any) => {
          if (res.status == "ok") {
            alert("Администратор добавлен успешно");
            location.reload();
          } else {
            alert(res.status);
          }
        }
      )).subscribe();
  }
}
