import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role } from '../../model/role.model';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private usersUrl: string;
  public user!: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/user';
  }

  public getAccountById(id: number): Promise<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/get/' + id).toPromise();
  }

  public setAccountSession(user: any): void {
    this.user = JSON.parse(user);
    console.log(this.user);
  }

  // public getAccount(): User {
  //   return this.user;
  // }
  
}
