import {Component, OnInit} from '@angular/core';
import {Product} from '../../models/product';
import {ProductService} from '../../service/product.service';
import {map} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: Product;

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    const id = parseInt(location.href.split('/').slice(-1)[0]);
    this.productService.getProduct(id).pipe(
      map(data => {
        this.product = data;
      })
    ).subscribe();
  }
}
