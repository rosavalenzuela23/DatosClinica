/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.marcos.Entidades.CartaConcentimiento;
import org.marcos.Entidades.Paciente;
import org.marcos.datos.interfaces.IDatosCartaConsentimiento;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author luis-
 */
public class CartaConsentimientoDAO implements IDatosCartaConsentimiento {

    @Override
    public List<Paciente> buscarPacientesSinCarta() {
        List<Paciente> pacientesSinCarta;
        try (jakarta.persistence.EntityManager manager = EntityManagerFactory.createInstance()) {
            String jpqlQuery = "SELECT p FROM Paciente p LEFT JOIN p.carta c WHERE c.id IS NULL";

            pacientesSinCarta = manager.createQuery(jpqlQuery, Paciente.class).getResultList();
        }
        return pacientesSinCarta;
    }

    public CartaConcentimiento obtenerCarta(Long id) {
        CartaConcentimiento cc;
        try (EntityManager manager = EntityManagerFactory.createInstance()) {
            cc = manager.find(CartaConcentimiento.class, id);
        }
        return cc;
    }

    private void escribirArchivo(String ruta, byte[] contenido) throws IOException {
        
        File f = new File("expedientes");
        
        if(!f.exists()) {
            f.mkdir();
        }
        
        File file = new File(ruta);
        file.createNewFile();
        
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            os.write(contenido);
        } catch (IOException e){
            throw e;
        }
        
    }
    
    @Override
    public boolean guardar(Paciente paciente, CartaConcentimiento carta, byte[] cartaEnBytes) throws IOException {
        
        escribirArchivo(carta.getRutaArchivo(), cartaEnBytes);
        
        try (EntityManager entityManager = EntityManagerFactory.createInstance()) {

            entityManager.getTransaction().begin();
            var p = entityManager.find(Paciente.class, paciente.getId());
            p.setCarta(carta);
            carta.setPaciente(p);
            entityManager.persist(carta);
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public byte[] convertirArchivoRutaEnBytes(byte[] datos, String rutaArchivo) throws IOException {
     File archivo = new File(rutaArchivo);

    try (FileOutputStream fos = new FileOutputStream(archivo)) {
        fos.write(datos);
    }
        return datos;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        try (EntityManager manager = EntityManagerFactory.createInstance()) {
            TypedQuery<Paciente> query = manager.createQuery("SELECT p FROM Paciente p LEFT JOIN CartaConcentimiento c ON c.paciente.id = p.id", Paciente.class);
            List<Paciente> resultados = query.getResultList();
            return resultados;
        }

    }

    @Override
    public List<Paciente> buscarPacienteSinCartaPorNombre(String nombre) {
        List<Paciente> pacientesSinCarta;
        try (jakarta.persistence.EntityManager manager = EntityManagerFactory.createInstance()) {
            String jpqlQuery = "SELECT p FROM Paciente p LEFT JOIN CartaConcentimiento c ON c.paciente.id = p.id WHERE p.nombres = :nombre";
            pacientesSinCarta = manager.createQuery(jpqlQuery, Paciente.class)
                    .setParameter("nombre", nombre)
                    .getResultList();
        }
        return pacientesSinCarta;
    }
}
