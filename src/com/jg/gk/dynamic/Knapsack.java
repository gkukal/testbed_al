package com.jg.gk.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.jg.gk.util.Util;

public class Knapsack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Knapsack obj = new Knapsack();
		obj.m_pArr = new int[]{ 10 , 9 , 8 ,40  };
		obj.wArry = new int[]{ 2 , 10 , 11 , 3 };
		obj.weight = 6;
		obj.knapsack( obj.m_pArr , obj.wArry  , obj.weight ) ;
	}
	
	/**
	 * Given a bag which can only take certain weight W.
	 * Given list of items with their weights and price. 
	 * Stuff these items in the bag such that it maximizes the value of bag - Knapsack01.java Youtube link
	 */
	
	
	int[] m_pArr = null;
	int[] wArry = null ;
	int weight  = 0;
			
	public void knapsack( int[] pArr , int[] wArry , int W  ){
		// NULL checks
		int max  =  0 ;
		int[]  bestList = null; 
		
		Map<String,Integer> memory = new HashMap<String,Integer>();
		for( int i= 0 ; i < pArr.length ; i++ ){
			//We will pick each one and drop at the same time and see which option gives us better
			int[] optedFor    = new int[pArr.length];
			int[] notoptedFor    = new int[pArr.length];
			int[] winner    = new int[pArr.length];
			optedFor[i] = 1; //1 means taken 
			Util.print("Index chosen "+ i +" price "+ m_pArr[i] +" weight "+wArry[i], 1);
			int totalPriceWhenChosen  =    knapsackRecursive(  notoptedFor , optedFor , weight - wArry[i] , 1 , memory , winner );
			System.out.println( "totalPriceWhenChosen "+ totalPriceWhenChosen);
			optedFor[i] = 0 ;
			notoptedFor[i] = 1;
			Util.print("Index not chosen "+ i +" price "+ m_pArr[i] +" weight "+wArry[i], 1);
			int totalPriceWhenNotChosen  = knapsackRecursive(  notoptedFor , optedFor , weight , 1 , memory , winner  );
			System.out.println( "totalPriceWhenNotChosen "+ totalPriceWhenNotChosen);
			int maxBetweenTwo = Math.max( totalPriceWhenChosen , totalPriceWhenNotChosen );
			if( max < maxBetweenTwo ){
				max = maxBetweenTwo ;
				System.out.println( "Max till now "+ max);
				if( totalPriceWhenChosen >= totalPriceWhenNotChosen ){
					winner[i] = 1;
					bestList = winner ;
				}
			}
		}
		System.out.println( max );
		if( bestList != null ){
			for( Integer index: bestList ){
				System.out.print( m_pArr[ index ] +" ,  ");
			}
		}
	}
	
	public Integer memory( int[] notOptedFor , int[] optedFor , Map<String,Integer> memory ){
		
		StringBuffer buffer = new StringBuffer();
		for( int i= 0 ; i < m_pArr.length ; i++ ){
			if( optedFor[ i ] == 1 ||  notOptedFor[i] == 1 ) continue;
			buffer.append( i +"_");
		}
		Integer returnV = memory.get( buffer.toString()) ;
		if( returnV != null ) System.out.println( "from memory"+ returnV);
		return memory.get( buffer.toString());
	}
	
	public int knapsackRecursive( int[] notOptedFor , int[] optedFor , int goal_weight , int level , Map<String,Integer> memory , int[] winner){
		//base condition
		if( goal_weight <= 0 ) return 0 ;
		
		//
		int max  =  0 ;
		int indexChosen = -1;
		for( int i= 0 ; i < m_pArr.length ; i++ ){
			//check if this are already in the list 
			if( optedFor[i] ==1  || notOptedFor[i] == 1  ) continue;
			optedFor[i] = 1 ;
			Util.print("Index chosen** "+ i +" price "+ m_pArr[i] +" weight "+wArry[i], level );
			Integer memory_from_totalPriceWhenChosen = memory( notOptedFor , optedFor , memory);
			int totalPriceWhenChosen  =    
					memory_from_totalPriceWhenChosen == null ? 
					m_pArr[i] + knapsackRecursive( notOptedFor , optedFor , goal_weight - wArry[i] , level+1 , memory , winner ) 
					: m_pArr[i] + memory_from_totalPriceWhenChosen;
			optedFor[i] = 0 ;
			notOptedFor[i] = 1 ;
			Integer memory_PriceWhenNotChosen = memory( notOptedFor , optedFor , memory);
			int totalPriceWhenNotChosen  = 
					memory_PriceWhenNotChosen == null ? 
					knapsackRecursive(  notOptedFor , optedFor , goal_weight , level+1 , memory , winner) 
					: memory_PriceWhenNotChosen;
			int maxBetweenTwo = Math.max( totalPriceWhenChosen , totalPriceWhenNotChosen );
			notOptedFor[i] = 0;
			if( max < maxBetweenTwo ){
				max = maxBetweenTwo ;
				if( totalPriceWhenChosen >= totalPriceWhenNotChosen ){
					indexChosen = i ;
				}
			}		
		}
		if( indexChosen >= 0 ){
			winner[indexChosen] = 1;
		}
		
		return max;
	}
	
	
}
