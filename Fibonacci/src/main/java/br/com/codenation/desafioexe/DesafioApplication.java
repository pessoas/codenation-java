package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> fib = new ArrayList<>();

		int pos = 1;

		fib.add(0);
		fib.add(1);

		while(fib.get(pos) < 350) {
			fib.add(fib.get(pos) + fib.get(pos - 1));
			pos++;
		}

		return fib;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}