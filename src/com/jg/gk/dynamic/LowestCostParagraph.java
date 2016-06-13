package com.jg.gk.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jg.gk.util.Util;

/**
 * Text Justification
Given a sequence of words, and a limit on the number of characters that 
can be put in one line (line width). Put line breaks in the given sequence 
such that the lines are printed neatly. Assume that the length of each word is smaller than the line width.
The word processors like MS Word do task of placing line breaks. 
The idea is to have balanced lines. In other words, not have few lines with
 lots of extra spaces and some lines with small amount of extra spaces.
The extra spaces includes spaces put at the end of every line except the last one.  
The problem is to minimize the following total cost.
 Cost of a line = (Number of extra spaces in the line)^3
 Total Cost = Sum of costs for all lines

For example, consider the following string and line width M = 15
 "Geeks for Geeks presents word wrap problem" 
     
Following is the optimized arrangement of words in 3 lines
Geeks for Geeks
presents word
wrap problem 

The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
So optimal value of total cost is 0 + 2*2 + 3*3 = 13


Please note that the total cost function is not sum of extra spaces, but sum of 
cubes (or square is also used) of extra spaces. The idea behind this cost 
function is to balance the spaces among lines. For example, consider the following two 
arrangement of same set of words:
1) There are 3 lines. One line has 3 extra spaces and all other lines have 0 extra spaces. 
Total extra spaces = 3 + 0 + 0 = 3. Total cost = 3*3*3 + 0*0*0 + 0*0*0 = 27.
2) There are 3 lines. Each of the 3 lines has one extra space. Total extra spaces = 1 + 1 + 1 = 3. 
Total cost = 1*1*1 + 1*1*1 + 1*1*1 = 3.
Total extra spaces are 3 in both scenarios, but second arrangement should be preferred because 
extra spaces are balanced in all three lines. The cost function with cubic sum serves the purpose 
because the value of total cost in second scenario is less.
 * @author gkukal
 *
 */
public class LowestCostParagraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line =  "Geeks for Geeks presents word wrap problem The extra spaces includes spaces put at the end of every line except the last one";
		LowestCostParagraph obj = new LowestCostParagraph();
		obj.m_lengthofLine = 20;
		int[]  markers = new int[line.split(" ").length] ;
		Map<String,Double> map = new HashMap<String,Double>();
		obj.lowestCostParagraph(line.split(" "), 0, markers , 0 , map);
		
		for( Integer marker : markers){
			System.out.println( " Markers " + marker );
		}
				
	}

	public int m_lengthofLine  = 15;
	
	/**
	 * 
	 * @param words
	 * @param startIndex
	 * @param tillThisIndex
	 * @return -1 for invalid entry
	 * -2 if the words not to fit in one line at all
	 */
	private int calculateLengthThatCanFit( String[] words , int startIndex , int tillThisIndex ){
		if( words == null || tillThisIndex >= words.length) {
			return -1;
		}
		int sum = 0;
		for( int j=startIndex ; j <= tillThisIndex ; j++  ){
			sum += words[j].length()  ;
			if( j < tillThisIndex -1 ){
				sum += 1;  
			}
			if( sum > m_lengthofLine ) return -2;
		}
		return sum;
	}
	
	
	
	public double lowestCostParagraph( String [] words, int startIndex , int[] markers , int level , Map<String,Double> map ){
		//Base condition 
		if( startIndex >= words.length ) return 0 ;
		
		double min = 0;
		for( int numOfWords = 0 ; numOfWords < words.length ; numOfWords++   ){
			int lengthOfWords =  calculateLengthThatCanFit( words , startIndex ,  startIndex + numOfWords);
			if( lengthOfWords <0 && lengthOfWords == -2 ){
				break;
			}else{
				double costOfPuttingTheWordsForThisLine =  Math.pow( new Double( m_lengthofLine - lengthOfWords) , 3.00d) ;
				String key = ( startIndex + numOfWords + 1) + " L "+ (level+1);
				double costOfAllOtherWords = 0.0;
				if( map.containsKey( key )){
					costOfAllOtherWords = map.get( key );
				}else{
					costOfAllOtherWords = lowestCostParagraph( words , startIndex + numOfWords + 1 , markers , level+1 , map );
					try {
						Double d = new Double( costOfAllOtherWords);
						map.put( key, costOfAllOtherWords );
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println( key );
						System.out.println( map );
						System.out.println( costOfAllOtherWords );
						e.printStackTrace();
					}
				}
				
				double totalCost = costOfPuttingTheWordsForThisLine + costOfAllOtherWords ;
				Util.print(
						"startIndex + numOfWords: "+numOfWords +" costOfPuttingTheWordsForThisLine "+ 
				costOfPuttingTheWordsForThisLine +" costOfAllOtherWords "+ costOfAllOtherWords , level );
				Util.print( "TotalCost: "+totalCost +" Level "+ level, level );
				if( min ==0  ) {
					min = totalCost;
					markers[level] = startIndex + numOfWords ;
				}else if( totalCost < min ) {
					min =  totalCost;
					markers[level] = startIndex + numOfWords ; //Marks the index of the last word taken
					Util.print( "TotalCost: "+totalCost +" Level "+ level, level );
				}
			}
		}
		return min;
	}
	
}
