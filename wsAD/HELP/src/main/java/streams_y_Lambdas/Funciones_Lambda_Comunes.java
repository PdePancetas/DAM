package streams_y_Lambdas;

public class Funciones_Lambda_Comunes {
	
	///////////////////////////////////////////////////////////////
	//TODOS LOS OBJETOS (T,U,...) SON GENERICOS
	//////////////////////////////////////////////////////////////

//	Predicate<T>: Recibe un objeto de tipo T y devuelve una expresion booleana.
//	Consumer<T>: Recibe un objeto de tipo T y no devuelve ningun valor.
//	Supplier<T>: No recibe ningun objeto pero si devuelve un valor de tipo T.
//	Function<T, R>: Recibe un objeto de tipo T y devuelve un valor de tipo T.
//	UnaryOperator<T>: Recibe un objeto de tipo T y devuelve otro de tipo T (el mismo tipo).
//	Comparator<T>: Define una regla para comparar dos objetos de tipo T.
	
//	BiFunction<T, U, R>: Recibe un objeto de tipo T, otro de tipo U y devuelve un valor de tipo R.
//	BinaryOperator<T>: Recibe dos objetos de tipo T y devuelve otro de tipo T (el mismo tipo).
//	BiPredicate<T, U>: Recibe un objeto de tipo T, otro de tipo U y devuelve un booleano.
//	BiConsumer<T, U>: Recibe un objeto de tipo T, otro de tipo U y no devuelve ningun valor.
	
//	ToIntFunction<T>, ToDoubleFunction<T>, etc.: Transforman un objeto de tipo T en un primitivo como int, double, etc.
//	IntFunction<R>, LongFunction<R>, DoubleFunction<R>: Toman un tipo primitivo (int, long, double) como entrada y producen un resultado de tipo R.
//	IntPredicate, DoublePredicate, etc.: Operan sobre valores primitivos como entrada y devuelven booleanos.
//	IntUnaryOperator, LongUnaryOperator, etc.: Operadores específicos para tipos primitivos con entrada y salida del mismo tipo.
//	IntBinaryOperator, DoubleBinaryOperator, etc.: Versión de BinaryOperator para tipos primitivos.
//	
//	Concurrencia: 
//		Callable<T>: No recibe ningun objeto, ejecuta una tarea y devuelve un resultado de tipo T o una excepcion.
//		Runnable<T>: No recibe ningun objeto, ejecuta una tarea y no devuelve ningun resultado.
}
