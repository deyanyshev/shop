import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable()
export class ApiService {
  constructor() {
  }

  public getCookie(name: string) {
    let ca: Array<string> = document.cookie.split(';');
    let caLen: number = ca.length;
    let cookieName = `${name}=`;
    let c: string;

    for (let i: number = 0; i < caLen; i += 1) {
      c = ca[i].replace(/^\s+/g, '');
      if (c.indexOf(cookieName) == 0) {
        return c.substring(cookieName.length, c.length);
      }
    }
    return '';
  }

  public deleteCookie(name: string) {
    this.setCookie(name, '', -1);
  }

  public setCookie(name: string, value: string, expireDays: number) {
    let d:Date = new Date();
    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
    let expires:string = `expires=${d.toUTCString()}`;
    document.cookie = `${name}=${value}; ${expires}`;
  }

  public generateRandomString() {
    let possible = "qwertyuiopasdfghjklzxcvbnm1234567890";
    let size = Math.floor(Math.random() * 7) + 15;
    let result = '';

    for (let i = 0; i < size; ++i) {
      result += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return result;
  }
}
