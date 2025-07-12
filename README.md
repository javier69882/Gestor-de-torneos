Javier Ignacio Van Rysselberghe Argomedo
Vicente Antonio Garcia ArayaVicente Antonio Garcia Araya
Grupo 10

Tema 1:  Sistema de Gestión de Torneos

Este sistema está diseñado para facilitar la organización de torneos deportivos o de juegos. Permitirá a un organizador definir las características del torneo, como el nombre, la disciplina (ej. fútbol, ajedrez, videojuegos), las fechas y un formato principal (como eliminatoria directa, eliminatorio doble, liga simple...). Se podrán inscribir participantes, ya sean jugadores individuales o equipos, almacenando información básica como nombres y datos de contacto. El sistema deberá ser capaz de generar un calendario de enfrentamientos o un bracket inicial basado en los inscritos y el formato. Durante el torneo, se registrarán los resultados de cada enfrentamiento, lo que actualizará automáticamente las posiciones, el avance en el bracket o las tablas de clasificación. Los usuarios podrán visualizar el estado actual del torneo, los próximos encuentros y las estadísticas generales.

Que puntos del enunciado se cumplen con nuestro proyecto

-Facilita la organización de un torneo que puede ser de videojuegos o de algún deporte físico

-Un administrador puede crear un torneo, dándole un nombre, seleccionando si es torneo físico o videojuego, los equipos que lo jugaran(los equipos pueden ser de 0 o mas participantes), la fecha de inicio del torneo, y su modalidad(eliminatoria directa, doble eliminación, liga simple).

-Los usuarios pueden acceder al programe, y podrán acceder a la gestión de equipos donde podrán crear sus equipo de 0 o mas participante, donde deben crear un equipo con nombre, y luego crear participantes con nombre, apellido, correo, y luego añadirlos a algún equipo

-El sistema dependiendo de la modalidad muestra una tabla o braket, en liga simple genera una tabla, en eliminación directa y doble genera un braket, pero que se muestra por rondas, ósea que puedes ver los resultados seleccionando la ronda.

-Los usuarios pueden seleccionar cualquier torneo, dentro de este pueden ver el estado actual, la información general del torneo y las fechas


En el repositorio estará disponible
-Diagrama de clases UML
-Diagrama de casos de uso
-El proyecto claramente
-Video de como se ve nuestra interfaz https://youtu.be/lHbMbK5ZVy4

Patrones de diseño

Utilizamos dos patrones de diseño, DECORATOR Y SINGLETON.

Singleton lo utilizamos para crear datos por defecto en el programa, con SingletonDatosPrueba, ósea que una vez ejecutado, ya estarán disponibles, equipos con participantes y torneos de todas las modalidades, ya creados, esto se hace en
-Constructor de Equipo en paquete Visual
-Main de paquete visual para cargar los torneos allí
-DobleEliminacionDecoratorTest
-EliminacionDirectaDecoratorTest
-LigaSimpleTest

Decorator
Se utilizo una interfaz ITorneo, para definir métodos comunes en nuestros torneos, además se creo TorneoBase, el cual puede ser TorneoFisico o TorneoVideojuego, además estos torneos base pueden ser decorados con TorneoDecorator con las disitintas modalidades, DobleEliminacionDecorator, EliminacionDirectaDecorator,LigaSimple(también es decorador).
Paquete Logico
-DobleEliminacionDecorator
-EliminacionDirectaDecorator
-ITorneo
-LigaSimple
-TorneoBase
-TorneoDecorator
-TorneoFisico
-TorneoVideojuegos 
-Paquete visual solo mostrara esto
Ademas esto nos permite crear infinitas modalidades, solo creando nuevos decoradore, y no otro gestor de equipos.

Decisiones importantes de nuestro proyecto
-Elegir este tema y no los otros, porque pensamos como seria hacer cada uno de los proyectos antes de elegirlo.
-Las modalidades que elegimos, porque cada una es distinta logicamente
-Patrones de diseño a utilizar, fue bueno elegir estos porque facilito crear las modalidades.
-Diseño de la interfaz, influyo en como íbamos a manejar los torneos, adminitradores y usuarios.
-Diseño de como es el braket, ya que buscamos una forma de fácil de generar un braket usando java, ósea haciéndolo por rondas.

Problematicas enfrentadas
-Patrones de diseño, no sabíamos como implementarlo al incio
-Como serian las vistas de los usuarios y administradores
-Utilizar JComboBox, porque en el proyecto anterior solos usamos imágenes, y mostramos paneles de forma simple, no sabíamos como usarlo aun.
-No saber como registrar bien los puntos al inicio, lo solucionamos creando una clase partido, que hace cada partido sea un objeto, con lo cual también solucionamos registrar la hora y fecha, además de saber si ya se jugo.
-Como equipo una autocritica seria no haber implementado los patrones de diseño desde el inicio, porque esto habría facilitado mucho todo.




