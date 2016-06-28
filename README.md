# Server-Side-Translator
Deep Server-Side Translator by Stas @lozenko

https://3monthjunior.slack.com/files/lozenko/F1GMKRFGB/Deep_Server-Side_Translator

Задача усложнена, поэтому если какие-то процессы вам покажуться странными - знайте, это сделанно для того чтобы вы познакомились с новым для себя стеком.
Перед вами архитектура приложения (кликните для более большей картинки, если понадобится)

![архитектура](data-translator.png)

У вас есть 2 Docker контейнера. На одном установлен чистый ElasticSearch, на другом установлен Redis. Для того чтобы максимально облегчить вам задачу, вопсользуйтесь сервисом DockerHub, если же хотите действительно разобраться в Docker - сделайте докер образы (docker images) самому.

Итак, контейнеры созданы. На диаграмме сверху над каждым контейнером, висит Filler-ы.

* ElasticFiller - ваш микросервис который 
  1. берет из файлика elastic-data.json все данные и каждые 30 секунд наполняет ElasticSearch данными, 
  2. но только в количестве 100 id. 
То есть каждые 30 секунд заполняюся последующими 100 рядками из id/first_name и так далее. Всего в файле 1000 записей. То есть за 30 секунд x 1000 записей = 5 минут и все данные заполнены.

* RedisFiller - делает то же самое но для Redis и берет данные из redis-data.xml. Заполняет с той же периодичностью

Тестовые данные берем отсюда: goo.gl/a1zqTs

Простые правила написания сервисов:
* Каждые 30 секунд в обоих сервисах должны имплементироваться через @Scheduled спринга
* "Каждые следующие 50 данных" заполняется с помощью батчинга (каково обсудите в чате)
* RedisFiller использует StAX
* ElasticFiller использует Jackson (но обычный парсинг, не databind)
* на обоих сервисах стоит GRADLE, не Maven (!!!)
* оба сервиса стартуют свои Docker контейнеры удаленно через Docker Java Client (берем версию 3.0.0)
* тесты, качество кода и все остальное на вашу совесть

Снизу ваш третий TranslatorService микросервис, который берет данные с уже заполненных хранилищ с докера. Единственное разница только в том, что все ваших 3 сервиса стартуют одинаково, поэтому нижний "может" ждать данные, но если он видит их он забирает.
Как он это делает?

Он берет данные с Redis и ElasticSearch и объеденяет их по ID, то есть если с elasticSearch приходит ID:1 Name: Masha Surname: Ivanova и так далее, то к нему добавляется Биткоин этого же ID с Redis и эта готовая полноценная склееная запись ложится в MongoDB которая установлена у вас локально.

После того как он все загрузит, он c помощью SSH библиотеки JSCH заходит на оба контейнера и останавливает их. (еще раз, не с помощью Docker Client, а только через scjh)

# Docker

## Установка на локальный компьютер
Скачать для своей ОС (детальнее на https://docs.docker.com/):

 * Mac - https://download.docker.com/mac/beta/Docker.dmg
 * Windows - https://download.docker.com/win/beta/InstallDocker.msi

## Запуск всех контейнеров с docker-compose

```bash
$ docker-compose up default
```

# DB
## Redis
    Default ports 6379
## MongoDB
    Default ports 27017
## Elasticsearch
    Default ports 6379
