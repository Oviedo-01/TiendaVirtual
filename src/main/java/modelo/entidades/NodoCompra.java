/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author estra
 */
/**
 * Nodo para cola de historial de compras
 */
public class NodoCompra {
    public Compra compra;
    public NodoCompra siguiente;
    
    public NodoCompra(Compra compra) {
        this.compra = compra;
        this.siguiente = null;
    }
}
