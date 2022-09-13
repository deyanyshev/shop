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
import {ProductService} from "../../service/product.service";
import {ApiService} from "../../service/api.service";

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

  constructor(private productService:ProductService, private typeService: TypeService,
              private countryService: CountryService, private apiService: ApiService,
              private fileService: FileService) {
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

  addProduct(imgInput: any) {
    this.newProduct.type = this.type;
    this.newProduct.country = this.country;
    this.newProduct.img = "";

    const file: File = imgInput.files[0];
    const filename = this.apiService.generateRandomString() + '.' + file.name.split('.').slice(-1)[0];

    this.fileService.uploadFile(file, filename).
    subscribe(data => {
        this.newProduct.img = 'static/img/' + filename;
        this.productService.save(this.newProduct).subscribe(
          data => {
            alert("Продукт добавлен успешно");
            location.reload();
          }
        );
      }
    );


  }

  sendImg(imgInput: any) {
    const file: File = imgInput.files[0];
    this.fileService.uploadFile(file, this.apiService.generateRandomString() + '.' + file.name.split('.').slice(-1)[0]).subscribe();
  }
}
