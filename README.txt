Popescu Bogdan Petrut
334CC
APD-Tema #2 Traffic simulator

    Implementarea temei:

        - pentru fiecare tip de intersectie am creat cate o clasa ce
            implementeaza interfata Intersection, in care am un constructor
            si diferite metode de sincronizare si structuri de date
            (liste, bariere, semafoare)

        - am citit ultima linie din input (daca era cazul) si am creat
        intersectia in ReaderHandlerFactory, in case-ul corespunzator

        - in IntersectionHandlerFactory am rezolvat exercitiile, in case-ul
        corespunzator

        - ex1: sleep in functie de masina

        - ex2: semafor pt cele n masini ce au voie simultan, sleep t la fiecare masina

        - ex3: semafor de valoare 1 in fiecare directie, sleep t la fiecare masina

        - ex4: se scriu prima oara toate mesajele de reached folosind o bariera,
        apoi pt fiecare directie un semafor de valoare x, dupa care se scriu
        mesajele cu selected folosind o alta bariera, de valoare <nr de directii> * x .
        Apoi, sleep t si mesaj exited

        - ex5: deoarece nu se mai tine cont ca mesajele sa ajunga in grupuri, nu mai
        este nevoie de bariere. In rest, analog ca 4.

        - ex6: am 2 cozi, pt masini cu/fara prioritate. Fiecare masina este adaugata
        in coada care i se cuvine. Daca nu exista masini cu prioritate in intersectie
        (coada celor cu prioritate e goala), pleaca cele fara prioritate, extragand
        capul cozii la fiecare pas. Altfel, cele fara prioritate asteapta, iar cele
        cu prioritate parcurg intersectia in t milisecunde.

        - ex7: am 2 variabile ce tin evidenta la ultimul mesaj transmis, pt a nu avea
        duplicate. Daca pietonii nu au inceput sa treaca, trimit green. Altfel, trimit
        red. Am observat ca, uneori, unele thread-uri nu apucau sa trimita ultimul green,
        asa ca la final verific ultimul mesaj transmis. Daca e red, afisez fortat ultimul green.
        In cazul meu, aveam ciclu infinit deoarece isFinished() nu-mi returna niciodata true,
        dar am rezolvat asta facand variabilele pass si finished din clasa Pedestrians de tip
        volatile.

        - ex8: am 2 semafoare, cel pentru directia 0 cu valoarea nrAllowed, celalalt
        cu valoarea 0. Astfel, voi incepe cu cele de pe directia 0. Am si o bariera cu
        valoarea nrAllowed, pe care o pun dupa ce afisez mesajul cu "has passed", deoarece
        imediat dupa dau release la semaforul pentru directia 1. Astfel, dupa ce trec un
        numar de nrAllowed masini pe directia 0, vor putea trece un nr de nrAllowed pe directia 1.

        - ex10: am cate o coada pt fiecare sens. Folosesc o bariera cu valoarea egala cu nr total
        de masini, pentru a afisa prima oara toate mesajele cu "stopped". Mesajul care arata trecerea
        trenului este afisat de un singur thread, dupa care, folosind un semafor de valoare 1, se
        scoate pe rand capul cozii in care se afla masina si se afiseaza mesajul corespunzator.




