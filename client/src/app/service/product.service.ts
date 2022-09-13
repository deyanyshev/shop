import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Product } from '../models/product';
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public findAll(name: string, type: string, country: string): Observable<Product[]> {
    const params = new HttpParams().append('name', name)
      .append('type', type).append('country', country);
    return this.http.get<Product[]>(`api/products/get-all`, {
      params
    });
  }

  public getProduct(id: number) {
    return this.http.get<Product>(`api/products/product/${id}`);
  }

  public save(product: Product) {
    return this.http.post('api/products/add', product);
  }

  public deleteProduct(id: number) {
    return this.http.post('api/products/delete', id);
  }
}
