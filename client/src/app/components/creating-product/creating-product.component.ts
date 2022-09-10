import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {Country} from "../../models/country";
import {Type} from "../../models/type";

@Component({
  selector: 'app-creating-product',
  templateUrl: './creating-product.component.html',
  styleUrls: ['./creating-product.component.css']
})
export class CreatingProductComponent implements OnInit {
  newProduct: Product;
  types: Type[];
  countries: Country[];

  constructor() {
    this.newProduct = new Product();
  }

  ngOnInit() {

  }

}
