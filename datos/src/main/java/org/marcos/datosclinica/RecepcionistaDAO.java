/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import org.marcos.Entidades.Recepcionista;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author 
 */
public class RecepcionistaDAO {
    
     public Recepcionista registrarRecepcionista(Recepcionista rec) {
       
        try (EntityManager manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.persist(rec);
            manager.getTransaction().commit();
        }

        return rec;
    }

    public Recepcionista obtenerRecepcionista(Long id) {
        Recepcionista rec;
        try (EntityManager manager = EntityManagerFactory.createInstance()) {
            rec = manager.find(Recepcionista.class, id);
        }
        return rec;
    }

    public Recepcionista actualizarRecepcionista(Recepcionista nuevosDatos) {
        try (var manager = EntityManagerFactory.createInstance()) {

            Recepcionista recepcionistaExistente = manager.find(Recepcionista.class, nuevosDatos.getId());
            if (recepcionistaExistente == null) {
                throw new IllegalArgumentException("No se encontró un recepcionista con el ID especificado.");
            }

            if (nuevosDatos.getUsuario() != null) {
               recepcionistaExistente.setUsuario(nuevosDatos.getUsuario());
            }
            if (nuevosDatos.getContrasenia() != null) {
                recepcionistaExistente.setContrasenia(nuevosDatos.getContrasenia());
            }

            manager.getTransaction().begin();
            manager.merge(recepcionistaExistente);
            manager.getTransaction().commit();
        }
        return nuevosDatos;
    }

    public void eliminarRecepcionsita(Long id) {
        try (var manager = EntityManagerFactory.createInstance()) {

            Recepcionista recepcionistaExistente = manager.find(Recepcionista.class, id);
            if (recepcionistaExistente == null) {
                throw new IllegalArgumentException("No se encontró un recepcionista con el ID especificado.");
            }

            manager.getTransaction().begin();
            manager.remove(recepcionistaExistente);
            manager.getTransaction().commit();
        }
    }

}
