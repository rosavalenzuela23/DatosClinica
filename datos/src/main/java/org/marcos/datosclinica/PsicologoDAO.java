/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import org.marcos.util.EntityManagerFactory;
import org.marcos.Entidades.Psicologo;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 *
 * @author 
 */
public class PsicologoDAO {
    
    public Psicologo registrarPsicologo(Psicologo psi) {
       
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.persist(psi);
            manager.getTransaction().commit();
        }
        
        return psi;
    }
    
    public Psicologo obtenerPsicologo(Long id) {
        Psicologo psi;
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            psi = manager.find(Psicologo.class, id);
        }
        return psi;
    }
    
    public Psicologo actualizarPsicologo(Psicologo nuevosDatos) {
        try (var manager = EntityManagerFactory.createInstance()) {

            
            Psicologo psicologoExistente = manager.find(Psicologo.class, nuevosDatos.getId());
            if (psicologoExistente == null) {
                throw new IllegalArgumentException("No se encontró un psicólogo con el ID especificado.");
            }

           
            if (nuevosDatos.getUsuario() != null) {
                psicologoExistente.setUsuario(nuevosDatos.getUsuario());
            }
            if (nuevosDatos.getContrasenia() != null) {
                psicologoExistente.setContrasenia(nuevosDatos.getContrasenia());
            }

            manager.getTransaction().begin();
            manager.merge(psicologoExistente);
            manager.getTransaction().commit();
        }
        return nuevosDatos;
    }
    
    public void eliminarPsicologo(Long id) {
        try (var manager = EntityManagerFactory.createInstance()) {

            // Eliminar relaciones en pacientes_psicologos
            manager.getTransaction().begin();
            var query = manager.createNativeQuery("DELETE FROM pacientes_psicologos WHERE id_psicologo = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            manager.getTransaction().commit();


            Psicologo psicologoExistente = manager.find(Psicologo.class, id);
            if (psicologoExistente == null) {
                throw new IllegalArgumentException("No se encontró un psicólogo con el ID especificado.");
            }

            manager.getTransaction().begin();
            manager.remove(psicologoExistente);
            manager.getTransaction().commit();
        }
    }

    public List<Psicologo> obtenerTodos() {
        List<Psicologo> psicologos;
        
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
                psicologos = manager.createQuery(
                        "SELECT p FROM Psicologo p"
                ).getResultList();
            manager.getTransaction().commit();
        }
        
        return psicologos;
    }

    
}
