/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import java.sql.SQLException;
import java.util.List;
import org.marcos.Entidades.Paciente;
import org.marcos.Entidades.Psicologo;
import org.marcos.datos.interfaces.IDatosPaciente;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author natsu
 */
public class PacienteDAO implements IDatosPaciente {

    @Override
    public Paciente guardar(Paciente obj) {
        
        try (var entityManager = EntityManagerFactory.createInstance()) {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
        }

        return obj;
    }

    @Override
    public Paciente obtener(Long id) {
        Paciente p;
        try (var entityManager = EntityManagerFactory.createInstance()) {
            p = entityManager.find(Paciente.class, id);
        }
        return p;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        var entityManager = EntityManagerFactory.createInstance();

        return null;
    }
    
    public List<Paciente> obtenerPacientesDelPsicologo(Long id) {
        List<Paciente> listaPacientes = null;
        
        try (var entityManager = EntityManagerFactory.createInstance()) {
            var psicologo = entityManager.find(Psicologo.class, id);
            listaPacientes = psicologo.getPacientes();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listaPacientes;
    }

}
