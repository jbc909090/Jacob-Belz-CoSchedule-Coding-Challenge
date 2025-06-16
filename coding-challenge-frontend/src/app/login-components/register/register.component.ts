import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from '../auth-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = "";
  password: string = "";
  password2: string = "";
  constructor(private authService: AuthServiceService, private router: Router) {}
  onSubmit() {
    if (this.password == this.password2) {
      this.authService.register(this.username, this.password).subscribe({
        next: (res) => {
          this.router.navigate(['/home']);
        },
        error: (err) => {
          console.error('Login failed', err);
        }
      });
    } else {
      console.log('Password mismatched');
    }
  }
  goToLogin() {
    this.router.navigate(['/']);
  }
}
