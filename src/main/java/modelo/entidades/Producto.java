/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import modelo.estructuras.ListaImagenesCircular;

/**
 *
 * @author estra
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String categoria;
    private ListaImagenesCircular listaImagenes;
    private NodoImagen imagenActual;
    
    // Constructor
    public Producto(int id, String nombre, double precio, String descripcion, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.categoria = categoria;
        this.listaImagenes = new ListaImagenesCircular();
        this.imagenActual = null;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getStock() {
        return stock;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    // Setters
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    // Métodos para gestión de imágenes
    public void agregarImagen(String rutaImagen) {
        listaImagenes.agregarImagen(rutaImagen);
        // Si es la primera imagen, la establecemos como actual
        if (imagenActual == null) {
            imagenActual = listaImagenes.obtenerPrimera();
        }
    }
    
    public void siguienteImagen() {
        if (imagenActual != null) {
            imagenActual = imagenActual.siguiente;
        }
    }
    
    public void anteriorImagen() {
        if (imagenActual != null) {
            imagenActual = imagenActual.anterior;
        }
    }
    
    public String getImagenActual() {
        if (imagenActual != null) {
            return imagenActual.rutaImagen;
        }
        return "sin_imagen.png"; // Imagen por defecto
    }
    
    public ListaImagenesCircular getListaImagenes() {
        return listaImagenes;
    }
    
    // toString para guardar en archivo
    // Formato: id|nombre|precio|descripcion|stock|categoria|img1;img2;img3
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|");
        sb.append(nombre).append("|");
        sb.append(precio).append("|");
        sb.append(descripcion).append("|");
        sb.append(stock).append("|");
        sb.append(categoria).append("|");
        
        // Agregar imágenes separadas por ;
        if (!listaImagenes.estaVacia()) {
            for (int i = 0; i < listaImagenes.obtenerTodas().size(); i++) {
                sb.append(listaImagenes.obtenerTodas().get(i));
                if (i < listaImagenes.obtenerTodas().size() - 1) {
                    sb.append(";");
                }
            }
        }
        
        return sb.toString();
    }
    
    // Método estático para crear Producto desde línea de archivo
    public static Producto fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length >= 6) {
            int id = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            double precio = Double.parseDouble(partes[2]);
            String descripcion = partes[3];
            int stock = Integer.parseInt(partes[4]);
            String categoria = partes[5];
            
            Producto producto = new Producto(id, nombre, precio, descripcion, stock, categoria);
            
            // Cargar imágenes si existen
            if (partes.length == 7 && !partes[6].isEmpty()) {
                String[] imagenes = partes[6].split(";");
                for (String img : imagenes) {
                    producto.agregarImagen(img);
                }
            }
            
            return producto;
        }
        return null;
    }
}
