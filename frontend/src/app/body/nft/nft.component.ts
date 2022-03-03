import { Component, Input, OnInit } from '@angular/core';
import { TokenService } from 'src/app/login/service/token.service';
import { AccountService } from '../account/services/account.service';
import { CartService } from '../cart/services/cart.service';
import { Nft } from '../model/nft.model';
import { User } from '../model/user.model';
import { NftServiceService } from '../services/nft-service.service';

@Component({
  selector: 'app-nft',
  templateUrl: './nft.component.html',
  styleUrls: ['./nft.component.scss']
})
export class NftComponent implements OnInit {
  
  /** Permet l'affichage du bouton AJOUTER sur le composant */
  @Input() ajouter: boolean = false;

  /** Permet l'affichage du bouton RETIRER sur le composant */
  @Input() retirer: boolean = false;

  /** Permet l'affichage du bouton VENDRE sur le composant */
  @Input() vendre: boolean = false;

  /** Permet l'affichage du bouton ANNULER sur le composant */
  @Input() annuler: boolean = false;

  /** Récupère un NFT */    
  @Input() nft!: Nft;

  /** Les différents attributs d'un NFT */
  id: string = "";
  name: string = "";
  creator: string = "";
  filepath: string = "../assets/images/nft/";
  price: number = 0;
  forSale: boolean = true;
  owner!: User;

  constructor(private nftService: NftServiceService,
    private accountService: AccountService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.setNft()
  }

  setNft(): void {
    this.id = this.nft.id;
    this.name = this.nft.name;
    this.creator = this.nft.creator;
    this.filepath += this.nft.filepath;
    this.price = this.nft.price;
    this.forSale = this.nft.forSale
    this.owner = this.nft.owner;
  }

  /**
   * Permet l'achat d'un NFT.
   */
  buyNft(): void {
    this.nftService.buyNft(this.id, this.accountService.getId()).then(result => {
      this.owner = result.owner;
    }).catch(error => {
      console.error("error ", error);
    })
  }

  /**
   * Ajoute le NFT au panier.
   */
  addToCart(): void {
    this.cartService.addToCart(+this.id);
  }

  /**
   * Supprime le NFT au panier.
   */
  removeFromCart(): void {
    this.cartService.removeFromCart(+this.id);
  }

  /**
   * Mets en vente le NFT.
   */
  sell(): void {
    this.nftService.sellNft(+this.id, this.price).then(result => {
      this.accountService.addToTabNftOnSale(result);
      this.accountService.removeFromTab(result.id);
    }).catch(error => {
      console.error("error ", error);
    })
  }

  /**
   * Annule la mise en vente du NFT.
   */
  cancel(): void {
    this.nftService.cancel(+this.id).then(result => {
      this.accountService.addToTab(result);
      this.accountService.removeFromTabNftOnSale(result.id);
    }).catch(error => {
      console.error("error ", error);
    })
  }

}
