import { Component, OnInit } from '@angular/core';
import { Nft } from '../model/nft.model';
import { NftServiceService } from '../services/nft-service.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {
  
  /** Ensemble des NFTs du User connecté */
  tabNFT: Nft[] = [];
  
  constructor(private readonly nftService: NftServiceService) { }

  ngOnInit(): void {
    // Récupère tous les NFTs qui n'ont pas de propriété
    this.nftService.getAllNft().then(result => {
      this.tabNFT = result;
    }).catch(error => {
      console.error("error ", error);
    })
  }

}
