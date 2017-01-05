import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { Http, Response } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import Account from '../dto/account.dto';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent implements OnInit, OnChanges {

	private accounts: Account[];
  private data: Observable<Account[]>;
  private anyErrors: boolean;
  private finished: boolean;

  @Input()
  account: Account;

  constructor(private http: Http) { }

  ngOnInit() {
    this.data = this.getAccounts();

    let subscription = this.data.subscribe(
      accounts => this.accounts = accounts,
      error => this.anyErrors = true,
      () => this.finished = true
    );
  }

  ngOnChanges(changes: SimpleChanges) {
    console.debug("Account Changed.");
  }

  getAccounts(): Observable<Account[]> {
    return this.http.get("/api/accounts")
//    return this.http.get("http://localhost:3000/accounts")
      .map(this.extractAccounts)
      .catch(this.handleAccountsError);
  }

  private extractAccounts(res: Response) {
    let body = res.json();

    return body || {};
  }

  private handleAccountsError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} = ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
