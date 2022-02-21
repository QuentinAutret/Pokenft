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
  
  public logout(): void {
    this.connectez.next(false);
    sessionStorage.removeItem("accessToken");
    this.router.navigate(['home']);
  }
  
  public isLoggedIn(): void {
    if (sessionStorage.getItem('accessToken') != null) {
      this.connectez.next(true);
    } else {
      this.connectez.next(false);      
    }
  }
  
}
