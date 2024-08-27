# DEVSU CHALLENGE - DAVID GONZÁLEZ
Este proyecto es respuesta a la evaluación técnica para java senior backend developer.

## Puesta en marcha
Para poder ejecutar la aplicación es necesario

1) Docker
2) El puerto 8080 libre

Esta aplicación se puede arrancar con un solo comando, siguiendo los pasos:


1) `git clone https://github.com/Gonza740/devsu-challenge-java-dg.git`
2) `cd devsu-challenge-java-dg`
3) `docker compose up`
4) Ingresar al enlace http://localhost:8080/api/webjars/swagger-ui/index.html

## Uso del swagger
Una vez que ingrese a la dirección detallada arriba, se mostrará una pantalla como sigue:
![alt text](https://github.com/Gonza740/devsu-challenge-java-dg/blob/master/documentation/swagger.png?raw=true)

En la cual podrá elegir entre los microservios para ver y utilizar los métodos de cada uno, como se muestra:


![alt text](https://github.com/Gonza740/devsu-challenge-java-dg/blob/master/documentation/clients.png?raw=true)

## Pruebas unitarias y de integración

Las pruebas para cumplir los requisitos 5 y 6 están en el microservicio de clientes, los command handlers tienen las pruebas unitarias y el controller tiene las pruebas de integración con Karate.


## Base de datos

- El scprit BaseDatos.sql se encuentra en devsu-challenge-database
- El puerto de la base también se ha expuesto para poder ingresar mediante cualquier gestor, **url:** jdbc:postgresql://localhost:5432/DEVSU, **username:** DEVSU, **password:** DEVSU

## Postman
La collecion de postman para las pruebas, recomiendo utilizar el swagger, se encuentra sobre documentation.

## Arquitectura de la solución
![alt text](https://github.com/Gonza740/devsu-challenge-java-dg/blob/master/documentation/arquitecture.png?raw=true)

## Arquitectura de los servicios de clientes y cuentas
![alt text](https://github.com/Gonza740/devsu-challenge-java-dg/blob/master/documentation/software.png?raw=true)

## Diseño de la base de datos
![alt text](https://github.com/Gonza740/devsu-challenge-java-dg/blob/master/documentation/database.png?raw=true)
