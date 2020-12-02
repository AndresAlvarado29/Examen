/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.factory.iEscuela;
import ec.edu.ups.modelo.Clase;

/**
 *
 * @author Andres
 */
public class Conexion {
   public iEscuela getConexion(String objeto) {
        if (objeto.equalsIgnoreCase("Docente")) {

            return new ControladorDocente();
        }
        if (objeto.equals("Estudiante")) {
            return new ControladorEstudiante();
        }
        if (objeto.equalsIgnoreCase("Rector")) {
            return new ControladorRector();
        }
        if (objeto.equalsIgnoreCase("Clase")) {
            return new ControladorClase();
           
       }
        return null;
    }  
}
