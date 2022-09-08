import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable } from 'rxjs';
import {ApiService} from "../service/api.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private api: ApiService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.api.getCookie('token');

    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });

    return next.handle(authReq);
  }
}
