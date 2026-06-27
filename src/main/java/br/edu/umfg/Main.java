package br.edu.umfg;

import br.edu.umfg.controller.AnimalController;
import br.edu.umfg.controller.ConsultaController;
import br.edu.umfg.controller.TutorController;
import br.edu.umfg.model.Animal;
import br.edu.umfg.model.Consulta;
import br.edu.umfg.model.Tutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String LINHA = "==================================================";

    public static void main(String[] args) {
        TutorController tutorController = new TutorController();
        AnimalController animalController = new AnimalController();
        ConsultaController consultaController = new ConsultaController();

        System.out.println(LINHA);
        System.out.println("CENARIO 1 - CLINICA VETERINARIA");
        System.out.println("Simulacao do fluxo: tutor -> animal -> consulta");
        System.out.println(LINHA);

        Tutor tutor = cadastrarTutor(tutorController);
        Animal animal = cadastrarAnimal(animalController, tutor);
        Consulta consulta = registrarConsulta(consultaController, animal);

        System.out.println("\nRESUMO DOS REGISTROS GERADOS");
        System.out.println(LINHA);
        System.out.println("Tutor: " + tutor.getId() + " - " + tutor.getNome());
        System.out.println("Animal: " + animal.getId() + " - " + animal.getNome());
        System.out.println("Consulta: " + consulta.getId() + " - " + consulta.getMotivo());

        System.out.println("\nANIMAIS DO TUTOR");
        System.out.println(LINHA);
        exibirAnimais(animalController.listarPorTutor(tutor.getId()));

        System.out.println("\nHISTORICO DE CONSULTAS DO ANIMAL");
        System.out.println(LINHA);
        exibirConsultas(consultaController.listarPorAnimal(animal.getId()));

        System.out.println("\nLISTAGEM GERAL DE TUTORES");
        System.out.println(LINHA);
        exibirTutores(tutorController.listar());

        System.out.println("\nLISTAGEM GERAL DE ANIMAIS");
        System.out.println(LINHA);
        exibirAnimais(animalController.listar());

        System.out.println("\nLISTAGEM GERAL DE CONSULTAS");
        System.out.println(LINHA);
        exibirConsultas(consultaController.listar());

        System.out.println("\nExecucao finalizada.");
    }

    private static Tutor cadastrarTutor(TutorController tutorController) {
        System.out.println("\n1. Cadastro do tutor");
        Tutor tutor = new Tutor("Marina Lopes", "Rua das Acacias, 150", "(44) 99999-0001");
        return tutorController.salvar(tutor);
    }

    private static Animal cadastrarAnimal(AnimalController animalController, Tutor tutor) {
        System.out.println("\n2. Cadastro do animal vinculado ao tutor");
        Animal animal = new Animal("Thor", "Cachorro", "Labrador", tutor.getId());
        return animalController.salvar(animal);
    }

    private static Consulta registrarConsulta(ConsultaController consultaController, Animal animal) {
        System.out.println("\n3. Registro da consulta vinculada ao animal");
        Consulta consulta = new Consulta(
                animal.getId(),
                LocalDate.now(),
                "Vacinacao anual",
                new BigDecimal("120.00")
        );
        return consultaController.salvar(consulta);
    }

    private static void exibirTutores(List<Tutor> tutores) {
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor encontrado.");
            return;
        }

        for (Tutor tutor : tutores) {
            System.out.println(
                    "ID: " + tutor.getId()
                            + " | Nome: " + tutor.getNome()
                            + " | Endereco: " + tutor.getEndereco()
                            + " | Telefone: " + tutor.getTelefone()
            );
        }
    }

    private static void exibirAnimais(List<Animal> animais) {
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
            return;
        }

        for (Animal animal : animais) {
            System.out.println(
                    "ID: " + animal.getId()
                            + " | Nome: " + animal.getNome()
                            + " | Especie: " + animal.getEspecie()
                            + " | Raca: " + animal.getRaca()
                            + " | ID Tutor: " + animal.getIdTutor()
                            + " | Tutor: " + animal.getNomeTutor()
            );
        }
    }

    private static void exibirConsultas(List<Consulta> consultas) {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        }

        for (Consulta consulta : consultas) {
            System.out.println(
                    "ID: " + consulta.getId()
                            + " | ID Animal: " + consulta.getIdAnimal()
                            + " | Data: " + consulta.getData()
                            + " | Motivo: " + consulta.getMotivo()
                            + " | Valor: R$ " + consulta.getValor()
            );
        }
    }
}
