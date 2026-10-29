Sistema de Controle de Chamados de TI

Aplicação de terminal desenvolvida em Java com MySQL para cadastrar, consultar e acompanhar chamados de suporte.

## Regras implementadas

- Todo cadastro começa com status ABERTO.
- O solicitante deve ter pelo menos 3 caracteres após retirar espaços das extremidades.
- A descrição deve ter pelo menos 10 caracteres após retirar espaços das extremidades.
- As prioridades aceitas são BAIXA, MEDIA e ALTA.
- As transições permitidas são ABERTO -> EM_ATENDIMENTO -> CONCLUIDO.
- Chamados concluídos continuam disponíveis para consulta.
- Consultas com dados do usuário usam `PreparedStatement`.
- Os dados ficam no MySQL após o encerramento do programa.

## Demonstração solicitada

Cadastre os quatro chamados indicados no arquivo `docs/relatorio-testes.md`. Depois faça as alterações solicitadas e use a opção 6 para conferir o resumo: 2 abertos, 1 em atendimento e 1 concluído.

## Organização

- `src/main/java`: código-fonte Java;
- `sql`: script de criação do banco e da tabela;
- `docs/modelo-dados.md`: modelo de dados e menu;
- `docs/relatorio-testes.md`: plano e registro dos testes;
- `database.properties.example`: modelo de configuração sem senha real.
