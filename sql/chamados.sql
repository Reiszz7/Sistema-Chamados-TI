CREATE DATABASE IF NOT EXISTS chamados_ti
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE chamados_ti;

CREATE TABLE IF NOT EXISTS chamados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    solicitante VARCHAR(120) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    prioridade VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTO',
    data_abertura TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_chamado_prioridade
        CHECK (prioridade IN ('BAIXA', 'MEDIA', 'ALTA')),
    CONSTRAINT ck_chamado_status
        CHECK (status IN ('ABERTO', 'EM_ATENDIMENTO', 'CONCLUIDO'))
);

CREATE INDEX idx_chamados_prioridade ON chamados (prioridade);
CREATE INDEX idx_chamados_status ON chamados (status);
