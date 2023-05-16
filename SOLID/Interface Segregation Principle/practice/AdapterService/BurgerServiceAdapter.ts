import { BurgerOrder } from "./BurgerOrder";

export class BurgerServiceAdapter implements BurgerOrder {
    
    private adaptee: BurgerOrder;
    
    
    constructor(adaptee: BurgerOrder) {
        this.adaptee = adaptee;
    }

    orderBurger(quantity: number): void {
        this.adaptee.orderBurger(quantity)
    }
}