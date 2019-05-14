/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.elevcalls.controller;

import com.senac.elevcalls.controller.exceptions.NonexistentEntityException;
import com.senac.elevcalls.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.senac.elevcalls.entity.PecasPendecias;
import com.senac.elevcalls.entity.Pendencias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Glauber
 */
public class PendenciasJpaController2 implements Serializable {

    public PendenciasJpaController2(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pendencias pendencias) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PecasPendecias pecasPendencias = pendencias.getPecasPendencias();
            if (pecasPendencias != null) {
                pecasPendencias = em.getReference(pecasPendencias.getClass(), pecasPendencias.getId());
                pendencias.setPecasPendencias(pecasPendencias);
            }
            em.persist(pendencias);
            if (pecasPendencias != null) {
                pecasPendencias.getPendencias().add(pendencias);
                pecasPendencias = em.merge(pecasPendencias);
            }
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

    public void edit(Pendencias pendencias) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pendencias persistentPendencias = em.find(Pendencias.class, pendencias.getId());
            PecasPendecias pecasPendenciasOld = persistentPendencias.getPecasPendencias();
            PecasPendecias pecasPendenciasNew = pendencias.getPecasPendencias();
            if (pecasPendenciasNew != null) {
                pecasPendenciasNew = em.getReference(pecasPendenciasNew.getClass(), pecasPendenciasNew.getId());
                pendencias.setPecasPendencias(pecasPendenciasNew);
            }
            pendencias = em.merge(pendencias);
            if (pecasPendenciasOld != null && !pecasPendenciasOld.equals(pecasPendenciasNew)) {
                pecasPendenciasOld.getPendencias().remove(pendencias);
                pecasPendenciasOld = em.merge(pecasPendenciasOld);
            }
            if (pecasPendenciasNew != null && !pecasPendenciasNew.equals(pecasPendenciasOld)) {
                pecasPendenciasNew.getPendencias().add(pendencias);
                pecasPendenciasNew = em.merge(pecasPendenciasNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pendencias.getId();
                if (findPendencias(id) == null) {
                    throw new NonexistentEntityException("The pendencias with id " + id + " no longer exists.");
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
            Pendencias pendencias;
            try {
                pendencias = em.getReference(Pendencias.class, id);
                pendencias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pendencias with id " + id + " no longer exists.", enfe);
            }
            PecasPendecias pecasPendencias = pendencias.getPecasPendencias();
            if (pecasPendencias != null) {
                pecasPendencias.getPendencias().remove(pendencias);
                pecasPendencias = em.merge(pecasPendencias);
            }
            em.remove(pendencias);
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

    public List<Pendencias> findPendenciasEntities() {
        return findPendenciasEntities(true, -1, -1);
    }

    public List<Pendencias> findPendenciasEntities(int maxResults, int firstResult) {
        return findPendenciasEntities(false, maxResults, firstResult);
    }

    private List<Pendencias> findPendenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pendencias.class));
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

    public Pendencias findPendencias(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pendencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getPendenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pendencias> rt = cq.from(Pendencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
