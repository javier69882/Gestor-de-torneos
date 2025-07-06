package Logico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DepositoTest {

    private Deposito<String> deposito;

    @BeforeEach
    public void setUp() {
        deposito = new Deposito<>();
    }

    @Test
    public void testAddElementoAndSize() {
        assertEquals(0, deposito.size());
        deposito.addElemento("A");
        assertEquals(1, deposito.size());
        deposito.addElemento("B");
        assertEquals(2, deposito.size());
    }

    @Test
    public void testGetElemento() {
        assertNull(deposito.getElemento());

        deposito.addElemento("X");
        deposito.addElemento("Y");
        assertEquals("X", deposito.getElemento());
        assertEquals(1, deposito.size());
        assertEquals("Y", deposito.getElemento());
        assertEquals(0, deposito.size());
        assertNull(deposito.getElemento());
    }

    @Test
    public void testVaciar() {
        deposito.addElemento("Uno");
        deposito.addElemento("Dos");
        deposito.vaciar();
        assertEquals(0, deposito.size());
        assertNull(deposito.getElemento());
    }

    @Test
    public void testGetElementos() {
        deposito.addElemento("A");
        deposito.addElemento("B");
        List<String> elementos = deposito.getElementos();
        assertEquals(2, elementos.size());
        assertTrue(elementos.contains("A"));
        assertTrue(elementos.contains("B"));

        // modificar la lista retornada no afecta al depósito real
        elementos.clear();
        assertEquals(2, deposito.size());
    }
}