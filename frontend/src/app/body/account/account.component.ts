import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  public user!: User;
  id!: number;

  constructor(private accountService: AccountService) { }
  
  ngOnInit(): void {
    console.log(this.accountService.getId());
    this.accountService.getAccountById(this.accountService.getId()).then(result => {
      console.log("Result : " + result);
      this.user = result;
    }).catch(error => {
      console.error("error ", error);
    })
  }

}
