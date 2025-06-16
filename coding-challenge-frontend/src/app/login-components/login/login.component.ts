import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = "";
  password: string = "";
  constructor(private authService: AuthServiceService, private router: Router) {}
  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Login failed', err);
      }
    });
  }
  goToRegister() {
    this.router.navigate(['/register']);
  }
}
