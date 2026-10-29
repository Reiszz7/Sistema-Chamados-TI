# Sistema de Controle de Chamados de TI

Aplicação de terminal desenvolvida em Java com MySQL para cadastrar, consultar e acompanhar chamados de suporte.

## Requisitos

- Java 17 ou superior;
- IntelliJ IDEA;
- MySQL 8 ou MariaDB;
- Maven, caso o IntelliJ não faça o download das dependências automaticamente.

## Configuração do banco

1. Abra o MySQL Workbench ou o terminal do Laragon.
2. Execute todo o arquivo `sql/chamados.sql`.
3. Na raiz do projeto, faça uma cópia de `database.properties.example` com o nome `database.properties`.
4. Confira usuário, senha e porta do MySQL. Para o Laragon padrão:

```properties
db.url=jdbc:mysql://localhost:3306/chamados_ti?serverTimezone=America/Sao_Paulo&useSSL=false&allowPublicKeyRetrieval=true
db.user=root
db.password=
```

O arquivo `database.properties` não deve ser enviado ao GitHub, pois está no `.gitignore`.

## Execução no IntelliJ

1. Abra a pasta `Sistema-Chamados-TI` como projeto Maven.
2. Aguarde o IntelliJ carregar o `pom.xml` e baixar o conector do MySQL.
3. Abra `src/main/java/br/com/senai/chamados/Main.java`.
4. Execute pelo botão verde ao lado do método `main`.
5. Use o menu do terminal para cadastrar e consultar chamados.

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
