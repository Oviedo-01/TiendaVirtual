/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import modelo.entidades.Producto;
import modelo.estructuras.PilaDeseos;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * Gestor para operaciones de la lista de deseos
 * Cada usuario tiene su propio DeseosManager
 */
public class DeseosManager {
    private PilaDeseos deseos;
    private String archivoDeseos;
    private String nombreUsuario;
    private ProductoManager productoManager;
    
    // Constructor
    public DeseosManager(String nombreUsuario, ProductoManager productoManager) {
        this.nombreUsuario = nombreUsuario;
        this.productoManager = productoManager;
        this.deseos = new PilaDeseos();
        this.archivoDeseos = "datos/usuarios/" + nombreUsuario + "/deseos.txt";
        cargarDeseosDeArchivo();
    }
    
    // Agregar producto a deseos (push)
    public void agregarADeseos(Producto producto) {
        // Verificar si el producto ya está en la lista de deseos
        for (Producto p : deseos.obtenerTodos()) {
            if (p.getId() == producto.getId()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El producto \"" + producto.getNombre() + "\" ya está en tu lista de deseos.",
                    "Producto duplicado",
                    JOptionPane.INFORMATION_MESSAGE
                );
                return; // No lo agrega
            }
        }

        // Si no está, agregarlo normalmente
        deseos.push(producto);
        guardarDeseosEnArchivo();

        JOptionPane.showMessageDialog(
            null,
            "El producto \"" + producto.getNombre() + "\" se agregó a tu lista de deseos.",
            "Producto agregado",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Eliminar producto específico de deseos
    public boolean eliminarDeDeseos(int idProducto) {
        boolean eliminado = deseos.eliminar(idProducto);
        if (eliminado) {
            guardarDeseosEnArchivo();
        }
        return eliminado;
    }
    
    // Eliminar el último producto agregado (pop)
    public Producto eliminarUltimo() {
        Producto producto = deseos.pop();
        if (producto != null) {
            guardarDeseosEnArchivo();
        }
        return producto;
    }
    
    // Obtener todos los productos de deseos
    public List<Producto> obtenerDeseos() {
        return deseos.obtenerTodos();
    }
    
    // Verificar si está vacío
    public boolean estaVacio() {
        return deseos.estaVacia();
    }
    
    // Cargar deseos desde archivo
    private void cargarDeseosDeArchivo() {
        if (!GestorArchivos.archivoExiste(archivoDeseos)) {
            return;
        }
        
        List<String> lineas = GestorArchivos.leerArchivo(archivoDeseos);
        // Cargar en orden inverso para mantener el orden de la pila
        for (int i = lineas.size() - 1; i >= 0; i--) {
            try {
                int idProducto = Integer.parseInt(lineas.get(i).trim());
                Producto producto = productoManager.obtenerProducto(idProducto);
                if (producto != null) {
                    deseos.push(producto);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error al parsear ID de producto: " + lineas.get(i));
            }
        }
    }
    
    // Guardar deseos en archivo
    private void guardarDeseosEnArchivo() {
        List<String> lineas = new java.util.ArrayList<>();
        // Guardamos desde el tope hacia abajo
        for (Producto producto : deseos.obtenerTodos()) {
            lineas.add(String.valueOf(producto.getId()));
        }
        GestorArchivos.escribirArchivo(archivoDeseos, lineas);
    }
}
