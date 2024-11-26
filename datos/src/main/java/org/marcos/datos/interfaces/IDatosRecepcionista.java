/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.marcos.datos.interfaces;

import java.util.List;
import org.marcos.Entidades.Empleado;
import org.marcos.Entidades.Recepcionista;

/**
 *
 * @author luis-
 */
public interface IDatosRecepcionista {
     
    public List<Empleado>mostrarEmpleadosDisponibles();
    
    public List<Empleado> mostrarRecepcionista();

    public Recepcionista agregarRecepcionista(Recepcionista rec, Empleado emp);

    public boolean actualizarRecepcionista(Empleado obj);

    public boolean eliminarRecepcionista(Empleado obj);

    public List<Empleado> buscarRecepcionistaPorNombre(String nombre);
    
}
