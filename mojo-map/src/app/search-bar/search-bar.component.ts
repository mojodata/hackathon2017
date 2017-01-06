import { Component, Input, Output, EventEmitter } from '@angular/core';
import { OnInit } from '@angular/core';
import { OnChanges, SimpleChanges } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import Account from '../dto/account.dto';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
  providers: []
})
export class SearchBarComponent implements OnInit {

	private accounts: Account[];
  private data: Observable<Account[]>;
  private anyErrors: boolean;
  private finished: boolean;

  @Input()
  account: Account;

  @Output()
  accountUpdated = new EventEmitter<Account>();

  constructor(private accountService: AccountService) { }

  ngOnInit() {
    this.data = this.getAccounts();

    let subscription = this.data.subscribe(
      accounts => this.accounts = accounts,
      error => this.anyErrors = true,
      () => this.finished = true
    );
  }

  onAccountChange(event: Event) {
    this.accountUpdated.emit(this.account);
  }

  getAccounts(): Observable<Account[]> {
    return this.accountService.getAccounts();
  }
}
