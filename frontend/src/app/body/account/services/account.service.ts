import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService implements OnInit {

  private usersUrl: string;
  public user!: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/user';
  }

  ngOnInit(): void {
    // this.initUser();
  }

  // private initUser(): void {
  //   this.user.id = 0;
  //   this.user.username = '';
  //   this.user.firstName = '';
  //   this.user.lastName = '';
  //   this.user.email = '';
  //   this.user.password = '';
  // }

  public getAccountById(id: number): Promise<User[]> {
    return this.http.get<User[]>(this.usersUrl + '/get/' + id).toPromise();
  }

  public setAccountSession(user: any): void {
    this.user = JSON.stringify(user);
  }

  public getAccount(): any {
    if (this.user) {
      return JSON.parse(this.user);
    }
    return {};
  }
  
}
