/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author estra
 */
public class NodoItemCarrito {
    public ItemCarrito item;
    public NodoItemCarrito siguiente;
    
    public NodoItemCarrito(ItemCarrito item) {
        this.item = item;
        this.siguiente = null;
    }
}
