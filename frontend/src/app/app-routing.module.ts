import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { BodyComponent } from './body/body.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  routes: Routes = [
    { path: 'home', component: BodyComponent },
    { path: 'login', component: LoginComponent },
    { path: 'account', component: AccountComponent}
  ];

}
