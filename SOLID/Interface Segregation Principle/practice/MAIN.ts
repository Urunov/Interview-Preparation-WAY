import { Burger } from './interfaces/Burger';
import { BurgerOrder } from './AdapterService/BurgerOrder';
import { BurgerServiceAdapter } from './AdapterService/BurgerServiceAdapter'
import { FrieOrder } from './AdapterService/FrieOrder';
import { FrieOrderServiceAdapter } from './AdapterService/FrieServiceAdapter'
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