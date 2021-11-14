# TPE Ingeniería de Software 2

Demo básica de RabbitMQ

## Instalar antes de ejecutar

Hay que tener instalado Java, Python, NodeJS y Ruby

Primero instalar docker y docker-compose 

[Instalar docker](https://docs.docker.com/get-docker/)

[Instalar docker-compose](https://docs.docker.com/compose/install/)

Luego para levantar RabbitMQ en la instancia de docker ejecutar este comando en el directorio inge2-tpe:
```
docker-compose up
```

Instalar la librería Bunny para la parte en Ruby
```
gem install bunny --version ">= 2.13.0"
```

Instalar Pika para la parte en Python
```
python -m pip install pika --upgrade
```

Instalar la librería amqp.node para la parte en JS
```
npm install amqplib
```

## Guia de ejecución

Levantar la webapp publisher en spring, ir al directorio publisher y ejecutar:
```
mvn spring-boot:run
```

Luego ejecutar la cantidad de redimension workers que se quiera, dentro del directorio redimension-worker ejecutar:
```
ruby redimension-worker.rb
```

Despues ejecutar el enviador de email, dentro del directorio email-sender-consumer ejecutar:
```
node email-sender-consumer.js
```

Por ultimo ejecutar el anotador de estadísticas, dentro del directorio stats-consumer ejecutar:
```
py stats-consumer.js
```