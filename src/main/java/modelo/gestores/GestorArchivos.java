/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.gestores;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria con métodos estáticos para manejo de archivos de texto
 */
public class GestorArchivos {
    
    // Leer todas las líneas de un archivo
    public static List<String> leerArchivo(String ruta) {
        List<String> lineas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) { // Ignorar líneas vacías
                    lineas.add(linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        
        return lineas;
    }
    
    // Escribir todo el contenido al archivo (sobrescribe)
    public static boolean escribirArchivo(String ruta, List<String> lineas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir archivo: " + e.getMessage());
            return false;
        }
    }
    
    // Agregar una línea al final del archivo (append)
    public static boolean agregarLineaArchivo(String ruta, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al agregar línea: " + e.getMessage());
            return false;
        }
    }
    
    // Verificar si un archivo existe
    public static boolean archivoExiste(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists() && archivo.isFile();
    }
    
    // Crear un archivo vacío
    public static boolean crearArchivo(String ruta) {
        try {
            File archivo = new File(ruta);
            
            // Crear directorios padres si no existen
            File directorioPadre = archivo.getParentFile();
            if (directorioPadre != null && !directorioPadre.exists()) {
                directorioPadre.mkdirs();
            }
            
            // Crear archivo
            if (!archivo.exists()) {
                return archivo.createNewFile();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
            return false;
        }
    }
    
    // Crear directorio
    public static boolean crearDirectorio(String ruta) {
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            return directorio.mkdirs();
        }
        return true;
    }
}