# Participante API
![project version](https://img.shields.io/badge/version-1.0.0-blue.svg)
- **Time:** Luis Garcia
- **Desde:** 16/02/2024

**Propósito:** Criação de uma API [CRUD] para gerenciamento de participantes.

**Descrição:** Este projeto servirá de Lab para validação das tecnologias descritas logo abaixo, dando subsídio tecnico
para a validação de futuras arquiteturas.

Critérios utilizados para construção da **API**:
- Cobertura de testes (Unitários, integrados e cobertura);
- Design de API RESTful;
- Documentação API: OpenApi 3
- Utilização Banco de Dados em Memória: H2;
- Java com JDK 17;
- Springboot 3;
- Gradle para controle de dependências;
- Hospedar em cloud: Heroku.

# Fases do Projeto / Roadmap
| Data       | Fase             | Descrição                                                                               |
|------------|------------------|-----------------------------------------------------------------------------------------|
| 16/02/2024 | Configuração     | Criação do esqueleto do projeto utilizando SpringBoot                                   |
| 16/02/2024 | Configuração     | Criação do repositório no Github                                                        |
| 16/02/2024 | Configuração     | Adição da Lib do Actuator (Health Check)                                                |
| 16/02/2024 | Configuração     | Configuração do OpenApi para gerar swagger das APIs                                     |
| 16/02/2024 | Desenvolvimento  | Criação dos DTOs para blindar o modelo do banco de dados                                |
| 16/02/2024 | Desenvolvimento  | Criação das Rotas e Endpoints para validação das chamadas e HTTPCodes                   |
| 16/02/2024 | Desenvolvimento  | Criação das Exceções customizadas                                                       |
| 16/02/2024 | Desenvolvimento  | Configuração do Handler de erros                                                        |
| 16/02/2024 | Configuração     | Adicionar banco H2 (inMemory) para validação da POC                                     |
| 16/02/2024 | Configuração     | Configurar camada persistência JPA                                                      |
| 16/02/2024 | Configuração     | Inclusão de consulta paginada usando Pageable                                           |
| 16/02/2024 | Desenvolvimento  | Criação da Camada de Negócio chamadas aos métodos JPA                                   |
| 16/02/2024 | Teste Integração | Criação dos payloads no postman para teste de integração                                |
| 16/02/2024 | Desenvolvimento  | Criação de introdução aos testes unitários por camadas (Controller/Business/Repository) |
| 16/02/2024 | Configuração     | Adição e configuração da Lib Jacoco (Cobertura de Testes)                               |
| 16/02/2024 | Configuração     | Validação do Jacoco com as métricas de cobertura                                        |
| 16/02/2024 | CodeReview       | Passar SonarLint antes commit e fixes de codigo                                         |

# Fora Escopo (Melhoria)
| Data     | Fase     | Descrição                                                                          |
|----------|----------|------------------------------------------------------------------------------------|
| Pendente | Melhoria | Aumentar cobertura de testes do projeto (90%)                                      |
| Pendente | Melhoria | Criar validator de duplicidade para CPF na inclusão e alteração                    |
| Pendente | Melhoria | Criar profile da aplicação por ambiente dev/hml/prd                                |
| Pendente | Melhoria | Alterar para utilizar credenciais como variaveis de ambiente (externalizar)        |
| Pendente | Melhoria | Empacotamento aplicação utilizando Docker                                          |
| Pendente | Melhoria | Banco de dados e demais itens de infra tbm containerizados                         |
| Pendente | Melhoria | Criação camada de log transacional (auditoria)                                     |
| Pendente | Melhoria | Criação camada de log sistema (erros)                                              |
| Pendente | Melhoria | Configurar monitoria aplicação utilizando UniqueId/TraceId + Cloud Sleuth + Jaeger |
| Pendente | Melhoria | Criação camada de cache para consultas menos sensíveis (Redis)                     |
| Pendente | Melhoria | Criação camada de autenticação implementando Header Token                          |

# Detalhamento Técnico

## Sumário
1. [Estrutura do Projeto](#estrutura-projeto)
1. [Dependências](#dependencias)
1. [API Health Check](#api-health-check)
1. [Open API](#open-api)
1. [Code Coverage](#code-coverage)

## Estrutura Projeto
Estrutura simples de projeto gerado pelo Spring Initializer, com boot, webMvc e demais dependencias.

Arquitetura simples MVC para ganho de agilidade no desenvolvimento e facilidade em migração futura para outra arquitetura.

O projeto foi estruturado inicialmente da seguinte forma:

![estrutura](/docs/estruturaPastas.png "Estrutura Pastas")

Para executar o projeto por CLI: (lembrar de verificar as JDK configurada)
``` 
// step: executar subida springboot
gradle bootRun

// step: testes e report cobertura
gradle test
```

## Dependencias
```
// Spring Boot
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-web'
developmentOnly 'org.springframework.boot:spring-boot-devtools'

// OpenAPI
implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.3.0'

// Lombok Processor
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'

// Guava Libs - Collections Handler
implementation group: 'com.google.guava', name: 'guava', version: '33.0.0-jre'

// H2 Database
runtimeOnly 'com.h2database:h2'

// Tests
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.springframework.boot:spring-boot-testcontainers'
testImplementation 'org.testcontainers:junit-jupiter'
```

## API Health Check
Actuator: http://localhost:8080/actuator/health

![actuator](/docs/actuator.png "Actuator Page")

## Open API
- Swagger: http://localhost:8080/swagger-ui/index.html
- Docs: http://localhost:8080/v3/api-docs
- Collection Postman (import): está na pasta docs (Participante API.postman_collection.json)

![swagger page](/docs/swaggerPage.png "Swagger Page").

## Code Coverage
Para cobertura de testes estou utilizando o Jacoco que deve compilar ao final do step de testes pré configurado.

**Exemplo do report gerado:** 

![jacoco report](/docs/jacocoReport.png "Jacoco Report").

**Path em que ele fica disponível após execução:**

![jacoco path](/docs/jacocoPath.png "Jacoco Report").

