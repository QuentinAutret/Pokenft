import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../account/services/account.service';
import { Nft } from '../model/nft.model';
import { User } from '../model/user.model';
import { NftServiceService } from '../services/nft-service.service';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  /** Le panier de l'utilisateur connecté */
  cart: Nft[] = [];

  /** Utilisateur connecté */
  public user!: User;

  constructor(
    private router: Router,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();
  }

  /**
   * Vide le panier et redirige sur la page principal.
   */
  cancelCart(): void {
    this.cart = [];
    this.router.navigate(['home']);
  }

}
