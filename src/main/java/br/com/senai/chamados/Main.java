package br.com.senai.chamados;

import br.com.senai.chamados.repository.ChamadoRepository;
import br.com.senai.chamados.service.ChamadoService;
import br.com.senai.chamados.ui.ConsoleUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChamadoRepository repository = new ChamadoRepository();
        ChamadoService service = new ChamadoService(repository);

        try (Scanner scanner = new Scanner(System.in)) {
            new ConsoleUI(scanner, service).iniciar();
        } catch (Exception e) {
            System.out.println("Não foi possível iniciar o sistema: " + e.getMessage());
        }
    }
}
