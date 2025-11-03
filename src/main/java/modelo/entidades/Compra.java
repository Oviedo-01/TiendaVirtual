/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una compra realizada (transacción completa)
 */
public class Compra {
    private String fecha;
    private List<ItemCarrito> productos;
    private double total;
    
    // Constructor
    public Compra(String fecha, List<ItemCarrito> productos, double total) {
        this.fecha = fecha;
        this.productos = productos;
        this.total = total;
    }
    
    // Getters
    public String getFecha() {
        return fecha;
    }
    
    public List<ItemCarrito> getProductos() {
        return productos;
    }
    
    public double getTotal() {
        return total;
    }
    
    // toString para guardar en archivo
    // Formato: fecha|total|idProd1:cant1;idProd2:cant2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fecha).append("|");
        sb.append(total).append("|");
        
        // Agregar productos en formato: idProducto:cantidad;idProducto:cantidad
        for (int i = 0; i < productos.size(); i++) {
            ItemCarrito item = productos.get(i);
            sb.append(item.getProducto().getId()).append(":").append(item.getCantidad());
            if (i < productos.size() - 1) {
                sb.append(";");
            }
        }
        
        return sb.toString();
    }
    
    // Método estático para crear Compra desde línea de archivo
    public static Compra fromString(String linea, modelo.gestores.ProductoManager productoManager) {
        String[] partes = linea.split("\\|");
        if (partes.length == 3) {
            String fecha = partes[0];
            double total = Double.parseDouble(partes[1]);
            String productosStr = partes[2];
            
            List<ItemCarrito> productos = new ArrayList<>();
            
            // Parsear productos: idProd1:cant1;idProd2:cant2
            String[] itemsArray = productosStr.split(";");
            for (String itemStr : itemsArray) {
                String[] itemPartes = itemStr.split(":");
                if (itemPartes.length == 2) {
                    int idProducto = Integer.parseInt(itemPartes[0]);
                    int cantidad = Integer.parseInt(itemPartes[1]);
                    
                    Producto producto = productoManager.obtenerProducto(idProducto);
                    if (producto != null) {
                        productos.add(new ItemCarrito(producto, cantidad));
                    }
                }
            }
            
            return new Compra(fecha, productos, total);
        }
        return null;
    }
}
