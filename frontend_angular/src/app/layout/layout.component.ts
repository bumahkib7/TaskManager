
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';


class stringify {
  constructor(select: (arg0: (state: { layout: { drawerOpen: boolean } }) => boolean) => Observable<boolean>) {
    this.select = select;
  }
    select: (arg0: (state: { layout: { drawerOpen: boolean; }; }) => boolean) => Observable<boolean>;

}

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {
  drawerOpen$: Observable<boolean>;
  private selectDrawerOpen  : (state: { layout: { drawerOpen: boolean } }) => boolean = state => state.layout.drawerOpen;
  isHandset$: Observable<boolean> = this.store.select(this.selectDrawerOpen);

  constructor(
    private router: Router,
    private store: Store<{ layout: { drawerOpen: boolean } }>
  ) {
    this.drawerOpen$ = this.store.select(this.selectDrawerOpen);
  }

  goHome() {
    this.router.navigate(['/']).then(r => console.log(r));
  }

  newTask() {
    this.router.navigate(['/new']).then(r => console.log(r));

  }

  toggleDrawer(): void {
    this.store.dispatch({ type: 'TOGGLE_DRAWER' });
  }
}
