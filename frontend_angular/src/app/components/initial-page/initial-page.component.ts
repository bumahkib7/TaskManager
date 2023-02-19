import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
	selector: 'app-initial-page',
	templateUrl: './initial-page.component.html',
	styleUrls: ['./initial-page.component.scss']
})
export class InitialPageComponent implements OnInit{
  tasks: any[] = []
	constructor(private router: Router) {
		this.router.navigate(['/login']).then(r => console.log(r));
	}

	ngOnInit() {

	}

	goToCreateTask() {
		this.router.navigate(['/create-task']).then(r => console.log(r));
	}

}
