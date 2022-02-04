import { Component, Input, OnInit } from '@angular/core';
import { Nft } from '../model/nft.model';
import { User } from '../model/user.model';

@Component({
  selector: 'app-nft',
  templateUrl: './nft.component.html',
  styleUrls: ['./nft.component.scss']
})
export class NftComponent implements OnInit {
  @Input() nft!: Nft;

  id: string = "";
  name: string = "";
  creator: string = "";
  filepath: string = "";
  price: number = 0;
  forSale: boolean = true;
  owner!: User;

  constructor() { }

  ngOnInit(): void {
    this.setNft()
  }

  setNft(): void {
    console.log(this.nft);
    this.name = this.nft.name;
    this.creator = this.nft.creator;
    this.filepath = this.nft.filepath;
    this.price = this.nft.price;
    this.forSale = this.nft.forSale
    this.owner = this.nft.owner;
  }

}
