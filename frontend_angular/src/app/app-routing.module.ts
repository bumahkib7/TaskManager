import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InitialPageComponent} from "./components/initial-page/initial-page.component";
import {CreateTaskComponent} from "./components/create-task/create-task.component";


let routes: Routes;
routes = [
  {path: '', redirectTo: '/initial-page', pathMatch: 'full'},
  {path: 'initial-page', component: InitialPageComponent},
  {path: 'create-task', component: CreateTaskComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
