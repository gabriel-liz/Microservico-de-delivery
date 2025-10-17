# Microservico-de-delivery

Foi desenvolvido um microServiço de delivery onde é criado uma encomenda inicial e após pode-se submeter a encomenda para processamento, Retirada da encomenda pelo Entregador e por fim Regitrar a conclusao da encomenda(entrega).


Para rodar a aplicação é necessario ter o banco de dados Postgres rodando. Para isso temos o arquivo docker-compose.yml no projeto
Alem de ter o postgres rodando é necessario criar os bancos:

courierdb, courierdb_test, deliverydb e deliverydb_test

**Para subir o arquivo:**

docker-compose up -d



Alem disso esse arquivo também start o Eureka e o Kafka


**Para acessar o Postgres:**

http://localhost:8083/

PGADMIN_DEFAULT_EMAIL: dba@delivery.com

PGADMIN_DEFAULT_PASSWORD: delivery


**Para acessar o Kafka:**

http://localhost:8084/


**Para acessar o Eureka:**

http://localhost:8761/




**Para testar a aplicação pode-se usar a Collection com as requests no Postman:**

Importar o arquivo: **postman-collection.yml.txt** que esta no projeto 
