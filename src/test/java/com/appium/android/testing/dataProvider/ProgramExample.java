package com.appium.android.testing.dataProvider;

import java.util.Iterator;

public class ProgramExample {

	public static void main(String[] args) {

		int[][] twoDArray = {{ 1, 4, 6 }, 
							{  0, 3, 4 }, 
							{  5, 7, 3 } };

		// Output = 6

		int minValue = twoDArray[0][0];
		int minColumn=0;

		for (int i = 0; i < twoDArray.length; i++) {

			for (int j = 0; j < twoDArray.length; j++) {
				if (twoDArray[i][j] < minValue) {
					minValue = twoDArray[i][j];
					minColumn=j;
					
				}
			}
		}
		int maxValue=twoDArray[0][minColumn];
		int k=0;
		while (k<3){
			  if (twoDArray[k][minColumn] > maxValue) {
               maxValue = twoDArray[k][minColumn];
		}

			  k++;

        }	
        System.out.println("Minimum value in array: " + minValue);

        System.out.println("Maximum value in the column of minimum value: " + maxValue);

	}
		

}
