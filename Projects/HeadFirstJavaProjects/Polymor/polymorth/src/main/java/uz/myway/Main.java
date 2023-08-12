package uz.myway;

/**
 * @author Hamdamboy
 * @project Default (Template) Project
 * @Email 'hamdamboy.urunov@gmail.com'
 * @Date : 12.08.2023 on сб
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Polymorythid!");

        Access access = new Access();
        Box box = new Box();
        Condition condition = new Condition();

//        Access access1 = new Access();
//
//        access1.access1();
//        access1.access2();
//        access1.access3();

        condition.access1();
        condition.access2();
        condition.access3();

        box.access1();
        box.access2();
        box.access3();

        access.access1();
        access.access2();
        access.access3();
    }
}