import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Nft } from '../model/nft.model';
import { User } from '../model/user.model';
import { NftServiceService } from '../services/nft-service.service';
import { AccountService } from './services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  /** Ensemble des NFTs du User connecté */
  tabNFT: Nft[] = [];

  /** User connecté */
  public user!: User;

  constructor(private accountService: AccountService,
    private nftService: NftServiceService) { }

  ngOnInit(): void {
    // Récupère les informations du User à partir de son identifiant
    this.accountService.getAccountById(this.accountService.getId()).then(result => {
      this.user = result;
    }).catch(error => {
      console.error("error ", error);
    })
    // Récupère tous les NFTs que l'User connecté possède
    this.nftService.getAllOfUser(this.accountService.getId()).then(result => {
      this.tabNFT = result;
    }).catch(error => {
      console.error("error ", error);
    })
    this.accountService.setTabNft(this.tabNFT);
  }

}
