/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.estructuras;

import modelo.entidades.NodoImagen;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author estra
 */
public class ListaImagenesCircular {
    private NodoImagen inicio;
    private int tamaño;
    
    // Constructor
    public ListaImagenesCircular() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    // Agregar imagen al final
    public void agregarImagen(String rutaImagen) {
        NodoImagen nuevoNodo = new NodoImagen(rutaImagen);
        
        if (inicio == null) {
            // Primera imagen - apunta a sí misma (circular)
            inicio = nuevoNodo;
            nuevoNodo.siguiente = nuevoNodo;
            nuevoNodo.anterior = nuevoNodo;
        } else {
            // Obtener el último nodo
            NodoImagen ultimo = inicio.anterior;
            
            // Insertar al final
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimo;
            nuevoNodo.siguiente = inicio;
            inicio.anterior = nuevoNodo;
        }
        tamaño++;
    }
    
    // Eliminar imagen por índice
    public boolean eliminarImagen(int indice) {
        if (indice < 0 || indice >= tamaño || inicio == null) {
            return false;
        }
        
        if (tamaño == 1) {
            inicio = null;
        } else {
            NodoImagen actual = inicio;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
            
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
            
            if (actual == inicio) {
                inicio = actual.siguiente;
            }
        }
        tamaño--;
        return true;
    }
    
    // Obtener la primera imagen (para inicializar imagenActual)
    public NodoImagen obtenerPrimera() {
        return inicio;
    }
    
    // Obtener todas las rutas (para mostrar o guardar)
    public List<String> obtenerTodas() {
        List<String> imagenes = new ArrayList<>();
        if (inicio == null) {
            return imagenes;
        }
        
        NodoImagen actual = inicio;
        do {
            imagenes.add(actual.rutaImagen);
            actual = actual.siguiente;
        } while (actual != inicio);
        
        return imagenes;
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
