import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {UserService} from "../../service/user.service";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {
  products: Product[];
  displayedColumns: string[] = ['icon', 'name', 'cost', 'actions'];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getProducts().pipe(
      map(data => {
        this.products = data;
      })
    ).subscribe();
  }

  removeProduct(id:number) {
    this.userService.revokeProduct(id).subscribe(
      data => {
        location.reload();
      }
    );
  }
}
