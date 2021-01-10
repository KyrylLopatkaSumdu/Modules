import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    @Test
    public void testAppendFormatted() {
        assertEquals("12 ", ShoppingCart.appendFormatted(new StringBuilder(), "123", 0, 2).toString());
        assertEquals("123 ", ShoppingCart.appendFormatted(new StringBuilder(), "123", 0, 3).toString());
        assertEquals("123  ", ShoppingCart.appendFormatted(new StringBuilder(), "123", 0, 4).toString());

        assertEquals(" 123 ", ShoppingCart.appendFormatted(new StringBuilder(), "123", 1, 4).toString());
        assertEquals("123  ", ShoppingCart.appendFormatted(new StringBuilder(), "123", -1, 4).toString());
    }


    @Test
    public void testDiscount() {
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 100));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 3));
        assertEquals(60, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 100));
        assertEquals(71, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 10));
        assertEquals(10, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 100));
    }

    @Test
    public void testFormatTicket() {
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 100));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 3));
        assertEquals(60, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 100));
        assertEquals(71, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 10));
        assertEquals(10, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 100));
    }



}
