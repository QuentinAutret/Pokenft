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

  /** Tableau des identifiants des NFTs du panier */
  nftsId: number[] = [];

  /** Utilisateur connecté */
  public user!: User;

  constructor(
    private router: Router,
    private cartService: CartService,
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
    // Récupère les informations du User à partir de son identifiant
    this.accountService.getAccountById(this.accountService.getId()).then(result => {
      this.user = result;
    }).catch(error => {
      console.error("error ", error);
    })
    this.cart = this.cartService.getCart();
  }

  /**
   * Vide le panier et redirige sur la page principal.
   */
  cancelCart(): void {
    this.cart = [];
    this.router.navigate(['home']);
  }

  /**
   * Achète le contenu du panier.
   */
  buyCart(): void {
    this.cart.forEach((value, index) =>
      this.nftsId.push(+value.id)
    );
    console.log(this.nftsId);
    console.log("this.user.id : " + this.user.id);
    this.cartService.buyCart(this.nftsId, this.user.id).then(result => {
      this.router.navigate(['account']);
    }).catch(error => {
      console.error("error ", error);
    })
  }

}
