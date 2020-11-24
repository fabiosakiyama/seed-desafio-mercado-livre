# Desafio 2 da segunda jornada do Dev Eficiente

O desafio consiste em implementar uma parte do mercado livre, seguindo as práticas do CDD (Cognitive Driven Development), proposto na jornada
do https://deveficiente.com/

# Stack
- JDK 11
- Spring boot 2.3.5
- Lombok
- H2
- JUnit 5
- Swagger
- Security com Keycloak

# Como subir o Keycloak?

Seguindo esse tutorial https://www.baeldung.com/spring-boot-keycloak, é necessário baixar o standalone, subir local e configurar:
- Um realm com nome 'SpringBootKeycloak'
- Um client com nome/resource 'mercado-livre-app'

# Gerando token

Primeiro, é necessário criar um usuário no Keycloak. Depois, basta fazer a chamada:
```
curl --location --request POST 'http://localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=9B43E751AC8743F07EE81E6CD9932C50.laptop-eb574g16; JSESSIONID=9B43E751AC8743F07EE81E6CD9932C50; OAuth_Token_Request_State=5f003f4f-9eff-441a-9564-864d78006a75' \
--data-urlencode 'client_id=mercado-livre-app' \
--data-urlencode 'username=seu-usuario' \
--data-urlencode 'password=sua-senha' \
--data-urlencode 'grant_type=password'
```

# Como rodar?

- Utilizar o maven wrapper p/ buildar (./mvnw clean install) e subir a app (./mvnw spring-boot:run)
- O banco configurado é o h2 em memória, ou seja, não vai persistir dados. (http://localhost:8080/h2/login.do)
- Acesse http://localhost:8080/swagger-ui/#/ para ver os endpoints disponíveis
