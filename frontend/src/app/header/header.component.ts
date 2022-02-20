import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn!: boolean;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    this.loginService.connectez.subscribe((data) => 
      this.isLoggedIn = data
    );
    this.loginService.isLoggedIn();
  }

  logout(): void {
    this.loginService.logout();
  }

}
