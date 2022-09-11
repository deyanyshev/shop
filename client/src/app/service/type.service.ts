import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../models/product';
import { Observable } from 'rxjs';
import {Type} from "../models/type";

@Injectable()
export class TypeService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Type[]> {
    return this.http.get<Type[]>('api/products/types');
  }
}
