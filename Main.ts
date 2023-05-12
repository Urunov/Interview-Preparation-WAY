import { Burger } from './interfaces/Burger';
import { BurgerOrder } from './AdapterServices/BurgerOrder';
import { BurgerServiceAdapter } from './AdapterServices/BurgerServiceAdapter'
import { FrieOrder } from './AdapterServices/FrieOrder';
import { FrieOrderServiceAdapter } from './AdapterServices/FrieServiceAdapter'
import { Fries } from './interfaces/Frie';

class Main {
    
    callOrder(): void {
      
      const burgerService: Burger = new BurgerServiceAdapter(new BurgerOrder());
      burgerService.orderBurger(10)

      const frieService: Fries = new FrieOrderServiceAdapter(new FrieOrder())
      frieService.orderFries(50)
      
    }
}
  


let order = new Main()
order.callOrder()