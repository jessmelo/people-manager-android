**Teste de Habilidades Android - Hexagon**

Arquitetura:
  - MVVM (cada View possui seu ViewModel)
      - View recebe input do usuário e representa os dados para o usuário
      - ViewModel atualiza o state dos objetos e atualiza o modelo através do UserRepository
  - SharedViewModel compartilhado pelas telas RegisterScreen e EditUserScreen para receber e atualizar URI de imagem selecionada pelo usuário (Singleton Pattern)
  - Activity única (MainActivity)
  - Para o modelo de dados foi utilizado o banco de dados SQLite com camada de abstração da biblioteca Room
  - Repository e DAO (Data Access Object) para manipulação dos dados
  - Componentes de UI modularizados para um código limpo e estruturado (diretório: ui/components)
    
Funcionalidades do aplicativo:
  - Adicionar novo usuário
  - Excluir usuário
  - Editar usuário
  - Visualizar lista de usuários cadastrados
  - (TODO) Ativar ou desativar usuário 

Frameworks utilizadas:
 - Componentes UI do Jetpack Compose (Navegação e Componentes visuais)
 - Biblioteca Room para persistência de dados na memória local
