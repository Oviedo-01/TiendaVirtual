/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import modelo.entidades.ItemCarrito;
import modelo.entidades.Producto;
import modelo.estructuras.ListaCarrito;
import java.util.List;

/**
 * Gestor para operaciones del carrito de compras
 * Cada usuario tiene su propio CarritoManager
 */
public class CarritoManager {
    private ListaCarrito carrito;
    private String archivoCarrito;
    private String nombreUsuario;
    private ProductoManager productoManager;
    
    // Constructor
    public CarritoManager(String nombreUsuario, ProductoManager productoManager) {
        this.nombreUsuario = nombreUsuario;
        this.productoManager = productoManager;
        this.carrito = new ListaCarrito();
        this.archivoCarrito = "datos/usuarios/" + nombreUsuario + "/carrito.txt";
        cargarCarritoDeArchivo();
    }
    
    // Agregar producto al carrito
    public void agregarAlCarrito(Producto producto, int cantidad) {
        ItemCarrito item = new ItemCarrito(producto, cantidad);
        carrito.agregarAlInicio(item);
        guardarCarritoEnArchivo();
    }
    
    // Eliminar item del carrito por índice
    public boolean eliminarDelCarrito(int indice) {
        boolean eliminado = carrito.eliminar(indice);
        if (eliminado) {
            guardarCarritoEnArchivo();
        }
        return eliminado;
    }
    
    // Obtener todos los items del carrito
    public List<ItemCarrito> obtenerCarrito() {
        return carrito.obtenerTodos();
    }
    
    // Calcular total del carrito
    public double calcularTotal() {
        return carrito.calcularTotal();
    }
    
    // Verificar si el carrito está vacío
    public boolean estaVacio() {
        return carrito.estaVacio();
    }
    
    // Vaciar el carrito (después de comprar)
    public void vaciarCarrito() {
        carrito.vaciar();
        guardarCarritoEnArchivo();
    }
    
    // Verificar si un producto ya existe en el carrito
    public boolean productoExisteEnCarrito(int idProducto) {
        return carrito.buscarProducto(idProducto) != null;
    }
    
    // Cargar carrito desde archivo
    private void cargarCarritoDeArchivo() {
        if (!GestorArchivos.archivoExiste(archivoCarrito)) {
            return;
        }
        
        List<String> lineas = GestorArchivos.leerArchivo(archivoCarrito);
        for (String linea : lineas) {
            ItemCarrito item = ItemCarrito.fromString(linea, productoManager);
            if (item != null) {
                carrito.agregarAlInicio(item);
            }
        }
    }
    
    // Guardar carrito en archivo
    public void guardarCarritoEnArchivo() {
        List<String> lineas = new java.util.ArrayList<>();
        for (ItemCarrito item : carrito.obtenerTodos()) {
            lineas.add(item.toString());
        }
        GestorArchivos.escribirArchivo(archivoCarrito, lineas);
    }
}