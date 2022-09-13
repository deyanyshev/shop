import {Component, OnInit} from '@angular/core';
import {Product} from '../../models/product';
import {ProductService} from '../../service/product.service';
import {map} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../models/user";
import {Role} from "../../models/enums/role";
import {UserService} from "../../service/user.service";
import {ToastService} from "../../service/toast.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  product: Product;
  user: User = new User();
  roles = Role;

  constructor(private userService: UserService, private productService: ProductService,
              public toastService: ToastService) {
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

  addProductToBasket() {
    if (this.user.role == null) location.href = '/auth';
    this.productService.addProductToBasket(this.product.id, this.user).pipe(
      map(data => {
        if (data.status == "ok") {
          this.toastService.show('Продукт добавлен в корзину', { classname: 'bg-success text-light', delay: 5000 })
        } else {
          this.toastService.show(data.status, { classname: 'bg-danger text-light', delay: 5000 })
        }
      })
    ).subscribe()
  }
}
