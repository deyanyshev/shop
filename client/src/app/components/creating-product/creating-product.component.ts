import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";

@Component({
  selector: 'app-creating-product',
  templateUrl: './creating-product.component.html',
  styleUrls: ['./creating-product.component.css']
})
export class CreatingProductComponent implements OnInit {
  newProduct: Product;

  constructor() {
    this.newProduct = new Product();
  }

  ngOnInit(): void {
  }

}
