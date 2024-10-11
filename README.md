# AnyLogic-test-task

## Архитектура
3 модуля:
1) config-server
2) task-manager-service (управление тасками, хранение их в DB)
3) task-executor-server (выполнение тасок)

## Запуск
Возможно следующие варианты запуска:
1) Через docker-compose файл в корне проекта
2) Запуск через средства сборки/IDE:
- Запустить config-server _(**mvn spring-boot:run** из корня модуля)_
- Запустить RabbitMQ (например, из docker-compose)
- Запустить task-manager-service (**mvn spring-boot:run -Dspring-boot.run.profiles=h2** из корня модуля) (по умолчанию он использует H2 DB, для переключения на Postgres следует указать в 
bootstrap.yml профиль **postgres** и переключить профиль Maven на **postgres**, далее **mvn spring-boot:run -Dspring-boot.run.profiles=postgres** из корня модуля). При запуске на Postgres следует предварительно запустить Postgres (например, из docker-compose).
- Запустить task-executor-service _(**mvn spring-boot:run** из корня модуля)_

Важно:
при переключении с одного профиля Maven на другой (для смены DB) надо пересобрать проект Maven, чтобы он подтянул зависимости из нового профиля. 

## API
Доступны следующие эндпоинты (коллекция находится в **postmancollection** в корне проекта):
- POST http://localhost:7878/api/calculateFactorial BODY: value: {value} - вызов операции вычисления факториала. Возвращает значение факториала.
- DELETE http://localhost:7878/api/task/{taskId} - остановка выполнения таски на экзекьюторе. При остановке таски возвращает true.

Actuator эндпоинты с базовыми метриками:
- http://localhost:7878/actuator - task-manager-service
- http://localhost:7543/actuator - task-executor-service

## Тестирование
На выполнение запроса стоит таймаут 50000 мс (task-manager-service), количество тредов для выполнения - 5/10 (task-executor-service), можно конфигурировать на config-server.   
Операция вычисления факториала выполняется в цикле с искусственным замедлением в 1 секунду для каждой итерации.

Возможные сценарии тестирования:    
1) Для одного эндпоинта http://localhost:7878/api/calculateFactorial:
- Вызов операции вычисления факториала с отрицательным числом или без числа - Ошибка **Value: null is not applicable for factorial calculation. Value must be positive**
- Вызов операции вычисления факториала с корректным числом - Ответ **{"result": {result}}**
2) Для одного эндпоинта http://localhost:7878/api/task/{taskId}:
- Вызов со значением несуществующей таски - Ошибка **Task not found for id: {taskId}**
- Вызов со значением уже завершенной/отмененной таски - Ошибка **Task with id: {taskId} is not executed because it has status: {taskStatus}**
3) Проверка параллельности выполнения запросов:
- Вызов эндпоинта http://localhost:7878/api/calculateFactorial 2 раза- первый с большим значением (например, 18), второй с маленьким (например, 3). Должны прийти ответы на оба запроса, при чем для запроса с меньшим значением первее, хоть он и стартовал после.
4) Проверка работы остановки расчета:
- Вызов эндпоинта http://localhost:7878/api/calculateFactorial с большИм значением, просмотр его taskId в БД или по логам приложения (так же можно сделать это просто перезапустив приложение (так как скрипт на старте пересоздает все таблицы), тогда из-за автоинкремента в таблице у первой запущенной таски будет taskId = 1). Во время выполнения запроса вызвать эндпоинт http://localhost:7878/api/task/{taskId}. Для удаления должно прийти true, для рассчитывающегося запроса- ошибка **Task with id: {taskId} is not executed because it has status: STOPPED**.
5) Проверка правильности выбора потоков вычисления при выполнении нескольких параллельно:
- Вызов эндпоинта http://localhost:7878/api/calculateFactorial с большИм значением (например, 20) 2 раза, просмотр taskId одного из них в БД или по логам приложения (так же можно сделать это просто перезапустив приложение, тогда в таблице у первой запущенной таски будет taskId = 1). Во время выполнения запроса вызвать эндпоинт http://localhost:7878/api/task/{taskId}. Для удаления должно прийти true, для одного из рассчитывающихся запросов- ошибка **Task with id: {taskId} is not executed because it has status: STOPPED**. Второй через какое-то время должен завершиться корректно.
