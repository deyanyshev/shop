import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../service/user.service";
import {Injectable} from "@angular/core";
import {map} from "rxjs/operators";
import {Role} from "../models/enums/role";
import {User} from "../models/user";

@Injectable()
export class AdminGuard implements CanActivate {
  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<boolean> {
    return this.userService.getUser().pipe(
      map((user) => {
        if (user.role == Role.admin || user.role == Role.super_admin) {
          return true;
        } else {
          this.router.navigateByUrl('/');
          return false;
        }
      })
    )
  }
}
