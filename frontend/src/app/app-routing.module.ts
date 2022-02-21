import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './body/account/account.component';
import { BodyComponent } from './body/body.component';
import { LoginComponent } from './login/login.component';
import { PageNonTrouveeComponent } from './page-non-trouvee/page-non-trouvee.component';

const routes: Routes = [
  { path: 'home', component: BodyComponent },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: '', component: BodyComponent },
  { path: '**', component: PageNonTrouveeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
