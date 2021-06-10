import { AbstractControl } from "@angular/forms";

export function DiscountValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const discount = control.get('discount');
    if (discount?.value < 0 || discount?.value > 100) {
        return {'discountInvalid': true}
    } else {
        return null;
    }
}