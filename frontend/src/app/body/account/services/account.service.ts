import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService implements OnInit {

  private usersUrl: string;
  private id!: number;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api/user';
  }

  ngOnInit(): void {
  }

  public getAccountById(id: number): Promise<User> {
    return this.http.get<User>(this.usersUrl + '/get/' + id).toPromise();
  }

  public getId(): number {
    return this.id;
  }
  
  public setId(id: number): void {
    this.id = id;
  }
}
