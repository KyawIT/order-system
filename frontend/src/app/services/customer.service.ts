import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  getCustomerById(customerId: number) : Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}customers/${customerId}`);
  }
}
