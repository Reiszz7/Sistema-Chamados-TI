# Relatório de testes

Os testes abaixo devem ser executados com o MySQL ligado e com o banco `chamados_ti` criado pelo script `sql/chamados.sql`.

| Teste | Resultado esperado | Resultado obtido | Situação | Evidência ou correção |
|---|---|---|---|---|
| T01 | Cadastro válido gera ID, data e status ABERTO. | A preencher durante a execução. | A preencher | Captura da tela ou observação. |
| T02 | Solicitante vazio ou somente espaços é recusado. | A preencher durante a execução. | A preencher | Mensagem de validação. |
| T03 | Descrição com menos de 10 caracteres é recusada. | A preencher durante a execução. | A preencher | Mensagem de validação. |
| T04 | Prioridade fora das opções é recusada. | A preencher durante a execução. | A preencher | Mensagem de validação. |
| T05 | ID inexistente mostra “Chamado não encontrado”. | A preencher durante a execução. | A preencher | Mensagem exibida. |
| T06 | ABERTO passa para EM_ATENDIMENTO. | A preencher durante a execução. | A preencher | Listagem antes e depois. |
| T07 | ABERTO não pode ir diretamente para CONCLUIDO. | A preencher durante a execução. | A preencher | Mensagem de transição inválida. |
| T08 | EM_ATENDIMENTO passa para CONCLUIDO. | A preencher durante a execução. | A preencher | Listagem antes e depois. |
| T09 | Após fechar e abrir, os registros continuam no banco. | A preencher durante a execução. | A preencher | Listagem após reabertura. |
| T10 | Filtros e resumo apresentam dados correspondentes. | A preencher durante a execução. | A preencher | Tela do filtro e do resumo. |

## Massa de demonstração

Cadastre os quatro registros solicitados na atividade:

- Ana Souza — “Computador não inicia ao pressionar o botão.” — ALTA;
- Bruno Lima — “Impressora do setor não imprime documentos.” — MEDIA;
- Carla Santos — “Solicitação de instalação de editor de texto.” — BAIXA;
- Diego Alves — “Sistema de estoque apresenta erro no acesso.” — ALTA.

Depois:

1. Coloque Ana em atendimento.
2. Coloque Bruno em atendimento e depois conclua o chamado.
3. Mantenha Carla e Diego abertos.
4. Filtre por prioridade ALTA.
5. Exiba o resumo.

Resumo esperado: 2 abertos, 1 em atendimento e 1 concluído.
