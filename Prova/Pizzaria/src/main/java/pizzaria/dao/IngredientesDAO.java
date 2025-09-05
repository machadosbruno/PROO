/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.dao;

import pizzaria.model.Ingredientes;
import pizzaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author bruno
 */
public class IngredientesDAO {
    // Salva ou atualiza um ingrediente
    public void salvar(Ingredientes ingrediente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ingrediente); // merge funciona para salvar (se id=null) ou atualizar (se id preenchido)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Busca um ingrediente pelo ID
    public Ingredientes buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Ingredientes.class, id);
        } finally {
            em.close();
        }
    }

    // Lista todos os ingredientes
    public List<Ingredientes> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            return em.createQuery("FROM Ingredientes", Ingredientes.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um ingrediente pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Ingredientes ingrediente = em.find(Ingredientes.class, id);
            if (ingrediente != null) {
                em.remove(ingrediente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
