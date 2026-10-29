package br.com.senai.chamados.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {

    private static final Properties CONFIG = carregarConfiguracao();

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        String url = CONFIG.getProperty("db.url");
        String user = CONFIG.getProperty("db.user");
        String password = CONFIG.getProperty("db.password", "");

        if (url == null || user == null) {
            throw new IllegalStateException(
                    "Configure o arquivo database.properties antes de executar o programa."
            );
        }

        return DriverManager.getConnection(url, user, password);
    }

    private static Properties carregarConfiguracao() {
        Properties properties = new Properties();
        Path caminho = Path.of("database.properties");

        if (!Files.exists(caminho)) {
            return properties;
        }

        try (InputStream input = Files.newInputStream(caminho)) {
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Não foi possível ler database.properties: " + e.getMessage(), e
            );
        }
    }
}
