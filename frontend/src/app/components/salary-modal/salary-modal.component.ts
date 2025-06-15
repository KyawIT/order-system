import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-salary-modal',
  imports: [FormsModule],
  templateUrl: './salary-modal.component.html',
  styleUrl: './salary-modal.component.css'
})
export class SalaryModalComponent {
  public salary = signal<number>(0);
  @Input() employee: Employee| null = null;
  constructor(private employeeService: EmployeeService) { }
  isModalOpen: boolean = false;
  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  updateSalary() {
    if (this.salary() < 0) {
      alert("Salary cannot be negative.");
      return;
    }
    if (this.employee) {
      this.employeeService.updateEmployeeSalary(this.employee.id, this.salary()).subscribe({
        next: () => {
          alert("Salary updated successfully.");
          window.location.reload();
        },
        error: (error) => {
          console.error('Error updating salary:', error);
          alert("Failed to update salary. Please try again.");
        }
      });
    }
  }
}
