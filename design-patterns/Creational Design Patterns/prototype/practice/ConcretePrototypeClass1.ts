

class ConcretePrototypeClass1 implements Prototype {
    clone(): Prototype {
        return new ConcretePrototypeClass1()
    }
    someOperation(): void {
        console.log("Executing someOperation in ConcretePrototype1");
    }
    
}