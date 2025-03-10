# Desafio DropBox | Servidor FTP

## *Funcionalidades*
1. Criar um crud de usuário.
2. Permitir upload de arquivos de usuários.
3. Listar todos upload de arquivo do usuário (Paginados e com filtros).
4. Excluir os arquivos do usuário.
5. Compartilhar os arquivos entre diferentes usuários.
6. Documentação da API.


## *Requisitos:*
* Cadastro e compartilhamento entre usuários deve ser no Mongodb;
* O upload, listagem, filtros e deleção devem ser feitos no servidor FTP (“O mongo so vai ter os usuários e o relacionamento com usuários compartilhados”);
* Testes unitários;
* Java/Spring;
* MongoDB;
* Docker.


# Swagger
http://localhost:9000/challengedropbox/swagger-ui/index.html

### User
* [POST] Criar usuário 