import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoginService } from '../login/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService: LoginService) {}

  get isLoggedIn(): boolean {
    console.log(this.loginService.isLogIn);
    return this.loginService.isLogIn;
  }

  ngOnInit(): void {
  }

  logout(): void {
    this.loginService.logout();
  }

}
