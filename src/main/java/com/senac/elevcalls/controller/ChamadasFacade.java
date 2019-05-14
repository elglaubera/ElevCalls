/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcalls.controller;

import com.senac.elevcalls.entity.Chamadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Glauber
 */
@Stateless
public class ChamadasFacade extends AbstractFacade<Chamadas> {

    @PersistenceContext(unitName = "com.senac_ElevCalls_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChamadasFacade() {
        super(Chamadas.class);
    }
    
}
