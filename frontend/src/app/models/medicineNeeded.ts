import { Pharmacy } from '../user-complaint/user-complaint.component';
import { Medicine } from './medicine';

export class MedicineNeeded {
  requested!: Date;
  medicine!: Medicine;
  pharmacy!: Pharmacy;
}
