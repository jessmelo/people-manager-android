**Teste de Habilidades Android - Hexagon**

Arquitetura do app:
  - MVVM 
      - Cada View possui seu ViewModel (ex.: RegisterScreen e RegisterViewModel)
      - View recebe input do usuário e representa os dados para o usuário
      - ViewModel atualiza o estado dos objetos e atualiza o modelo através do UserRepository
  - SharedViewModel compartilhado pelas telas RegisterScreen e EditUserScreen para receber e atualizar URI de imagem selecionada pelo usuário (Singleton Pattern)
  - Activity única que recebe o componente de navegação principal (MainActivity)
  - Para o modelo de dados foi utilizado o banco de dados SQLite com camada de abstração da biblioteca Room
  - Repository e DAO (Data Access Object) para manipulação dos dados
  - Componentes de UI modularizados para um código limpo e estruturado (diretório: ui/views/components)
    
Funcionalidades do aplicativo:
  - Adicionar novo usuário
  - Excluir usuário
  - Editar usuário
  - Visualizar lista de usuários cadastrados
  - Ativar ou desativar usuário 

Frameworks utilizadas:
 - Componentes UI do Jetpack Compose (Navegação e Componentes visuais do Material 3)
 - Biblioteca Room para persistência de dados na memória local

TODO:
  - Terminar implementação de funcionalidades - FEITO
  - Adicionar testes
  - Ajustar design do app 
