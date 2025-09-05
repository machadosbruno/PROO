/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.dao;

import pizzaria.model.Clientes;
import pizzaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ClienteDAO {
    // Salva ou atualiza um cliente
    public void salvar(Clientes cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente); // merge funciona para salvar (se id=null) ou atualizar (se id preenchido)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Busca um cliente pelo ID
    public Clientes buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    // Lista todos os clientes
    public List<Clientes> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            return em.createQuery("FROM Clientes", Clientes.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um cliente pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Clientes cliente = em.find(Clientes.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
