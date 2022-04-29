# partimos de uma imagem padrão do Java 11 para a construção da imagem do docker do nosso micro serviço
FROM adoptopenjdk/openjdk:11alpine

# o Run é utilizado para executar comando no "terminal" do micro serviço
# apk update é utilizado para atualziar os repositórios que o container pode fazer download
# apk bash é utilizado para adicionar o bash(shell scrpit - linguagem de terminal CMD)
RUN apk update && apk add bash

# criar uma pasta nova dentro do container
RUN mkdir /opt/app

# copiar o arquivo app.jar para a pasta criada anteriormente
COPY target/app.jar /opt/app/app.jar

# abrir (expor) porta para começar a receber as requisições
EXPOSE 8080

# para executar o jar do microserviço
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "/opt/app/app.jar"]

# docker build -t primeiro-ms
