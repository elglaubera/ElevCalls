/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcalls.controller;

import com.senac.elevcalls.controller.exceptions.NonexistentEntityException;
import com.senac.elevcalls.controller.exceptions.RollbackFailureException;
import com.senac.elevcalls.entity.PecasPendecias;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Glauber
 */
public class PecasPendeciasJpaController implements Serializable {

    public PecasPendeciasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PecasPendecias pecasPendecias) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(pecasPendecias);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PecasPendecias pecasPendecias) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            pecasPendecias = em.merge(pecasPendecias);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pecasPendecias.getId();
                if (findPecasPendecias(id) == null) {
                    throw new NonexistentEntityException("The pecasPendecias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PecasPendecias pecasPendecias;
            try {
                pecasPendecias = em.getReference(PecasPendecias.class, id);
                pecasPendecias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pecasPendecias with id " + id + " no longer exists.", enfe);
            }
            em.remove(pecasPendecias);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PecasPendecias> findPecasPendeciasEntities() {
        return findPecasPendeciasEntities(true, -1, -1);
    }

    public List<PecasPendecias> findPecasPendeciasEntities(int maxResults, int firstResult) {
        return findPecasPendeciasEntities(false, maxResults, firstResult);
    }

    private List<PecasPendecias> findPecasPendeciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PecasPendecias.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PecasPendecias findPecasPendecias(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PecasPendecias.class, id);
        } finally {
            em.close();
        }
    }

    public int getPecasPendeciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PecasPendecias> rt = cq.from(PecasPendecias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
