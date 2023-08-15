export interface ErrorOrderService{
    orderBurger(quantity: number): void
    orderFries(fries: number): void;
    orderCombo(quantity: number, fries: number): void
}