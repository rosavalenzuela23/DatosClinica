/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    @Override
    public boolean guardar(Paciente paciente, CartaConcentimiento carta, String rutaArchivo) {
        try (EntityManager entityManager = EntityManagerFactory.createInstance()) {

            carta.setRutaArchivo(rutaArchivo);
            entityManager.getTransaction().begin();
            paciente.setCarta(carta);
            carta.setPaciente(paciente);
            entityManager.merge(paciente);
            entityManager.merge(carta);
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
