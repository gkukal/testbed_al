package com.jg.gk.recursion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StringPermutationWithDuplicates {

	
	public static void main(String args[]) {
	
		StringPermutationWithDuplicates sp = new StringPermutationWithDuplicates();
		Map<String, Object> mem = new HashMap<String,Object> ();
        sp.permute("AABC".toCharArray(), 0 , 1 , mem );
    
	}
	
	
	
	public void permute( char[] array , int startIndex , int level , Map<String, Object> mem  ){
		
		if( array == null || array.length == 0 ) return;
		if( startIndex == array.length -1 ) {
			System.out.println( " print "+ new String( array ));
			return;
		}
		String key1 = "LEVEL:"+level+" ";
		for( int i = startIndex  ; i < array.length ; i++  ){
			//swap only when in that level that character has not been already used in the first position. 
			//if this is the string AABC, 
			//when i=1 and level =1 the A is compared with A again, which should not be done. So the following check eliminates that execution.
			String keyToCheck = "LEVEL:"+level+" " + new Character( array[i]).toString() ;
			if( mem.containsKey(keyToCheck) ) continue; 
			swap( array , startIndex , i );
			mem.put(keyToCheck, null);
			permute( array , startIndex+1 , level+ 1, mem );
			swap( array , i , startIndex );
		}
		//It is important to remove this after each iteration.
		Iterator<Entry<String, Object>> iterator = mem.entrySet().iterator();
		while( iterator.hasNext() ){
			String str = iterator.next().getKey();
			if( str .contains(key1)){
				iterator.remove();
			}
		}
		
		
	}
	
	public void swap( char[] array , int to , int from ){
		
		if( to < 0 || from < 0 || to > array.length -1 || from > (array.length -1) || to == from  ) return;
		char toData =  array[ to ];
		array[ to ] =  array[ from ];
		array[ from ] = toData ;

	}
	
	
	
}
