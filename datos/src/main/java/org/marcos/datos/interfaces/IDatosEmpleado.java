/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.marcos.datos.interfaces;

import java.util.List;
import org.marcos.Entidades.Empleado;

/**
 *
 * @author luis-
 */
public interface IDatosEmpleado {

    public List<Empleado> mostrarEmpleados();

    public Empleado agregarEmpleado(Empleado obj);

    public Empleado actualizarEmpleado(Empleado obj);

    public Empleado eliminarEmpleado(Empleado obj);

    public List<Empleado> buscarEmpleadoPorNombre(String nombre);

}
