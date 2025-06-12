import { Component, OnInit, signal } from '@angular/core';

@Component({
  selector: 'app-manager',
  imports: [],
  templateUrl: './manager.component.html',
  styleUrl: './manager.component.css'
})
export class ManagerComponent implements OnInit{
  public username = signal<string|null>("");
  ngOnInit(): void {
    this.username.set(localStorage.getItem("username"));

    const hasReloaded = sessionStorage.getItem('hasReloaded');
    if (!hasReloaded) {
      sessionStorage.setItem('hasReloaded', 'true');
      window.location.reload();
    } else {
      sessionStorage.removeItem('hasReloaded');
    }
  }

}
