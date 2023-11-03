import java.util.ArrayList;

//Início da classe 'Player'
public class Player {

	//Variaveis utilizadas
	private int id;
	private String nome;
	private ArrayList<Card> mao;
	private int pontos;
	
	//Construtor que inicializa 'Player'
	public Player(){
		this.pontos = 0;
		this.id = (int) System.currentTimeMillis() % 1000;
		this.mao = new ArrayList<Card>();
	}
	
	//Método que adiciona as cartas do tipo 'Card' na mão dos jogadores 'Player' e atribui seus respectivos valores
	public void addCarta(Card carta){
		this.pontos += carta.getValor(); //Jogamos os valores das cartas em pontos para poder calcular quanto cada jogador tem 
		this.mao.add(carta); //Após isso, jogamos as cartas e os pontos na mão de cada jogador
		ajustarValorAs(); //Chama o método para ajustar o valor do Ás
	}
	
	//Método que retorna um número de 'id' para o jogador ('Player')
	public int getId() {
		return id;
	}
	
	//Método que seta um numero de 'id' para o jogador ('Player')
	public void setId(int id) {
		this.id = id;
	}
	
	//Método que retorna o 'nome' referente ao jogador ('Player')
	public String getNome() {
		return nome;
	}
	
	//Método que seta o 'nome' referente ao jogador ('Player')
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Método que retorna a 'mao' (vetor de cartas 'Card') do jogador ('Player')
	public ArrayList<Card> getMao() {
		return mao;
	}
	
	//Método que seta/recebe a mão (vetor de cartas 'Card') do 'Player'
	public void setMao(ArrayList<Card> mao) {
		this.mao = mao;
	}
	
	//Método que retorna os pontos que o jogador('Player') possui 
	public int getPontos() {
		return pontos;
	}
	
	//Método que seta/recebe os pontos do jogador ('Player')
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	//Método para ajustar o valor de Ás, 1 ou 11, dependendo da circunstancia do jogo 
	public void ajustarValorAs() {
        for (Card carta : mao) {
            if (carta.getValor() == 11 && getPontos() > 21) {
                //Se o valor do Ás for 11 e o total de pontos do jogador for maior que 21, ajusta o valor do Ás para 1
                carta.ajustarValorDoAsPara1();
                recalcularPontos(); //Recalcula os pontos do jogador ('Player')
            }
        }
    }

	//Método que arruma os pontos de acordo com qual o valor definido de ÁS, atualizando os pontos totais do jogador ('Player')
    private void recalcularPontos() {
        int total = 0; //Inicia total como 0
        for (Card carta : mao) {
            total += carta.getValor(); //Jogara o novo valor de Ás no total 
        }
        setPontos(total); //Recebe o valor de total nos pontos
    }
	
} //Final da classe 'Player'