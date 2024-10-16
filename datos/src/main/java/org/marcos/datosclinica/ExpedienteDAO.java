/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.marcos.Entidades.Expediente;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author natsu
 */
public class ExpedienteDAO {
    
    public List<Expediente> buscarExpedientesAbiertos(Long idPsicologo) {
        List<Expediente> expedientes;
        try (jakarta.persistence.EntityManager manager = EntityManagerFactory.createInstance()) {
            var jakartaQuery = " SELECT exp FROM Psicologo p JOIN p.pacientes.expediente exp WHERE p.id = :id";
            expedientes = manager.createQuery(jakartaQuery).setParameter("id", idPsicologo).getResultList();
        }
        return expedientes;
    }
    
    public Expediente obtenerExpediente(Long id) {
        Expediente exp;
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            exp = manager.find(Expediente.class, id);
        }
        return exp;
    }
    
    public Expediente registrarExpediente(Expediente exp) {
       
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.persist(exp);
            manager.getTransaction().commit();
        }
        
        return exp;
    }
    
    public Expediente actualizarExpediente(Expediente exp) {
        try(var manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.merge(exp);
            manager.getTransaction().commit();
        }
        return exp;
    }
    
}
