# user-management
API para la creación de usuarios

Se debe crear una base de datos en postgres con el nombre user_management_db, los script estan en una migración de flywaydb, lo cual hará que se creen todas las tablas necesarias de forma automatica.

Para explorar los endpoints ir al siguiente link para mirar las definiciones de estos.
http://localhost:8080/swagger-ui/index.html#/user-controller/createUser


nota: al final agregué la parte de docker, en caso de usar docker se debn seguir los siguientes pasos 

# Creación de la red para comunicar los dos contenedores
docker network create mynet

# Descargar la imagen de postgres que usa la palicación: 
docker pull postgres:13

# Creación del container de postgres:
docker run -p5432:5432 --name postgres-container-13 -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=user_management_db -d --network=mynet postgres:13

# Creación de imagen de la aplicación:
docker build -t user-man-image .

# Creación del container de la aplicacion
docker run -p8080:8080 --name user-man-container --network=mynet user-man-image

# Una vez hecho los pasos anteriores, deberíamos ver la aplicación corriendo en el puerto 8080


# Nota: dejo el curl del servicio de creacion de usuarios


curl --location 'http://localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "Hunter2*",
"phones": [
    {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
    }
]
}'
