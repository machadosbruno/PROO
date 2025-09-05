/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import pizzaria.dao.UsuarioDAO; // Importa o DAO diretamente
import pizzaria.model.Usuarios;
/**
 *
 * @author bruno
 */
public class UsuariosController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void salvar(Usuarios usuario) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (cliente.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        usuarioDAO.salvar(usuario);
    }

    public Usuarios buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public List<Usuarios> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    public void remover(int id) {
        usuarioDAO.remover(id);
    }
}
