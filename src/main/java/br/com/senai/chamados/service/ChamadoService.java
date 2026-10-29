package br.com.senai.chamados.service;

import br.com.senai.chamados.model.Chamado;
import br.com.senai.chamados.model.Prioridade;
import br.com.senai.chamados.model.StatusChamado;
import br.com.senai.chamados.repository.ChamadoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public int cadastrar(String solicitante, String descricao, Prioridade prioridade)
            throws SQLException {
        String solicitanteLimpo = validarSolicitante(solicitante);
        String descricaoLimpa = validarDescricao(descricao);

        if (prioridade == null) {
            throw new IllegalArgumentException("A prioridade deve ser Baixa, Média ou Alta.");
        }

        return repository.inserir(solicitanteLimpo, descricaoLimpa, prioridade);
    }

    public List<Chamado> listar() throws SQLException {
        return repository.listarTodos();
    }

    public Optional<Chamado> consultar(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("O identificador deve ser positivo.");
        }
        return repository.buscarPorId(id);
    }

    public void atualizarStatus(int id, StatusChamado novoStatus) throws SQLException {
        Chamado chamado = consultar(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado."));

        if (novoStatus == null) {
            throw new IllegalArgumentException("Status inválido.");
        }

        boolean transicaoValida =
                chamado.getStatus() == StatusChamado.ABERTO
                        && novoStatus == StatusChamado.EM_ATENDIMENTO
                || chamado.getStatus() == StatusChamado.EM_ATENDIMENTO
                        && novoStatus == StatusChamado.CONCLUIDO;

        if (!transicaoValida) {
            throw new IllegalArgumentException(
                    "Transição inválida. Permitido: aberto -> em atendimento -> concluído."
            );
        }

        repository.atualizarStatus(id, novoStatus);
    }

    public List<Chamado> filtrar(Prioridade prioridade, StatusChamado status)
            throws SQLException {
        return repository.filtrar(prioridade, status);
    }

    public Map<StatusChamado, Integer> resumo() throws SQLException {
        return repository.resumoPorStatus();
    }

    private String validarSolicitante(String solicitante) {
        if (solicitante == null || solicitante.trim().length() < 3) {
            throw new IllegalArgumentException(
                    "O solicitante é obrigatório e deve possuir pelo menos 3 caracteres."
            );
        }
        return solicitante.trim();
    }

    private String validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().length() < 10) {
            throw new IllegalArgumentException(
                    "A descrição é obrigatória e deve possuir pelo menos 10 caracteres."
            );
        }
        return descricao.trim();
    }
}
