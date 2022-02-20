import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/body/model/user.model';
import * as moment from "moment";
import { Observable } from 'rxjs/internal/Observable';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  loginUrl: string;
  public isLogIn: boolean = false;
  result: any;
  errorMessage = '';

  constructor(private http: HttpClient) {
    this.loginUrl = 'http://localhost:8080/api/auth'
  }
  
  login(data: any): Observable<any> {
    return this.http.put<User>(this.loginUrl + '/signin', data);
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(this.loginUrl + '/signup', {
      username,
      email,
      password
    });
  }
  
  private setSession(authResult: { expiresIn: moment.DurationInputArg1; idToken: string; }) {
    const expiresAt = moment().add(authResult.expiresIn,'second');
    
    localStorage.setItem('accessToken', authResult.idToken);
  }          
  
  public logout() {
    this.isLogIn = false;
    localStorage.removeItem("accessToken");
  }
  
  public isLoggedIn() {
    return localStorage.getItem('accessToken') != null;
  }
  
  isLoggedOut() {
    return !this.isLoggedIn();
  }
  
}
