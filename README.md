Сервис, который обращается к сервису курсов валют, и отдает gif в ответ:
если курс по отношению к рублю за сегодня стал выше вчерашнего, то сервис отдает рандомную отсюда - https://giphy.com/search/rich,
если ниже - отсюда https://giphy.com/search/broke

Ссылки на API
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

Сервис на Spring Boot 2 + Java
Запросы приходят на HTTP endpoint, туда передается код валюты.
Для взаимодействия с внешними сервисами используется Feign.
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки. 
Для сборки использован Gradle. На сервис написаны мока тесты.

Для запуска программы необходимо:
1. Собрать Jar файл. gradle bootJar
2. Запустить Jar файл. java -jar ./build/libs/demo-0.0.1-SNAPSHOT.jar
