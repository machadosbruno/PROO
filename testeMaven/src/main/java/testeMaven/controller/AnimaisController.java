/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeMaven.controller;

import java.util.List;
import testeMaven.dao.AnimaisDAO; // Importa o DAO diretamente
import testeMaven.model.Tbanimal;
/**
 *
 * @author bruno
 */
public class AnimaisController {
    private AnimaisDAO animalDAO = new AnimaisDAO();

    public void salvar(Tbanimal animal) {
        // Futuramente, aqui podem entrar regras de negócio.
        // Ex: if (animal.getEmail().contains("@invalid.com")) { throw new Exception(...) }
        animalDAO.salvar(animal);
    }

    public Tbanimal buscarPorId(int id) {
        return animalDAO.buscarPorId(id);
    }

    public List<Tbanimal> listarTodos() {
        return animalDAO.listarTodos();
    }

    public void remover(int id) {
        animalDAO.remover(id);
    }
}
