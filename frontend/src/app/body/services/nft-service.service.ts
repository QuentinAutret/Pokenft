import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Nft } from '../model/nft.model';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class NftServiceService {

  private usersUrl: string;
  private nftsUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/user';
    this.nftsUrl = 'http://localhost:8080/api/nft'
  }

  public getAllNft(): Promise<Nft[]> {
    return this.http.get<Nft[]>(this.nftsUrl + '/getAll').toPromise();
  }

  public getAllUser(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/getAll');
  }


}
