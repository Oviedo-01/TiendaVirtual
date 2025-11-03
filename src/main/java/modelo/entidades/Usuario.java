/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author estra
 */
public class Usuario {
    private String nombre;
    private String email;
    private String contraseña;
    
    // Constructor
    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    // Método para validar contraseña
    public boolean validarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
    
    // toString para guardar en archivo: nombre|email|contraseña
    @Override
    public String toString() {
        return nombre + "|" + email + "|" + contraseña;
    }
    
    // Método estático para crear Usuario desde línea de archivo
    public static Usuario fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length == 3) {
            return new Usuario(partes[0], partes[1], partes[2]);
        }
        return null;
    }
}
