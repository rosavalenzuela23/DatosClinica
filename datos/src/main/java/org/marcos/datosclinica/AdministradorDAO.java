/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import org.marcos.Entidades.Administrador;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author marco
 */
public class AdministradorDAO {
    
    public Administrador registrarAdministrador(Administrador adm) {

        try (EntityManager manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.persist(adm);
            manager.getTransaction().commit();
        }

        return adm;
    }
     
     public Administrador obtenerAdministrador(Long id) {
        Administrador adm;
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            adm = manager.find(Administrador.class, id);
        }
        return adm;
    }
     
    public Administrador actualizarAdministrador(Administrador nuevosDatos) {
        try (var manager = EntityManagerFactory.createInstance()) {

            
            Administrador administradorExistente = manager.find(Administrador.class, nuevosDatos.getId());
            if (administradorExistente == null) {
                throw new IllegalArgumentException("No se encontró un psicólogo con el ID especificado.");
            }

           
            if (nuevosDatos.getUsuario() != null) {
                administradorExistente.setUsuario(nuevosDatos.getUsuario());
            }
            if (nuevosDatos.getContrasenia() != null) {
                administradorExistente.setContrasenia(nuevosDatos.getContrasenia());
            }

            manager.getTransaction().begin();
            manager.merge(administradorExistente);
            manager.getTransaction().commit();
        }
        return nuevosDatos;
    }
}
