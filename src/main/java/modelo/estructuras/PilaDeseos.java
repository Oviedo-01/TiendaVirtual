/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.estructuras;

import modelo.entidades.Producto;
import modelo.entidades.NodoProducto;
import java.util.ArrayList;
import java.util.List;

/**
 * Pila (Stack) para gestionar la lista de deseos
 * LIFO: Last In, First Out
 * Los productos agregados recientemente están al tope
 */
public class PilaDeseos {
    private NodoProducto tope;
    private int tamaño;
    
    // Constructor
    public PilaDeseos() {
        this.tope = null;
        this.tamaño = 0;
    }
    
    // Push: Agregar producto al tope de la pila
    public void push(Producto producto) {
        NodoProducto nuevoNodo = new NodoProducto(producto);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamaño++;
    }
    
    // Pop: Eliminar y devolver el producto del tope
    public Producto pop() {
        if (tope == null) {
            return null;
        }
        Producto producto = tope.producto;
        tope = tope.siguiente;
        tamaño--;
        return producto;
    }
    
    // Peek: Ver el producto del tope sin eliminarlo
    public Producto peek() {
        if (tope != null) {
            return tope.producto;
        }
        return null;
    }
    
    // Eliminar un producto específico (buscar y eliminar)
    public boolean eliminar(int idProducto) {
        if (tope == null) {
            return false;
        }
        
        // Caso especial: está en el tope
        if (tope.producto.getId() == idProducto) {
            tope = tope.siguiente;
            tamaño--;
            return true;
        }
        
        // Buscar en el resto de la pila
        NodoProducto actual = tope;
        while (actual.siguiente != null) {
            if (actual.siguiente.producto.getId() == idProducto) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    // Obtener todos los productos (para mostrar en interfaz)
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        NodoProducto actual = tope;
        while (actual != null) {
            productos.add(actual.producto);
            actual = actual.siguiente;
        }
        return productos;
    }
    
    // Verificar si está vacía
    public boolean estaVacia() {
        return tope == null;
    }
    
    // Obtener tamaño
    public int getTamaño() {
        return tamaño;
    }
    
    // Verificar si un producto ya existe en la pila
    public boolean existe(int idProducto) {
        NodoProducto actual = tope;
        while (actual != null) {
            if (actual.producto.getId() == idProducto) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}
