/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.marcos.datosclinica;

import DTOEntidades.DTOCartaConcentimiento;
import DTOEntidades.DTOPaciente;
import java.io.IOException;
import java.util.Date;
import org.marcos.Entidades.CartaConcentimiento;
import org.marcos.Entidades.EstadoCivil;
import org.marcos.Entidades.Expediente;
import org.marcos.Entidades.Paciente;
import org.marcos.Entidades.TipoVivienda;

/**
 *
 * @author natsu
 */
public class DatosClinica {

    public static void main(String[] args) throws IOException {
        
        var dao = new SesionDAO();
        var expediente = new Expediente();
        expediente.setId(2L);
        
//        for(Sesion s : dao.obtenerSesiones(expediente)) {
//            System.out.println(s.getId());
//        }

          Paciente paciente = new Paciente();

      
        paciente.setId(1L); 
        paciente.setNombre("Carlos");
        paciente.setApellidoPaterno("Gomez");
        paciente.setApellidoMaterno("Lopez");
        paciente.setFecha(new Date());
        paciente.setTelefono("123456789");
        paciente.setTelefonoEmergencia("987654321");
        paciente.setEscolaridad("Licenciatura");
        paciente.setEstadoCivil(EstadoCivil.CASADO);
        paciente.setTipoVivienda(TipoVivienda.FORANEO); 
        
       
        CartaConcentimiento carta = new CartaConcentimiento();
        carta.setId(1L); 
        carta.setRutaArchivo("fjsklñdfjsdklfjksdl");
        CartaConsentimientoDAO ccDAO = new CartaConsentimientoDAO();
        String ruta = "C:\\Users\\luis-\\Downloads\\9 Reporte de Avance.pdf\\";
       
        boolean success = ccDAO.guardar(paciente, carta,ruta );
        System.out.println("----------------------------");
        System.out.println("Operación exitosa: " + success);
        System.out.println(ccDAO.buscarPacientesSinCarta());
        System.out.println("----------------------------");
        
        System.out.println(DTOCartaConcentimiento.from(ccDAO.obtenerCarta(1l)));
        
        System.out.println(ccDAO.convertirArchivoRutaEnBytes(ruta));
        
        System.out.println(  DTOPaciente.from(ccDAO.obtenerTodos().get(0)));
       

        
    }
}
