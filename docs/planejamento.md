# Planejamento

## Requisitos funcionais

1. Cadastrar chamado com solicitante, descrição e prioridade.
2. Listar chamados com identificador, solicitante, prioridade, status e data de abertura.
3. Consultar um chamado pelo identificador.
4. Atualizar o status seguindo as transições permitidas.
5. Filtrar chamados por prioridade, status ou ambos.
6. Exibir a quantidade de chamados por status.
7. Persistir os registros no MySQL para que continuem disponíveis após fechar o programa.
8. Informar mensagens claras para entradas inválidas e identificadores inexistentes.

## Regras principais

- Todo chamado é criado como ABERTO.
- O solicitante precisa ter pelo menos 3 caracteres úteis.
- A descrição precisa ter pelo menos 10 caracteres úteis.
- As prioridades aceitas são BAIXA, MEDIA e ALTA.
- As transições aceitas são ABERTO -> EM_ATENDIMENTO -> CONCLUIDO.
- Chamados concluídos continuam disponíveis para consulta.

## Menu planejado

```text
===== SISTEMA DE CHAMADOS DE TI =====
1 - Cadastrar chamado
2 - Listar chamados
3 - Consultar chamado por identificador
4 - Atualizar status
5 - Filtrar chamados
6 - Exibir resumo por status
0 - Sair
```
