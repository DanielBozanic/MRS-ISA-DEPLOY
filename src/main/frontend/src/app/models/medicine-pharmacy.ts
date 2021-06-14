import { Pharmacy } from '../user-complaint/user-complaint.component';
import { Medicine } from './medicine';

export class MedicinePharmacy {
  amount!: Number;
  cost!: Number;
  medicine!: Medicine;
  pharmacy!: Pharmacy;
}
