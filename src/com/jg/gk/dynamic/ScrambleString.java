package com.jg.gk.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jg.gk.util.Util;

public class ScrambleString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		ScrambleString obj = new ScrambleString();
		boolean scramble = obj.scramble( "ehtrg".toCharArray(), "great");
		System.out.println( scramble );
	}
	
	
	public boolean scramble( char[] arrayStr , String matchString  ){
		//Get the first character out and do sramble on the rest of the characters
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		
		Set<String> returnFromLowerStack =  
				scrambleUtil( arrayStr , 0 , arrayStr.length -1  , matchString , 0 , map );
		
		
		if( returnFromLowerStack != null ){
			for( String sLowerStack : returnFromLowerStack ){

				if( matchString.equals(sLowerStack) ){
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	private String createKey( char[] arrayStr , int startIndex , int end ){
		StringBuffer buffer = new StringBuffer();
		
		for( int i = startIndex ; i <= end ; i++){
			buffer.append( arrayStr[i]);
		}
		return buffer.toString();
	}
	
	public Set<String> scrambleUtil( char[] arrayStr , int startIndex , int endIndex,  String matchString, int level , 
			Map<String,Set<String>> memory){
		
		//base condition
		Set<String> returnList = new HashSet<String>();
		if( startIndex == endIndex ){
			String string = Character.toString( arrayStr[ startIndex]);
			returnList.add( string );
			return returnList;
		}
		
		
		//Get the first character out and do scramble on the rest of the characters
		int length = 0;
		for( int i=startIndex; i < endIndex  ; i++  ){
			
			int pivot = startIndex +length;
			String keyLeft = createKey( arrayStr , startIndex , pivot ) ;
			length++;
			Set<String> returnListLeft = null;
			if( !memory.containsKey(keyLeft)){
				returnListLeft = scrambleUtil( arrayStr , startIndex, pivot , matchString , level+1 , memory);
				memory.put(keyLeft, returnListLeft);
			}else{
				returnListLeft = memory.get(keyLeft);
			}
			Util.print( "KeyLeft"+keyLeft + " startIndex "+ startIndex+ "pivot "+ pivot, level);
			Set<String> returnListRight = null;
			String keyRight = createKey( arrayStr , pivot+1 , endIndex ) ;
			if( !memory.containsKey(keyRight)){
				returnListRight = scrambleUtil( arrayStr , pivot+1, endIndex  , matchString , level+1 , memory);
				memory.put(keyRight, returnListRight );
			}else{
				returnListRight = memory.get(keyRight);
			}
			
			if(returnListLeft != null ){
				for( String left : returnListLeft){
					if(returnListRight != null ){
						for( String right : returnListRight ){
							returnList.add( left+right);
							Util.print(" left+right "+ left+right, level);
							returnList.add( right+left);
							Util.print(" right+left "+ right+left, level);
							
						}
					}
				}
			}
			
		}
		
		return returnList ;
		
		
	}

}
