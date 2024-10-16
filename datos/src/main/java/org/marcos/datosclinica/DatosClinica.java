/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.marcos.datosclinica;

import org.marcos.Entidades.Expediente;
import org.marcos.Entidades.Psicologo;
import org.marcos.Entidades.Sesion;

/**
 *
 * @author natsu
 */
public class DatosClinica {

    public static void main(String[] args) {
        
        var dao = new SesionDAO();
        var expediente = new Expediente();
        expediente.setId(2L);
        
        for(Sesion s : dao.obtenerSesiones(expediente)) {
            System.out.println(s.getId());
        }
        
    }
}
