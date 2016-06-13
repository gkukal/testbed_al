package com.jg.gk.dynamic;

import java.util.HashMap;
import java.util.Map;

import com.jg.gk.util.Util;

public class PrintMaxSumOfSubSquareMatrix {

	
	public static void main( String[] args ){
		 int[][] matrix =
			 {
					{1, 1, 1, 1, 1},
			        {2, 2, 2, 2, 2},
			        {3, 8, 6, 7, 3},
			        {4, 4, 4, 4, 4},
			        {5, 5, 5, 5, 5},
			    };
		 PrintMaxSumOfSubSquareMatrix obj = new PrintMaxSumOfSubSquareMatrix();
		 Map<String,Integer> memory = new HashMap<String,Integer>();
		 obj.findMaxSumSubMatrix( matrix, 0, 0, 3, 0, memory );
		
	}
	int sizeOfMatrixSquareNeeded =  3; 
	
	
	public int findMaxSumSubMatrix( int[][] matrix , int rowStart , int columStart , int sizeNeeded , int level ,
			Map<String,Integer> memory){
		
		//Base condition
		if( sizeNeeded == 0  ) return 0 ;
		if( sizeNeeded == 1  ) {
			
			return matrix[rowStart][columStart];
		}
		//
		int sizeOfMatrix  = matrix.length ;
		int sumMax = 0;
		int winnerRow = -1;
		int winnerCol = -1 ;
		int lastIndexToGoTo = sizeOfMatrix - sizeNeeded ;
		for( int row=rowStart  ; row <= lastIndexToGoTo ; row++){// to cover all rows
			for( int col=columStart  ; col <= lastIndexToGoTo ; col++){ // to cover all columns as starting point
				
				String key = "Row"+row+"col"+col+"size"+(sizeNeeded-1);
				int sumFromLowerStack = 0 ;
				if( !memory.containsKey( key )){
					sumFromLowerStack = findMaxSumSubMatrix(  matrix , row , col , sizeNeeded-1 , level+1 , memory );
					memory.put( key , sumFromLowerStack );
					Util.print("sumFromLowerStack --> "+ key + " "+ sumFromLowerStack, level );
				}else{
					sumFromLowerStack = memory.get(key);
				}
				Util.print("sumFromLowerStack "+ key + " "+ sumFromLowerStack, level );
				
				int rowToBeSummed =  row + sizeNeeded -1 ;
				int sum = 0;
				for( int colT= col ; colT < col + sizeNeeded ; colT++){
					sum += matrix[rowToBeSummed][colT];
				}
				int colToBeSummed = col + sizeNeeded -1 ;
				for( int rowT= row ; rowT < row + sizeNeeded -1 ; rowT++){
					sum += matrix[rowT][colToBeSummed];
				}
				if( sumMax < sum + sumFromLowerStack){
					sumMax = sum + sumFromLowerStack;
					winnerRow = row ;
					winnerCol = col ;
				}
			}
		}
		Util.print( "Winner Row "+ winnerRow +" winnerCol "+ winnerCol +"Size"+sizeNeeded+" Sum "+ sumMax , level);
		return sumMax ;
		
	}
	
	
	
}
