/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcos.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 *
 * @author natsu
 */
public class EntityManagerFactory {

    private static final String PERSISTENCE_UNIT_NAME = "org.marcos_datos_jar_0.0.1-SNAPSHOTPU";

    public static EntityManager createInstance() {
        var factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return factory.createEntityManager();
    }

}
