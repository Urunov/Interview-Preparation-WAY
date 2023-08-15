
class ConcretePrototypeClass2 implements Prototype{
    clone(): Prototype{
        return new ConcretePrototypeClass2();
    }

    someOperation(): void {
        console.log('Executing some operation in ConcretePrototype class 2')
    }
}