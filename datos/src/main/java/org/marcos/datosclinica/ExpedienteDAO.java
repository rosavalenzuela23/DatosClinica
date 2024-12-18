/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.marcos.Entidades.Expediente;
import org.marcos.Entidades.Paciente;
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
            
            var expedienteHelper = manager.find(Expediente.class, exp.getId());
            
            exp.setPaciente(expedienteHelper.getPaciente());
            
            if (exp.getIntegranteHogar() != null) {
                for(var integranteHogar : exp.getIntegranteHogar()) {
                    integranteHogar.setExpediente(exp);
                }
            }
            
            if (exp.getFamiliaresConfianza()!= null) {
                for(var familiarConfianza : exp.getFamiliaresConfianza()) {
                    familiarConfianza.setExpediente(exp);
                }
            }
            
            manager.getTransaction().begin();
            manager.merge(exp);
            manager.getTransaction().commit();
        }
        return exp;
    }
    
    public Expediente obtenerExpedientePorIdPaciente(Long id) {
        Expediente exp;
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            var paciente = manager.find(Paciente.class, id);
            exp = paciente.getExpediente();
        }
        return exp;
    }
    
}
