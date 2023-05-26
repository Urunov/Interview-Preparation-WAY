package org.example.book;

import org.example.book.model.transport.Ship;
import org.example.book.model.transport.Truck;

/**
 * @author ${USER}
 * @project ${PROJECT_NAME}
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date ${DATE}
 */
public class LogisticApp {
    public static void main(String[] args) {
        System.out.println("Logistic APP start is working now.");
        System.out.println("-----------------------------------");
        Truck truck = new Truck();
        truck.deliver("BC Truck ", 434.4);
        truck.deliver("LC Truck", 32.23);
        System.out.println("---------------------------");
        Ship ship = new Ship();
        ship.deliver("GS Ship", 434.3);
        ship.deliver("LG qoravoy", 323.2);
    }
}