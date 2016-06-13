package DiceThrowWays;

public class DiceThrowWays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * There are N dices and M faces on each dice.
	 * Ways to reach to sum S when all N dices are thrown.
	 * 
	 *   
	 * if Ist dice has firstDiceV value in first run then we have to find that
	 * how to find
	 * int diceways ( int targetSum , int numOfDices  ){
	 * diceWays( S - firstDiceV , N - 1 )   
	 *   
	 *   
	 *   
	 *   
	 */
	
	int faceValuesMax = 6 ;// in increments of 1
	
	public int diceways ( int targetSum , int numOfDices , int levelInStack ){
		//base condition 
		if( targetSum == 0 && numOfDices == 0){
			return 1;
		}else if( numOfDices ==0  && targetSum > 0 ){
			return -1 ;
		}
		
		int ways = 0 ;
		for( int i = 0 ; i < numOfDices ; i++ ){
			for( int j = 1 ; j <= faceValuesMax ; j++ ){
				//for each dice, taking each valie of faceValuesMax
				int valueWays =  diceways( targetSum - j , numOfDices -1 , levelInStack+1 );
				if( valueWays == 1 ){
					ways += 1 ;
				}
			}
		}
		
		return ways;
	}
	
}
