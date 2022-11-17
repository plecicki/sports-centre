# Sports Centre - Back-End [PL/ENG]
## Front-End repository with description, images and gifs presenting the program in README.md [PL/ENG]
https://github.com/plecicki/sports-centre-frontend

## Table of contents / Spis treści
* [Jak uruchomić projekt? [PL]](#uruchomienie)
* [Opis back-end'u projektu [PL]](#opis)
* [How to start a project [ENG]](#start)
* [Project back-end description [ENG]](#description)
## Jak uruchomić projekt? [PL]<a name="uruchomienie"></a>
Projekt zrobiono za pomocą: <br>
- Java 17.0.4.1 2022-08-18 LTS
- Gradle 7.5 (Zawarte w gradle-wrapper.properties)
- Spring Boot 2.5.0

Najpierw należy uruchomić back-end aplikacji uruchamiając metodę 'main' w klasie SportsCentreApplication.java, która jest zawarta w tym repozytorium, potem front-end zawarty w repozytorium, do którego prowadzi link powyżej.
Przed uruchomieniem należy podjąć następujące kroki:
### Obowiązkowo
- Stworzyć bazę danych MySQL o nazwie "sports_centre" (Wystarczy utworzyć bazę bez tabel. Tabele zostaną utworzone przez Hibernate przy uruchomieniu).
- Stworzyć użytkownika w bazie danych o danych logowania zawartych w pliku application.properties. Login: sports_user, Hasło: sports_user oraz nadać mu uprawnienia do operacji w bazie.
- Stworzyć zmienną środowiskową o nazwie "CREATE_ADMIN_KEY" o dowolnej wartości, która przyda się w panelu rejestracyjnym we front-end'zie przy tworzeniu konta administratora. Można również na sztywno przypisać jakiś wymyślony klucz w pliku application.properties do pola "create.admin.key"
- Stworzyć zmienne środowiskowe "WEATHER_KEY", "YOUTUBE_KEY", "MAIL_USERNAME", "MAIL_PASSWORD" o dowolnych wartościach lub przypisać dowolne wartości do pól w application.properties "weather.key", "youtube.key", "spring.mail.username", "spring.mail.password", jeśli nie chcemy korzystać z zewnętrznych API oraz z funkcji wysyłania maili, a jeśli chcemy, to w zakładce "Opcjonalnie" jest napisane, co należy zrobić. (W razie wpisania losowych wartości będą pojawiały się komunikaty w konsoli back-end'u, ale program będzie działał. Wiecej o tym poniżej)
### Opcjonalnie (Niewypełnienie ich spowoduje utratę niektórych funkcjonalności oraz pojawianie się komunikatów w konsoli po stronie back-endu aplikacji)
- Zaimportować bazę danych MySQL dołączoną do projektu w folderze "SportsCentreDB", co pozwoli na sprawdzenie działania aplikacji już z przykładowymi danymi. Nie pomiń żadnego pliku przy importowaniu.
- W celu przetestowania funkcji wysyłania maili przez program należy uzupełnić dane konfiguracyjne w pliku application.properties. Przykładowe jest już zawarte w tym pliku, gdzie username oraz password są schowane w zmiennych środowiskowych. Nawet w przypadku, gdy nie mamy zamiaru wprowadzić danych konfiguracyjnych do własnego konta np. w serwisie "MailTrap" nie należy kasować przykładowych danych, ponieważ program się nie skompiluje, a tak to jedynie poinformuje w konsoli, że ma problem z autentykacją, ale reszta funkcji projektu będzie działała prawidłowo pomimo komunikatu w konsoli o problemie z autentykacją.
- W celu wyświetlenia pogody na dzień jutrzejszy należy wpisać klucz dostępowy do pola "weather.key" w application.properties do serwisu VisualCrossing, który można wyrobić na stronie https://www.visualcrossing.com/ za darmo. W przypadku niespełnienia tego warunku zostanie wyświetlony label na stronie z komunikatem o błędzie.<br>
![image](https://user-images.githubusercontent.com/84147482/200683503-3d4868af-c4be-4a5e-a258-265b8f08b2ec.png)
- W celu wyświetlenia danych na temat filmów z YouTube należy podać klucz w pliku application.properties i przypisać go do pola "youtube.key". W przypadku niespełnienia tego kryterium zostanie wyświetlony label informujący o błędzie. Klucz można wyrobić tutaj: https://developers.google.com/youtube/v3 <br>
![image](https://user-images.githubusercontent.com/84147482/200683723-3b5d5c9e-0a86-4018-8536-a3fda2c8d220.png) <br>
W razie uruchomienia aplikacji bez podania kluczy zostaniemy poinformowani komunikatami: <br>
![image](https://user-images.githubusercontent.com/84147482/200684197-1a174dd8-ec5c-40ac-9f0c-70c0458084fe.png)

## Opis back-end'u projektu [PL]<a name="opis"></a>
Projekt pełni zadania zarządzania danymi w bazie danych ośrodka sportowego, który przetrzymuje dane na temat użytkowników, kart, faktur, kont oraz zamówień. Posiada funkcję cyklicznego wysyłania maili do danych użytkowników. Projekt zawiera testy jednostkowe pokrywające kod w stopniu przedstawionym na grafice poniżej: <br>
![image](https://user-images.githubusercontent.com/84147482/200802072-a9050ea5-69ef-49c1-8737-91acf42b8740.png)
### Kontrolery
#### Kontrolery administracyjne
![image](https://user-images.githubusercontent.com/84147482/200588342-13788d60-9b8d-40e5-8690-1844554c43a1.png) <br>
Te kontrolery zapewniają dostęp do podstawowych żądań CRUD takich jak:
- GET Pobranie wszystkich rekordów z tabeli
- GET Pobranie jednego rekordu po id
- POST Utworzenie nowego rekordu
- PUT Edycja rekordu znajdując go po id
- DELETE Usunięcie rekordu po id
<br><br> Te kontrolery nie zapewniają odporności na kolizje np. nie można usunąć karty która jest przypisana do jakiegoś użytkownika (Zostanie zwrócony błąd 500).
#### Kontrolery specjalistyczne
![image](https://user-images.githubusercontent.com/84147482/200589757-aacd5c83-748f-4013-a919-19c76f1523cf.png) <br>
Te kontrolery zapewniają dostęp do żądań użytych we front-end'zie aplikacji takich jak:
- POST Utworzenie nowego konta
- POST Logowanie
- GET Sprawdzenie czy dany użytkownik istnieje po jego nazwie (do walidacji przy rejestracji)
- PUT Ustawienie statusu faktury "Opłacona" po id faktury bez konieczności tworzenia ciała żądania (JSON, XML). Użyte do szybkiej zmiany statusu za pomocą jednego kliknięcia przycisku we front-endzie.
- PUT Ustawienie statusu faktury "Nieopłacona" po id faktury bez konieczności tworzenia ciała żądania (JSON, XML). Użyte do szybkiej zmiany statusu za pomocą jednego kliknięcia przycisku we front-endzie.
- GET Pobranie listy z fakturami po numerze id użytkownika. Użyte do przedstawienia użytkownikowi tylko i wyłącznie jego własnych faktur.
- POST Utworzenie zamówienia w sklepie (Zwraca dane o zamówieniu (Wzorzec Dekorator)).
- POST Edycja użytkownika, żądanie zwraca obiekt zawierający stare oraz nowe dane (Wzorzec Prototyp w serwisie). Użyte do edycji swoich własnych danych przez użytkownika.
- PUT Edycja danych użytkownika z walidacją, czy przypisywana do niego karta istnieje. Ustawienie w ewentualnej starej karcie wartości w polu "user" na null.
- DELETE Usunięcie użytkownika po id. Jeśli istnieje karta mająca przypisanego usuwanego użytkownika, wtedy to pole w karcie zmienia się w null.
- POST Utworzenie użytkownika z walidacją, czy przypisywana karta do niego istnieje. Jeśli tak to wartość pola "user" w karcie jest zmieniana na id utworzonego użytkownika.
- GET Pobranie karty po id użytkownika
- DELETE Usuwanie karty po id z ewentualną zmianą pola użytkownika który ją posiadał na null
- GET Pobranie pogody na jutro w miejsca wpisanego w application.properties z zewnętrznego API
- GET Pobranie informacji na temat filmów w serwisie YouTube z zewnętrznego API na podstawie adresów filmów wpisanych w pliku application.properties
### Pliki domenowe
Projekt zawiera pliki na podstawie których zostały automatycznie utworzone struktury tabel w bazie danych tzw. Encje. Pozostałe pliki to pliki DTO (Data Transfer Object) służące do przekazywania danych do kontrolera oraz z niego. Struktura tych plików jest wzorcem do tworzenia treści JSON wpisywanych do żądań HTTP w razie ręcznej konfiguracji bazy danych np. za pomocą programu Postman.
### Wyjątki
W projekcie zostały utworzone specjalne typy wyjątków w celu doprecyzowania, jakie błędy są wyrzucane poprzez back-end. Dodatkowo jest zawarta klasa zwracająca odpowiednią treść do front-endu w zależności od typu wyrzuconego wyjątku. <br>
Typy utworzonych wyjątków to:
- Karta nieznaleziona na podstawie id użytkownika
- Karta nieznaleziona
- Faktura nieznaleziona
- Brak uprawnień do utworzenia konta administratora
- Zamówienie nieznalezione
- Nazwa użytkownika jest zajęta
- Użytkownik nieznaleziony
- Błędne hasło
- Błędny login
### Mappery
Program umożliwa konwersję obiektów z jednego typu na inny w celu odpowiedniego ich dostosowania do działań wykonywanych przez serwisy programu.
### Repozytoria
Umożliwiają wykonywanie prostych operacji na bazie danych takich jak np. zapisywanie danych czy ich usuwanie. Umożliwiają dopisanie własnych metod do obsługi danych, ponieważ każde repozytorium dziedziczy metody z interfejsu CrudRepository<T, ID>.
### Scheduler / Wysyłanie Maili / Thymeleaf
Program zapewnia funkcję automatycznego wysyłania maili do użytkowników codziennie o godzinie 10:00 za pomocą Scheduler'a. Dodatkowo scheduler automatycznie wystawia faktury w przypadku użytkowników z automatyczną ich odnową. <br>
Maile są komponowane na podstawie danych użytkownika takich jak np. jego imię, gdzie z narzuconą prostą szatą graficzną w języku HTML za pomocą Thymeleaf zawierają przypomnienia dostosowane pod użytkownika. <br>
Maile są wysyłane do:
- Użytkowników posiadających automatyczną odnowę subskrypcji informujące o automatycznym przedłużeniu jej (Scheduler wystawia fakturę).
- Użytkowników posiadających manualną odnowę subskrypcji przypominające tydzień przed jej ukończeniem i później o jej odnowieniu z informacją, ile dni zostało
- Użytkowników posiadających manualną odnowę subskrypcji przypominające po jej ukończeniu o jej odnowieniu z informacją, ile dni minęło od jej wygaśnięcia <br><br>
Przykładowe maile: <br>
![image](https://user-images.githubusercontent.com/84147482/200600231-e26460f6-6164-490d-ab03-ed3927587df1.png)
![image](https://user-images.githubusercontent.com/84147482/200600445-34918f2c-4dff-49aa-ab47-e4eb634d381c.png)
### Serwisy
W klasach serwisowych są wykonywane operacje wymienione przy okazji opisu kontrolerów przy użyciu m.in. repozytoriów dziedziczących z interfejsu CrudRepository<T, ID>.
### Sklep z suplementami
Użytkownicy i administratorzy mogą składać zamówienia, których opisy są tworzone za pomocą konkatenacji oraz wzorca projektowego dekorator.
### Klienci
Projekt zawiera dwie klasy typu Client które wysyłają żądania i pobierają informacje z zewnętrznych API zawierające dane o pogodzie i filmach z serwisu YouTube. Do prawidłowego działania klientów niezbędne są dostarczone klucze dostępowe do zewnętrznych API w pliku konfiguracyjnym. Jest to czynność opcjonalna i projekt może działać również bez tego i błąd w kluczu nie spowoduje błędu w całej aplikacji, tylko zostanie wyświetlony komunikat we front-end o braku danych ze strony zewnętrznych API.
### Konfiguracja
Klasy konfiguracyjne umożliwiają pobieranie danych z pliku application.properties i tym samym danych ze zmiennych środowiskowych, co umożliwia utajnienie danych wrażliwych jak np. klucze do zewnętrznych API, co uczyniłem.
### Testy
Projekt zawiera równe 100 testów jednostkowych pokrywających kod: <br>
![image](https://user-images.githubusercontent.com/84147482/200802778-fbdcc33a-d983-4ed6-b39d-583d320c1e79.png) <br>
Testy obejmują takie elementy programu jak kontrolery, serwisy, mappery i pliki konfiguracyjne. <br>
![image](https://user-images.githubusercontent.com/84147482/200803054-a9e01833-c8b6-496d-8312-d5797263b898.png)
![image](https://user-images.githubusercontent.com/84147482/200803128-a7f29f6b-3994-4f06-a4fe-d4a72b2e4ad3.png)
### Wzorce projektowe
- Dekorator - do tworzenia zamówień (pakiet "supplements" oraz klasa serwisowa "SupplementsService") <br>
- Builder - Klasa "UserToClone" <br>
- Prototyp - płytki klon, użyte w "UserCardService" <br>
- Singleton - we front - end'zie, nie w tym repozytorium.

## How to start a project [ENG]<a name="start"></a>
The project was made with: <br>
- Java 17.0.4.1 2022-08-18 LTS
- Gradle 7.5 (Included in gradle-wrapper.properties)

First you need to start the back-end of the application by running the 'main' method in the SportsCentreApplication.java class, which is contained in this repository, then the front-end contained in the repository linked above.
Before commissioning, the following steps must be taken:
### Obligatory
- Create a MySQL database named "sports_centre" (Just create the database without the tables. The tables will be created by Hibernate on startup).
- Create a user with the data contained in the application.properties file. Login: sports_user, Password: sports_user and give him permission to operate in the database.
- Create an environment variable named "CREATE_ADMIN_KEY" with any value that will be useful in the registration panel in front-end when creating an administrator account. You can also rigidly assign some invented key in the application.properties file to the "create.admin.key" field
- Create environment variables "WEATHER_KEY", "YOUTUBE_KEY", "MAIL_USERNAME", "MAIL_PASSWORD" with arbitrary values or assign any values to fields in application.properties "weather.key", "youtube.key", "spring.mail.username" , "spring.mail.password", if you do not want to use external APIs and the e-mail function, and if you want, in the "Optional" tab it is written what to do. (If you enter random values, messages will appear in the back-end console, but the program will work. More on that below)
### Optional (Failure to complete them will result in the loss of some functionalities and the appearance of messages in the console on the back-end of the application)
- Import the MySQL database attached to the project in the "SportsCentreDB" folder, which will allow you to check the operation of the application with sample data. Do not skip any file.
- In order to test the function of sending e-mails by the program, complete the configuration data in the application.properties file. An example is already included in this file, where username and password are hidden in the environment variables. Even if we do not intend to enter the configuration data into our own account, e.g. in the "MailTrap" service, do not delete the sample data, because the program will not compile, and this will only inform the console that it has a problem with authentication, but the rest of the functions project will work properly despite the message in the console about the problem with authentication.
- In order to display the weather for tomorrow, enter the access key to the field "weather.key" in the application.properties to the VisualCrossing website, which can be obtained at https://www.visualcrossing.com/ for free. If this condition is not met, a label will be displayed on the page with the error message.<br>
![image](https://user-images.githubusercontent.com/84147482/200683503-3d4868af-c4be-4a5e-a258-265b8f08b2ec.png)
- In order to display data on YouTube videos, enter the key in the application.properties file and assign it to the "youtube.key" field. If this criterion is not met, an error label will be displayed. The key can be obtained here: https://developers.google.com/youtube/v3 <br>
![image](https://user-images.githubusercontent.com/84147482/200683723-3b5d5c9e-0a86-4018-8536-a3fda2c8d220.png) <br>
If the application is launched without providing the keys, we will be informed with the following messages: <br>
![image](https://user-images.githubusercontent.com/84147482/200684197-1a174dd8-ec5c-40ac-9f0c-70c0458084fe.png)

## Project back-end description [ENG]<a name="description"></a>
The project performs the task of managing data in the database of the sports center, which stores data on users, cards, invoices, accounts and orders. It has the function of periodically sending e-mails to given users. The project includes unit tests that cover the code to the extent shown in the graphic below: <br>
![image](https://user-images.githubusercontent.com/84147482/200802072-a9050ea5-69ef-49c1-8737-91acf42b8740.png)
### Controllers
#### Administration Controllers
![image](https://user-images.githubusercontent.com/84147482/200588342-13788d60-9b8d-40e5-8690-1844554c43a1.png) <br>
These controllers provide access to basic CRUD requests such as:
- GET Get all records from the table
- GET Get one record by id
- POST Create a new record
- PUT Edit record by finding it by id
- DELETE Delete a record by id
These controllers do not provide collision immunity, e.g. it is not possible to remove a card that is assigned to a user (an error 500 will be returned).
#### Specialist controllers
![image](https://user-images.githubusercontent.com/84147482/200589757-aacd5c83-748f-4013-a919-19c76f1523cf.png) <br>
These controllers provide access to requests used in the front-end of applications such as:
- POST Create a new account
- POST Login
- GET Checks if a given user exists after his name (to be validated during registration)
- PUT Setting the invoice status "Paid" by the invoice id without the need to create the request body (JSON, XML)
- PUT Setting the invoice status "Unpaid" by the invoice id without creating the request body (JSON, XML)
- GET Get the list with invoices by user's id number
- POST Create an order in the store (Returns data about the order (Decorator Java Pattern on the website))
- POST Edit user, request returns an object containing old and new data (Prototype Java Pattern on the website)
- PUT Edit user data with validation if the assigned card exists. Setting the value in the "user" field to null in a possible old card
- DELETE Delete user by id. If there is a card with the deleted user assigned to it, then this field in the card changes to null
- POST Create a user with validation if the assigned card exists. If so, the value of the "user" field is changed to the id of the created user
- GET Get card by user id
- DELETE Delete card by id with possible change of user field to null
- GET Get the weather for tomorrow in the place entered in application.properties from an external API
- GET Get information about YouTube videos from an external API based on video URLs entered in the application.properties file
### Domain files
The project contains files on the basis of which the structure of tables in the database is created, the so-called Entities. The remaining files are DTO (Data Transfer Object) files used to transfer data to and from the controller. The structure of these files is a pattern for creating JSON content entered into HTTP requests in the case of manual database configuration, e.g. using the Postman program.
### Exception
Special exception types have been created in the project to clarify what errors are thrown through the back-end. Additionally, there is a class returning appropriate content to the front-end depending on the type of the exception thrown. <br>
The types of exceptions created are:
- Card not found by user id
- Card not found
- Invoice not found
- You are not authorized to create an administrator account
- Order not found
- Username taken
- User not found
- Wrong password
- Wrong login
### Mappers
The program enables the conversion of objects from one type to another in order to adapt them to the actions performed by the program services.
### Repositories
They enable simple database operations, such as saving or deleting data. They allow you to add your own methods to handle the data, because each repository inherits methods from the CrudRepository<T, ID> interface.
### Scheduler / Sending emails / Thymeleaf
The program provides the function of automatically sending e-mails to users every day at 10:00 using the Scheduler. In addition, the scheduler automatically issues invoices for users with automatic renewal. <br>
E-mails are composed on the basis of user data, such as his name, where, with a simple graphic design in HTML, using Thymeleaf, they contain reminders tailored to the user. <br>
E-mails are sent to:
- Users with automatic subscription renewal informing about its automatic renewal (the Scheduler issues an invoice).
- Users with manual subscription renewal reminding one week before its termination and later about its renewal, with information about how many days are left
- Users with manual subscription renewal reminding after its completion about its renewal with information about how many days have passed since its expiry <br> <br>
Sample e-mails: <br>
![image](https://user-images.githubusercontent.com/84147482/200600231-e26460f6-6164-490d-ab03-ed3927587df1.png)
![image](https://user-images.githubusercontent.com/84147482/200600445-34918f2c-4dff-49aa-ab47-e4eb634d381c.png)
### Services
In the service classes, the operations mentioned in the description of the controllers are performed using, inter alia, repositories inheriting from the CrudRepository <T, ID> interface.
### Supplement Store
Users and administrators can place orders whose descriptions are created using concatenation and the Decorator Design Pattern.
### Clients
The project contains two classes of Client type that send requests and retrieve information from external APIs containing data about weather and movies from YouTube. For the proper operation of clients, the provided access keys to external APIs in the configuration file are necessary. This is an optional step and the project can work without it, and an error in the key will not cause an error in the entire application, only a message will be displayed in the front-end about the lack of data from external APIs.
### Configuration
Configuration classes allow you to retrieve data from the application.properties file and take data from environment variables, which allows you to hide sensitive data such as keys to external APIs, what I did.
### Tests
The project contains 100 unit tests covering the code: <br>
![image](https://user-images.githubusercontent.com/84147482/200802778-fbdcc33a-d983-4ed6-b39d-583d320c1e79.png) <br>
Tests include such program elements as controllers, services, mappers and configuration files. <br>
![image](https://user-images.githubusercontent.com/84147482/200803054-a9e01833-c8b6-496d-8312-d5797263b898.png)
![image](https://user-images.githubusercontent.com/84147482/200803128-a7f29f6b-3994-4f06-a4fe-d4a72b2e4ad3.png)

### Project patterns
- Decorator - for creating orders (package "supplements" and service class "SupplementsService") <br>
- Builder - Class "UserToClone" <br>
- Prototype - shallow clone, used in "UserCardService" <br>
- Singleton - in the front-end, not in this repository.
