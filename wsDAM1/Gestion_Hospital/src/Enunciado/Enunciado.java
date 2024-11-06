/*Sistema de gestión de un hospital

Clases:
    1. Paciente: Clase que representa un paciente en el hospital. 
       Contiene información como nombre, fecha de nacimiento, historial médico, etc.
    2. Enfermedad: Clase que representa una enfermedad. 
       Contiene información como nombre, código, síntomas, tratamiento, etc.
    3. HistorialMedico: Clase que representa el historial médico de un paciente.
       Contiene una lista de Enfermedades y sus fechas de diagnóstico.
     	
     	
Enunciado:

Desarrollar un sistema que permita gestionar la información de los pacientes de un hospital.
El sistema debe:
    • Almacenar la información de los pacientes en un HashMap. La clave es el DNI del paciente 
      (el DNI del paciente se asocia al objeto paciente que NO TIENE el DNI como atributo)
    • Permitir al usuario registrar un nuevo paciente.
    • Permitir al usuario buscar un paciente por su DNI.
    • Permitir al usuario agregar una nueva enfermedad al historial médico de un paciente.
    • Permitir al usuario consultar el historial médico de un paciente.
    • Buscar todos los pacientes que hayan tenido una Enfermedad concreta.
    
    
Manejo de excepciones:
    • Se debe controlar la posibilidad de que el usuario introduzca un DNI no válido.
    • Se debe controlar la posibilidad de que el usuario intente agregar una enfermedad que no existe.
    • Se debe controlar la posibilidad de que el usuario intente consultar el historial médico de un 
   	  paciente que no existe.
*/