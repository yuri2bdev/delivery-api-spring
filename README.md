# Delivery-API

O projeto consiste na construção de um sistema simplificado para o controle de delivery de um restaurante, utilizando as tecnologias Java 8, Spring Boot 2, Firebird como banco de dados, API RESTful, Maven e OpenAPI 3.0 (Swagger). As APIs devem permitir o cadastro, alteração, deleção e consulta de clientes, pedidos e entregas. Para garantir a segurança do sistema, será implementado um cadastro de usuários e login com autenticação via token JWT. Vale ressaltar que as operações de cadastro, alteração, deleção e consulta de pedidos e entregas só poderão ser executadas caso o usuário esteja logado. O objetivo do projeto é fornecer uma solução simples e eficiente para o controle de delivery de um restaurante, facilitando as operações cotidianas do negócio.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.


### 📋 Pré-requisitos

Para instalar o software, é necessário ter os seguintes requisitos técnicos:

-Sistema operacional compatível com as tecnologias utilizadas (por exemplo, Windows, Linux, MacOS)
-Java 8 instalado na máquina
-Spring Boot 2
-Banco de dados Firebird

Para instalar o software, siga os seguintes passos:

- Faça um clone do repositório Git em sua máquina;
- Configure o ambiente com as tecnologias citadas;
- Abra o projeto na IDE de sua escolha para obter mais detalhes.

### 🔧 Instalação

Instalação do ambiente de desenvolvimento para Java
1. Instale o Java Development Kit (JDK) em sua máquina, seguindo as instruções do site oficial da Oracle.
2. Instale uma IDE (Integrated Development Environment) para desenvolvimento em Java, como o Eclipse, NetBeans ou IntelliJ IDEA.
3. Configure o ambiente de desenvolvimento, definindo o diretório de instalação do JDK e a IDE que você escolheu.


## ⚙️ Executando os testes

# Usuarios

- POST Usuario (cria um novo usuário)
http://localhost:8080/api/v1/usuarios
- GET Usuario (busca todos os usuários)
http://localhost:8080/api/v1/usuarios
- GET Usuario (busca usuário por username)
http://localhost:8080/api/v1/usuarios/{username}

# Autenticação

- POST Auth - login (faz login de um usuário e retorna o token)
http://localhost:8080/api/v1/auth/login

# Clientes

- GET Cliente (busca todos os clientes)
http://localhost:8080/api/v1/clientes
- GET Cliente (busca cliente por ID)
http://localhost:8080/api/v1/clientes/{id}
- POST Cliente (cria um novo cliente)
http://localhost:8080/api/v1/clientes
- PUT Cliente (edita um cliente existente)
http://localhost:8080/api/v1/clientes/{id}
- DELETE Cliente (deleta um cliente existente)
http://localhost:8080/api/v1/clientes/{id}

# Entregas

- GET Entrega (busca todas as Entregas)
http://localhost:8080/api/v1/entregas
- GET Entrega (busca Entrega por ID)
http://localhost:8080/api/v1/entregas/{id}
- POST Entrega (cria uma nova entrega)
http://localhost:8080/api/v1/entregas
- PUT Entrega (edita uma Entrega existente)
http://localhost:8080/api/v1/entregas/{id}
- DELETE Entrega (deleta uma Entrega existente)
http://localhost:8080/api/v1/entregas/{id}

# Pedidos

- GET Pedido (busca todos os Pedidos)
http://localhost:8080/api/v1/pedidos
- GET Pedido (busca pedidos por ID)
http://localhost:8080/api/v1/pedidos/{id}
- POST Pedido (cria um novo pedido)
http://localhost:8080/api/v1/pedidos
- PUT Pedido (edita um pedido existente)
http://localhost:8080/api/v1/pedidos/{id}
- DELETE Pedido (deleta um pedido existente)
http://localhost:8080/api/v1/pedidos/{id}

## 🛠️ Construído com

* [Spring Boot]([http://www.dropwizard.io/1.0.2/docs/](https://spring.io/projects/spring-boot)) - O framework java usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/) - IDE Utilizada para contruir o projeto
* [GitHub](https://github.com/) - Ferramenta utilizada para versionar e disponibilizar o projeto
* [Postman](https://www.postman.com/) - Ferramenta utilizada para testar o projeto
* [IBExpert](https://www.ibexpert.net/cms/) - Ferramenta utilizada para acessar o Banco de dados
* [Firebird](https://firebirdsql.org/) - Sistema de banco de dados utilizado

🧠💻❤️😉
