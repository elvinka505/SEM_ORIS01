# TravelPlanner (SEM_ORIS01)

## Коротко
Проект — web-приложение на сервлетах + Freemarker для семестровой работы.

## Требования
- JDK 11+ (проверьте pom.xml)
- Maven
- PostgreSQL

## Быстрый старт (локально)
1. Создайте базу:
```bash
createdb travel_db
psql -d travel_db -f src/main/resources/schema.sql
