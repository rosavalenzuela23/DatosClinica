/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.marcos.datos.interfaces;

import java.util.List;
import org.marcos.Entidades.Paciente;

/**
 *
 * @author natsu
 */
public interface IDatosPaciente {
    
    public Paciente guardar(Paciente obj);
    public Paciente obtener(Long id);
    public List<Paciente> obtenerTodos();
    
    
}
