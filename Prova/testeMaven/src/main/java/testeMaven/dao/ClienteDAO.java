/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeMaven.dao;

import testeMaven.model.Tbcliente;
import testeMaven.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ClienteDAO {
    // Salva ou atualiza um cliente
    public void salvar(Tbcliente cliente) {
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
    public Tbcliente buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Tbcliente.class, id);
        } finally {
            em.close();
        }
    }

    // Lista todos os clientes
    public List<Tbcliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            return em.createQuery("FROM Tbcliente", Tbcliente.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um cliente pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Tbcliente cliente = em.find(Tbcliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
