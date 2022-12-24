### Шаги для запуска тестов:

1) установить java openjdk (тесты проводились на openjdk 11);
2) установить nodejs и npm;
3) установить docker и docker-compose;
4) установить git;
5) запустить сервис docker;
6) в пустой папке выполнить `git clone https://github.com/romanhudenko/qa-diploma`
7) в папке qa-diploma/artifacts/gate_simulator/ выполнить команду `npm start` и не прерывать её до прохождения всех
   тестов;
8) в папке qa-diploma/artifacts/mysql/ выполнить команду `docker-compose up` от администратора и не прерывать её до
   прохождения всех тестов;
9) в папке qa-diploma/artifacts/mysql/ выполнить команду `java -jar aqa-shop.jar` и не прерывать её до прохождения всех
   тестов;
10) в папке qa-diploma выполнить команду `./gradlew test`
11) если нужна генерация человекочитаемого репорта allure нужно в папке qa-diploma выполнить команду `./gradlew
    allureServe`
