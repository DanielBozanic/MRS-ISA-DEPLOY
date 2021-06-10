import { AbstractControl } from "@angular/forms";

export function PriceValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const price = control.get('price');
    if (price?.value < 0) {
        return {'priceInvalid': true}
    } else {
        return null;
    }
}