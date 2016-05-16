package com.jg.gk.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jg.gk.util.Util;

public class AlphaToIntegerConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlphaToIntegerConversion obj = new AlphaToIntegerConversion();
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		obj.makeCombinations(new int[]{1,2,1}, 0, 0, map);
		
		List<String> list = map.get(0);
		if( list != null ){
			for( String s : list ){
				System.out.println( s );
			}
		}
	}

	
	public void makeCombinations( int[] arr , int startIndex , int level , Map<Integer,List<String>> map ){
		
		//Base condition //
		if( startIndex ==  arr.length -1 ){
			if( !map.containsKey(level)) {
				map.put(level, new ArrayList<String>());
			}
			char valueOfCurrentChar =  getValue( arr , startIndex, startIndex );
			map.get(level).add( new String( valueOfCurrentChar+""));
			return;	
		}
		
		//
		makeCombinations(arr, startIndex+1 , level+1 , map );
		List<String> valuesPStack = map.get( level+1 );	//from lower stack
		
		//one at a time 
		if(valuesPStack != null ){
			map.put( level , new ArrayList<String>() );
			for( int i = 0 ; i < 2; i++ ){
				char valueOfCurrentChar =  getValue( arr , startIndex, startIndex+i );
				Util.print("valueOfCurrentChar "+ valueOfCurrentChar, level);
				for( String str : valuesPStack ){
					String s = valueOfCurrentChar+","+str.substring( 0+i );
					Util.print(" V  " +s, level);
					map.get( level ).add( s );
				}
			}
			map.remove( level +1 );
		}
	}
	
	
	Character getValue( int[] arr , int startIndex, int endIndex ){
		
		if( startIndex < 0  || endIndex < 0 || startIndex > endIndex ) 
			return null;
		int sum =  0;
		for( int i = startIndex  ; i <= endIndex ; i++ ){
			int intCharV = arr[startIndex];
			int mF = ( endIndex - startIndex == 0 ) ? 1 : ( endIndex - startIndex )*10;
			sum += mF*intCharV ;
		}
		//=2

		int asciiForThisDigit = ( (int) 'a' )  + sum -1 ;

		return (char) ( asciiForThisDigit  ); 
	}
	
}
