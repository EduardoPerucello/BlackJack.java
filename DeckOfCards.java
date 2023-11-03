import java.security.SecureRandom;

//Início da classe 'DeckOfCards'
public class DeckOfCards
{
   private Card[] deck; //Vetor (array) de objetos 'Card'
   private int currentCard; //Index da próxima carta ('Card') a ser distribuída (0-51)
   private static final int NUMBER_OF_CARDS = 52; //Constante # de 'Card's
   //Gerador de números aleatórios
   private static final SecureRandom randomNumbers = new SecureRandom();

   //Construtor que preenche o baralho de cartas ('Card')
   public DeckOfCards()
   {
      String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", 
         "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
      String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

      deck = new Card[NUMBER_OF_CARDS]; //Cria vetor (array) de objeto do tipo 'Card'
      currentCard = 0; //Primeira carta ('Card') distribuida será 0 

      //Popular baralho com objetos do tipo 'Card' (cartas)
      for (int count = 0; count < deck.length; count++) 
         deck[count] = 
            new Card(faces[count % 13], suits[count / 13]);
   } 

   //Método que embaralha o baralho de cartas com algoritmo de passagem única
   public void shuffle()
   {
      //A próxima chamada ao método 'dealCard' deve começar com 'currentCard' = 0 novamente
      currentCard = 0; 

      //Para cada 'Card', é escolhido uma das 52 cartas do baralho 
      for (int first = 0; first < deck.length; first++) 
      {
         //Seleciona um número aleatório entre 0 e 51
         int second =  randomNumbers.nextInt(NUMBER_OF_CARDS);
         
         //Troca a carta ('Card') atual por uma selecionada aleatoriamente 
         Card temp = deck[first];        
         deck[first] = deck[second];   
         deck[second] = temp;            
      } 
   } 

   //Método que distribui uma carta ('Card')
   public Card dealCard()
   {
      //Determinar se as cartas ('Card') remanecentes precisam ser distribuidas
      if (currentCard < deck.length)
         return deck[currentCard++]; //Retorna cartas ('Card') atual no vetor array 
      else        
         return null; //Retorna null para indicar que todas as cartas foram distribuídas 
   } 

} //Final da classe 'DeckOfCards'