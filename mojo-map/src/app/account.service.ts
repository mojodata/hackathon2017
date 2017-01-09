import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import Account from './dto/account.dto';

@Injectable()
export class AccountService {

  constructor(private http: Http) { }

  public getAccounts(): Observable<Account[]> {
    return this.http.get("http://position-dashboard-service.mybluemix.net/api/accounts")
      .map(this.extractAccounts)
      .catch(this.handleError);
  }

  public getAccountDetails(account: Account): Observable<any> {
    return this.http.get(`http://position-dashboard-service.mybluemix.net/api/accounts/${account.accountNumber}/holdings`)
      .map(this.extractHoldings)
      .catch(this.handleError);
  }

  public getHoldings(account: Account, country: string): Observable<any> {
    return this.http.get(`http://position-dashboard-service.mybluemix.net/api/accounts/${account.accountNumber}/${country}/holdings`)
      .map(this.extractHoldings)
      .catch(this.handleError);
  }

  private extractAccounts(res: Response) {
    let body = res.json();
    return body || {};
  }

  private extractHoldings(res: Response) {
    let body = res.json();
    if (body && body.holdings) {
      let holdings: any[] = body.holdings;
      let count = 0;
      holdings.forEach(holding => {
        count++;
        if (count % 5 == 0) {
          holding.newsFeed = 'Y';
        } else {
          holding.newsFeed = 'N';
        }
      });

    }
    return body || {};
  }

  private handleError(error: Response | any) {
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
