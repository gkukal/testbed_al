package com.jg.gk.recursion;

import java.util.List;

public class NumberOfPathsInMxNMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int targetX = 0 ;
	int targetY = 0 ;
	/**
	 * if matrix has 2 you cannot cross it 
	 * You can go right, left up and down only ( not diagonal)
	 * @param matrix
	 * @param visited
	 * @param level
	 */
	public void paths( int[][] matrix , int x , int y , boolean[][] visited , int level ){
		
		if( matrix == null ) return ;
		//base condition
		if( x== targetX && y == targetY) {
			// we are done and print the coordinates in visited 
			if( visited != null ){
				for( int i = 0  ; i < visited.length ;i++ ){
					for( int j = 0  ; j < visited[i].length ;j++ ){
						System.out.print( visited[i][j]);
					}
					System.out.println();
				}
			}
		}
		
		//from current x and y coordinates, find what can all the next steps be. Here make use of 
		// visited[][] to find if that coordinate is already covered in the stacks.
		
		//There are just four options and lets check one by one
		if( y+1 < matrix[0].length && visited[x][y+1] == false && matrix[x][y+1] != 2 ){//right
			visited[x][y+1] = true; 
			paths( matrix , x , y+1 , visited , level +1 );
			visited[x][y+1] = false; 
		}
		
		if( y-1 >= 0 && visited[x][y-1] == false && matrix[x][y-1] != 2 ){//left
			visited[x][y-1] = true; 
			paths( matrix , x , y-1 , visited , level +1 );
			visited[x][y-1] = false; 

		}
		if( x+1 < matrix.length && visited[x+1][y] == false && matrix[x+1][y] != 2 ){//down
			visited[x+1][y] = true; 
			paths( matrix , x+1 , y , visited , level +1 );
			visited[x+1][y] = false; 
		}
		if( x-1 < matrix.length && visited[x-1][y] == false && matrix[x-1][y] != 2  ){//up
			visited[x-1][y] = true; 
			paths( matrix , x-1 , y , visited , level +1 );
			visited[x-1][y] = false; 
		}

	
		
		
		
	}
	
	
	
}
