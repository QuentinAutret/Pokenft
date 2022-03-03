import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  /** Permet de savoir si le User est connecté */
  isLoggedIn!: boolean;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {
    // Récupère l'information sur la connexion du User
    this.loginService.connectez.subscribe((data) => 
      this.isLoggedIn = data
    );
    // Mets à jour la souscription sur la connexion du User
    this.loginService.isLoggedIn();
  }

  /**
   * Déconnecte le User connecté.
   */
  logout(): void {
    this.loginService.logout();
  }

}
