package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n");
                
                
                if(opcao == 1) cadastrar(console);
                else if(opcao == 2) listar(console);
                else if(opcao == 3) alterar(console);
                else if(opcao == 4) deletar(console);
                
            } while(opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	}
	
	private static void alterar(Scanner console) throws IOException, SQLException {
		Aluno a = new Aluno();
		AlunoJDBC acao = new AlunoJDBC();
		
		System.out.print("\n*** Cadastrar Aluno ***\n\r");
		
		System.out.println("Nome: ");
		a.setNome(console.nextLine());
		System.out.println("Sexo: ");
		a.setSexo(console.nextLine());
		System.out.println("Data de nascimento: ");
		a.setDt_nasc( new Date(console.nextLine()) );
		System.out.println("ID: ");
		a.setId(console.nextInt());
		
		acao.alterar(a);
		System.out.println("\n\n\n\n");
		
	}

	private static void listar(Scanner console) throws IOException, SQLException {
		AlunoJDBC acao = new AlunoJDBC();
		
		System.out.println("\n*** Listagem de Alunos ***\n\r");
		for (Aluno aluno : acao.listar()) {
			System.out.println("Nome: " + aluno.getNome());
			System.out.println("Sexo: " + aluno.getSexo());
			System.out.println("Nascimento: " + aluno.getDt_nasc());
			System.out.println();
		}
		System.out.println("-=-=-=-=-=-");
		
	}
	
	private static void deletar(Scanner console) throws IOException, SQLException {
		AlunoJDBC acao = new AlunoJDBC();
		
		System.out.println("\n*** Deletar Registro ***\n\r");
		System.out.println("ID: ");
		int id = console.nextInt();
		acao.apagar(id);
		System.out.println("-=-=-=-=-=-");
		
	}

	private static void cadastrar(Scanner console) throws IOException {
		Aluno a = new Aluno();
		AlunoJDBC acao = new AlunoJDBC();
		
		System.out.print("\n*** Cadastrar Aluno ***\n\r");
		System.out.print("Nome: ");
		a.setNome(console.nextLine());
		System.out.print("Sexo: ");
		a.setSexo(console.nextLine());
		System.out.print("Data de nascimento: ");
		a.setDt_nasc( new Date(console.nextLine()) );
		
		acao.salvar(a);
		System.out.println("\n\n\n\n");
	}
}
