package br.edu.umfg.controller;

import br.edu.umfg.model.Animal;
import br.edu.umfg.service.AnimalService;

import java.util.List;

public class AnimalController {

    private final AnimalService animalService = new AnimalService();

    public Animal salvar(Animal animal) {
        Animal animalSalvo = animalService.salvar(animal);
        System.out.println("✓ Animal cadastrado com sucesso! ID gerado: " + animalSalvo.getId());
        return animalSalvo;
    }

    public List<Animal> listar() {
        return animalService.listar();
    }

    public Animal buscarPorId(int id) {
        return animalService.buscarPorId(id);
    }

    public List<Animal> listarPorTutor(int idTutor) {
        return animalService.listarPorTutor(idTutor);
    }

    public void atualizar(Animal animal) {
        animalService.atualizar(animal);
        System.out.println("✓ Animal atualizado com sucesso!");
    }

    public void excluir(int id) {
        animalService.excluir(id);
        System.out.println("✓ Animal excluído com sucesso!");
    }
}