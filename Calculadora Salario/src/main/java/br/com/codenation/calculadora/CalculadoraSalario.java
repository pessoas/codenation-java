package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		if(salarioBase <= 1039.00){
			return 0;
		}
		double salarioMenosInss = calcularInss(salarioBase);
		double salarioFinal = calcularIrrf(salarioMenosInss);

		return Math.round(salarioFinal);
	}
	

	private double calcularIrrf(double salarioCalculo) {
		if(salarioCalculo < 3000.01) {
			return salarioCalculo;
		}else if(salarioCalculo < 6000.01) {
			return  salarioCalculo * 0.925;
		} else {
			return salarioCalculo * 0.85;
		}
	}

	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase < 1500.01) {
			return salarioBase * 0.92;
		} else if (salarioBase < 4000.01) {
			return salarioBase * 0.91;
		} else {
			return salarioBase * 0.89;
		}
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/