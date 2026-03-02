
# CentralDesk API
**Sistema REST para gerenciamento de chamados.**
Desenvolvido por [Felipe Pereira](https://www.linkedin.com/in/felipeluizpereira/)

## Stack

- Java 17+
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Spring Security
- SpringDoc OpenAPI 2.8.5
- Maven

## Arquitetura
- REST API
- Camadas separadas
- DTOs
- Validação
- Tratamento global de exceções
- Controle por roles

## Estrutura Atual
- Entidades principais:
    - Usuario
    - Analista
    - Grupo
    - Chamado
    - HistoricoChamado


- Relacionamentos:
    - Analista pertence a múltiplos grupos
    - Chamado pertence a um grupo
    - Chamado possui histórico de alterações

## Ciclo de Vida do Chamado

ABERTO → EM_ATENDIMENTO → RESOLVIDO → FINALIZADO

Regras implementadas:

- Chamado só pode ser assumido por analista pertencente ao grupo.
- Chamado só pode ser resolvido se estiver EM_ATENDIMENTO.
- Histórico é registrado automaticamente a cada transição.

## Segurança
Spring Security configurado com autenticação padrão para ambiente de desenvolvimento.

## Testes Automatizados

Foram implementados testes de integração utilizando:

- SpringBootTest
- MockMvc
- Spring Security Test

Cenários testados:
- Criação de chamado (201)
- Validação (400)
- Recurso inexistente (404)
- Autenticação (401)

## Front-End
### Integração Angular + Spring Boot

- Consumo de API REST `/chamados`
- Paginação via query params (`page`, `size`)
- Renderização dinâmica com `@for`
- Controle manual de change detection
- Tratamento de CORS
- Reactive forms
- Backend protegido por Spring Security (temporariamente liberado para desenvolvimento)

## 🚀 Status do Projeto

Versão atual: **v0.2.0**

### Funcionalidades implementadas:

- ✅ Criação de chamado
- ✅ Reabertura de chamado
- ✅ Finalização automática após 3 dias
- ✅ Atribuição de analista (com validação de grupo)
- ✅ Resolução de chamado com controle de estado
- ✅ Histórico automático de alterações
- ✅ Tratamento global de exceções (404 / 409)
- ✅ Documentação automática via Swagger (SpringDoc)
- ✅ Stack estabilizada com Spring Boot 3.4.3
- ✅ Criação de chamado com validação via DTO
- ✅ Redirecionamento automático após criação
- ✅ Paginação com Spring Data (Pageable)
- ✅ Filtro por status via query param