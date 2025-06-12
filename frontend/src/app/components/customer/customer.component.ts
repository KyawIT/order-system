import {Component, inject, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, RouterModule} from '@angular/router';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order.model';

// @ts-ignore
@Component({
  selector: 'app-customer',
  imports: [CommonModule, RouterModule],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent implements OnInit {

  private route = inject(ActivatedRoute);
  public username = signal<string|null>("");
  public orders = signal<Order[]>([]);

  constructor(private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.username.set(localStorage.getItem("username"));

    const hasReloaded = sessionStorage.getItem('hasReloaded');
    if (!hasReloaded) {
      sessionStorage.setItem('hasReloaded', 'true');
      window.location.reload();
    } else {
      sessionStorage.removeItem('hasReloaded');
    }

    this.orderService.getAllOrdersByCustomer(localStorage.getItem("id")!).subscribe({
      next: (data) => {
        this.orders.set(data);
      },
      error: (error) => {
        console.error('Error fetching orders:', error);
      }
    });
  }

  handleDeleteOrder(orderId: number): void {
    this.orderService.deleteOrder(orderId).subscribe({
      next: () => {
        this.orders.set(this.orders().filter(order => order.id !== orderId));
      },
      error: (error) => {
        console.error('Error deleting order:', error);
      }
    });
  }


}
