import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../body/account/services/account.service';
import { LoginService } from './service/login.service';
import { TokenService } from './service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  /** Le formulaire de connexion */
  formGroup!: FormGroup;

  /** Permet de savoir si le User est connecté */
  isLoggedIn!: boolean;

  /** Message d'erreur lors de la connexion */
  errorMessage = '';

  /** Tableau des rôles dont disposent le User connecté */
  roles: string[] = [];
  
  constructor( 
    private loginService: LoginService, 
    private tokenService: TokenService,
    private accountService: AccountService,
    private router: Router
  ) {}     

  /**
   * Initialisation du formulaire de connexion.
   */
  initForm(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.initForm();
  }

  /**
   * Gère la connexion d'un User. Enregistre ses informations (son token, ses rôles et informations de compte).
   * Redirige sur la page d'accueil.
   */
  onSubmit(): void {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.value).subscribe({
        next: (data) => {
          this.tokenService.saveToken(data.accessToken);
          this.tokenService.saveUser(data);
          this.loginService.connectez.next(true);
          this.roles = this.tokenService.getUser().roles;
          this.accountService.setId(data.id);
          this.router.navigate(['home']);
          },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.loginService.connectez.next(false);
          this.router.navigate(['login']);
        }
      });
    }
  }
  
}
  