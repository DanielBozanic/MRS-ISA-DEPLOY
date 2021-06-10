import { Pharmacy } from '../user-complaint/user-complaint.component';
import { Dermatologist } from './dermatologist';

export class Appointment {
  start!: Date;
  end!: Date;
  price!: Number;
  discount!: Number;
  done!: Boolean;
  pharmacy!: Pharmacy;
  dermatologist!: Dermatologist;
}
