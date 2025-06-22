package Logico;

import java.util.ArrayList;
import java.util.List;

public class Deposito<T>{

    private ArrayList<T> almacen;

    public Deposito() {
        almacen = new ArrayList<>();
    }

    public void addElemento(T elemento) {
        almacen.add(elemento);
    }



    public T getElemento() {
        if (almacen.size() > 0) {
            return almacen.remove(0);
        }
        return null;
    }
    public int size() {
        return almacen.size();
    }

    public void vaciar() {
        almacen.clear();
    }
    public List<T> getElementos() {
        return new ArrayList<>(almacen);
    }
}