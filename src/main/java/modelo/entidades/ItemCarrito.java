/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author estra
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    
    // Constructor
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    // Getters y Setters
    public Producto getProducto() {
        return producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    // Calcular subtotal (precio × cantidad)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
    
    // toString para guardar en archivo
    // Formato: idProducto|cantidad
    @Override
    public String toString() {
        return producto.getId() + "|" + cantidad;
    }
    
    // Método estático para crear ItemCarrito desde línea de archivo
    // Necesita ProductoManager para buscar el producto por ID
    public static ItemCarrito fromString(String linea, modelo.gestores.ProductoManager productoManager) {
        String[] partes = linea.split("\\|");
        if (partes.length == 2) {
            int idProducto = Integer.parseInt(partes[0]);
            int cantidad = Integer.parseInt(partes[1]);
            
            Producto producto = productoManager.obtenerProducto(idProducto);
            if (producto != null) {
                return new ItemCarrito(producto, cantidad);
            }
        }
        return null;
    }
}
