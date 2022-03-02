import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from 'src/app/login/service/token.service';
import { Nft } from '../model/nft.model';

@Injectable({
  providedIn: 'root'
})
export class NftServiceService {

  private nftsUrl: string;

  constructor(private http: HttpClient,
    private tokenService: TokenService) {
    this.nftsUrl = 'http://localhost:8080/api/nft'
  }

  public getAllNft(): Promise<Nft[]> {
    return this.http.get<Nft[]>(this.nftsUrl + '/getAllOnSale').toPromise();
  }

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

  public getAllOfUser(id: number): Promise<Nft[]> {
    return this.http.get<Nft[]>(this.nftsUrl + '/getAllOfUser?id=' + id).toPromise();
  }

  public getNft(id: number): Promise<Nft> {
    return this.http.get<Nft>(this.nftsUrl + '/get?id=' + id).toPromise();
  }

}
