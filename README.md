Alunos: Guilherme Müller, Luiz Gustavo Kobilacz e Rafael Hiro Kato Kawakami

A classe **artigo** possui os atributos: **id**, que é um atributo tipo long e identificador e único para cada artigo, **titulo** e **resumo** são atributos String, enquanto o atributo **publicado** é boolean e indica se determinado artigo está publicado ou não. Cada um dos atributos também possui métodos **GET** e **SET** para atualizar e recuperar esses atributos respoectivamente. A classe também possui uma função **toString**, que irá concatenar os atributos em forma de String. A classe também conta com dois construtores: um vazio e outro que irá realizar o set dos atributos titulo, resumo e publicado.

O trabalho também conta com 4 classses “controllers”: as classes **SecController** e **HomeController** são responsáveis pelo login e pela tela inicial do sistema respectivamente, enquanto as classes **ArtigoController** e **EditoraController** são responsáveis pela manutenção dos objetos artigo e terão suas funções explicadas mais detalhadamente a seguir.

Através dos controllers de editora e artigos, existem os seguintes métodos:

O servidor atende na URL <http://localhost:8080/api>

A classe **ArtigoController** tem como prinicpal função a edição dos objetos criados pela classe **EditorController** e possui os seguintes métodos:

- **createArtigo**: http://localhost:8080/api/artigos cria um novo artigo dando o set nos atributos titulo, resumo e publicado de acordo com as informações que o usuário preencher, enquanto atribui um ID automaticamente para o artigo. Essa função é acessada através de um botão na tela de criação de artigos;
- **getAllArtigos**: http://localhost:8080/api/artigos lista todos os artigos ao colocá-los em um vetor do tipo lista e posteriormente em formato de uma tabela que mostra o id, título, resumo e o estado de publicação do artigo;
- **getArtigoById**: http://localhost:8080/api/artigos/{id} lista um artigo atarvés de um determinado id;
- **updateArtigo**: http://localhost:8080/api/artigos/{id} através de um determinado id, atualiza os atributos de titulo, resumo e publicado;
- **deleteArtigo**: http://localhost:8080/api/artigos/{id} através de um determinado id, faz um hard delete do artigo com o id fornecido.
- **deleteAllArtigo**: http://localhost:8080/api/artigos faz um hard delete de todos os artigos cadastrados
- **getArtigoByPublicado**: <http://localhost:8080/api/artigos/{publicado} irá listar todos todos os artigos cujo atributo boolean publicado seja true.

A classe **EditoraController** tem como principal função a edição e criação de elementos através de um model de artigo e possui os seguintes métodos:

- **createForm**: <http://localhost:8080/api/editora/cria> um novo objeto artigo através do modelo “Artigo”
- **editForm**: http://localhost:8080/api/editora/salvar atualiza um artigo da lista de artigos.
- **showAll**: http://localhost:8080/api/editora/artigos retorna a lista de artigos com os artigos cadastrados.
- **showById**: http://localhost:8080/api/editora/artigos/{id} retorna a lista de artigos com o artigo com o id buscado.
- **deleteById**: http://localhost:8080/api/editora/artigos/excluir/{id} irá deletar um artigo da lista de artigos com o id informado.
- **deleteAll**: http://localhost:8080/api/editora/artigos/excluir-todos irá excluir todos os artigos da lista de artigos.
