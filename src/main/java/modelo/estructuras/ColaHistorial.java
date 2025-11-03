/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.estructuras;

import modelo.entidades.Compra;
import modelo.entidades.NodoCompra;
import java.util.ArrayList;
import java.util.List;

/**
 * Cola (Queue) para gestionar el historial de compras
 * FIFO: First In, First Out
 * Las compras más antiguas están al frente
 */
public class ColaHistorial {
    private NodoCompra frente;
    private NodoCompra atras;
    private int tamaño;
    
    // Constructor
    public ColaHistorial() {
        this.frente = null;
        this.atras = null;
        this.tamaño = 0;
    }
    
    // Encolar: Agregar compra al final de la cola
    public void encolar(Compra compra) {
        NodoCompra nuevoNodo = new NodoCompra(compra);
        
        if (atras == null) {
            // Cola vacía: frente y atrás apuntan al mismo nodo
            frente = nuevoNodo;
            atras = nuevoNodo;
        } else {
            // Agregar al final
            atras.siguiente = nuevoNodo;
            atras = nuevoNodo;
        }
        tamaño++;
    }
    
    // Desencolar: Eliminar y devolver la compra del frente
    public Compra desencolar() {
        if (frente == null) {
            return null;
        }
        
        Compra compra = frente.compra;
        frente = frente.siguiente;
        
        // Si la cola quedó vacía, actualizar atrás
        if (frente == null) {
            atras = null;
        }
        
        tamaño--;
        return compra;
    }
    
    // Frente: Ver la compra del frente sin eliminarla
    public Compra frente() {
        if (frente != null) {
            return frente.compra;
        }
        return null;
    }
    
    // Obtener todas las compras (para mostrar historial completo)
    public List<Compra> obtenerTodas() {
        List<Compra> compras = new ArrayList<>();
        NodoCompra actual = frente;
        while (actual != null) {
            compras.add(actual.compra);
            actual = actual.siguiente;
        }
        return compras;
    }
    
    // Verificar si está vacía
    public boolean estaVacia() {
        return frente == null;
    }
    
    // Obtener tamaño
    public int getTamaño() {
        return tamaño;
    }
}
