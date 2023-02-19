import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { MainDrawerComponent} from './components/main-drawer/main-drawer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatGridListModule} from "@angular/material/grid-list";
import { ItemComponent } from './components/item/item.component';
import { LayoutComponent } from './components/layout/layout.component';
import { AppComponent } from './components/app/app.component';
import { InitialPageComponent } from './components/initial-page/initial-page.component';
import { CreateTaskComponent } from './components/create-task/create-task.component';
import {ReactiveFormsModule} from "@angular/forms";
import { TaskListComponentComponent } from './components/task-list-component/task-list-component.component';

@NgModule({
  declarations: [
    LoginComponent,
    TopBarComponent,
    MainDrawerComponent,
    ItemComponent,
    LayoutComponent,
    AppComponent,
    InitialPageComponent,
    CreateTaskComponent,
    TaskListComponentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatTooltipModule,
    MatGridListModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [TopBarComponent, MainDrawerComponent, LoginComponent]
})
export class AppModule { }
