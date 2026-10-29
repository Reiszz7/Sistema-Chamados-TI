package br.com.senai.chamados.repository;

import br.com.senai.chamados.config.Database;
import br.com.senai.chamados.model.Chamado;
import br.com.senai.chamados.model.Prioridade;
import br.com.senai.chamados.model.StatusChamado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChamadoRepository {

    public int inserir(String solicitante, String descricao, Prioridade prioridade)
            throws SQLException {
        String sql = "INSERT INTO chamados "
                + "(solicitante, descricao, prioridade, status) "
                + "VALUES (?, ?, ?, 'ABERTO')";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, solicitante);
            statement.setString(2, descricao);
            statement.setString(3, prioridade.name());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        throw new SQLException("Não foi possível obter o identificador do chamado.");
    }

    public List<Chamado> listarTodos() throws SQLException {
        String sql = "SELECT id, solicitante, descricao, prioridade, status, data_abertura "
                + "FROM chamados ORDER BY id";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            List<Chamado> chamados = new ArrayList<>();
            while (result.next()) {
                chamados.add(mapear(result));
            }
            return chamados;
        }
    }

    public Optional<Chamado> buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, solicitante, descricao, prioridade, status, data_abertura "
                + "FROM chamados WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(mapear(result));
                }
            }
        }

        return Optional.empty();
    }

    public boolean atualizarStatus(int id, StatusChamado novoStatus) throws SQLException {
        String sql = "UPDATE chamados SET status = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, novoStatus.name());
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Chamado> filtrar(Prioridade prioridade, StatusChamado status)
            throws SQLException {
        StringBuilder sql = new StringBuilder(
                "SELECT id, solicitante, descricao, prioridade, status, data_abertura "
                        + "FROM chamados WHERE 1 = 1"
        );
        List<String> parametros = new ArrayList<>();

        if (prioridade != null) {
            sql.append(" AND prioridade = ?");
            parametros.add(prioridade.name());
        }
        if (status != null) {
            sql.append(" AND status = ?");
            parametros.add(status.name());
        }
        sql.append(" ORDER BY id");

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setString(i + 1, parametros.get(i));
            }

            try (ResultSet result = statement.executeQuery()) {
                List<Chamado> chamados = new ArrayList<>();
                while (result.next()) {
                    chamados.add(mapear(result));
                }
                return chamados;
            }
        }
    }

    public Map<StatusChamado, Integer> resumoPorStatus() throws SQLException {
        String sql = "SELECT status, COUNT(*) AS quantidade FROM chamados GROUP BY status";
        Map<StatusChamado, Integer> resumo = new EnumMap<>(StatusChamado.class);

        for (StatusChamado status : StatusChamado.values()) {
            resumo.put(status, 0);
        }

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                StatusChamado status = StatusChamado.valueOf(result.getString("status"));
                resumo.put(status, result.getInt("quantidade"));
            }
        }

        return resumo;
    }

    private Chamado mapear(ResultSet result) throws SQLException {
        Timestamp timestamp = result.getTimestamp("data_abertura");

        return new Chamado(
                result.getInt("id"),
                result.getString("solicitante"),
                result.getString("descricao"),
                Prioridade.valueOf(result.getString("prioridade")),
                StatusChamado.valueOf(result.getString("status")),
                timestamp.toLocalDateTime()
        );
    }
}
