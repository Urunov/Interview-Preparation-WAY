package uz.openclosed.noncompliant;

import java.security.InvalidParameterException;

public class Calculator {
    public void calculator(CalculatorOperation operation){
        if(operation == null){
            throw new InvalidParameterException("Can not perform operation");
        }
        if(operation instanceof Addition){
            Addition addition = (Addition) operation;
            addition.setResult(addition.getLeft() + addition.getRight());
        } else if (operation instanceof Subtraction) {
            Subtraction subtraction = (Subtraction) operation;
            subtraction.setResult(subtraction.getLeft() - subtraction.getRight());
        }
    }
}
