# Java Developer Challenge

Required technical test on selection process.

## Description

Microservice serving a REST API endpoints to retrieve specific information from database, based on given params.

## Getting Started

### Dependencies

* Maven project. See [pom.xml](pom.xml)

### Installing

* Clone present git repository.

### Executing program

* Run program from: [RentalCarApplication](src/main/java/com/challenge/rentalcar/RentalcarApplication.java)

## Author

Oriol Ballesteros - ballesterosoriol@gmail.com

## Comments

* Implemented architecture: [Hexagonal Architecture](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/)

* Coding principles and good practices:
    * [SOLID](https://www.baeldung.com/solid-principles)
    * [KISS](https://www.baeldung.com/java-clean-code)

* Testing: [JUnit](https://junit.org/junit5/), a simple, straightforward and powerful testing framework.

* [Lombok's API](https://projectlombok.org/) usage was evaluated. While super-simple, yet powerful, and straightforward to use, for an small project as this is, it was considered unnecessary.

***

## Decision-making

La presente aplicación puede levantar dudas o consideraciones.
Aquí se intentan resolver algunas de las posibles dudas.

La __API__ presentada responda a:
* endpoints `/pricing/{carModel}` y `/pricing/surcharge/{carModel}`; obligatorios según las instrucciones del challenge.
* endpoints `/client/points` y `/car/inventory`; sumadas a las anteriores, poder representar la totalidad de funciones de la aplicación descritas en el challenge:
  _...three primaries functions: Have an inventory of cars, Calculate the price for rental, Keep the track of the customer loyalty points_

Se ha decidido dejar el nivel de testing a la cobertura presentada priorizando la gestión del tiempo.
Los tests elaborados se consideran una buena representación de la totalidad de los tests que se pueden elaborar.
Ejemplo: `PriceCalculatorImplTest` sirve de ejemplo para los tests ausentes de `SurchargeCalculatorImpl`. Lo mismo ocurre con los Controllers no testeados.
Los UT e IT ausentes suponen una forma de repetición de los tests presentes. 

El endpoint de clientes supone una mínima representación simbólica de las supuestas funciones de la aplicación en el control de los _loyalty points_ de los clientes.
La gestión de clientes seria implica autenticación, registro, roles... elementos fuera del scope del presente challenge.

Uso generalizado de la keyword `final`. Costumbre de programación nacida cuando el uso de `final` suponía una mejora (aunque menor) en la gestión de la memoria.
Actualmente la conservo en proyectos personales por facilitar, personalmente, la lectura de código.

Algunos métodos como getters, setters o constructores se conservan aún utilizarse. Una primera refactorización en un escenario laboral real sin duda sería limpiarlos.
Al tratarse de una prueba, la línea entre simulación y efectividad en el desarrollo queda más difuminada; 
lo que lleva a conservar esos métodos al considerarlos una mejor muestra de la forma de aproximación a la implementación de código.

Se da preferencia a la atomizidad e independencia de las clases y test. Chocan así los principios KISS (Keep it super simple) y DRY (Don't repeat yourself); 
al quedar cierto margen, debatible, a considerar mejor centralizar bloques de código similares.
Aunque a nivel de refactorización o de Code-smell pueda suponer una puntuación menor, la práctica profesional ha corroborado las ventajas de la atomizidad aún suponiendo cierto mínimo margen de código repetido.


