import { Routes } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {SignupComponent} from './components/signup/signup.component';
import {CustomerComponent} from './components/customer/customer.component';
import { OrderComponent } from './components/order/order.component';
import { ManagerComponent } from './components/manager/manager.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { EmployeeComponent } from './components/employee/employee.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'customer/:id', component: CustomerComponent},
  {path: 'customer/:id/orders', component: OrderComponent},
  {path: 'manager/:id', component: ManagerComponent},
  {path: 'manager/:id/addemployee', component: AddEmployeeComponent},
  {path: 'employee/:id', component: EmployeeComponent}
];
