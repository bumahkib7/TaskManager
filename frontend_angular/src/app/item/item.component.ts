import {Component, Input} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent {

  @Input() Icon: string | undefined;
  @Input() iconSize: string | undefined;
  @Input() title: string | undefined;
  @Input() to: string | undefined;
  @Input() disableTooltip = false;
  selected = false;

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((val) => {
        this.selected = this.router.url === this.to;
    });
  }

}
