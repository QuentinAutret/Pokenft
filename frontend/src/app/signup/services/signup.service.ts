import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/app/body/model/user.model';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  /** URL du serveur pour les requêtes de création de compte */
  private signupUrl: string;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.signupUrl = 'http://localhost:8080/api/auth';
  }

  signup(data: any): Observable<any> {
    return this.http.put<User>(this.signupUrl + '/signup', data);
  }
}
