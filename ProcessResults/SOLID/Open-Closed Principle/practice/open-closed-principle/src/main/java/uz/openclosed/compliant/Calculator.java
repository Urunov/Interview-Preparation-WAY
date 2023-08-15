package uz.openclosed.compliant;

import java.security.InvalidParameterException;

public class Calculator {
    public void calculate(CalculatorOperation calculatorOperation){
        if (calculatorOperation == null){
            throw new InvalidParameterException("Cannot perform operation");
        }
        calculatorOperation.perform();
    }
}
