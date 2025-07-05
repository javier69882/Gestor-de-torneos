package Logico;

import java.util.ArrayList;
import java.util.List;

/**
 * almacen generico de elementos
 * permite agregar, obtener y vaciar elementos
 * este codigo fue copiado de una tarea anterior
 * @param <T> tipo de elemento a almacenar
 */
public class Deposito<T>{

    private ArrayList<T> almacen;

    /**
     * crea un deposito vacio
     */
    public Deposito() {
        almacen = new ArrayList<>();
    }

    /**
     * agrega un elemento al deposito
     * @param elemento elemento a agregar
     */
    public void addElemento(T elemento) {
        almacen.add(elemento);
    }

    /**
     * obtiene y elimina el primer elemento del deposito, o null si esta vacio
     * @return primer elemento del deposito o null
     */
    public T getElemento() {
        if (almacen.size() > 0) {
            return almacen.remove(0);
        }
        return null;
    }

    /**
     * retorna la cantidad de elementos en el deposito
     * @return cantidad de elementos
     */
    public int size() {
        return almacen.size();
    }

    /**
     * elimina todos los elementos del deposito
     */
    public void vaciar() {
        almacen.clear();
    }

    /**
     * retorna una lista con todos los elementos del deposito
     * @return lista de elementos
     */
    public List<T> getElementos() {
        return new ArrayList<>(almacen);
    }
}
