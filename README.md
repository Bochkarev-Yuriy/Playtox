# Playtox
test task

Тестовое задание Playtox Java Developer
Данный документ содержит описание стандартного тестового задания для соискателей на должность Java разработчика.
Общее описание

Необходимо реализовать Web приложение простого интернет-магазина.
Перед началом реализации задания необходимо выполнить структурную декомпозицию работ (WBS) и дать оценку трудозатрат и времени выполнения задачи.
Требования

Общие требования

Архитектура - Apache Tomcat, Hibernate, MySQL. Плюсом будет использование Spring Framework и Apache Wicket.
Должна быть система логирования (на основе готового решения, например Log4j). Приложение должно логировать в файл любые действия, приводящие к изменению данных. Приложение должно корректно обрабатывать и логировать ошибки.
Приложение должно устойчиво работать в многопользовательской среде под высокой нагрузкой (от 10 тыс. запросов в минуту).
Структура данных

В приложении должны быть следующие сущности, содержащие поля:
Пользователь

ID
Имя (логин)
Пароль
Роль (пользователь или администратор)
Товар

ID
Название
Описание
Цена
Количество на складе
Покупка

ID
ID пользователя
ID товара
Дата покупки
Цена покупки
Количество единиц товара
Функции администратора

Администратор должен иметь возможность:
Входить в систему
Смотреть список товаров
Создавать/редактировать/удалять товар
Смотреть список совершенных покупок
Функции пользователя

Пользователь должен иметь возможность:
Регистрироваться
Входить в систему
Смотреть список товаров
Совершать покупку*
* - При совершении покупки, изменяется значение поля “Количество на складе” сущности товар, создается новая сущность “Покупка”.
