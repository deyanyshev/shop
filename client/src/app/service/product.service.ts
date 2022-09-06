import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../models/product';
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Product[]> {
    return this.http.get<Product[]>('api/products');
  }

  public save(product: Product) {
    return this.http.post('api/add-product', product);
  }
}
