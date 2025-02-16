# Microserviço de Pagamentos

## Visão Geral
O microserviço de pagamentos é responsável pelo processamento e gerenciamento de pagamentos dentro do ecossistema do sistema de autoatendimento de fast food. Ele fornece APIs para criação, consulta e atualização de pagamentos, garantindo segurança e rastreabilidade das transações.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.1**
- **Spring Web** (para exposição de endpoints REST)
- **Spring Data JPA** (para persistência de dados)
- **PostgreSQL** (banco de dados relacional)
- **Docker & Docker Compose** (para conteinerização e orquestração)
- **Swagger/OpenAPI** (documentação da API)
- **JUnit 5** (para testes unitários)
- **Surefire & JaCoCo** (para geração de relatórios de cobertura e integração com SonarQube Cloud)

## Arquitetura
Este microserviço segue a arquitetura **hexagonal**, separando claramente os componentes internos e externos:

- **Camada de Domínio**: Modelação da entidade `Pagamento` e regras de negócio.
- **Camada de Aplicação**: Contém serviços que implementam as regras de negócio e interagem com os adaptadores.
- **Adaptadores de Entrada**: Controllers responsáveis pela exposição das APIs.
- **Adaptadores de Saída**: Repositórios para persistência e comunicação com outras APIs.

## Endpoints Disponíveis

### Criar Pagamento
```http
POST /register-payment-record
```

Query Params:
```
orderId (Long) - Obrigatório
userId (Long) - Opcional
totalPrice (Double) - Obrigatório

PUT api/payments/register-payment-record?orderId=1&userId=1&totalPrice=20.5
```

Response:
```json
{
  "orderId": 1,
  "userId": 1,
  "amount": 20.5,
}
```
Consultar Pagamento por ID

```http
GET /check-status/{paymentId}
```

Response:
```json
{
  "orderId": 1,
  "userId": 1,
  "amount": 20.5,
}
```
Atualizar Status do Pagamento
```http
PUT /send-to-bank/{paymentId}
```

Request Body:
```
paymentId
```
Response:
```json
{
  "id": 1,
  "orderId": 1,
  "userId": 1,
  "amount": 20.5,
  "status": "APPROVED"
}
```

## Configuração e Execução
### Executando com Docker Compose

1. Clone este repositório:
```bash
git clone https://github.com/ThaynaraDaSilva/ez-fastfood-catalog-ms
cd ez-fastfood-catalog-ms
```
2. Execute o Docker Compose:
```bash
docker-compose up -d
```

3. Acesse a documentação da API via Swagger:
```bash
http://localhost:8080/swagger-ui.html
```

Executando Localmente (Sem Docker)

1. Configure o PostgreSQL e atualize application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pagamentos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

2. Execute a aplicação com:
```bash
mvn spring-boot:run
```

## Cobertura de Testes

Os testes foram escritos utilizando JUnit 5, garantindo a qualidade e confiabilidade do código. Para geração de relatórios e integração com o SonarQube Cloud, utilizamos Surefire e JaCoCo.

Os relatórios de cobertura podem ser visualizados no painel do SonarQube e servem como evidência do cumprimento dos requisitos de qualidade do código.

Execute os testes com:
```bash
mvn test
```
## Evidência da Cobertura de Testes
Abaixo está uma captura de tela da cobertura de testes gerada pelo SonarQube Cloud:

![Image](https://github.com/user-attachments/assets/84259242-098a-4f79-a1b8-512f965f7514)

![Image](https://github.com/user-attachments/assets/8a362bbc-f75c-4a56-a967-fab191984128)

![Image](https://github.com/user-attachments/assets/ff53276e-ff4b-428a-8055-e7d67f3ef24f)

Considerações Finais

Este microserviço pode ser integrado a outros componentes do sistema de autoatendimento, garantindo um fluxo seguro e eficiente de pagamentos. Para futuras melhorias, pode-se incluir suporte a diferentes gateways de pagamento e webhooks para notificação de status.

https://www.javatodev.com/how-to-use-amazon-sqs-with-spring-boot/

 aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/order-payment-queue --message-body '{\"orderId\":1,\"userId\":1,\"amount\":20.5}'
 
