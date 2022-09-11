import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {Country} from "../../models/country";
import {Type} from "../../models/type";
import {TypeService} from "../../service/type.service";
import {CountryService} from "../../service/country.service";
import {map, startWith} from "rxjs/operators";
import {Observable} from "rxjs";
import {FormControl} from '@angular/forms';
import {FileService} from "../../service/file.service";
import {FileSaverService} from "ngx-filesaver";
import {ProductService} from "../../service/product.service";

class ImageSnippet {
  constructor(public src: string, public file: File) {}
}

@Component({
  selector: 'app-creating-product',
  templateUrl: './creating-product.component.html',
  styleUrls: ['./creating-product.component.css']
})
export class CreatingProductComponent implements OnInit {
  newProduct: Product;
  type: Type;
  country: Country;

  types: Type[];
  filteredTypes: Observable<Type[]>;
  typeControl = new FormControl<string|Type>('', {nonNullable: true});

  countries: Country[];
  filteredCountries: Observable<Country[]>;
  countryControl = new FormControl<string|Country>('');

  selectedFile: ImageSnippet;

  constructor(private productService:ProductService, private typeService: TypeService, private countryService: CountryService, private fileSaverService: FileSaverService) {
    this.newProduct = new Product();
    this.type = new Type();
    this.country = new Country();
  }

  ngOnInit() {
    this.typeService.findAll().pipe(
      map(data => {
        this.types = data;
        this.filteredTypes = this.typeControl.valueChanges.pipe(
          startWith(''),
          map(value => {
            return this.type_filter(value as string);
          }),
        );
      })
    ).subscribe();

    this.countryService.findAll().pipe(
      map(data => {
        this.countries = data;
        this.filteredCountries = this.countryControl.valueChanges.pipe(
          startWith(''),
          map(value => {
            return this.country_filter(value as string);
          })
        )
      })
    ).subscribe();
  }

  private type_filter(name: string): Type[] {
    const filterValue = name.toLowerCase();
    return this.types.filter(type => type.name.toLowerCase().includes(filterValue));
  }

  private country_filter(name: string): Country[] {
    const filteredValue = name.toLowerCase();
    return this.countries.filter(country => country.name.toLowerCase().includes(filteredValue));
  }

  addProduct() {
    //Сохранение изображения

    /////////////////////

    this.newProduct.type = this.type;
    this.newProduct.country = this.country;
    this.newProduct.img = "";

    this.productService.save(this.newProduct).subscribe(
      data => {
        location.href = '/';
      }
    );
  }

  processFile(imgInput: any) {
    const file: File = imgInput.files[0];
  }
}
