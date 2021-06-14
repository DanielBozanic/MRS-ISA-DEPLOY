import { AbstractControl } from "@angular/forms";

export function PointsValidator(control: AbstractControl): {[key: string]: boolean} | null {
    const points = control.get('points');
    if (points?.value < 0) {
        return {'pointsInvalid': true}
    } else {
        return null;
    }
}