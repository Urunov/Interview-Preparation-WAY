import { Burger } from "../interfaces/Burger";

export class BurgerOrder implements Burger{
    orderBurger(quantity: number): void {
        console.log(`Order burger with quantity ${quantity}`);
    }
}