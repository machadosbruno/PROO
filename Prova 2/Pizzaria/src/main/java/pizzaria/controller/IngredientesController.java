/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria.controller;

import java.util.List;
import pizzaria.dao.IngredientesDAO; // Importa o DAO diretamente
import pizzaria.model.Ingredientes;
/**
 *
 * @author bruno
 */
public class IngredientesController {
    private IngredientesDAO ingredienteDAO = new IngredientesDAO();

    public void salvar(Ingredientes ingrediente) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (ingrediente.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        ingredienteDAO.salvar(ingrediente);
    }

    public Ingredientes buscarPorId(int id) {
        return ingredienteDAO.buscarPorId(id);
    }

    public List<Ingredientes> listarTodos() {
        return ingredienteDAO.listarTodos();
    }

    public void remover(int id) {
        ingredienteDAO.remover(id);
    }
}
