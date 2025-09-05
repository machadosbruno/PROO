/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.dao;

import pizzaria.model.Usuarios;
import pizzaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author bruno
 */
public class UsuarioDAO {
    // Salva ou atualiza um usuario
    public void salvar(Usuarios usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario); // merge funciona para salvar (se id=null) ou atualizar (se id preenchido)
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Busca um usuario pelo ID
    public Usuarios buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    // Lista todos os usuarios
    public List<Usuarios> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            // JPQL (Java Persistence Query Language)
            return em.createQuery("FROM Usuarios", Usuarios.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Remove um usuario pelo ID
    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuarios usuario = em.find(Usuarios.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
