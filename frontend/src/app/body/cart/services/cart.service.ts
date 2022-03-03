import { Injectable } from '@angular/core';
import { AccountService } from '../../account/services/account.service';
import { Nft } from '../../model/nft.model';
import { NftServiceService } from '../../services/nft-service.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  /** Le panier */
  private cart: Nft[] = [];

  constructor(
    private nftService: NftServiceService
  ) {}

  /**
   * Ajoute au panier le NFT ayant pour identifiant celui donné en paramètre.
   * @param idNft Identifiant du NFT à ajouter
   */
  async addToCart(idNft: number): Promise<void> {
    // Vérifie si le NFT n'est pas déjà présent dans le panier
    if (this.cart.some(res => +res.id !== idNft) || this.cart.length === 0) {
      this.cart.push(await this.nftService.getNft(idNft));
    }
  }

  getCart(): Nft[] {
    return this.cart;
  }

  /**
   * Supprime du panier le NFT ayant le même identifiant que celui en paramètre.
   * @param idNft Identifiant du NFT à retirer
   */
  removeFromCart(idNft: number): void {    
    // Parcours le panier pour supprimer le NFT ayant le même id que le paramètre
    this.cart.forEach((value,index)=>{
      if (+value.id === idNft) this.cart.splice(index, 1);
    });
  }

}
