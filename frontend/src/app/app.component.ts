import {Component, OnInit, signal} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
    public username = signal<string>("");
    ngOnInit(): void {
      this.username.set(localStorage.getItem('username') ?? "");
    }

    handleLogout(): void {
      localStorage.clear();
    }
}
