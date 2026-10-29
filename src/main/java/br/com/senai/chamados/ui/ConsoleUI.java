package br.com.senai.chamados.ui;

import br.com.senai.chamados.model.Chamado;
import br.com.senai.chamados.model.Prioridade;
import br.com.senai.chamados.model.StatusChamado;
import br.com.senai.chamados.service.ChamadoService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleUI {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Scanner scanner;
    private final ChamadoService service;

    public ConsoleUI(Scanner scanner, ChamadoService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar(service.listar());
                    case 3 -> consultar();
                    case 4 -> atualizarStatus();
                    case 5 -> filtrar();
                    case 6 -> exibirResumo();
                    case 0 -> continuar = false;
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Operação não realizada: " + e.getMessage());
            }
        }

        System.out.println("Programa encerrado.");
    }

    private void exibirMenu() {
        System.out.println("\n===== SISTEMA DE CHAMADOS DE TI =====");
        System.out.println("1 - Cadastrar chamado");
        System.out.println("2 - Listar chamados");
        System.out.println("3 - Consultar chamado por identificador");
        System.out.println("4 - Atualizar status");
        System.out.println("5 - Filtrar chamados");
        System.out.println("6 - Exibir resumo por status");
        System.out.println("0 - Sair");
    }

    private void cadastrar() throws Exception {
        System.out.println("\n--- Cadastro de chamado ---");
        String solicitante = lerTexto("Solicitante: ");
        String descricao = lerTexto("Descrição: ");
        Prioridade prioridade = lerPrioridade();

        int id = service.cadastrar(solicitante, descricao, prioridade);
        System.out.println("Chamado cadastrado com sucesso. Identificador: " + id);
        System.out.println("Status inicial: ABERTO");
    }

    private void consultar() throws Exception {
        int id = lerInteiro("Identificador do chamado: ");
        Optional<Chamado> chamado = service.consultar(id);

        if (chamado.isEmpty()) {
            System.out.println("Chamado não encontrado.");
            return;
        }

        exibirChamado(chamado.get());
    }

    private void atualizarStatus() throws Exception {
        int id = lerInteiro("Identificador do chamado: ");
        StatusChamado status = lerNovoStatus();

        service.atualizarStatus(id, status);
        System.out.println("Status atualizado com sucesso.");
    }

    private void filtrar() throws Exception {
        System.out.println("\n--- Filtros ---");
        Prioridade prioridade = lerPrioridadeOpcional();
        StatusChamado status = lerStatusOpcional();

        List<Chamado> chamados = service.filtrar(prioridade, status);
        listar(chamados);
    }

    private void exibirResumo() throws Exception {
        Map<StatusChamado, Integer> resumo = service.resumo();

        System.out.println("\n--- Resumo por status ---");
        System.out.println("Abertos: " + resumo.get(StatusChamado.ABERTO));
        System.out.println("Em atendimento: " + resumo.get(StatusChamado.EM_ATENDIMENTO));
        System.out.println("Concluídos: " + resumo.get(StatusChamado.CONCLUIDO));
    }

    private void listar(List<Chamado> chamados) {
        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado encontrado.");
            return;
        }

        System.out.println("\n--- Chamados ---");
        for (Chamado chamado : chamados) {
            exibirChamado(chamado);
        }
    }

    private void exibirChamado(Chamado chamado) {
        System.out.println("ID: " + chamado.getId());
        System.out.println("Solicitante: " + chamado.getSolicitante());
        System.out.println("Descrição: " + chamado.getDescricao());
        System.out.println("Prioridade: " + formatar(chamado.getPrioridade().name()));
        System.out.println("Status: " + formatar(chamado.getStatus().name()));
        System.out.println("Data de abertura: "
                + chamado.getDataAbertura().format(FORMATO_DATA));
        System.out.println("----------------------------------------");
    }

    private Prioridade lerPrioridade() {
        while (true) {
            System.out.println("1 - Baixa\n2 - Média\n3 - Alta");
            int opcao = lerInteiro("Prioridade: ");

            switch (opcao) {
                case 1 -> { return Prioridade.BAIXA; }
                case 2 -> { return Prioridade.MEDIA; }
                case 3 -> { return Prioridade.ALTA; }
                default -> System.out.println("Prioridade não permitida.");
            }
        }
    }

    private Prioridade lerPrioridadeOpcional() {
        while (true) {
            System.out.println("0 - Todas\n1 - Baixa\n2 - Média\n3 - Alta");
            int opcao = lerInteiro("Prioridade: ");

            switch (opcao) {
                case 0 -> { return null; }
                case 1 -> { return Prioridade.BAIXA; }
                case 2 -> { return Prioridade.MEDIA; }
                case 3 -> { return Prioridade.ALTA; }
                default -> System.out.println("Prioridade não permitida.");
            }
        }
    }

    private StatusChamado lerNovoStatus() {
        while (true) {
            System.out.println("1 - Em atendimento\n2 - Concluído");
            int opcao = lerInteiro("Novo status: ");

            switch (opcao) {
                case 1 -> { return StatusChamado.EM_ATENDIMENTO; }
                case 2 -> { return StatusChamado.CONCLUIDO; }
                default -> System.out.println("Status não permitido.");
            }
        }
    }

    private StatusChamado lerStatusOpcional() {
        while (true) {
            System.out.println("0 - Todos\n1 - Aberto\n2 - Em atendimento\n3 - Concluído");
            int opcao = lerInteiro("Status: ");

            switch (opcao) {
                case 0 -> { return null; }
                case 1 -> { return StatusChamado.ABERTO; }
                case 2 -> { return StatusChamado.EM_ATENDIMENTO; }
                case 3 -> { return StatusChamado.CONCLUIDO; }
                default -> System.out.println("Status não permitido.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private String formatar(String valor) {
        return valor.replace('_', ' ');
    }
}
