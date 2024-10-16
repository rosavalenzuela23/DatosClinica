/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.datosclinica;

import java.util.List;
import org.marcos.Entidades.Expediente;
import org.marcos.Entidades.Sesion;
import org.marcos.util.EntityManagerFactory;

/**
 *
 * @author natsu
 */
public class SesionDAO {

    public Sesion agregarSesion(Sesion s) {
        try (var manager = EntityManagerFactory.createInstance()) {
            manager.getTransaction().begin();
            manager.persist(s);
            manager.getTransaction().commit();
        }
        return s;
    }

    public List<Sesion> obtenerSesiones(Expediente p) {
        List<Sesion> sesiones;

        try (var manager = EntityManagerFactory.createInstance()) {
            sesiones = manager.createQuery(
                    "SELECT s FROM Sesion s WHERE s.expediente.id = :id"
            ).setParameter("id", p.getId()).getResultList();
        }

        return sesiones;
    }

}
