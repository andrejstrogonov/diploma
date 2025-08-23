# Дипломный проект «Доска объявлений»

Backend-приложение на Spring Boot, реализующее REST-API для сервиса размещения объявлений (аналог Avito). Проект соответствует [OpenAPI-спецификации](https://github.com/dmitry-bizin/front-react-avito/blob/v1.19/openapi.yaml) и предназначен для совместной работы с React-frontend.

## Функциональные возможности
* Регистрация и авторизация пользователей  
* Публикация, редактирование и удаление объявлений  
* Загрузка изображений объявлений и аватаров пользователей  
* Комментирование объявлений  
* Ролевое разграничение доступа (`USER`, `ADMIN`)  
* Документация Swagger UI (`/swagger-ui.html`)

## Технологический стек
* Java 17, Maven
* Spring Boot 2.7: Web, Security, Data JPA
* PostgreSQL 14 + Liquibase
* Lombok
* springdoc-openapi-ui 1.7

## Требования к окружению
* Maven 3.0
* PostgreSQL (локально или в Docker)

### Сборка jar
```bash
./mvnw clean package
java -jar target/graduate-work-0.0.1-SNAPSHOT.jar
```

## Конфигурация
Все параметры задаются в `src/main/resources/application.properties`.

| Параметр | Назначение | Значение по умолчанию                      |
|----------|-----------|--------------------------------------------|
| `spring.datasource.url` | URL БД PostgreSQL | `jdbc:postgresql://localhost:5432/diploma` |
| `spring.datasource.username` | Пользователь | `user`                         |
| `spring.datasource.password` | Пароль | `123`                                      |
| `spring.liquibase.enabled` | Включить Liquibase | `true`                                     |


## Документация API
Swagger UI: `http://localhost:8080/swagger-ui.html`  
JSON-описание: `http://localhost:8080/v3/api-docs`

## Запуск фронтенда (Docker)
Для тестирования бэкенд вместе с пользовательским интерфейсом, запустите готовый Docker-образ React-приложения:

```bash
docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21
```

Фронтенд будет доступен по адресу http://localhost:3000 и уже настроен на работу с бэкендом на порту 8080.


 
