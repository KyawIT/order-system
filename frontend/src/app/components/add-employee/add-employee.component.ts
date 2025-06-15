import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Employee, EmployeeDto } from '../../models/employee.model';
import { EmployeeService } from '../../services/employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.css'
})
export class AddEmployeeComponent implements OnInit {
  public name = signal<string>('');
  public username = signal<string>('');
  public password = signal<string>('')
  public departmentId = signal<number>(0);
  public salary = signal<number>(0);

  constructor(private employeeService: EmployeeService, private router: Router, private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.departmentId.set(Number(localStorage.getItem("id")));
    console.log(`Department ID: ${this.departmentId()}`);
    
  }

  addEmployee(): void {
    const employee: EmployeeDto = {
      name: this.name(),
      userName: this.username(),
      password: this.password(),
      departmentId: this.departmentId(),
      salary: this.salary(),
      depManager: this.departmentId()
    };

    console.log(employee);
    

    this.employeeService.addEmployee(employee).subscribe({
      next: (response) => {
        console.log('Employee added successfully:', response);
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      error: (error) => {
        console.error(error.message);
        alert('Username or Password already exists');
      }
    });
  }

}
