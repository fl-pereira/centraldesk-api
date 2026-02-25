
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


## 🚀 Status do Projeto

Versão atual: **v0.2.0**

### Funcionalidades implementadas:

- ✅ Criação de chamado
- ✅ Atribuição de analista (com validação de grupo)
- ✅ Resolução de chamado com controle de estado
- ✅ Histórico automático de alterações
- ✅ Tratamento global de exceções (404 / 409)
- ✅ Documentação automática via Swagger (SpringDoc)
- ✅ Stack estabilizada com Spring Boot 3.4.3