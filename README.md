#  Webscraping

![GitHub language count](https://img.shields.io/github/languages/count/dugabriel/scraping?style=for-the-badge)
![Open issues](https://img.shields.io/github/issues/dugabriel/scraping?style=for-the-badg)

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

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
http://<host local>:8080/scraping/swagger-ui/index.html
```
