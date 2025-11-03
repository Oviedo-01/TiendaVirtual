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
 * Lista enlazada simple para gestionar el catálogo de productos
 */
public class ListaProductos {
    private NodoProducto inicio;
    private int tamaño;
    
    // Constructor
    public ListaProductos() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    // Agregar producto al final
    public void agregarProducto(Producto producto) {
        NodoProducto nuevoNodo = new NodoProducto(producto);
        
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoProducto actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }
    
    // Obtener producto por ID
    public Producto obtenerProducto(int id) {
        NodoProducto actual = inicio;
        while (actual != null) {
            if (actual.producto.getId() == id) {
                return actual.producto;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    // Obtener productos por categoría (para productos similares)
    public List<Producto> obtenerPorCategoria(String categoria) {
        List<Producto> productos = new ArrayList<>();
        NodoProducto actual = inicio;
        while (actual != null) {
            if (actual.producto.getCategoria().equalsIgnoreCase(categoria)) {
                productos.add(actual.producto);
            }
            actual = actual.siguiente;
        }
        return productos;
    }
    
    // Obtener todos los productos (para mostrar catálogo)
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        NodoProducto actual = inicio;
        while (actual != null) {
            productos.add(actual.producto);
            actual = actual.siguiente;
        }
        return productos;
    }
    
    // Verificar si está vacía
    public boolean estaVacia() {
        return inicio == null;
    }
    
    // Obtener tamaño
    public int getTamaño() {
        return tamaño;
    }
}
