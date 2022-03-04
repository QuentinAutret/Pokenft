import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../body/account/services/account.service';
import { TokenService } from '../login/service/token.service';
import { ErrorService } from '../page-non-trouvee/services/error.service';
import { SignupService } from './services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  /** Le formulaire d'inscription */
  formGroup!: FormGroup;

  /** Message d'erreur de la requête */
  errorMessage: any;
  
  constructor(
    private errorService: ErrorService,
    private router: Router,
    private signupService: SignupService
  ) { }
  
  /**
   * Initialisation du formulaire d'inscription.
   */
  initForm(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', Validators.required),
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }
  
  ngOnInit(): void {
    this.initForm();
  }

  /**
   * Gère la création d'un User. Enregistre ses informations (pseudo, prénom, nom, email, mot de passe).
   * Redirige sur la page d'accueil.
   */
  onSubmit(): void {
    if (this.formGroup.valid) {
      this.signupService.signup(this.formGroup.value).subscribe({
        next: (data) => {
          console.log("ça marche !");
          console.log(data);
          this.router.navigate(['login']);
          },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.errorService.setError(err.error.message);
          this.router.navigate(['**']);
        }
      });
    }
  }

}
