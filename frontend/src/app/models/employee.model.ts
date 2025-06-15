export interface Employee {
    id: number;
    name: string;
    salary: number;
    departmentName: string;
}

export interface EmployeeDto {
    name: string;
    salary: number;
    departmentId: number;
    depManager: number;
    userName: string;
    password: string;
}
