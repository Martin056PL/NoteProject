Aplikacja służy do zarządzania, tworzonymi przez użytkownika, notatkami.

W celu uruchomienia aplikacji należy:
1. sklonować repozytorium (github.com/Martin056PL/NoteProject) 
2. następnie w folderze projektu, wykorzystując Mavena, wykonać polecenie "mvn package" 
3. ostatnią czynnością, z wykorzystaniem Dockera, jest "docker-compose up".

Aby aplikacja uruchomiła sie prawidłowo należy upewnić się, że porty 8888 oraz 5432 nie są wykorzystywane.

Istnieje możliwość pobrania paczki .jar (github.com/Martin056PL/NoteProject/packages/48933)
 
Po uruchomieniu aplikacji listę dostępnych endpointów można znaleźć pod: localhost:8888/swagger-ui.html