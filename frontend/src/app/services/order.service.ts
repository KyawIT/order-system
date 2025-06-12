import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order, OrderDto } from '../models/order.model';
import { Drink } from '../models/drink.model';
import { Dish } from '../models/dish.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  getAllOrdersByCustomer(id:string): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl + 'orders/Customer/all/' + id);
  }

  getAllDrinks(): Observable<Drink[]> {
    return this.http.get<Drink[]>(this.apiUrl + 'drinks');
  }

  getAllDish(): Observable<Dish[]> {
    return this.http.get<Dish[]>(this.apiUrl + 'dishes');
  }

  addNewOrder(order: OrderDto): Observable<Order> {
    return this.http.post<Order>(this.apiUrl + 'orders', order);
  }

  deleteOrder(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + 'orders/' + id);
  }
}
