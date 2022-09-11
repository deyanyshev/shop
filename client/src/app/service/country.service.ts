import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Country} from "../models/country";

@Injectable()
export class CountryService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Country[]> {
    return this.http.get<Country[]>('api/products/countries');
  }
}
