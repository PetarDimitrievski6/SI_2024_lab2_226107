import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    @Test
    public void everyBranchTest(){
        RuntimeException exception;
        List<Item> list = new ArrayList<>();

        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

        list.add(new Item(null, "1invalid", 0, 0));
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));
        list.clear();

        list.add(new Item("any", null, 0, 0));
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(exception.getMessage().contains("No barcode!"));
        list.clear();

        list.add(new Item("any", "123", 5, 0));
        assertFalse(SILab2.checkCart(list, 1));
        list.clear();

        list.add(new Item("any", "0123", 1000, 0.9F));
        list.add(new Item("any", "0456", 10000, 0.9F));
        assertTrue(SILab2.checkCart(list, 9840));
        list.clear();
    }

    @Test
    public void multipleConditionTest(){
        List<Item> list = new ArrayList<>();

        list.add(new Item("any", "0123", 350, 0.9F));
        assertTrue(SILab2.checkCart(list, 1000));
        list.clear();

        list.add(new Item("any", "555", 350, 0.9F));
        assertTrue(SILab2.checkCart(list, 1000));
        list.clear();

        list.add(new Item("any", "555", 350, 0));
        assertTrue(SILab2.checkCart(list, 1000));
        list.clear();

        list.add(new Item("any", "555", 200, 0.9F));
        assertTrue(SILab2.checkCart(list, 1000));
    }
}
