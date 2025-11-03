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
        // Si el archivo no existe, crearlo con productos de ejemplo
        if (!GestorArchivos.archivoExiste(ARCHIVO_PRODUCTOS)) {
            crearCatalogoInicial();
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
    
    // Crear catálogo inicial con productos de ejemplo
    private void crearCatalogoInicial() {
        GestorArchivos.crearArchivo(ARCHIVO_PRODUCTOS);
        
        // Productos de tecnología
        Producto p1 = new Producto(1, "Smartphone Galaxy", 299.99, "Teléfono inteligente con pantalla AMOLED", 15, "Tecnologia");
        p1.agregarImagen("imagenes/smartphone1.jpg");
        p1.agregarImagen("imagenes/smartphone2.jpg");
        productos.agregarProducto(p1);
        
        Producto p2 = new Producto(2, "Laptop Premium", 899.99, "Laptop ultradelgada con SSD 512GB", 8, "Tecnologia");
        p2.agregarImagen("imagenes/laptop1.jpg");
        p2.agregarImagen("imagenes/laptop2.jpg");
        productos.agregarProducto(p2);
        
        Producto p3 = new Producto(3, "Smartwatch Pro", 199.99, "Reloj inteligente con monitoreo de salud", 20, "Tecnologia");
        p3.agregarImagen("imagenes/smartwatch1.jpg");
        productos.agregarProducto(p3);
        
        Producto p4 = new Producto(4, "Auriculares Bluetooth", 149.99, "Auriculares con cancelación de ruido", 25, "Tecnologia");
        p4.agregarImagen("imagenes/auriculares1.jpg");
        productos.agregarProducto(p4);
        
        Producto p5 = new Producto(5, "Cámara Digital 4K", 599.99, "Cámara profesional con grabación 4K", 10, "Tecnologia");
        p5.agregarImagen("imagenes/camara1.jpg");
        p5.agregarImagen("imagenes/camara2.jpg");
        p5.agregarImagen("imagenes/camara3.jpg");
        productos.agregarProducto(p5);
        
        Producto p6 = new Producto(6, "Monitor 4K 27 pulgadas", 399.99, "Monitor para gaming y diseño", 12, "Tecnologia");
        p6.agregarImagen("imagenes/monitor1.jpg");
        productos.agregarProducto(p6);
        
        // Productos de hogar
        Producto p7 = new Producto(7, "Cafetera Automática", 89.99, "Cafetera programable con molinillo", 18, "Hogar");
        p7.agregarImagen("imagenes/cafetera1.jpg");
        productos.agregarProducto(p7);
        
        Producto p8 = new Producto(8, "Aspiradora Robot", 249.99, "Robot aspirador inteligente", 14, "Hogar");
        p8.agregarImagen("imagenes/aspiradora1.jpg");
        productos.agregarProducto(p8);
        
        guardarProductosEnArchivo();
    }
}
