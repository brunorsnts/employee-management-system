package application;
import entities.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        // Chamando a classe Scanner e alterando a localização para o separador de números
        // decimais ser o padrão americano.
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Criando uma lista do tipo classe: Funcionario.
        List<Funcionario> lista = new ArrayList<>();

        System.out.print("Quantos funcionários serão registrados? ");
        int n = sc.nextInt();

        // Lendo os dados de cada funcionário.
        for(int i=0; i<n; i++) {
            System.out.println();
            System.out.println("Funcionário #" + (i+1) + ":");

            System.out.print("Id: ");
            int id = sc.nextInt();
            while (temId(lista, id)) {
                System.out.print("Id já registrado. Tente outro: ");
                id = sc.nextInt();
            }

            sc.nextLine();
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Salário: ");
            double salario = sc.nextDouble();
            lista.add(new Funcionario(id, nome, salario)); // Criando um objeto do tipo Funcionário e adicionando a lista.
        }

        // Leitura de id de um funcionário para reajuste salarial sobre uma porcentagem.
        System.out.println();
        System.out.print("Entre com o id do funcionário que terá um reajuste salarial: ");
        int id = sc.nextInt();
        Funcionario emp = lista.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (emp == null) {
            System.out.println("Este Id não existe!");
        }

        else {
            System.out.print("Digite a porcentagem para reajuste salarial: ");
            double porcentagem = sc.nextDouble();
            emp.reajusteSalarial(porcentagem);
        }

        // Exibe todos os objetos do tipo Funcionário adicionados na lista.
        System.out.println();
        System.out.println("Lista de funcionários:");
        for (Funcionario obj : lista) {
            System.out.println(obj);
        }

        System.out.print(lista);

        sc.close();
    }

    public static boolean temId(List<Funcionario> lista, int id) {
        Funcionario emp = lista.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}
