/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.dao;

import pizzaria.model.Pizza;
import pizzaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author bruno
 */
public class PizzaDAO {
    // Salva ou atualiza um cliente
    public void salvar(Pizza pizza) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pizza); // merge funciona para salvar (se id=null) ou atualizar (se id preenchido)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Busca um cliente pelo ID
    public Pizza buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            String jpql = "SELECT p FROM Pizza p " +
                          "JOIN FETCH p.PizzaIngredientes i " +
                          "WHERE p.id = :id";
            TypedQuery<Pizza> query = em.createQuery(jpql, Pizza.class);
            query.setParameter("id", id);
            
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // Lista todos os pizza
    public List<Pizza> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            String jpql = "SELECT p FROM Pizza p " +
                          "JOIN FETCH i.PizzaIngredientes i ";
            TypedQuery<Pizza> query = em.createQuery(jpql, Pizza.class);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Remove uma pizza pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pizza cliente = em.find(Pizza.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
