import { AbstractControl } from "@angular/forms";

export function MedicinePointsValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const requiredNumberOfPoints = control.get('points');
    if (requiredNumberOfPoints?.value < 0) {
        return {'pointsInvalid': true}
    } else {
        return null;
    }
}