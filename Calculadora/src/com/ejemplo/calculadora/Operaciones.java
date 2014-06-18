package com.ejemplo.calculadora;

public class Operaciones {
	public String sumar(Double numero1, Double numero2 ){
		String respuesta =String.valueOf(numero1+numero2);
		return respuesta;
	} 
	public String restar(Double numero1, Double numero2 ){
		Double resultado = null;
		if (numero1 >numero2){
			resultado= numero1-numero2;
		}
		if (numero2>numero1){
			resultado=numero2-numero1;
		}
		String respuesta =String.valueOf(resultado);
		return respuesta;
	} 
	public String multiplicar(Double numero1, Double numero2 ){
		String respuesta =String.valueOf(numero1*numero2);
		return respuesta;
	} 
	public String dividir(Double numero1, Double numero2 ){
		String respuesta =String.valueOf(numero1/numero2);
		return respuesta;
	} 
	
}
