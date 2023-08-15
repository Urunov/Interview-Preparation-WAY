import { Fries } from "../interfaces/Frie";

export class FrieOrder implements Fries{
    orderFries(fries: number): void {
        console.log(`Order burger with quantity ${fries}`);
    }
}