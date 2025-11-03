/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import modelo.entidades.Usuario;
import modelo.estructuras.ListaUsuarios;
import java.util.List;

/**
 * Gestor para operaciones relacionadas con usuarios
 * (Login, registro, persistencia)
 */
public class UsuarioManager {
    private ListaUsuarios usuarios;
    private static final String ARCHIVO_USUARIOS = "datos/usuarios.txt";
    
    // Constructor
    public UsuarioManager() {
        this.usuarios = new ListaUsuarios();
        cargarUsuariosDeArchivo();
    }
    
    // Registrar nuevo usuario
    public boolean registrarUsuario(String nombre, String email, String contraseña) {
        // Validar que el email no esté registrado
        if (usuarios.existeUsuario(email)) {
            return false;
        }
        
        // Crear usuario
        Usuario nuevoUsuario = new Usuario(nombre, email, contraseña);
        usuarios.agregarUsuario(nuevoUsuario);
        
        // Guardar en archivo
        guardarUsuariosEnArchivo();
        
        // Crear carpeta personal del usuario
        String carpetaUsuario = "datos/usuarios/" + nombre;
        GestorArchivos.crearDirectorio(carpetaUsuario);
        GestorArchivos.crearArchivo(carpetaUsuario + "/carrito.txt");
        GestorArchivos.crearArchivo(carpetaUsuario + "/deseos.txt");
        GestorArchivos.crearArchivo(carpetaUsuario + "/historial.txt");
        
        return true;
    }
    
    // Login: validar credenciales
    public Usuario login(String email, String contraseña) {
        Usuario usuario = usuarios.buscarPorEmail(email);
        
        if (usuario != null && usuario.validarContraseña(contraseña)) {
            return usuario;
        }
        
        return null;
    }
    
    // Verificar si un usuario existe
    public boolean usuarioExiste(String email) {
        return usuarios.existeUsuario(email);
    }
    
    // Cargar usuarios desde archivo
    private void cargarUsuariosDeArchivo() {
        // Si el archivo no existe, crearlo
        if (!GestorArchivos.archivoExiste(ARCHIVO_USUARIOS)) {
            GestorArchivos.crearArchivo(ARCHIVO_USUARIOS);
            return;
        }
        
        List<String> lineas = GestorArchivos.leerArchivo(ARCHIVO_USUARIOS);
        for (String linea : lineas) {
            Usuario usuario = Usuario.fromString(linea);
            if (usuario != null) {
                usuarios.agregarUsuario(usuario);
            }
        }
    }
    
    // Guardar usuarios en archivo
    private void guardarUsuariosEnArchivo() {
        List<String> lineas = new java.util.ArrayList<>();
        for (Usuario usuario : usuarios.obtenerTodos()) {
            lineas.add(usuario.toString());
        }
        GestorArchivos.escribirArchivo(ARCHIVO_USUARIOS, lineas);
    }
}
