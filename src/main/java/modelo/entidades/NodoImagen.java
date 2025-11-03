/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author estra
 */
public class NodoImagen {
    public String rutaImagen;
    public NodoImagen siguiente;
    public NodoImagen anterior;
    
    // Constructor
    public NodoImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
        this.siguiente = null;
        this.anterior = null;
    }
}
