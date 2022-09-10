import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../service/user.service";
import {Injectable} from "@angular/core";
import {map} from "rxjs/operators";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<boolean> {
    return this.userService.isLoggedIn().pipe(
      map((data) => {
        if (data) {
          return true;
        } else {
          this.router.navigateByUrl('/auth');
          return false;
        }
      })
    )
  }
}
