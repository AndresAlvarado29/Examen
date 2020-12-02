/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class Clase {
 private String nombre;
 private List<Docente> docentes;

    public Clase() {
    }

    public Clase(String nombre) {
        this.nombre = nombre;
        docentes=new ArrayList<>();
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 public void agregarDocente(Docente docente){
     docentes.add(docente);
 }
 


    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }
    
    
 
 
}
