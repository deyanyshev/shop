import { Component, OnInit } from '@angular/core';
import {ApiService} from "../../service/api.service";
import {Product} from "../../models/product";
import {TypeService} from "../../service/type.service";
import {CountryService} from "../../service/country.service";
import {Country} from "../../models/country";
import {Type} from "../../models/type";
import {map} from "rxjs/operators";
import {ProductService} from "../../service/product.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  name: string = '';
  type: string = '';
  country: string = '';

  products: Product[];
  types: Type[];
  countries: Country[];

  constructor(private apiService: ApiService, private productService: ProductService,
              private typeService: TypeService, private countryService: CountryService,
              private route: ActivatedRoute) {
    this.name = '';
    this.type = '';
    this.country = '';
  }

  ngOnInit() {
    this.typeService.findAll().pipe(
      map(data => {
        this.types = data;
        this.types.push({id: -1, name: 'Все'})
      })
    ).subscribe();

    this.countryService.findAll().pipe(
      map(data => {
        this.countries = data;
        this.countries.push({id: -1, name: 'Все'})
      })
    ).subscribe();

    this.route.queryParams.subscribe(params => {
      if (params['name'] != null) this.name = params['name'];
      this.productService.findAll(this.name, this.type, this.country).pipe(
        map(data => {
          this.products = data;
        })
      ).subscribe();
    })
  }

  onChangeType(type:any) {
    if (type == "Все") {
      this.type = "";
    } else {
      this.type = type;
    }

    this.productService.findAll(this.name, this.type, this.country).pipe(
      map(data => {
        this.products = data;
      })
    ).subscribe();
  }

  onChangeCountry(country:any) {
    if (country == "Все") {
      this.country = "";
    } else {
      this.country = country;
    }

    this.productService.findAll(this.name, this.type, this.country).pipe(
      map(data => {
        this.products = data;
      })
    ).subscribe();
  }
}
