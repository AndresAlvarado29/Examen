/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.factory.iEscuela;
import ec.edu.ups.modelo.Clase;
import ec.edu.ups.modelo.Docente;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class ControladorClase implements iEscuela<Clase>{
 /**
     * Tamaño del archivo:
     * 
     * nombre 25 caracteres
     */
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    private Docente docente;
    private Clase clase;
    private List<Docente> docentes;

    public ControladorClase() {
         docentes=new ArrayList<>();
        tamañoRegistro = 25;
        try {
            archivo = new RandomAccessFile("Datos/Clases.dat", "rw");
            tamañoRegistro = 25;

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }

    @Override
    public void create(Clase objeto) {
        try {
            archivo.seek(archivo.length());
            archivo.writeUTF(objeto.getNombre());
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:ControladorRector)");
            e.printStackTrace();

        }

    }

    @Override
    public Clase read(String nombre) {
        try {
            int salto = 0;

            while (salto < archivo.length()) {
                archivo.seek(salto);
                String nombreArchivo = archivo.readUTF();

                if (nombre.equals(nombreArchivo)) {
                    return new Clase(nombre);

                }
                salto += 25;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: ControladorRector)");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void update(Clase objeto) {
        try {
            int salto = 0;

            while (salto < archivo.length()) {
                archivo.seek(salto);
                String nombreArchivo = archivo.readUTF();

                if (objeto.getNombre().equals(nombreArchivo)) {
                    archivo.writeUTF(objeto.getNombre());
          
                    break;

                }
                salto += 25;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: ControladorEstudiante)");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Clase objeto) {
        try {
            String nombre = objeto.getNombre();
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String nombreArchivo = archivo.readUTF();
                if (nombre.trim().equals(nombreArchivo.trim())) {
                    archivo.writeUTF(llenarEspacios(25));
                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error delete Rector");
        }
    }

    @Override
    public Clase login(String cedula, String contraseña) {
        return null;
    }

    public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    }

    @Override
    public void agregar(Clase objeto) {     
        docentes.add(docente);
    }
    public void agregarDocente(Docente docente){
       docente=new Docente(docente.getCedula(),docente.getNombre(),docente.getApellido(),docente.getDireccion(),docente.getUsuario(),docente.getContraseña()); 
       clase.agregarDocente(docente);
       
        
    }

}
