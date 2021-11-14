# TPE Ingeniería de Software 2

Demo básica de RabbitMQ

## Instalar esto antes de ejecutar

Primero instalar docker y docker-compose

[Instalar docker](https://docs.docker.com/get-docker/)

[Instalar docker-compose](https://docs.docker.com/compose/install/)

Luego para levantar RabbitMQ en la instancia de docker ejecutar este comando en el directorio inge2-tpe:
```
docker-compose up
```

Instalar Pika para la parte en Python
```
python -m pip install pika --upgrade
```

Instalar la librería amqp.node para la parte en JS
```
npm install amqplib
```