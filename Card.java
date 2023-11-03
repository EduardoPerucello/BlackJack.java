//Início da classe 'Card'
public class Card 
{
   private final String face; //Número(face) da carta('Card') ("Ace", "Deuce", ...)
   private final String suit; //Naipe(suit) da carta('Card') ("Hearts", "Diamonds", ...)
   private int valor;

   //Construtor de dois argumentos para inicializar número(face) e naipe(suit) da carta('Card')
   public Card(String face, String suit)
   {
      this.face = face;
      this.suit = suit; 
      
      //Switch case para atribuir o valor das cartas
          switch(face){

         //Valor do Ás
       case "Ace":
         valor = 11; //Valor padrão do Ás é 11
         break;

         //Valor do Dois
       case "Deuce":
         valor = 2;
         break; 

         //Valor do Três
       case "Three":
         valor = 3;
         break; 

         //Valor do Quatro
       case "Four":
         valor = 4;
         break; 

         //Valor do Cinco
       case "Five":
         valor = 5;
         break;

         //Valor do Seis
       case "Six":
         valor = 6;
         break;

         //Valor do Sete
       case "Seven":
         valor = 7;
         break;
 
         //Valor do Oito
       case "Eight":
         valor = 8;
         break;

         //Valor do Nove
       case "Nine":
         valor = 9;
         break;

         //Valor do Dez, Dama, Valéte e Rei = 10
       default:
         valor = 10;
      }
   } 

    //Método para ajustar o valor do Ás para 1
    public void ajustarValorDoAsPara1() {
      if (face.equals("Ace")) { //Se for igual a face Ás ele ira atribuir 1 no valor
          valor = 1;
      }
  }

   //Método que retorna uma String que representa a carta('Card') retirada
   public String toString() 
   { 
      return face + " of " + suit;
   } 

   //Método que retorna o valor da carta setado pelo método 'setValor'
   public int getValor() {
		return valor;
	 }

   //Método que seta o valor da carta definido pelos métodos 'Card' e 'ajustarValorDoAsPara1'
	 public void setValor(int valor) {
      this.valor = valor;
   }

} //Final da classe 'Card'