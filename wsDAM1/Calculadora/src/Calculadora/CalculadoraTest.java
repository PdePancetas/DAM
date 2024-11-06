package Calculadora;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;


class CalculadoraTest {
	private static  Calculadora calculadora;
	
	@BeforeAll
	public static void creaCalculadora() {
		calculadora=new Calculadora(20,10);
	}
	/*
	@AfterEach
	public void borraCalculadora() {
		calculadora=null;
	}*/
	
	@Test
	void testSuma() {
		double valorEsperado = 30; 
		double resultado = calculadora.suma(); 
		assertEquals(valorEsperado,resultado,0); 

	}

	@Test
	void testResta() {
		double valorEsperado = 10; 
		double resultado = calculadora.resta(); 
		assertEquals(valorEsperado,resultado,0); 

	}

	@Test
	void testMultiplica() {
		double valorEsperado = 200; 
		
		double resultado = calculadora.multiplica(); 
		assertEquals(valorEsperado,resultado,0);
	}

	@Test
	void testDivide1() {
		double valorEsperado = 2; 
		double resultado = calculadora.divide(); 
		assertEquals(valorEsperado,resultado,0);
		
			}
	
	@Test
	void testDivide2() {
		
		try{
			calculadora.divide();
		     //Fail hace que el método falle
		    fail("Error división por cero");
		}catch(ArithmeticException e) { 
			//Prueba correcta
		} 
			}
	
	
}
