import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  form: FormGroup;
  
  constructor(private fb:FormBuilder, 
    private loginService: LoginService, 
    private router: Router) {
      
      this.form = this.fb.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      });
    }
    
    ngOnInit(): void {
    }
    
    login() {
      const val = this.form.value;
      console.log(val.username + " " + val.password)
      if (val.username && val.password) {
        this.loginService.login(val.username, val.password);
        console.log("User is logged in");
        this.router.navigateByUrl('/');
      }
    
    }     
}
    