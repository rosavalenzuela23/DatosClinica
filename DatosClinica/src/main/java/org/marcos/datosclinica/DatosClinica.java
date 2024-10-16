/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.marcos.datosclinica;

import org.marcos.Entidades.Paciente;
import org.marcos.datos.interfaces.IDatosPaciente;

/**
 *
 * @author natsu
 */
public class DatosClinica {

    public static void main(String[] args) {
        
        IDatosPaciente daopaciente = new PacienteDAO();
        
        var p = new Paciente();
        p.setNombre("rosa");
        
        daopaciente.guardar(p);
        
    }
}
