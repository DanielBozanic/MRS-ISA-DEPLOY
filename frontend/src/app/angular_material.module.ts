import { NgModule } from  '@angular/core';
 
import { MatButtonModule } from  '@angular/material/button';
import { MatToolbarModule } from  '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';


@NgModule({
imports: [MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule, MatSnackBarModule, MatSelectModule, 
        MatTableModule, MatSidenavModule, MatDialogModule, MatPaginatorModule],
exports: [MatButtonModule,MatToolbarModule, MatCardModule, MatFormFieldModule, 
        MatNativeDateModule, MatDatepickerModule, MatIconModule, MatCheckboxModule, 
        MatInputModule, MatRadioModule, MatListModule, MatSnackBarModule, MatSelectModule, 
        MatTableModule, MatSidenavModule, MatDialogModule, MatPaginatorModule]
})
 
export class AngularMaterialModule { }