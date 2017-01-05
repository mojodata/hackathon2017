import { Component, ViewContainerRef } from '@angular/core';
// import './rxjs-operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
	private viewContainerRef: ViewContainerRef;

	public constructor(viewContainerRef: ViewContainerRef) {
		this.viewContainerRef = viewContainerRef;
	}

  title = 'Mojo UI!';
}
