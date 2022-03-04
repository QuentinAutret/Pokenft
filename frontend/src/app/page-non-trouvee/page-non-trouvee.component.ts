import { Component, OnInit } from '@angular/core';
import { ErrorService } from './services/error.service';

@Component({
  selector: 'app-page-non-trouvee',
  templateUrl: './page-non-trouvee.component.html',
  styleUrls: ['./page-non-trouvee.component.scss']
})
export class PageNonTrouveeComponent implements OnInit {

  constructor(
    public errorService: ErrorService
  ) { }

  ngOnInit(): void {
  }

}
