import { Routes } from '@angular/router';
import { LoginComponent } from './login-components/login/login.component';
import { RegisterComponent } from './login-components/register/register.component';
import { GifViewerComponent } from './gif-viewer/gif-viewer.component';
import { HistoryComponent } from './gif-viewer/history/history.component';

export const routes: Routes = [
    {path: "login", component: LoginComponent},
    {path: "register", component: RegisterComponent},
    {path: "home", component: GifViewerComponent},
    {path: "history", component: HistoryComponent}

];
