/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.estructuras;

import modelo.entidades.ItemCarrito;
import modelo.entidades.NodoItemCarrito;
import java.util.ArrayList;
import java.util.List;

/**
 * Lista enlazada simple con inserción al INICIO
 * Los productos agregados recientemente aparecen primero
 */
public class ListaCarrito {
    private NodoItemCarrito inicio;
    private int tamaño;
    
    // Constructor
    public ListaCarrito() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    // Agregar item AL INICIO (más recientes primero)
    public void agregarAlInicio(ItemCarrito item) {
        NodoItemCarrito nuevoNodo = new NodoItemCarrito(item);
        nuevoNodo.siguiente = inicio;
        inicio = nuevoNodo;
        tamaño++;
    }
    
    // Eliminar item por índice
    public boolean eliminar(int indice) {
        if (indice < 0 || indice >= tamaño || inicio == null) {
            return false;
        }
        
        if (indice == 0) {
            inicio = inicio.siguiente;
        } else {
            NodoItemCarrito actual = inicio;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }
        tamaño--;
        return true;
    }
    
    // Obtener item por índice
    public ItemCarrito obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        
        NodoItemCarrito actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.item;
    }
    
    // Calcular total del carrito
    public double calcularTotal() {
        double total = 0;
        NodoItemCarrito actual = inicio;
        while (actual != null) {
            total += actual.item.getSubtotal();
            actual = actual.siguiente;
        }
        return total;
    }
    
    // Obtener todos los items (para mostrar en interfaz)
    public List<ItemCarrito> obtenerTodos() {
        List<ItemCarrito> items = new ArrayList<>();
        NodoItemCarrito actual = inicio;
        while (actual != null) {
            items.add(actual.item);
            actual = actual.siguiente;
        }
        return items;
    }
    
    // Verificar si está vacío
    public boolean estaVacio() {
        return inicio == null;
    }
    
    // Obtener tamaño
    public int getTamaño() {
        return tamaño;
    }
    
    // Vaciar el carrito (después de comprar)
    public void vaciar() {
        inicio = null;
        tamaño = 0;
    }
}
