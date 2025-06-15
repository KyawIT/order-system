import { Component, OnInit, signal } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order.model';
import { Employee } from '../../models/employee.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee',
  imports: [],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit{
  public username = signal<string|null>("");
  public orders = signal<Order[]>([]);
  public employee = signal<Employee | null>(null);
  constructor(private orderService: OrderService, private employeeService: EmployeeService) {
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

    this.orderService.getOrderByEmployeeId(Number(localStorage.getItem("id"))).subscribe({
      next: (data) => {
        this.orders.set(data);
        console.log('Orders fetched:', this.orders());
      },
      error: (error) => {
        console.error('Error fetching orders:', error);
      }
    });

    this.employeeService.getEmployeeById(Number(localStorage.getItem("id"))).subscribe({
      next: (data) => {
        this.employee.set(data);
        console.log('Employee fetched:', this.employee());
      },
      error: (error) => {
        console.error('Error fetching employee:', error);
      }
    });
  }

}
