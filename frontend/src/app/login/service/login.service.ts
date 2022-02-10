import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/body/model/user.model';
import * as moment from "moment";
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  loginUrl: string;

  constructor(private http: HttpClient) {
    this.loginUrl = 'http://localhost:8080/api/auth'
  }
  
  login(username: string, password: string ) {
    return this.http.post<User>(this.loginUrl + '/signin', {username, password})
    .subscribe((res: any) => this.setSession);
  }
  
  private setSession(authResult: { expiresIn: moment.DurationInputArg1; idToken: string; }) {
    const expiresAt = moment().add(authResult.expiresIn,'second');
    
    localStorage.setItem('accessToken', authResult.idToken);
  }          
  
  logout() {
    localStorage.removeItem("accessToken");
  }
  
  public isLoggedIn() {
    return localStorage.getItem('accessToken') != null;
  }
  
  isLoggedOut() {
    return !this.isLoggedIn();
  }
  
}
