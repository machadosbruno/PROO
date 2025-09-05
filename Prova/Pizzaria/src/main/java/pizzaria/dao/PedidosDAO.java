/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.dao;

import pizzaria.model.Pedido;
import pizzaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author bruno
 */
public class PedidosDAO {
    // Salva ou atualiza um cliente
    public void salvar(Pedido animal) {
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
    public Pedido buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT a FROM Pedido a WHERE a.id = :id";
            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // Lista todos os clientes
    public List<Pedido> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            return em.createQuery("FROM Pedido", Pedido.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um cliente pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido animal = em.find(Pedido.class, id);
            if (animal != null) {
                em.remove(animal);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
