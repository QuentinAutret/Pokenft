import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Nft } from '../../model/nft.model';
import { User } from '../../model/user.model';
import { NftServiceService } from '../../services/nft-service.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  /** Ensemble des NFTs du User connecté */
  private tabNFT: Nft[] = [];

  /** URL du serveur pour les requêtes du User */
  private usersUrl: string;

  /** Identifiant du User */
  private id!: number;

  constructor(
    private http: HttpClient
  ) {
    this.usersUrl = 'http://localhost:8080/api/user';
  }

  /**
   * Récupère un User en fonction de son identifiant.
   * @param id Identifiant du User
   * @returns  Ine Promise du User
   */
  public getAccountById(id: number): Promise<User> {
    return this.http.get<User>(this.usersUrl + '/get?id=' + id).toPromise();
  }

  public getId(): number {
    return this.id;
  }
  
  public setId(id: number): void {
    this.id = id;
  }

  public getTabNft(): Nft[] {
    return this.tabNFT;
  }

  public setTabNft(tab: Nft[]): void {
    this.tabNFT = tab;
  }

  public removeFromTab(id: number): void {
    this.tabNFT.forEach((value,index)=>{
      if (+value.id === id) this.tabNFT.splice(index, 1);
    });
  }
}
