import { FrieOrder } from "./FrieOrder";

export class FrieOrderServiceAdapter implements FrieOrder{
    
    private adaptee: FrieOrder
    
    constructor(adaptee: FrieOrder) {
        this.adaptee = adaptee
    }

    orderFries(fries: number): void {
        this.adaptee.orderFries(fries)
    }
    
}