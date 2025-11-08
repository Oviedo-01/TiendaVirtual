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
    private String rol; // NUEVO: "usuario" o "admin"
    
    // Constructor completo (con rol)
    public Usuario(String nombre, String email, String contraseña, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    // Constructor sin rol (por defecto es "usuario")
    public Usuario(String nombre, String email, String contraseña) {
        this(nombre, email, contraseña, "usuario");
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
    
    public String getRol() {
        return rol;
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
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    // Métodos de utilidad
    public boolean validarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
    
    public boolean esAdministrador() {
        return "admin".equalsIgnoreCase(rol);
    }
    
    public boolean esUsuario() {
        return "usuario".equalsIgnoreCase(rol);
    }
    
    // toString para guardar en archivo: nombre|email|contraseña|rol
    @Override
    public String toString() {
        return nombre + "|" + email + "|" + contraseña + "|" + rol;
    }
    
    // Método estático para crear Usuario desde línea de archivo
    public static Usuario fromString(String linea) {
        String[] partes = linea.split("\\|");
        if (partes.length == 4) {
            // Formato nuevo: nombre|email|contraseña|rol
            return new Usuario(partes[0], partes[1], partes[2], partes[3]);
        } else if (partes.length == 3) {
            // Formato antiguo (retrocompatibilidad): nombre|email|contraseña
            return new Usuario(partes[0], partes[1], partes[2], "usuario");
        }
        return null;
    }
}