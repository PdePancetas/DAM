package Calculadora;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest2 {
	
	private Calculadora calcu;
	
	@ParameterizedTest
	@CsvSource({"20, 10, 2"}) 
	public void testDivide0 (int a, int b, int valorEsperado){ 
	calcu=new Calculadora(a,b);
	int resultado = calcu.divide(); 
	assertEquals(valorEsperado, resultado); 
	} 
	
	@ParameterizedTest
	
	@CsvSource({"20,10,2", "30,-2,-15", "5, 2, 2" }) 

	public void testDivide1(int a, int b, int valorEsperado){ 
	calcu=new Calculadora(a,b);
	int resultado = calcu.divide(); 
	assertEquals(valorEsperado, resultado); 
	}

	@ParameterizedTest
	@CsvSource({"5, 0, 1" }) 
	public void testDivide2 (int a, int b, int valorEsperado){ 
		calcu=new Calculadora(a,b);
		try{
			calcu.divide();
		     //Fail hace que el método falle
		    fail("Error división por cero");
		}catch(ArithmeticException e) { 
			//Prueba correcta
		} 
	}
}
