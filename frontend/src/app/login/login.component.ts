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
  
  formGroup!: FormGroup;

  isLoggedIn!: boolean;
  errorMessage = '';
  roles: string[] = [];
  
  constructor( 
    private loginService: LoginService, 
    private tokenService: TokenService,
    private accountService: AccountService,
    private router: Router) {
  }     

  initForm(): void {
    this.formGroup = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.initForm();
  }

  onSubmit(): void {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.value).subscribe({
        next: (data) => {
          this.tokenService.saveToken(data.accessToken);
          console.log(data);
          console.log(data.username);
          this.tokenService.saveUser(data);
          this.loginService.connectez.next(true);
          this.roles = this.tokenService.getUser().roles;
          this.accountService.setAccountSession(data);
          this.router.navigate(['home']);
          },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.loginService.connectez.next(false);
        }
      });
    }
  }
  
}
  