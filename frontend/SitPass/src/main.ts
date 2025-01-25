// main.ts ili app.component.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';

// Dodajemo `.dark` klasu na <html> element
document.documentElement.classList.add('dark');

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
