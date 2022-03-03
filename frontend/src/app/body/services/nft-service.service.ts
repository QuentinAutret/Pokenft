import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from 'src/app/login/service/token.service';
import { Nft } from '../model/nft.model';

@Injectable({
  providedIn: 'root'
})
export class NftServiceService {

  /** URL du serveur pour les requêtes des NFTs */
  private nftsUrl: string;

  constructor(
    private http: HttpClient,
    private tokenService: TokenService
  ) {
    this.nftsUrl = 'http://localhost:8080/api/nft'
  }

  /**
   * Récupère tous les NFTs qui sont à vendre (non pas de propriétaire).
   * @returns Une Promise de tableau de NFT
   */
  public getAllNft(): Promise<Nft[]> {
    return this.http.get<Nft[]>(this.nftsUrl + '/getAllOnSale').toPromise();
  }

  /**
   * Achète un NFT à partir de son identifiant et l'identifiant du User connecté.
   * @param id     Identifiant du NFT
   * @param userId Identifiant du User connecté
   * @returns      Une Promise
   */
  public buyNft(id: string, userId: number): Promise<any> {
    const headerDict = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.tokenService.getToken()}`
    }

    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(headerDict), 
    };
    const body = {id, userId};
    return this.http.post(this.nftsUrl + '/buy', body, requestOptions).toPromise();
  }

  /**
   * Récupère tous les NFTs du User connecté à partir de son identifiant.
   * @param id Identifiant du User connecté
   * @returns  Une Promise de tableau de NFT
   */
  public getAllOfUser(id: number): Promise<Nft[]> {
    return this.http.get<Nft[]>(this.nftsUrl + '/getAllOfUser?id=' + id).toPromise();
  }

  /**
   * Récupère un NFT à partir de son identifiant.
   * @param id Identifiant du NFT
   * @returns  Une Promise du NFT
   */
  public getNft(id: number): Promise<Nft> {
    return this.http.get<Nft>(this.nftsUrl + '/get?id=' + id).toPromise();
  }

}
