# EDA 2 - Biblioteca

##Enunciado

### MVP
Se requiere gestionar el alquiler de libros para una biblioteca. Para esto se necesita guardar la siguiente
informacion: 
- Libros: con id, nombre, genero, autor (Unica persona), editorial, fecha de lanzamiento. 
- Clientes: id, Dni, Nombre, Apellido, Fecha Alta, Estado(Activo, Dado de baja, Vetado).
- Empleados: id, Dni, Nombre, Apellido, cargo.
- Orden de alquiler: cliente (Siempre que este activo), los libros que alquila (5 maximo)
el empleado que tomo la orden, la fecha de devolucion, y el estado del alquiler.
- Stock de los libros

El sistema debe poder cumplir con las siguientes funcionalidades:
- ABM de libros
- ABM de Clientes
- ABM de Empleados
- ABM de Ordenes
- Manejo stock de los libros 

### V1
TBD

## Participantes
- Franco Natanael Tomaino 
- Zelaya Marcelo Andres 
- Gonzalo Elias Fernandez
- Sergio Cervera Billordo

## Environment
- Java 1.8
- Junit 4.0