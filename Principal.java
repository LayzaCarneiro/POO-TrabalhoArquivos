import java.io.IOException;
import java.util.Scanner;

public class Principal {
  public static void main(String[] args) throws InterruptedException, IOException{
    Funcoes função = new Funcoes();
    Scanner entrada = new Scanner(System.in);

    while(true){
      System.out.print("\nOpções:"+
                       "\n [1] Criar disciplina e inserir alunos" +
                       "\n [2] Exibir disciplinas" +
                       "\n [3] Gerar gabarito" +
                       "\n [4] Obter média geral da disciplina" +
                       "\n [5] Sair" +
                       "\n\n>>>>>>>>>>>>>>> ");

      int escolha = entrada.nextInt();

      if(escolha == 1){
        System.out.println("\nOpção 1 selecionada com sucesso. Carregando...");
        função.carregando();
        função.criarDiretorio();
      }
      else if(escolha == 2){
        System.out.println("\nOpção 2 selecionada com sucesso. Carregando...");
        função.carregando();
        função.exibirDisciplinas();
      }
      else if(escolha == 3){
        System.out.println("\nOpção 3 selecionada com sucesso. Carregando...");
        função.carregando();
        função.criarGabarito();
      }
      else if(escolha == 4){
        System.out.println("\nOpção 4 selecionada com sucesso. Carregando...");
        função.carregando();
        função.gerarResultados();
      }
      else if(escolha == 5){
        System.out.println("\nSaindo do programa...");
        Thread.sleep(2000);
        System.exit(0);
      }
    }
  }
}
