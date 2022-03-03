import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  /** URL du serveur pour les requêtes du User */
  private usersUrl: string;

  /** Identifiant du User */
  private id!: number;

  constructor(private http: HttpClient) {
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
}
