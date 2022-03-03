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
  @Input() ajouter: boolean = false;
  @Input() retirer: boolean = false;
    
  @Input() nft!: Nft;

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

  buyNft(): void {
    this.nftService.buyNft(this.id, this.accountService.getId()).then(result => {
      console.log(result);
      this.owner = result.owner;
    }).catch(error => {
      console.error("error ", error);
    })
  }

  addToCart(): void {
    this.cartService.addToCart(+this.id);
  }

  removeFromCart(): void {
    this.cartService.removeFromCart(+this.id);
  }

}
