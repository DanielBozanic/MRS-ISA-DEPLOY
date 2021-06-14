import { AbstractControl } from "@angular/forms";

export function QuantityValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const quantity = control.get('quantity');
    if (quantity?.value < 1) {
        return {'quantityInvalid': true}
    } else {
        return null;
    }
}