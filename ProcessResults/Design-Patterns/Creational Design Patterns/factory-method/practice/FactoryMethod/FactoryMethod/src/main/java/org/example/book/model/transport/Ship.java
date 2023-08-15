package org.example.book.model.transport;

import org.example.book.model.interfaces.Transport;

/**
 * @author hamdamboy
 * @project FactoryMethod
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date 5/19/23
 */
public class Ship implements Transport {

    private String name;
    private Double price;
    @Override
    public void deliver(String name, Double price) {
        this.name = name;
        this.price = price;
        System.out.println("Delivery By Ship." + name + " and spend: " + price);
    }
}
