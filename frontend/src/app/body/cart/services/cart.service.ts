import { Injectable } from '@angular/core';
import { AccountService } from '../../account/services/account.service';
import { Nft } from '../../model/nft.model';
import { NftServiceService } from '../../services/nft-service.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cartUrl: string;
  private cart: Nft[] = [];

  constructor(
    private nftService: NftServiceService,
    private accountService: AccountService
  ) {
    this.cartUrl = 'http://localhost:8080/api/cart';
  }

  async addToCart(idNft: number): Promise<void> {
    if (this.cart.some(res => +res.id !== idNft) || this.cart.length === 0) {
      this.cart.push(await this.nftService.getNft(idNft));
    }
  }

  getCart(): Nft[] {
    return this.cart;
  }

}
