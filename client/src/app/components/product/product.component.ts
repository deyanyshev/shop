import {Component, OnInit} from '@angular/core';
import {Product} from '../../models/product';
import {ProductService} from '../../service/product.service';
import {map} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../models/user";
import {Role} from "../../models/enums/role";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: Product;
  user: User = new User();
  roles = Role;

  constructor(private userService: UserService, private productService: ProductService) {
  }

  ngOnInit() {
    const id = parseInt(location.href.split('/').slice(-1)[0]);
    this.productService.getProduct(id).pipe(
      map(data => {
        this.product = data;
      })
    ).subscribe();

    this.userService.getUser().pipe(
      map(user => {
        this.user = user;
      })
    ).subscribe();
  }

  deleteProduct() {
    this.productService.deleteProduct(this.product.id).subscribe(data => {
      location.href = '/';
    })
  }
}
