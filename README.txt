# Proyecto Base de Datos SQLite - GRUPO 31

Este proyecto es una billetera virtual en Java desarrollada en Eclipse que utiliza una base de datos SQLite y una GUI hecha con componentes personalizados con AWT y SWING. La aplicación es completamente portable y puede ejecutarse en cualquier PC con Java instalado, sin necesidad de una instalación previa de SQLite en el sistema. 

## Correcciones
- Se soluciona la compra para las criptomonedas que fallaban.
- Se implementa el uso de excepciones (login, registro y compra).
- Se agregan las cantidades compradas y gastadas en las transacciones.
- Se actualiza el monto total de la base datos en la tabla del inicio.
- Se reemplaza el uso del thread con lambda por un timer/timerExcecution.

## Aviso

- Decidimos no usar ARS y solo USD para una mejor interracion visual, al poder mostrar una menor cantidad de dígitos.


## Consejo

- El programa no debería tirar errores, excepto cuando se ingresan tipos de datos distintos a los solicitados entre un String y un double.
- Al momento de iniciar sesión y generar datos, el usuario recibe 30000 dólares FIAT para poder comprar y se genera en la DB las monedas cripto con stock para poder comprar.

