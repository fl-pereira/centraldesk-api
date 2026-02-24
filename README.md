
# CentralDesk API
**Sistema REST para gerenciamento de chamados.**
Desenvolvido por [Felipe Pereira](https://www.linkedin.com/in/felipeluizpereira/)

## Stack
- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- Swagger

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

## Segurança
Spring Security configurado com autenticação padrão para ambiente de desenvolvimento.


## Status do projeto

**23/02/2026**  
Backend estruturado e operacional

*Próximo passo: implementação das regras de negócio e fluxo completo de chamados.*