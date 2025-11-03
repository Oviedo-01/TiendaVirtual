/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import modelo.entidades.Compra;
import modelo.entidades.ItemCarrito;
import modelo.estructuras.ColaHistorial;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Gestor para operaciones del historial de compras
 * Cada usuario tiene su propio HistorialManager
 */
public class HistorialManager {
    private ColaHistorial historial;
    private String archivoHistorial;
    private String nombreUsuario;
    private ProductoManager productoManager;
    
    // Constructor
    public HistorialManager(String nombreUsuario, ProductoManager productoManager) {
        this.nombreUsuario = nombreUsuario;
        this.productoManager = productoManager;
        this.historial = new ColaHistorial();
        this.archivoHistorial = "datos/usuarios/" + nombreUsuario + "/historial.txt";
        cargarHistorialDeArchivo();
    }
    
    // Agregar compra al historial
    public void agregarCompra(List<ItemCarrito> productos, double total) {
        String fecha = obtenerFechaActual();
        Compra compra = new Compra(fecha, productos, total);
        historial.encolar(compra);
        guardarHistorialEnArchivo();
    }
    
    // Obtener todas las compras
    public List<Compra> obtenerHistorial() {
        return historial.obtenerTodas();
    }
    
    // Verificar si está vacío
    public boolean estaVacio() {
        return historial.estaVacia();
    }
    
    // Obtener fecha actual en formato yyyy-MM-dd
    private String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
    }
    
    // Cargar historial desde archivo
    private void cargarHistorialDeArchivo() {
        if (!GestorArchivos.archivoExiste(archivoHistorial)) {
            return;
        }
        
        List<String> lineas = GestorArchivos.leerArchivo(archivoHistorial);
        for (String linea : lineas) {
            Compra compra = Compra.fromString(linea, productoManager);
            if (compra != null) {
                historial.encolar(compra);
            }
        }
    }
    
    // Guardar historial en archivo
    private void guardarHistorialEnArchivo() {
        List<String> lineas = new java.util.ArrayList<>();
        for (Compra compra : historial.obtenerTodas()) {
            lineas.add(compra.toString());
        }
        GestorArchivos.escribirArchivo(archivoHistorial, lineas);
    }
}
