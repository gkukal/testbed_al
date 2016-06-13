package com.jg.gk.dynamic;

import com.jg.gk.util.Util;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
		int[] arr = new int[]{ 9 , 1 , 2 , 15 , 10, 12 ,13,14} ;
		int[] largestMemory  = new int[arr.length ];
		for( int i= 0 ; i < largestMemory.length ; i++ ){
			largestMemory[i] = -1;
		}
		obj.longestIncreasingSubsequence(arr, 0, arr.length -1 , largestMemory , 0);
		for( int i= 0 ; i < largestMemory.length ; i++ ){
			System.out.println( largestMemory[i]);
		}
	}

	
	
	public void longestIncreasingSubsequence( int[] arr , int stIndex, int endIndex , int[] largestMemory , int level ){
		
		/**
		 * Base condition
		 */
		if( endIndex == 0 ) {
			largestMemory[endIndex] = 1;
		}
		/**
		 * find the largest increasing subsequence ending at each index
		 * Where ever a[endIndex] > a[i] need to get the value for max subsequence till that index
		 * Get the max value from all such indices 
		 * 1+ that max is the max that ending at this index.
		 * Also keep a track of overall Max 
		 */
		int max = 0 ;
		for( int i = endIndex-1 ; i >=0  ; i--){
			
			if( largestMemory[i] == -1 ){
				longestIncreasingSubsequence( arr , stIndex , i , largestMemory  , level+1 );
			}
			int maxFromThisIndex =  largestMemory[i];
			Util.print(" Value at "+i, level);
			if( ( arr[i] < arr[ endIndex] ) && max < maxFromThisIndex ){
				max = maxFromThisIndex ;
				
			}
		}
		largestMemory[endIndex] = max +1 ;
	}
	
}
