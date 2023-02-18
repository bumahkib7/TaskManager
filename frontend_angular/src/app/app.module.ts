import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { MainDrawerComponent} from './main-drawer/main-drawer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatGridListModule} from "@angular/material/grid-list";
import { ItemComponent } from './item/item.component';
import { LayoutComponent } from './layout/layout.component';
import { AppComponent } from './app/app.component';

@NgModule({
  declarations: [
    LoginComponent,
    TopBarComponent,
    MainDrawerComponent,
    ItemComponent,
    LayoutComponent,
    AppComponent,
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

  ],
  providers: [],
  bootstrap: [TopBarComponent, MainDrawerComponent, LoginComponent]
})
export class AppModule { }
