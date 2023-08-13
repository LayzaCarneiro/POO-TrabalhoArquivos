import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Funcoes {

  public void criarDiretorio() throws IOException, InterruptedException{
    Scanner criarDiretorio = new Scanner(System.in);
    Scanner dadosAluno = new Scanner(System.in);
    String texto = "";

    System.out.print("\nInforme o nome da disciplina: ");
    String disciplina = criarDiretorio.nextLine();

    File diretorio = new File("c://Users//Layza//java//projetoFile//disciplinas");
    diretorio.mkdir();
    File subdir1 = new File("c://Users//Layza//java//projetoFile//disciplinas//" + disciplina);
    subdir1.mkdir();
    File alunos = new File("c://Users//Layza//java//projetoFile//disciplinas//" + disciplina + "//alunos.txt");

    while(true){
      System.out.print("\nNome do aluno: ");
      String nomeAluno = dadosAluno.nextLine().toUpperCase();
      System.out.print("Respostas do aluno: ");
      String respostasAluno = dadosAluno.nextLine().toUpperCase();

      if(respostasAluno.length() != 10){
        Thread.sleep(1000);
        System.out.println("\nDados incorretos. A resposta do aluno deve conter 10 itens V ou F.");
      } else {
        texto += (respostasAluno + "\t" + nomeAluno + "\n" );
      }

      try{
        FileWriter fw = new FileWriter(alunos);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(texto);
        bw.close();
        fw.close();
      } catch (IOException e){
        e.printStackTrace();
      }

      System.out.print("\nDeseja adicionar outro aluno na disciplina? \n  [1] Sim \n  [2] Não \n>>>>>>>>>>>>  ");
      int addAluno = criarDiretorio.nextInt();

      if(addAluno == 2){
        Thread.sleep(1000);
        System.out.println("\nAção concluída com sucesso.\n");
        Thread.sleep(1000);
        break;
      }
    }
  }

  public void exibirDisciplinas() throws InterruptedException{
    File subdir1 = new File("c://Users//Layza//java//projetoFile//disciplinas");
    String[] disciplinas = subdir1.list();
    System.out.println("\nDisciplinas existentes: ");
    for(int i=0; i < disciplinas.length; i++){
      File filho = new File(subdir1, disciplinas[i]);
      Thread.sleep(1000);
      System.out.println("\n  - " + filho.getName());
    }
    Thread.sleep(2000);
  }

  public void gerarResultados() throws IOException, InterruptedException{
    Scanner dados = new Scanner(System.in);
    System.out.print("Nome da disciplina: ");
    String nomeDisciplina = dados.nextLine();
    File disciplina = new File("c://Users//Layza//java//projetoFile//disciplinas//" + nomeDisciplina + "//alunos.txt");

    String texto = "";

    try {
      FileReader fr = new FileReader(disciplina);
      BufferedReader br = new BufferedReader(fr);
      while (br.ready()) {
          texto += br.readLine() + "\n";
      }
      br.close();
      fr.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    String[] linhas = texto.split("\n");
    double media = 0;
    int qtdAlunos = 0;
    int nota;

    ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    for (String linha : linhas) {
      String respostaAluno = linha.split("\t")[0];
      String nomeAluno = linha.split("\t")[1];
      Aluno aluno = new Aluno(nomeAluno, respostaAluno);
      alunos.add(aluno);
      nota = aluno.calcularNota(nomeDisciplina, respostaAluno);
      media += nota;
      qtdAlunos++;
    }
    Thread.sleep(1000);
    System.out.println("\nMédia Geral: " + (media / qtdAlunos));
    Thread.sleep(1000);


    List<Aluno> ordemAlfabetica = alunos.stream().sorted(Comparator.comparing(Aluno::getNome)).collect(Collectors.toList());

    File resultadoPorNome = new File("c://Users//Layza//java//projetoFile//disciplinas//" + nomeDisciplina + "//notaOrdemAlfabetica.txt");

    String out = "";
    System.out.println("ordem: " + ordemAlfabetica);
    for (Aluno aluno : ordemAlfabetica) {
         out += aluno.getNome() + "\t" + aluno.getNota() + "\n";
    }

    out += "Média Geral : \t" + (media / qtdAlunos);

    try {
        FileWriter fileWriter = new FileWriter(resultadoPorNome);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(out);
        bufferedWriter.close();
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();;
    }

    List<Aluno> ordemDecrescenteDeNota = alunos.stream()
                .sorted(Comparator.comparing(aluno -> aluno.getNota(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

    File resultadoPorNota = new File("c://Users//Layza//java//projetoFile//disciplinas//" + nomeDisciplina + "//notaOrdem.txt");
    out = "";

    for (Aluno aluno : ordemDecrescenteDeNota) {
        out += aluno.getNome() + "\t" + aluno.getNota() + "\n";
    }

    out += "Media Geral : \t" + (media / qtdAlunos);
    try {
        FileWriter fileWriter = new FileWriter(resultadoPorNota);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(out);
        bufferedWriter.close();
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();;
    }
  }

  public void criarGabarito() throws InterruptedException{
    Scanner dadosGabarito = new Scanner(System.in);
    String respostas = "";

    System.out.print("Nome da disciplina que deseja criar gabarito: ");
    String nomeDisciplina = dadosGabarito.nextLine();

    File gabarito = new File("c://Users//Layza//java//projetoFile//disciplinas//" + nomeDisciplina + "//gabarito.txt");

    System.out.print("\nGabarito: ");
    respostas += dadosGabarito.nextLine().toUpperCase();
    if(respostas.length() == 10){
      try {
        FileWriter fw = new FileWriter(gabarito);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.write(respostas);
        bufferedWriter.close();
        fw.close();
      } catch (IOException e){
        e.printStackTrace();
      }
      carregando();
      Thread.sleep(1000);
      System.out.println("Gabarito criado com sucesso.");
    } else {
      System.out.println("Dados inválidos. O gabarito deve conter 10 respostas.");
    }
    Thread.sleep(2000);
  }

  public void carregando() throws InterruptedException{
    Thread.sleep(800);
    System.out.println(".");
    Thread.sleep(800);
    System.out.println(".");
    Thread.sleep(800);
    System.out.println(".");
  }

}
