import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenService } from 'src/app/login/service/token.service';
import { AccountService } from '../../account/services/account.service';
import { Nft } from '../../model/nft.model';
import { NftServiceService } from '../../services/nft-service.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  /** Le panier */
  private cart: Nft[] = [];

  /** URL du serveur pour les requêtes des NFTs */
  private nftsUrl: string;

  constructor(
    private nftService: NftServiceService,
    private tokenService: TokenService,
    private http: HttpClient
  ) {
    this.nftsUrl = 'http://localhost:8080/api/nft'
  }

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

  /**
   * Achète les NFTs présents dans le panier.
   * @param nftsId Tableau des identifiants des NFTs présents dans le panier
   * @param userId Identifiant du User connecté
   * @returns      Une Promise
   */
  buyCart(nftsId: number[], userId: number): Promise<any> {
    const headerDict = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    }

    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(headerDict), 
    };
    // Body de la requête
    const body = { nftsId, userId };
    console.log(body);
    return this.http.post(this.nftsUrl + '/buyAll', body, requestOptions).toPromise();
  }

}
