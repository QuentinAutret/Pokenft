import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { User } from 'src/app/body/model/user.model';
import * as moment from "moment";
import { Observable } from 'rxjs/internal/Observable';
import { BehaviorSubject, Subject } from 'rxjs';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class LoginService implements OnInit {
  
  connectez: Subject<boolean> = new Subject();
  loginUrl: string;
  result: any;
  errorMessage = '';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.loginUrl = 'http://localhost:8080/api/auth';
  }

  ngOnInit(): void {
    this.isLoggedIn();
  }
  
  /**
   * Permet la connexion d'un User à partir de son pseudo et mot de passe.
   * @param data Pseudo et mot de passe
   * @returns    Un Observable
   */
  login(data: any): Observable<any> {
    return this.http.put<User>(this.loginUrl + '/signin', data);
  }

  /**
   * Permet la création d'un compte User.
   * @param username Pseudo du User
   * @param email    Email du User
   * @param password Mot de passe du User
   * @returns Un Observable
   */
  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl + '/signup', {
      username,
      email,
      password
    });
  }        
  
  /**
   * Déconnecte le User connecté et retourne à la page d'accueil.
   */
  public logout(): void {
    this.connectez.next(false);
    sessionStorage.removeItem("accessToken");
    this.router.navigate(['home']);
  }
  
  /**
   * Mets à jour la souscription sur la connexion du User.
   */
  public isLoggedIn(): void {
    if (sessionStorage.getItem('accessToken') != null) {
      this.connectez.next(true);
    } else {
      this.connectez.next(false);      
    }
  }
  
}
