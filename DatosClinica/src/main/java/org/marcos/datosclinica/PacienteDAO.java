/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import java.util.List;
import org.marcos.Entidades.Paciente;
import org.marcos.datos.interfaces.IDatosPaciente;
import org.marcos.util.EntityManagerGetter;

/**
 *
 * @author natsu
 * @param <Paciente> el paciente a guardar
 */
class PacienteDAO<T> implements IDatosPaciente {

    @Override
    public Paciente guardar(Paciente obj) {
        var entityManager = EntityManagerGetter.getEntityManager();
        
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        
        return obj;
    }

    @Override
    public Paciente obtener(Long id) {
        var entityManager = EntityManagerGetter.getEntityManager();
        
        Paciente p = entityManager.find(Paciente.class, id);
        
        return p;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        var entityManager = EntityManagerGetter.getEntityManager();
        
        return null;
    }

  

    
    
}
