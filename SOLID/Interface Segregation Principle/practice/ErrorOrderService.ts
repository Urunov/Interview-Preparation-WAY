import { ErrorOrderService } from "./interfaces/ErrorOrderService";

class BurgerOrderService implements ErrorOrderService {
    orderBurger(quantity: number): void {
        console.log(`Recieved order of ${quantity} burgers`);
    }
    
    orderFries(fries: number): void {
        throw new Error("No fries in burger only order");
    }
    
    orderCombo(quantity: number, fries: number): void {
        throw new Error("No combo in burger only order");
    }
}