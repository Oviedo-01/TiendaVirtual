/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import modelo.entidades.Producto;
import modelo.estructuras.ListaProductos;
import java.util.List;

/**
 * Gestor para operaciones relacionadas con productos
 * (Catálogo, búsqueda, productos similares)
 */
public class ProductoManager {
    private ListaProductos productos;
    private static final String ARCHIVO_PRODUCTOS = "datos/productos.txt";
    
    // Constructor
    public ProductoManager() {
        this.productos = new ListaProductos();
        cargarProductosDeArchivo();
    }
    
    // Obtener todos los productos del catálogo
    public List<Producto> obtenerTodosProductos() {
        return productos.obtenerTodos();
    }
    
    // Obtener producto por ID
    public Producto obtenerProducto(int id) {
        return productos.obtenerProducto(id);
    }
    
    // Obtener productos similares (misma categoría)
    public List<Producto> obtenerSimilares(String categoria) {
        return productos.obtenerPorCategoria(categoria);
    }
    
    // Agregar nuevo producto al catálogo
    public void agregarProducto(Producto producto) {
        productos.agregarProducto(producto);
        guardarProductosEnArchivo();
    }
    
    // Cargar productos desde archivo
    public void cargarProductosDeArchivo() {
        // Si el archivo no existe, crearlo vacío
        if (!GestorArchivos.archivoExiste(ARCHIVO_PRODUCTOS)) {
            GestorArchivos.crearArchivo(ARCHIVO_PRODUCTOS);
            return;
        }
    
        List<String> lineas = GestorArchivos.leerArchivo(ARCHIVO_PRODUCTOS);
        for (String linea : lineas) {
            Producto producto = Producto.fromString(linea);
            if (producto != null) {
                productos.agregarProducto(producto);
            }
        }
    }
    
    // Guardar productos en archivo
    public void guardarProductosEnArchivo() {
        List<String> lineas = new java.util.ArrayList<>();
        for (Producto producto : productos.obtenerTodos()) {
            lineas.add(producto.toString());
        }
        GestorArchivos.escribirArchivo(ARCHIVO_PRODUCTOS, lineas);
    }
    
    // Eliminar producto por ID
    public boolean eliminarProducto(int id) {
        boolean eliminado = productos.eliminarProducto(id);
        if (eliminado) {
            guardarProductosEnArchivo();
        }
        return eliminado;
    }
    
}
