import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
	selector: 'app-top-bar',
	templateUrl: './top-bar.component.html',
	styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit {


	constructor(private router: Router) {

	}

	ngOnInit(): void {
	}

	@Input() drawerOpen: boolean = false;
	@Input() drawerClosed: boolean = true;


	toggleDrawer() {
		this.drawerOpen = !this.drawerOpen;
	}


	goHome() {
		// this.router.navigate(['/home']);
		this.router.navigate(['/']).then(r => console.log(r));
	}

	newTask() {
		this.router.navigate(['/new-task']).then(r => console.log(r));
	}
}
