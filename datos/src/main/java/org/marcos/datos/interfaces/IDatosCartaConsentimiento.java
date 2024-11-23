/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.marcos.datos.interfaces;

import java.io.IOException;
import java.util.List;
import org.marcos.Entidades.CartaConcentimiento;
import org.marcos.Entidades.Paciente;

/**
 *
 * @author luis-
 */
public interface IDatosCartaConsentimiento {
    
    public boolean guardar(Paciente paciente, CartaConcentimiento carta, String rutaArchivo);
    public CartaConcentimiento obtenerCarta (Long id);
    public List<Paciente>obtenerTodos();
    public byte[]convertirArchivoRutaEnBytes(byte[] datos, String rutaArchivo) throws IOException;
    public List<Paciente>buscarPacientesSinCarta();
    public List<Paciente>buscarPacienteSinCartaPorNombre(String nombre);
//public CartaConcentimiento obtenerDocumento(String url);
    
}
