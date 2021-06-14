import { AbstractControl } from "@angular/forms";

export function RequiredNumberOfPointsValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const requiredNumberOfPoints = control.get('requiredNumberOfPoints');
    if (requiredNumberOfPoints?.value < 0) {
        return {'requiredNumberOfPointsInvalid': true}
    } else {
        return null;
    }
}