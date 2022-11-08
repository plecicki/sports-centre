# Sports Centre - Back-End [PL/ENG]
## Front-End repository with description and gif's presenting the program in README.md [PL/ENG]
SOON
## Table of contents / Spis treści
* [Jak uruchomić projekt? [PL]](#uruchomienie)
* [Opis projektu [PL]](#opis)
* [How to start a project [ENG]](#start)
* [Project description [ENG]](#description)
## Jak uruchomić projekt? [PL]<a name="uruchomienie"></a>
## Opis projektu [PL]<a name="opis"></a>
Projekt pełni zadania zarządzania danymi w bazie danych ośrodka sportowego, który przetrzymuje dane na temat użytkowników, kart, faktur, kont oraz zamówień.
### Kontrolery
#### Kontrolery administracyjne
![image](https://user-images.githubusercontent.com/84147482/200588342-13788d60-9b8d-40e5-8690-1844554c43a1.png) <br>
Te kontrolery zapewniają dostęp do podstawowych żądań CRUD takich jak:
- GET Pobranie wszystkich rekordów z tabeli
- GET Pobranie jednego rekordu po id
- POST Utworzenie nowego rekordu
- PUT Edycja rekordu znajdując go po id
- DELETE Usunięcie rekordu po id
Kontrolery nie zapewniają odporności na kolizje np. nie można usunąć karty która jest przypisana do jakiegoś użytkownika (Zostanie zwrócony błąd 500).
#### Kontrolery specjalistyczne
![image](https://user-images.githubusercontent.com/84147482/200589757-aacd5c83-748f-4013-a919-19c76f1523cf.png) <br>
Te kontrolery zapewniają dostęp do żądań użytych we front-end'zie aplikacji takich jak:
- POST Utworzenie nowego konta
- POST Logowanie
- GET Sprawdzenie czy dany użytkownik istnieje po jego nazwie (do walidacji przy rejestracji)
- PUT Ustawienie statusu faktury "Opłacona" po id faktury bez konieczności tworzenia ciała żądania (JSON, XML)
- PUT Ustawienie statusu faktury "Nieopłacona" po id faktury bez konieczności tworzenia ciała żądania (JSON, XML)
- GET Pobranie listy z fakturami po numerze id użytkownika
- POST Utworzenie zamówienia w sklepie (Zwraca dane o zamówieniu (Wzorzez Dekorator w serwisie))
- POST Edycja użytkownika, żadanie zwraca obiekt zawierający stare oraz nowe dane (Wzorzec Prototyp w serwisie)
- PUT Edycja danych użytkownika z walidacją, czy przypisywana do niego karta istnieje. Ustawienie w ewentualnej starej karcie wartości w polu "user" na null
- DELETE Usunięcie użytkownika po id. Jeśli istnieje karta mająca przypisanego usuwanego użytkownika, wtedy to pole w karcie zmienia się w null
- POST Utworzenie użytkownika z walidacją, czy przypisywana karta do niego istnieje. Jeśli tak to wartość pola "user" jest zmieniana na id utworzonego użytkownika
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
W klasach serwisowych są wykonywane operacje wymienione przy okazji opisu kontrolerów przy użyciu m.in. repozytoriów dziedzicząców z interfejsu CrudRepository<T, ID>.
### Sklep z suplementami
Użytkownicy i administratorzy mogą składać zamówienia, których opisy są tworzone za pomocą konkatenacji oraz wzorca projektowego dekorator.
### Klienci
Projekt zawiera dwie klasy typu Client które wysyłają żądania i pobierają informacje z zewnętrznych API zawierające dane o pogodzie i filmach z serwisu YouTube. Do prawidłowego działania klientów niezbędne są dostarczone klucze dostępowe do zewnętrznych API w pliku konfiguracyjnym. Jest to czynność opcjonalna i projekt może działać również bez tego i błąd w kluczu nie spowoduje błędu w całej aplikacji, tylko zostanie wyświetlony komunikat we front-end o braku danych ze strony zewnętrznych API.
### Konfiguracja
Klasy konfiguracyjne umożliwiają pobieranie danych z pliku application.properties i tym samym danych ze zmiennych środowiskowych, co umożliwia utajnienie danych wrażliwych jak np. klucze do zewnętrznych API, co uczyniłem.
## How to start a project [ENG]<a name="start"></a>
## Project description [ENG]<a name="description"></a>
