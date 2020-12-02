/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.factory.iEscuela;
import ec.edu.ups.modelo.Estudiante;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 *
 * @author Andres
 */
public class ControladorEstudiante implements iEscuela<Estudiante>{
 /**
     * Tamaño del archivo:
     * 
     * cedula 10 caracteres
     * nombre 25 caracteres
     * apellido 25 caracteres
     * direccion 50 cxaracteres  
     */
    private RandomAccessFile archivo;
private int tamañoRegistro;

    public ControladorEstudiante() {
         tamañoRegistro = 110;
        try {
            archivo = new RandomAccessFile("Datos/Estudiantes.dat", "rw");
            tamañoRegistro = 110;
            
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }


    @Override
    public void create(Estudiante objeto) {
       try {
            archivo.seek(archivo.length());
            archivo.writeUTF(objeto.getCedula());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getDireccion());
            

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:ControladorEstudiante)");
            e.printStackTrace();

        }
  
    }

    @Override
    public Estudiante read(String cedula) {
        try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                
                if (cedula.equals(cedulaArchivo)) {
                    return new Estudiante(cedula, archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim());
                    
                }
                salto += 110;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: ControladorEstudiante)");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void update(Estudiante objeto) {
        try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                
                if (objeto.getCedula().equals(cedulaArchivo)) {
                    archivo.writeUTF(objeto.getCedula());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getDireccion());
                   
                    break;
                    
                }
                salto += 110;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: ControladorEstudiante)");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Estudiante objeto) {
         try {
            String cedula = objeto.getCedula();
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                if (cedula.trim().equals(cedulaArchivo.trim())) {
                    archivo.writeUTF(llenarEspacios(10));
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeUTF(llenarEspacios(50));
                   
                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error delete usuario");
        }
    }

    @Override
    public Estudiante login(String cedula, String contraseña) {
        try {
            int salto = 66;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String correoArchivo = archivo.readUTF();
                String contraseñaArchivo = archivo.readUTF();
                
                if (correoArchivo.trim().equals(cedula) && contraseñaArchivo.equals(contraseña)) {
                    archivo.seek(salto - 66);
                    return new Estudiante(archivo.readUTF(), archivo.readUTF().trim(), archivo.readUTF().trim(), cedula);
                    
                }
                salto += 110;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (login: ControladorEstudiante)");
            e.printStackTrace();

        }
        return null;
    }
   public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    } 

    @Override
    public void agregar(Estudiante objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
}
