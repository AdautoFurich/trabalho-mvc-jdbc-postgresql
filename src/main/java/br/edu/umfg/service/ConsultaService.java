package br.edu.umfg.service;

import br.edu.umfg.model.Consulta;
import br.edu.umfg.repository.AnimalRepository;
import br.edu.umfg.repository.ConsultaRepository;

import java.math.BigDecimal;
import java.util.List;

public class ConsultaService {

    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final AnimalRepository animalRepository = new AnimalRepository();

    public Consulta salvar(Consulta consulta) {
        if (animalRepository.buscarPorId(consulta.getIdAnimal()) == null) {
            throw new IllegalArgumentException("Não é possível registrar consulta para animal inexistente.");
        }

        if (consulta.getData() == null) {
            throw new IllegalArgumentException("A data da consulta é obrigatória.");
        }

        if (consulta.getMotivo() == null || consulta.getMotivo().isBlank()) {
            throw new IllegalArgumentException("O motivo da consulta é obrigatório.");
        }

        if (consulta.getValor() == null || consulta.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }

        return consultaRepository.salvar(consulta);
    }

    public List<Consulta> listar() {
        return consultaRepository.listar();
    }

    public Consulta buscarPorId(int id) {
        return consultaRepository.buscarPorId(id);
    }

    public List<Consulta> listarPorAnimal(int idAnimal) {
        return consultaRepository.listarPorAnimal(idAnimal);
    }

    public void atualizar(Consulta consulta) {
        if (consulta.getId() <= 0) {
            throw new IllegalArgumentException("O ID da consulta é obrigatório para atualização.");
        }

        if (consultaRepository.buscarPorId(consulta.getId()) == null) {
            throw new IllegalArgumentException("Consulta não encontrada para atualização.");
        }

        if (animalRepository.buscarPorId(consulta.getIdAnimal()) == null) {
            throw new IllegalArgumentException("Não é possível registrar consulta para animal inexistente.");
        }

        if (consulta.getData() == null) {
            throw new IllegalArgumentException("A data da consulta é obrigatória.");
        }

        if (consulta.getMotivo() == null || consulta.getMotivo().isBlank()) {
            throw new IllegalArgumentException("O motivo da consulta é obrigatório.");
        }

        if (consulta.getValor() == null || consulta.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor da consulta não pode ser negativo.");
        }

        consultaRepository.atualizar(consulta);
    }

    public void excluir(int id) {
        consultaRepository.excluir(id);
    }
}
