import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/body/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/user';
  }

  public getAllNft(): Promise<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/getAll').toPromise();
  }

}
