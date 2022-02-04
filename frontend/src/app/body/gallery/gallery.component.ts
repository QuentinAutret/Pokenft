import { Component, OnInit } from '@angular/core';
import { Nft } from '../model/nft.model';
import { NftComponent } from '../nft/nft.component';
import { NftServiceService } from '../services/nft-service.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {
  tabNFT: Nft[] = [];
  constructor(private readonly nftService: NftServiceService) { }

  ngOnInit(): void {
    this.nftService.getAllNft().then(result => {
      console.info("result ", result)
      this.tabNFT = result
    }).catch(error => {
      console.error("error ",error)
    })
  }

}