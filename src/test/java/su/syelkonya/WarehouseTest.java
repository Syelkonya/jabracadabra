package su.syelkonya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        warehouse.receive("apple", 100);
        warehouse.receive("banana", 50);
        warehouse.receive("cherry", 20);
    }

    @Test
    void receive() {
        warehouse.receive("apple", 50);
        assertEquals(150, warehouse.quantityMap.get("apple"));
    }

    @Test
    void receiveNewProduct() {
        warehouse.receive("mango", 30);
        assertEquals(30, warehouse.quantityMap.get("mango"));
    }

    @Test
    void shipSuccess() {
        assertTrue(warehouse.ship("apple", 30));
        assertEquals(70, warehouse.quantityMap.get("apple"));
    }

    @Test
    void shipNotEnough() {
        assertFalse(warehouse.ship("apple", 200));
        assertEquals(100, warehouse.quantityMap.get("apple")); // не изменился
    }

    @Test
    void shipExact() {
        assertTrue(warehouse.ship("cherry", 20));
        assertEquals(0, warehouse.quantityMap.get("cherry"));
    }

    @Test
    void shipNonExistent() {
        assertFalse(warehouse.ship("mango", 10));
    }

    @Test
    void printStock() {
        assertDoesNotThrow(() -> warehouse.printStock());
    }
}