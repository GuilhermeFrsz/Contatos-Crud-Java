package contato.aplicacao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import contato.DAO.ContatoDAO;
import contato.model.Contato;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		
		char op;
		String nome;
		int idade;
		char conf;
		
		 System.out.println("Escolha, o que vc quer fazer? ");
		 System.out.println(" 1 - Cadastrar  \n 2- Contatos salvos \n 3 - Atualizar \n 4 - Deletar  "
		 		+ "\n ----------------------");
		 op = (char)System.in.read();
		
		 if ((op == '1')) {
			  Scanner ler = new Scanner(System.in);
				Contato contato = new Contato();
				
				System.out.printf("Informe um nome:\n");
				ler.nextLine();
				nome = ler.nextLine();
				
				System.out.printf("Informe uma idade:\n");
				idade = ler.nextInt(); // Leia a idade e armazene na variável "idade"

				contato.setNome(nome);
				contato.setIdade(idade);
				contato.setDataCadastro(new Date());
				
				boolean cadastroConfirmado = false;
				while (!cadastroConfirmado) {
				    System.out.println("Confirmar? \n S - Sim    N - Não \n -----------  ");
				    char opn = (char) System.in.read();
				    
				    if (opn == 's' || opn == 'S') {
				        contatoDao.save(contato);
				        ler.close();
				        cadastroConfirmado = true; // O cadastro foi confirmado, saia do loop
				    } else if (opn == 'n' || opn == 'N') {
				        System.out.println("Cadastro cancelado.");
				        cadastroConfirmado = true; // O usuário cancelou, saia do loop
				    } else {
				        System.out.println("Opção inválida. Tente novamente.");
				    }
				}
				 
				 
				
		 } if (op == '2') {
			 System.out.println("Listagem de contatos salvos: ");
			    List<Contato> contatos = contatoDao.getContatos();
			    
			    for (int i = 0; i < contatos.size(); i++) {
			        Contato contato = contatos.get(i);
			        System.out.println((i + 1) + ". Nome: " + contato.getNome() + ", Idade: " + contato.getIdade());
			    }
			 
			 
				
		  } if  ( op == '3'){
			  Scanner ler = new Scanner(System.in);
			  
			  System.out.println("Selecione o contato a ser atualizado:");
			    List<Contato> contatos = contatoDao.getContatos();
			    
			    for (int i = 0; i < contatos.size(); i++) {
			        Contato contato = contatos.get(i);
			        System.out.println((i + 1) + ". Nome: " + contato.getNome() + ", Idade: " + contato.getIdade());
			    }
			    
			    System.out.print("Escolha o número do contato a ser atualizado: ");
			    int escolha = ler.nextInt();

			    if (escolha >= 1 && escolha <= contatos.size()) {
			        Contato contatoEscolhido = contatos.get(escolha - 1);

			        // Aqui você pode permitir que o usuário insira as novas informações para o contato escolhido
			        System.out.println("Informe o novo nome:");
			        ler.nextLine(); // Consuma a nova linha pendente
			        String novoNome = ler.nextLine();
			        contatoEscolhido.setNome(novoNome);

			        System.out.println("Informe a nova idade:");
			        int novaIdade = ler.nextInt();
			        contatoEscolhido.setIdade(novaIdade);

			        // Agora você pode chamar o método de atualização com o contato atualizado
			        contatoDao.update(contatoEscolhido);

			        System.out.println("Contato atualizado com sucesso!");
			    } else {
			        System.out.println("Número de contato inválido.");
			    }

			  
			  
			 
				
		  } if (op == '4') {
			  Scanner ler = new Scanner(System.in);
			  
			  System.out.println("Selecione o contato a ser excluído:");
			    List<Contato> contatos = contatoDao.getContatos();

			    for (int i = 0; i < contatos.size(); i++) {
			        Contato contato = contatos.get(i);
			        System.out.println((i + 1) + ". Nome: " + contato.getNome() + ", Idade: " + contato.getIdade()+ ", Data " +contato.getDataCadastro());
			    }
			    
			    System.out.print("Escolha o número do contato para mandar ao espaço: ");
			    int escolha = ler.nextInt();

			    if (escolha >= 1 && escolha <= contatos.size()) {
			        Contato contatoExcluir = contatos.get(escolha - 1);

			        // Chame o método de exclusão do seu DAO com o ID do contato a ser excluído
			        contatoDao.deleteByID(contatoExcluir.getId());

			        
			    } else {
			        System.out.println("Número de contato inválido.");
			    }
			    
		  }
	 
	
	}

}
