package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		for(int i = 0; i < elements.length; i++) {
			soma += elements[i];
		}
		return soma / elements.length;
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);
		
		int contMax = 0;
		int contCurr = 0;
		
		int max = elements[0];
		int curr = elements[0];
		
		for(int i = 0; i < elements.length; i++) {
			
			if(contCurr >= contMax) {
				max = curr;
				contMax = contCurr;
				contCurr = 0;
			}
			if(max == elements[i]) {
				contMax++;
			} else {
				if (curr == elements[i]){
					contCurr++;
				} else {
					curr = elements[i];
					contCurr = 1;
				}
				
			}
		}
		return max;
	}

	public static int median(int[] elements) {
		
		Arrays.sort(elements);
		
		if(elements.length%2 != 0) {
			return elements[elements.length/2];
		} else {
			return (elements[elements.length/2] + elements[(elements.length/2) - 1]) / 2;
		}
		
	}
}