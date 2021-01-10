import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Tester {

    @Test
    public void test11() {
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem("", 0.99, 5, Type.REGULAR);
            cart.addItem("123456789012742865873y8732y385538jfbzbd", 0.99, 5, Type.REGULAR);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        cart.addItem("h", 0.99, 5, Type.REGULAR);
        assertEquals(" # Item Price Quan. Discount Total\n" +
                "---------------------------------------------------------\n" +
                " 1 h                       $.99    5  -      $4.95\n" +
                "---------------------------------------------------------\n" +
                " 1      $4.95", cart.toString());
    }

    @Test
    public void test12() {
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem("hgv", -1.0, 5, Type.REGULAR);
            cart.addItem("ghv", 10001, 5, Type.REGULAR);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        cart.addItem("h", 100.0, 5, Type.REGULAR);
        assertEquals(" # Item Price Quan. Discount Total\n" +
                "---------------------------------------------------------\n" +
                " 1 h                    $100.00    5  -    $500.00\n" +
                "---------------------------------------------------------\n" +
                " 1    $500.00", cart.toString());
    }

    @Test
    public void test13() {
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem("hgv", 5, -1, Type.REGULAR);
            cart.addItem("ghv", 5, 10001, Type.REGULAR);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        cart.addItem("h", 100.0, 5, Type.REGULAR);
        assertEquals(" # Item Price Quan. Discount Total\n" +
                "---------------------------------------------------------\n" +
                " 1 h                    $100.00    5  -    $500.00\n" +
                "---------------------------------------------------------\n" +
                " 1    $500.00", cart.toString());
    }

    @Test
    public void test14() {
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem("hgv", 5, 1, Type.valueOf("vgc"));
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        cart.addItem("h", 100.0, 5, Type.REGULAR);
        assertEquals(" # Item Price Quan. Discount Total\n" +
                "---------------------------------------------------------\n" +
                " 1 h                    $100.00    5  -    $500.00\n" +
                "---------------------------------------------------------\n" +
                " 1    $500.00", cart.toString());
    }

    @Test
    public void test15() {
        ShoppingCart cart = new ShoppingCart();
        for (int i = 1; i < 100; i++) {
            cart.addItem("hgv", 5, 1, Type.DISCOUNT);
        }
        try {
            cart.addItem("hgv", 5, 1, Type.DISCOUNT);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void test16() {
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem(null, 5, 1, null);
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        cart.addItem("h", 100.0, 5, Type.REGULAR);
        assertEquals(" # Item Price Quan. Discount Total\n" +
                "---------------------------------------------------------\n" +
                " 1 h                    $100.00    5  -    $500.00\n" +
                "---------------------------------------------------------\n" +
                " 1    $500.00", cart.toString());
    }

    @ParameterizedTest
    @EnumSource(value = Type.class, names = {"REGULAR"})
    public void test21 (Type type) {
        int discount = ShoppingCart.calculateDiscount(new Item("name", 100, type, 5));
        assertEquals(0, discount);
    }

    @ParameterizedTest
    @EnumSource(value = Type.class, names = {"SECOND"})
    public void test22 (Type type) {
        int discount = ShoppingCart.calculateDiscount(new Item("name", 100, type, 5));
        assertEquals(50, discount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 999})
    public void test23 (int quantity) {
        int discount = ShoppingCart.calculateDiscount(new Item("name", 100, Type.DISCOUNT, quantity));
        assertTrue(10 <= discount && discount <=80);
    }

    //80
    @ParameterizedTest
    @EnumSource(value = Type.class, names = {"SALE"})
    public void test24 (Type type) {
        int discount = ShoppingCart.calculateDiscount(new Item("name", 100, type, 5));
        assertEquals(80, discount);
    }

    //80
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 999})
    public void test25 (int price) {
        int discount = ShoppingCart.calculateDiscount(new Item("name", price, Type.DISCOUNT, 5));
        assertTrue(10 <= discount && discount <=90);
    }


}
