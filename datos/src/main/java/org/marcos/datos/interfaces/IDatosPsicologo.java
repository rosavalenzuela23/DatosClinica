/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.marcos.datos.interfaces;

import java.util.List;
import org.marcos.Entidades.Empleado;
import org.marcos.Entidades.Psicologo;

/**
 *
 * @author luis-
 */
public interface IDatosPsicologo {
    
    public List<Empleado>mostrarEmpleadosDisponibles();
    
    public List<Empleado> mostrarPsicologo();

    public Psicologo agregarPsicologo(Psicologo psi, Empleado emp);

    public Psicologo actualizarPsicologo(Psicologo obj);

    public Psicologo eliminarPsicologo(Psicologo obj);

    public List<Empleado> buscarPsicologoPorNombre(String nombre);

    
}
