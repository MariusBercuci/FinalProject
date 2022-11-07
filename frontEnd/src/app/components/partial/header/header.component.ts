import { UserService } from './../../../service/user.service';
import { User } from './../../../shared/models/User';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  cartQuantity=0;

  constructor(cartService:CartService) {
    cartService.getCartObservable().subscribe((newCart) => {
      this.cartQuantity = newCart.totalCount;
   })

   UserService.userObservable.subscribe((newUser) => {
     this.user = newUser;
   })

  }

  ngOnInit(): void {
  }

  }
