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
  }

  
}
