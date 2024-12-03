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
}
