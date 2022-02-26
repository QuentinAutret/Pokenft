import { AfterViewInit, Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  public user!: User;

  constructor(private accountService: AccountService) { }
  
  ngOnInit(): void {
    this.user.id = this.accountService.getAccount().id;
    this.user.username = this.accountService.getAccount().username;
    this.user.email = this.accountService.getAccount().email;
    this.user.roles = this.accountService.getAccount().roles;
    console.log(this.user.id);
    console.log(this.user.username);
    console.log(this.user.email);
    console.log(this.user.roles);
  }

  
}
