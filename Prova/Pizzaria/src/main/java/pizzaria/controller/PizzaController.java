/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import pizzaria.dao.PizzaDAO; // Importa o DAO diretamente
import pizzaria.model.Pizza;
/**
 *
 * @author bruno
 */
public class PizzaController {
    private PizzaDAO pizzaDAO = new PizzaDAO();

    public void salvar(Pizza pizza) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (pizza.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        pizzaDAO.salvar(pizza);
    }

    public Pizza buscarPorId(int id) {
        return pizzaDAO.buscarPorId(id);
    }

    public List<Pizza> listarTodos() {
        return pizzaDAO.listarTodos();
    }

    public void remover(int id) {
        pizzaDAO.remover(id);
    }
}
