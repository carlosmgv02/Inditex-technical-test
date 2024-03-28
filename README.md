# Inditex Pricing API

## Descripción

Este proyecto es una API de precios para una prueba técnica de Inditex.

## Clases Principales

El proyecto se compone de varias clases clave:

- `Price`: Esta clase de entidad representa la información del precio en la base de datos.
- `PriceRepository`: Interfaz para el repositorio que extiende `JpaRepository`, utilizada para acceder a la información de precios en la base de datos.
- `IPriceService`: Interfaz del servicio que define los métodos para obtener información de precios.
- `PriceService`: Implementación de `IPriceService` que contiene la lógica de negocio para recuperar información de precios.
- `PriceController`: Controlador REST que expone los endpoints de la API para consultar precios.
- `PriceNotFoundException | GlobalExceptionHandler`: Clases de excepción y manejador global de excepciones para manejar excepciones personalizadas.
## Requisitos

- JDK 17
- Maven
- Docker

## Instalación y Ejecución

### Construir el Proyecto

Primero, construye el proyecto con Maven. Ejecuta el siguiente comando en el directorio raíz del proyecto:

```bash
mvn clean package
```



Este comando generará un archivo JAR ejecutable en el directorio `target/`.
### Construir la Imagen Docker

Después de construir el proyecto, puedes crear una imagen Docker ejecutando:

```bash
docker build -t inditex-pricing-api .
```


### Ejecutar la Aplicación con Docker

Una vez que la imagen se ha construido, se puede iniciar la aplicación ejecutando:

```bash
docker run -p 8080:8080 --name inditex-container inditex-pricing-api
```



Esto iniciará la aplicación y la hará accesible en `http://localhost:8080`.
## Uso

La API está disponible en `http://localhost:8080/api/prices`.
- `date`: Fecha y hora de aplicación del precio en formato `yyyy-MM-dd HH:mm:ss`.
- `productId`: Identificador del producto.
- `brandId`: Identificador de la cadena.

Ejemplo de solicitud con `curl`:

```bash
curl "http://localhost:8080/api/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1"
```


