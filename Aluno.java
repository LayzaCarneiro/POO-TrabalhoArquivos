import java.io.*;

public class Aluno {
  private String nome;
  private String respostas;
  private int nota = 0;

  public Aluno (String nome, String respostas){
    this.nome = nome;
    this.respostas = respostas;
  }

  public int calcularNota (String disciplina, String respostas) throws IOException{
    int qtdV = 0;
    int qtdF = 0;

    File gab = new File("c://Users//Layza//java//projetoFile//disciplinas//" + disciplina + "//gabarito.txt");
    FileReader fr = new FileReader(gab);
    BufferedReader bf = new BufferedReader(fr);
    String gabarito = bf.readLine();

    for(int i = 0; i < 10; i++){
      if(respostas.charAt(i) == gabarito.charAt(i)){
        nota++;
      }
      if(respostas.charAt(i) == 'V'){
        qtdV++;
      } else {qtdF++;}
    }

    if(qtdV == 10 || qtdF == 10){
      nota = 0;
    }

    fr.close();
    bf.close();

    return nota;
  }

  public String getNome() {
    return nome;
  }

  public String getRespostas() {
    return respostas;
  }

  public int getNota() {
    return nota;
  }
}
