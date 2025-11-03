/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.estructuras;

import modelo.entidades.Usuario;
import modelo.entidades.NodoUsuario;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author estra
 */
/**
 * Lista enlazada simple para gestionar usuarios del sistema
 */
public class ListaUsuarios {
    private NodoUsuario inicio;
    private int tamaño;
    
    // Constructor
    public ListaUsuarios() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    // Agregar usuario al final
    public void agregarUsuario(Usuario usuario) {
        NodoUsuario nuevoNodo = new NodoUsuario(usuario);
        
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoUsuario actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }
    
    // Buscar usuario por email (para login)
    public Usuario buscarPorEmail(String email) {
        NodoUsuario actual = inicio;
        while (actual != null) {
            if (actual.usuario.getEmail().equalsIgnoreCase(email)) {
                return actual.usuario;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    // Buscar usuario por nombre
    public Usuario buscarPorNombre(String nombre) {
        NodoUsuario actual = inicio;
        while (actual != null) {
            if (actual.usuario.getNombre().equalsIgnoreCase(nombre)) {
                return actual.usuario;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    // Verificar si existe un usuario con ese email
    public boolean existeUsuario(String email) {
        return buscarPorEmail(email) != null;
    }
    
    // Obtener todos los usuarios (para guardar en archivo)
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        NodoUsuario actual = inicio;
        while (actual != null) {
            usuarios.add(actual.usuario);
            actual = actual.siguiente;
        }
        return usuarios;
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
