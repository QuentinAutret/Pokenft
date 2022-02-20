import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    if (this.tokenService.getToken() != null) {
      this.isLoggedIn = true;
      this.roles = this.tokenService.getUser().roles;
    }
  }

  onSubmit(): void {
    if (this.formGroup.valid) {
      this.loginService.login(this.formGroup.value).subscribe(
        data => {
          this.tokenService.saveToken(data.accessToken);
          this.tokenService.saveUser(data);
          this.isLoggedIn = true;
          this.roles = this.tokenService.getUser().roles;
        },
        err => {
          this.errorMessage = err.error.message;
          this.isLoggedIn = false;
        }
      );
    }
  }

}
  