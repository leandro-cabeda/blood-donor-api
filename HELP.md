# Objetivo

* Este projeto foi desenvolvido para poder dar suporte a uma aplicação
* realizada em flutter para atender as necessidades de uma agencia de
* banco de sangue para quem possa doar sangue e cadastrar também.

# Começando
- Inicialmente importa o projeto e instale as depedencias do maven
- Depois vai no arquivo application.properties e altere os dados do banco mysql utilizando a conta e cria uma database
- Com isso pode subir a aplicação com a porta que foi definida no arquivo descrito acima.

### Ferramentas
- Java versão 17
- MySQL versão 8.0
- Maven

### Configurações e Acessos de testes
- Foi implementado umas configurações no projeto de acordo da necessidade
- Basta acessar o endpoint do swagger para visualizar a api implementada no endereço sem ssl: http://localhost:8081/swagger-ui/index.html
- Basta acessar o endpoint do swagger para visualizar a api implementada no endereço com ssl: https://localhost:8081/swagger-ui/index.html
- Basta acessar o endereço sem ssl: http://localhost:8081/v3/api-docs para visualizar as documentações da api
- Basta acessar o endereço com ssl: https://localhost:8081/v3/api-docs para visualizar as documentações da api
- Acessar lista de doadores da api com ssl : https://localhost:8081/api/donors
- Acessar lista de doadores da api sem ssl : http://localhost:8081/api/donors

### Buildar aplicação e executar
- Basta executar o comando: mvn clean install
- Basta executar o comando: mvn spring-boot:run

### Startar Aplicação
- Apenas suba a aplicação para inicializar o projeto pelo padrão da porta consta: 8080


### Observação importante
- O projeto foi desenvolvido para atender as necessidades de uma agencia de banco de sangue para quem possa doar sangue e cadastrar também.
- Porém não consegui ajustar de acordo o cors da aplicação mesmo configurando no front quanto no back, isso me atrapalhou bastante, mas em si aplicação  toda foi implementada pelo que entendi no arquivo pdf recebido e do arquivo que continha os dados.
- Tive problemas com a biblioteca do swagger em conflitos das dependencias da versão do java 19, mas implementei igualmente.
