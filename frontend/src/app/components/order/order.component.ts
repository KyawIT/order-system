import { Component, OnInit, signal } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { Drink } from '../../models/drink.model';
import { Dish } from '../../models/dish.model';
import { OrderDto } from '../../models/order.model';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-order',
  imports: [RouterModule],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent implements OnInit {
  constructor(private orderService: OrderService, private router:Router, private route:ActivatedRoute) { }
  public drinks = signal<Drink[]>([]);
  public dishes = signal<Dish[]>([]);

  public addedDrinks = signal<Drink[]>([]);
  public addedDishes = signal<Dish[]>([]);

  public totalPrice = signal<number>(0);
  public ktmScore = signal<number>(0);
  
  ngOnInit(): void {
    this.orderService.getAllDrinks().subscribe({
      next: (data) => {
        this.drinks.set(data);
      },
      error: (error) => {
        console.error('Error fetching drinks:', error);
      }
    });

    this.orderService.getAllDish().subscribe({
      next: (data) => {
        this.dishes.set(data);
      },
      error: (error) => {
        console.error('Error fetching dishes:', error);
      }
    });
  }

  handleDishOrder(dish:Dish): void {
    this.totalPrice.update(price => Math.round((price + dish.price) * 100) / 100);
    this.ktmScore.update(price => Math.round(price + dish.price));
    this.addedDishes.update(dishes => [...dishes, dish]);
  }

  handleDrinkOrder(drink:Drink): void {
  this.totalPrice.update(price => Math.round((price + drink.price) * 100) / 100);
  this.ktmScore.update(price => Math.round(price + drink.price));
    this.addedDrinks.update(drinks => [...drinks, drink]);
  }

  handleOrder(): void {
    const orderDto : OrderDto = {
      price: this.totalPrice(),
      ktmScore: this.ktmScore(),
      dishIds: this.addedDishes().map(dish => dish.id),
      drinkIds: this.addedDrinks().map(drink => drink.id),
      customerId: localStorage.getItem("id")!,
      date: new Date().toISOString()
    };

    if (orderDto.dishIds.length === 0 && orderDto.drinkIds.length === 0) {
      alert('Please add at least one dish or drink to your order.');
      return;
    }

    this.orderService.addNewOrder(orderDto).subscribe({
      next: (response) => {
        console.log('Order placed successfully:', response);
        // Reset the order state
        this.addedDrinks.set([]);
        this.addedDishes.set([]);
        this.totalPrice.set(0);
        this.ktmScore.set(0);
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      error: (error) => {
        console.error('Error placing order:', error);
      }
    });
    
  }

}
