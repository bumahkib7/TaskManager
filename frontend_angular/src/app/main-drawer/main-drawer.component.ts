import {Component, EventEmitter, Input, Output} from '@angular/core';


@Component({
	selector: 'app-main-drawer',
	templateUrl: './main-drawer.component.html',
	styleUrls: ['./main-drawer.component.scss']
})
export class MainDrawerComponent {
	@Input() closed: boolean = false;
	@Output() toggleDrawer = new EventEmitter();
	drawerOpen: boolean = true; // disable patch
	to!: string;
	Icon!: string;
	iconSize!: number;
	title!: string;

	ontoggleDrawer(): void {
		this.drawerOpen = !this.drawerOpen;
	}

}

