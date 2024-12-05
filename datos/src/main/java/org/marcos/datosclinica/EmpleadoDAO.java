/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.marcos.Entidades.Empleado;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author marco
 */
public class EmpleadoDAO {
    
    public Empleado obtenerEmpleado(Long id) {
        Empleado emp;
        try(EntityManager manager = EntityManagerFactory.createInstance()) {
            emp = manager.find(Empleado.class, id);
        }
        return emp;
    }
    
    public Empleado obtenerEmpleado(String usuario, String password) throws Exception {
        Empleado emp = null;
        try (jakarta.persistence.EntityManager manager = EntityManagerFactory.createInstance()) {
            String jakartaQuery = "SELECT emp FROM Empleado emp WHERE emp.usuario = :usuario AND emp.contrasenia = :password";
            emp = manager.createQuery(jakartaQuery, Empleado.class)
                    .setParameter("usuario", usuario)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            throw new Exception("Usuario u contraseña invalida");
        }
        return emp;
    }
    
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = null;
        try (jakarta.persistence.EntityManager manager = EntityManagerFactory.createInstance()) {
            String jakartaQuery = "SELECT emp FROM Empleado emp";
            empleados = manager.createQuery(jakartaQuery, Empleado.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener empleados: " + e.getMessage());
        }
        return empleados;
    }
    
    public Empleado eliminarEmpleado(Long id) {
        
        try (var manager = EntityManagerFactory.createInstance()) {
            
            Empleado empleadoExistente = manager.find(Empleado.class, id);
            if (empleadoExistente == null) {
                throw new IllegalArgumentException("No se encontró un empleado con el ID especificado.");
            }

            
            manager.getTransaction().begin();
            empleadoExistente.setEstado(false); 
            manager.merge(empleadoExistente); 
            manager.getTransaction().commit();

           
            return empleadoExistente;
        }
    }



}
