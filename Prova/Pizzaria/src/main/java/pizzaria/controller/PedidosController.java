/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import pizzaria.dao.PedidosDAO; // Importa o DAO diretamente
import pizzaria.model.Pedido;
/**
 *
 * @author bruno
 */
public class PedidosController {
    private PedidosDAO pedidoDAO = new PedidosDAO();

    public void salvar(Pedido pedido) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (pedido.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        pedidoDAO.salvar(pedido);
    }

    public Pedido buscarPorId(int id) {
        return pedidoDAO.buscarPorId(id);
    }

    public List<Pedido> listarTodos() {
        return pedidoDAO.listarTodos();
    }

    public void remover(int id) {
        pedidoDAO.remover(id);
    }
}
