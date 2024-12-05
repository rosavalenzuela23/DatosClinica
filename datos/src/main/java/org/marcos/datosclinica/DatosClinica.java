/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.marcos.datosclinica;

import DTOEntidades.DTOCartaConcentimiento;
import DTOEntidades.DTOPaciente;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.marcos.Entidades.Administrador;
import org.marcos.Entidades.CartaConcentimiento;
import org.marcos.Entidades.EstadoCivil;
import org.marcos.Entidades.Expediente;
import org.marcos.Entidades.Paciente;
import org.marcos.Entidades.Psicologo;
import org.marcos.Entidades.TipoVivienda;
import org.marcos.Entidades.Recepcionista;

/**
 *
 * @author natsu
 */
public class DatosClinica {

    public static void main(String[] args) throws IOException {
        
        PsicologoDAO pdao = new PsicologoDAO();
        
//        Psicologo test = new Psicologo();
//        test.setUsuario("curry");
//        test.setContrasenia("777");
//        pdao.registrarPsicologo(test);
        AdministradorDAO adao = new AdministradorDAO();
        Administrador test2 = new Administrador();
        test2.setUsuario("guest");
        test2.setContrasenia("guest");
        test2.setEstado(false);
        adao.registrarAdministrador(test2);
        
//        RecepcionistaDAO rdao = new RecepcionistaDAO();
//        Recepcionista test3 = new Recepcionista();
//        test3.setUsuario("chamber");
//        test3.setContrasenia("888");
//        rdao.registrarRecepcionista(test3);
        
    }
}
