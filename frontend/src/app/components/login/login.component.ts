import {Component, signal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  public username = signal<string>("")
  public password = signal<string>("")

  constructor(private router: Router, private loginService: LoginService) {}

  handleLogin() {
    console.log(this.username());
    console.log(this.password());
    if (!this.username() || !this.password()){
      alert("Please enter a valid credentials");
    }


    this.loginService.login(this.username(), this.password()).subscribe({
      next: (response) => {
       
        localStorage.setItem("username", this.username());
        localStorage.setItem("password", this.password());
        localStorage.setItem("role", response.type || "");
        localStorage.setItem("id", response.id?.toString() || "0");
        console.log(response.type);
        
        if (response.type?.toLocaleLowerCase() === "depmanager") {
          this.router.navigate([`/manager/${this.username()}`]);
        } else if (response.type?.toLocaleLowerCase() === "customer") {
          this.router.navigate([`/customer/${this.username()}`]);
        } else if (response.type?.toLocaleLowerCase() === "employee") {
          this.router.navigate([`/employee/${this.username()}`]);
        } else {
          alert("Unknown user type");
          return;
        }
      },
      error: (error) => {
        console.error("Login failed", error);
        alert("Invalid credentials, please try again.");
      }
    });
  }



}
