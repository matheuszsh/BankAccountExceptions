package application;

import model.entities.BankAccount;
import model.exceptions.DomainException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BusinessProgram {
    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);

        try {
            System.out.println("***** Dados Conta Bancária *****");

            System.out.print("Número da Conta:");
            int numero = get.nextInt();
            get.nextLine();

            System.out.print("Titular da Conta:");
            String nome = get.nextLine();

            System.out.print("Depósito inicial:");
            Double saldo = get.nextDouble();
            get.nextLine();

            System.out.print("Defina um limite para o saque(até R$ 10.000,00):");
            Double limiteSaque = get.nextDouble();
            get.nextLine();

            BankAccount account = new BankAccount(numero, nome, saldo, limiteSaque);

            Boolean operacao = true;
            while (operacao == true){
                System.out.println(account);

                int opcao;
                do {
                    System.out.println("Operações:\n\n(1) saque\n(2) Depósito\n(0) sair\n\n>>:");
                    opcao = get.nextInt();
                    get.nextLine();
                }while (!(opcao >= 0 && opcao <=2));

                switch (opcao){
                    case 1:
                        System.out.println("Saldo: R$" + String.format("%.2f", account.getSaldo()));
                        System.out.print("Valor do saque:");
                        saldo = get.nextDouble();
                        get.nextLine();

                        account.saque(saldo);
                        break;
                    case 2:
                        System.out.println("Saldo: R$" + String.format("%.2f", account.getSaldo()));
                        System.out.print("Valor do depósito:");
                        saldo = get.nextDouble();
                        get.nextLine();

                        account.deposito(saldo);
                        break;
                    default:
                        System.out.println("Programa encerrado.");
                        System.exit(0);
                        break;
                }
                System.out.println("Deseja continuar(s)sim ou (n)não:?");
                char exitOption = get.next().charAt(0);
                if (exitOption == 's' || exitOption == 'n'){
                    if (exitOption == 's'){
                        operacao = true;
                    }
                    else {
                        operacao = false;
                    }
                }
                else {
                    System.out.println("Erro: opção inválida.");
                    operacao = true;
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Erro: Entrada inválida.");
        }
        catch (DomainException e){
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e){
            System.out.println("Erro: Não catalogado(RuntimeException).");
        }
    }
}
