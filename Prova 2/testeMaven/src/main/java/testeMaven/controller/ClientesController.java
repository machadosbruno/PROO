/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeMaven.controller;

import java.util.List;
import testeMaven.dao.ClienteDAO; // Importa o DAO diretamente
import testeMaven.model.Tbcliente;
/**
 *
 * @author bruno
 */
public class ClientesController {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public void salvar(Tbcliente cliente) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (cliente.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        clienteDAO.salvar(cliente);
    }

    public Tbcliente buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public List<Tbcliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    public void remover(int id) {
        clienteDAO.remover(id);
    }
}
