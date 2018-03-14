Symulator Epidemii na przyk�adzie trzech pa�stw granicz�cych ze sob�,
odizolowanych od reszty �wiata.
* Aby aplikacja dzia�a�a poprawnie 
  trzeba mie� wy�wietlacz o rozdzielczo�ci min 1920x1080
Zasady:
- Pocz�tkowo ustalamy liczb� populacji, procentow� liczb� chorych,
  szanse na zara�enie oraz wsp migracji
- Zak�adamy, �e jeden chory mo�e zarazi� jedn� osob�,
- Choroba mo�e mie� kilka stadi�w
- Leczy� mo�na ludzi w dw�ch pierwszych stadiach choroby
- Mo�na szczepi� ludzi
- lek wynajdywany jest, gdy liczba zgon�w i chorych stanowi
  min 10% liczby ludnosci
- lek ma 50% skuteczno�ci, a pocz�tkowa jego ilo�c to 5%   populacji
- osoby wyleczone nie moga ponownie zachorowac
- progresja choroby oznacza, ze osoba o stadiu np. 3 bedzie w nastepnym kroku miala stadium czwarte, po ostatnim stadium umiera
-koniec symulacji, gdy nie ma ju� chorych os�b

Sekwencja kroku symulacji:
1. Je�eli jest lek: leczenie.
2. Progresja choroby.
3. Zara�anie.
4. Migracja.
5. Wynajdywanie leku.
6. Wpisywanie danych do tabeli.
7. Sprawdzanie czy koniec.

Dany wy�ietlane s� w tabeli oraz po zako�czeniu symulacji mamy wykresy liczby chorych i zgon�w od czasu.

Tydzie� oznacza jeden krok symulacji.

Autor:
Tomasz Paw�owski
