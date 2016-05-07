package com.jg.gk.dynamic;

import java.util.HashMap;
import java.util.Map;

public class Eggdropping {

	
	public static void main( String[] args ){
		Eggdropping obj = new Eggdropping();
		Map<String,Integer> memory  =  new HashMap<String,Integer>();
		int eggDropping = obj.eggDropping(10, 4, memory , 0);
		System.out.println( eggDropping );
	}
	
	/**
	 * f = top N floors
	 * e = eggs left to be used
	 * @param f
	 * @param e
	 */
	public int eggDropping( int f, int e , Map<String,Integer> memory , int l ){
		
		if( f == 0 || e == 0 ){
			return 0; // because this should not be considered as a trial. 
		}
		
		/**
		 * When you drop the egg from top floor then two options can be there
		 * 1. If the egg breaks, then we have to find the minimum from  eggDropping( f-1, e -1 )  with one less egg and one less floor
		 * 2. If egg does not drop then we have to find the max next floor where the floor can be broken
		 *   for all floors between top f and where the egg has broken int i = 1 ; i < f ; i++, 
		 *    eggDropping(  f - i  , e )
		 * We need to find MIN of these two options +1 
		 * 
		 */
		int min =  Integer.MAX_VALUE;
		for( int i = 0 ; i < f ; i++ ){
			String key1 = "Floor"+(f -1) +"eggs"+(e-1);
			int minFirstOptionEggBreaks = memory.containsKey( key1 ) ? memory.get( key1 ) : eggDropping( f -1 , e -1 , memory , l+1);
			Util.print( key1 +" ::  "+ minFirstOptionEggBreaks , l );
			memory.put( key1 , minFirstOptionEggBreaks );
			String key2 =  "Floor"+(f - i -1 ) +"eggs"+e ;
			int minSecondOptionEggDoesNotBreak =  memory.containsKey( key2 )? memory.get( key2 ) : eggDropping( f - i -1  , e , memory, l+1 );
			Util.print( key2 +" ::  "+ minSecondOptionEggDoesNotBreak , l );
			memory.put( key2 , minSecondOptionEggDoesNotBreak );
			int minValue  = 1+ Math.max( minFirstOptionEggBreaks , minSecondOptionEggDoesNotBreak);
			
			
			if( min > minValue ){
				min = minValue ;
			}
			
		}
		return min;
	}
}
