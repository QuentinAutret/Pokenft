import { Injectable, Input } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  /** Le message d'erreur reçu par  */
  errorMessage: any;

  constructor() { }

  public getError(): any {
    return this.errorMessage;
  }

  public setError(error: any): void {
    this.errorMessage = error;
  }

}
