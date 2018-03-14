Symulator Epidemii na przyk³adzie trzech pañstw granicz¹cych ze sob¹,
odizolowanych od reszty œwiata.
* Aby aplikacja dzia³a³a poprawnie 
  trzeba mieæ wyœwietlacz o rozdzielczoœci min 1920x1080
Zasady:
- Pocz¹tkowo ustalamy liczbê populacji, procentow¹ liczbê chorych,
  szanse na zara¿enie oraz wsp migracji
- Zak³adamy, ¿e jeden chory mo¿e zaraziæ jedn¹ osobê,
- Choroba mo¿e mieæ kilka stadiów
- Leczyæ mo¿na ludzi w dwóch pierwszych stadiach choroby
- Mo¿na szczepiæ ludzi
- lek wynajdywany jest, gdy liczba zgonów i chorych stanowi
  min 10% liczby ludnosci
- lek ma 50% skutecznoœci, a pocz¹tkowa jego iloœc to 5%   populacji
- osoby wyleczone nie moga ponownie zachorowac
- progresja choroby oznacza, ze osoba o stadiu np. 3 bedzie w nastepnym kroku miala stadium czwarte, po ostatnim stadium umiera
-koniec symulacji, gdy nie ma ju¿ chorych osób

Sekwencja kroku symulacji:
1. Je¿eli jest lek: leczenie.
2. Progresja choroby.
3. Zara¿anie.
4. Migracja.
5. Wynajdywanie leku.
6. Wpisywanie danych do tabeli.
7. Sprawdzanie czy koniec.

Dany wyœietlane s¹ w tabeli oraz po zakoñczeniu symulacji mamy wykresy liczby chorych i zgonów od czasu.

Tydzieñ oznacza jeden krok symulacji.

Autor:
Tomasz Paw³owski
