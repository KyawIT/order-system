import { Component, OnInit, signal, ViewChild } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeeService } from '../../services/employee.service';
import { RouterModule } from '@angular/router';
import { SalaryModalComponent } from "../salary-modal/salary-modal.component";

@Component({
  selector: 'app-manager',
  imports: [RouterModule, SalaryModalComponent],
  templateUrl: './manager.component.html',
  styleUrl: './manager.component.css'
})
export class ManagerComponent implements OnInit{
  selectedEmployee = signal<Employee | null>(null);
  @ViewChild(SalaryModalComponent)
  salaryModal!: SalaryModalComponent;


  public username = signal<string|null>("");
  public employees = signal<Employee[]>([]);
  public managerDepId = signal<number|null>(null);
  public employeeByDepartment = signal<Employee[]>([]);

  constructor(private employeeService : EmployeeService) {

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

    // Get logged in manger's department ID
    this.employeeService.getDepIdByEmployeeId(Number(localStorage.getItem("id"))).subscribe({
      next: (data) => {
        this.managerDepId.set(data);
        console.log(`managerDepId ID: ${this.managerDepId()}`);
        
      },
      error: (error) => {
        console.error('Error fetching department ID:', error);
      }
    });


    // Get all employees and filter by Manager department ID
    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.employees.set(data);
        console.log('Employees fetched:', this.employees());
        data.forEach(emp => {
          this.employeeService.getDepIdByEmployeeId(emp.id).subscribe({
            next: (depId) => {
              if (depId === this.managerDepId()) {
                this.employeeByDepartment.update(current => [...current, emp]);
              }
            },
            error: (error) => {
              console.error(`Error fetching department ID for employee ${emp.id}:`, error);
            }
          });
        });
        
      },
      error: (error) => {
      }
    });
  }

  openSalaryModal(emp: Employee) {
    this.selectedEmployee.set(emp);
    this.salaryModal.openModal();
  }
}
