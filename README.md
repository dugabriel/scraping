# SpringBoot with mongoDB

![GitHub language count](https://img.shields.io/github/languages/count/dugabriel/mongodbwithspring?style=for-the-badge)
![Open issues](https://img.shields.io/github/issues/dugabriel/mongodbwithspring?style=for-the-badg)

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] Criar cadastro da lista de favoritos
- [x] Criar cadastro de cliente
- [x] Criar cadastro de produto
- [x] CRUD da lista de favoritos
- [x] Regras de negócio
- [ ] Teste de performance
- [ ] Refactor
- [ ] Testes unitários
- [ ] Testes integrados

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

* Java instalado na máquina, junto ao maven `<JDK11 / maven 3.6+>`
* Docker e docker-compose disponível no sistema

## 🚀 Build do projeto

Para subir a aplicação local, você deve seguir estas etapas:

Navegar até a pasta docker e executar comando abaixo para subir o mongodb:
```
 docker-compose -f mongo.yml up
```

Na pasta raiz do projeto, executar a instalção das dependencias através do maven com o comando:
```
 mvn clean install
```

Agora, para subir o app basta rodar o comando:
```
 mvn spring-boot:run
```


## ☕ Utilizando as apis do projeto

Para usar as apis, basta acessar o endereço do swagger:

```
http://<host local>:8080/ecommerce/swagger-ui/index.html
```


## 📫 Contribuindo para o projeto

Para contribuir, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

