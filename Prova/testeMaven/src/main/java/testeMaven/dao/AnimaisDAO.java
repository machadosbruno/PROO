/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeMaven.dao;

import testeMaven.model.Tbanimal;
import testeMaven.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author bruno
 */
public class AnimaisDAO {
    // Salva ou atualiza um cliente
    public void salvar(Tbanimal animal) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(animal); // merge funciona para salvar (se id=null) ou atualizar (se id preenchido)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Busca um cliente pelo ID
    public Tbanimal buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT a FROM Tbanimal a JOIN FETCH a.tbcliente WHERE a.id = :id";
            TypedQuery<Tbanimal> query = em.createQuery(jpql, Tbanimal.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // Lista todos os clientes
    public List<Tbanimal> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            String jpql = "SELECT a FROM Tbanimal a JOIN FETCH a.tbcliente";
            return em.createQuery(jpql, Tbanimal.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um cliente pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Tbanimal animal = em.find(Tbanimal.class, id);
            if (animal != null) {
                em.remove(animal);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
