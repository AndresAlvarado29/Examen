/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.factory.iEscuela;
import ec.edu.ups.modelo.Rector;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 *
 * @author Andres
 */
public class ControladorRector implements iEscuela<Rector> {
 /**
     * Tamaño del archivo:
     * 
     * cedula 10 caracteres
     * nombre 25 caracteres
     * apellido 25 caracteres
     * direccion 50 cxaracteres
     * usuario 15 caracteres
     * contraseña 8 caracteres
     */
    private RandomAccessFile archivo;
private int tamañoRegistro;

    public ControladorRector() {
         tamañoRegistro = 133;
        try {
            archivo = new RandomAccessFile("Datos/Rector.dat", "rw");
            tamañoRegistro = 133;
            
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }


    @Override
    public void create(Rector objeto) {
       try {
            archivo.seek(archivo.length());
            archivo.writeUTF(objeto.getCedula());
            archivo.writeUTF(objeto.getNombre());
            archivo.writeUTF(objeto.getApellido());
            archivo.writeUTF(objeto.getDireccion());
            archivo.writeUTF(objeto.getUsuario());
            archivo.writeUTF(objeto.getContraseña());

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:ControladorRector)");
            e.printStackTrace();

        }
  
    }

    @Override
    public Rector read(String cedula) {
        try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                
                if (cedula.equals(cedulaArchivo)) {
                    return new Rector(cedula, archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF(),archivo.readUTF());
                    
                }
                salto += 133;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: ControladorRector)");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void update(Rector objeto) {
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
            archivo.writeUTF(objeto.getUsuario());
            archivo.writeUTF(objeto.getContraseña());          
                    break;
                    
                }
                salto += 133;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: ControladorEstudiante)");
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Rector objeto) {
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
                    archivo.writeUTF(llenarEspacios(15));
                    archivo.writeUTF(llenarEspacios(8));
                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error delete Rector");
        }
    }

    @Override
    public Rector login(String cedula, String contraseña) {
        try {
            int salto = 66;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String correoArchivo = archivo.readUTF();
                String contraseñaArchivo = archivo.readUTF();
                
                if (correoArchivo.trim().equals(cedula) && contraseñaArchivo.equals(contraseña)) {
                    archivo.seek(salto - 66);
                    return new Rector(archivo.readUTF(), archivo.readUTF().trim(), archivo.readUTF().trim(), cedula, archivo.readUTF().trim(),archivo.readUTF());
                    
                }
                salto += 133;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (login: ControladorRector)");
            e.printStackTrace();

        }
        return null;
    }
   public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    } 

    @Override
    public void agregar(Rector objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
  
}
