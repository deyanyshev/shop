import {Component, OnInit} from '@angular/core';
import {Product} from '../../assets/product';
import {ProductService} from '../../service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html'
})
export class ProductComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.findAll().subscribe((data: Product[]) => {
      this.products = data;
    });
  }
}
