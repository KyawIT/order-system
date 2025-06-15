import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee, EmployeeDto } from '../models/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  getAllEmployees() :Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiUrl}employees/all`);
  }

  getDepIdByEmployeeId(employeeId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}employees/getDepartment/${employeeId}`);
  }

  addEmployee(employee: EmployeeDto): Observable<EmployeeDto> {
    return this.http.post<EmployeeDto>(`${this.apiUrl}employees`, employee);
  }

  updateEmployeeSalary(employeeId: number, salary: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}employees/${employeeId}`, { 
      salary: salary.toString()
     });
  }

  getEmployeeById(employeeId: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}employees/${employeeId}`);
  }
}
