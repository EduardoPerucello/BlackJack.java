import java.util.ArrayList;
import java.util.Scanner;

//Início da classe 'DeckOfCardsTest'
public class DeckOfCardsTest
{  
	//Atribuição de nome para facilitar na hora da chamada no codigo
	private static DeckOfCards baralho;
	private static ArrayList<Player> jogadores;
	private static Scanner in = new Scanner(System.in); 
	private static int opcaoJogo = 1;
	private static int pontosMaior = 0;

   //Executa aplicação (função main)
   public static void main(String[] args)
   {

		baralho = new DeckOfCards();
	 	jogadores = new ArrayList<Player>();

		//Variaveis utilizadas
      	int opcao = 1;
		Player jogador;
		String nome;

		//Criação da Banca como um jogador 
		jogador = new Player();
		jogador.setNome("Banca");
		jogadores.add(jogador);

      //Inicio
		while(opcao >= 1)
      {
			
			//Menu Inicial
			System.out.println();
			System.out.println("---------- BlackJack ----------");
			System.out.println("------ Digite sua opção -------");
			System.out.println();
			System.out.println("1 - Adicionar Jogador");
			System.out.println("2 - Iniciar Jogo");
			System.out.println("0 - Sair");
			opcao = in.nextInt();
			
			//Adicionar jogador
			if(opcao == 1)
         {
				jogador = new Player();
				System.out.println("\nDigite o nome do jogador:");
				nome = new String(in.next());
				jogador.setNome(nome); //Salva o nome do jogador
				jogadores.add(jogador); //Adiciona o jogador na mesa do jogo
			}

         //Inicia o jogo				
          if(opcao == 2){

			//Verificação se há ao menos dois jogadores criados (banca + 1 jogador)
            if(jogadores.size() < 2) //Se não houver nenhum jogador inserido, o programa ira dizer que você precisa adicionar um jogador 
            {
               System.out.println("Voce precisa adicionar jogadores!!!");
            }
               else
               {
                  while(opcaoJogo >= 1) //Se tiver jogador ele ira efetuar esse while
                  {

                  //Primeira rodada
                  if(opcaoJogo == 1)
                  {
                     baralho.shuffle(); //Coloca cartas do baralho em ordem aleatória (embaralha o baralho)
					 darCartas(jogadores, baralho); //Chamada método para distribuir cartas do baralho 
                        System.out.println(); 
                        System.out.println(); //Pula linha
                     opcaoJogo = 2; //Após dar as cartas o programa atribui 2 na opção de jogo para que o jogo prossiga 
                  }
                  
						//Demais rodadas
						if(opcaoJogo == 2)
                  {
							for(int j = (jogadores.size() - 1); j >= 0; j--)
                     {
								while(jogadores.get(j).getPontos() <= 21 && opcaoJogo != 3) //O jogo daquela pessoa ira parar assim que seus pontos sejam maiores que 21
                        {
									
									jogar(jogadores.get(j), baralho); //Chamada da função de jogar na main
									
								}
								opcaoJogo = 2; 
							}
                  }
                  opcaoJogo = -1;
                  vencedor(); //Chamada da função para saber quem é o vencedor 
               }
               opcao = -1; //Se a opção for 0 ele encerrara o programa
            }
          }
      }
   }

   //Método que distribui cartas aos jogadores; Faz a contagem de cartas restantes no baralho
   public static void  darCartas(ArrayList<Player> jogadores, DeckOfCards baralho)
   {
		for(int i = 1; i <= 2; i++)
      {
			for(Player j: jogadores) //For para dar apenas duas cartas a primeira vez para cada participante
         {
				j.addCarta(baralho.dealCard()); //Remove do baralho as cartas e da para os jogadores
			}
      }
   }

   //Método que mostra a situação atual da mesa, as cartas e pontos da banca e dos jogadores 
   public static void mostrarMesa(ArrayList<Player> jogadores)
   {
		System.out.println();
		System.out.println("|¨¨   Mesa   ¨¨|");
		
		for(Player j: jogadores){
			System.out.println("-------------------------");
			System.out.print("Jogador: ");
			System.out.print("\t" + j.getNome()); //Irá imprimir o nome do jogador 
			System.out.println("\nCartas:");

			for(Card c: j.getMao()){
				System.out.println("\t" + c.toString()); //Irá imprimir as cartas do jogador
			}
			
			System.out.println("Pontos -> ");
			System.out.println("\t" + j.getPontos()); //Irá imprimir os pontos do jogador
			System.out.println();
		}
		System.out.println("|__   Mesa   __|");
		System.out.println();
	}

   //Método do jogo em andamento: mostra mão do jogador, dando a opção de receber nova carta, ou ficar com o que já tem
   public static void jogar(Player jogador, DeckOfCards baralho)
   {
		
		mostrarMesa(jogadores); //Chamada da funçõa para mostrar a mesa
		System.out.println();
		System.out.println("Jogador: " + jogador.getNome()); //Chama o nome do jogador para saber de quem é a vez
		System.out.println("------ Digite sua opção -------");
		System.out.println("2 - Hint (receber uma carta)");
		System.out.println("3 - Stand (encerrar mao)");
		opcaoJogo = in.nextInt();
		
		//Hint (recebe nova carta)
		if(opcaoJogo == 2){
			jogador.addCarta(baralho.dealCard()); //Caso seja 2, o programa ira dar mais uma carta para o jogador
		}
		
		//Stand (encera mão com as cartas que já possui)
		if(opcaoJogo == 3){
			System.out.println("Opcao:  Stand -> Pontos: " + jogador.getPontos()); //Caso seja 3, o programa ira encerrar a vez do jogador e contabilizar os pontos
		}
		
		//Verifica se o jogador possui a maior pontuação da mesa, atribuindo seus pontos a variável 'pontoMaior'
		if(jogador.getPontos() >= pontosMaior && jogador.getPontos() <= 21){
			pontosMaior = jogador.getPontos(); //Compara para ver qual jogador ganhou 
		}
	}

   //Método que define e imprime o vencedor
   public static void vencedor()
   {
		int contEmpate = 0;
		
		System.out.println("--------------");
		System.out.println("     \\o/     ");
		System.out.println();
		System.out.println("Vencedor(es): ");
		System.out.println();
		
		//Procura quem foi o jogador com a maior quantidade de pontos acumulada ('pontosMaior')
		for(Player j: jogadores){
			if(j.getPontos() == pontosMaior){
				System.out.println(j.getNome() + " Pontos -> " + j.getPontos());
				contEmpate++; //Se os pontos forem iguais, o programa ira dizer que teve um empate
			}
		}

		//Impressão se houver empate (2 ou mais jogadores com pontos iguais a 'pontosMaior')
		if(contEmpate > 1){
			System.out.println("Portanto, tivemos um empate!!!!");
		}
		System.out.println();
		System.out.println("     \\o/     ");
		System.out.println("--------------");
		System.out.println();
	}
	
}//Final da classe DeckOfCardsTest
