import { Pharmacy } from '../user-complaint/user-complaint.component';
import { Dermatologist } from './dermatologist';
import { Medicine } from './medicine';
import { Pharmacist } from './pharmacist';

export class Employment {
  start!: Number;
  end!: Number;
  pharmacist!: Pharmacist;
  dermatologist!: Dermatologist;
  pharmacy!: Pharmacy;
}
