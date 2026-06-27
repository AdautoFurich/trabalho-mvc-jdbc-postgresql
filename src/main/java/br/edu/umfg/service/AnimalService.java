package br.edu.umfg.service;

import br.edu.umfg.model.Animal;
import br.edu.umfg.repository.AnimalRepository;
import br.edu.umfg.repository.TutorRepository;

import java.util.List;

public class AnimalService {

    private final AnimalRepository animalRepository = new AnimalRepository();
    private final TutorRepository tutorRepository = new TutorRepository();

    public Animal salvar(Animal animal) {
        if (animal.getNome() == null || animal.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do animal é obrigatório.");
        }

        if (animal.getEspecie() == null || animal.getEspecie().isBlank()) {
            throw new IllegalArgumentException("A espécie do animal é obrigatória.");
        }

        if (animal.getRaca() == null || animal.getRaca().isBlank()) {
            throw new IllegalArgumentException("A raça do animal é obrigatória.");
        }

        if (tutorRepository.buscarPorId(animal.getIdTutor()) == null) {
            throw new IllegalArgumentException("Não é possível cadastrar animal para tutor inexistente.");
        }

        return animalRepository.salvar(animal);
    }

    public List<Animal> listar() {
        return animalRepository.listar();
    }

    public Animal buscarPorId(int id) {
        return animalRepository.buscarPorId(id);
    }

    public List<Animal> listarPorTutor(int idTutor) {
        return animalRepository.listarPorTutor(idTutor);
    }

    public void atualizar(Animal animal) {
        if (animal.getId() <= 0) {
            throw new IllegalArgumentException("O ID do animal é obrigatório para atualização.");
        }

        if (animalRepository.buscarPorId(animal.getId()) == null) {
            throw new IllegalArgumentException("Animal não encontrado para atualização.");
        }

        if (animal.getNome() == null || animal.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do animal é obrigatório.");
        }

        if (animal.getEspecie() == null || animal.getEspecie().isBlank()) {
            throw new IllegalArgumentException("A espécie do animal é obrigatória.");
        }

        if (animal.getRaca() == null || animal.getRaca().isBlank()) {
            throw new IllegalArgumentException("A raça do animal é obrigatória.");
        }

        if (tutorRepository.buscarPorId(animal.getIdTutor()) == null) {
            throw new IllegalArgumentException("Não é possível vincular animal a tutor inexistente.");
        }

        animalRepository.atualizar(animal);
    }

    public void excluir(int id) {
        animalRepository.excluir(id);
    }
}
