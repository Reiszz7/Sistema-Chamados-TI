# Modelo de dados

## Tabela `chamados`

| Campo | Tipo | Regra |
|---|---|---|
| `id` | `INT` | Chave primária e gerada automaticamente |
| `solicitante` | `VARCHAR(120)` | Obrigatório; mínimo de 3 caracteres úteis |
| `descricao` | `VARCHAR(500)` | Obrigatória; mínimo de 10 caracteres úteis |
| `prioridade` | `VARCHAR(10)` | BAIXA, MEDIA ou ALTA |
| `status` | `VARCHAR(20)` | ABERTO, EM_ATENDIMENTO ou CONCLUIDO |
| `data_abertura` | `TIMESTAMP` | Preenchida automaticamente pelo banco |

## Relacionamentos

O sistema utiliza uma tabela principal, pois esta versão não possui cadastro de usuários, setores ou equipamentos. Portanto, não há relacionamento externo necessário.

## Menu da aplicação

```text
1 - Cadastrar chamado
2 - Listar chamados
3 - Consultar chamado por identificador
4 - Atualizar status
5 - Filtrar chamados
6 - Exibir resumo por status
0 - Sair
```
