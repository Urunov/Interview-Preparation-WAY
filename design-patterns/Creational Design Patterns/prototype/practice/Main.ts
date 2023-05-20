class Main {
    private prototype: Prototype;
    
    constructor(prototype: Prototype){
        this.prototype = prototype;
    }

    createClone(): Prototype{
        return this.prototype.clone()
    }

    executeOperation(): void {
        this.prototype.someOperation()
    }
}


const prototype1 = new ConcretePrototypeClass1();
const prototype2 = new ConcretePrototypeClass2();

const client1 = new Main(prototype1);
const client2 = new Main(prototype2);

const clone1 = client1.createClone();
const clone2 = client2.createClone();
