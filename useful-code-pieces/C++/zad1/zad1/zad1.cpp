/*
    Program z udziwnieniami 
*/

#include <iostream> // dyrektywa zawierajaca podstawowe metody z ktorych bêdziemy korzystac
#include <string> //dyrektywa zawierajaca obiekt mogacy przechowywac tekst
#include <vector> //dyrektywa zawierajaca dynamiczna strukture przechowywania danych
#include <iterator> //dyrektywa zawierajaca klase umozliwiajaca nam iterowanie po zaawansowanych strukturach danych, w tym wypadku po vectorze

using namespace std; //dla programu o tak malej strukturze mozemy skorzystac z tej komendy, bo mamy pewnosc ze nie wykorzystamy 

struct Produkt // tworzymy strukture ktora pozwoli nam przechowywac w jednej zmiennej dwie inne zmienne
{
    string nazwaProduktu; //lancuch znakow zawierajacy nazwe produktu
    int iloscProduktow; //zmienna typu liczby calkowitej przechowujaca ilosc produktow

    Produkt() {

    }

    Produkt(string nazwa, int ilosc) {
        nazwaProduktu = nazwa;
        iloscProduktow = ilosc;
    }
};

void printOutAll(Produkt* tab, int size, string title); //definiuje metode printOutAll ktora rozwijam pod mainem

int main()
{
    vector<Produkt> paczka; //struktura danych przechowujaca wszystkie nasze produkty (jako ze nie znamy dokladnego koncowego rozmiaru skorzystamy z vectora)
    cout << "Program rozdzielajacy produkty z paczki pomiedzy ludzmi <wpisz 0 podczas wpisywania ilosci produktow azeby zaprzestac wpisywanie produktow>" << endl; //komenda cout wysyla output do konsoli, endl konczy linie
    Produkt produkt;
    do {
        cout << "Wpisz nazwe produktu: ";
        cin >> produkt.nazwaProduktu; //pobieramy z konsoli nazwe produktu
        cout << "Wpisz ilosc produktu: ";
        cin >> produkt.iloscProduktow; //a tu ilosc produktow

        if (produkt.iloscProduktow > 0) 
        { //jezeli ilosc nie jest rowna 0 (co bedzie rowne zakonczeniu petli) dodajemy nasze produkty do vectora
            paczka.push_back(produkt); //dodajemy produkt do naszej struktury danych
        }

        else if(produkt.iloscProduktow < 0) //co zrobic jezeli ilosc produktow bedzie ujemna
        {
            cout << "Nie moze byc ujemnej liczby produktow!"; //tutaj mozna dodac petle umozliwiajaca zmiane ilosci produktow do momentu az nasza ilosc nie bedzie rowna tyle ile chcemy
        }

    } while (produkt.iloscProduktow != 0); //petla do{}while wykonuje pierwsza iteracje oprogramowania niezaleznie od wyniku wyrazenia logicznego w while()

    int size = paczka.size(); //ilosc roznorodnych produktow w paczce 
    Produkt* tabOs1 = new Produkt[size]; //tworzymy dynamicznie tablice o stalym rozmiarze rownym rozmiarowi naszej paczki dla lokatora nr.1
    Produkt* tabOs2 = new Produkt[size]; //tablica dla lokatora nr. 2
    Produkt* tabGos = new Produkt[size]; //resztki dla Gospodarza

    int i = 0; //iterator dla wspollokatorow
    int j = 0; //iterator dla gospodarza

    for (Produkt p : paczka) 
    { 

        int iloscPoPodzialeCalkowitymNaDwa = p.iloscProduktow / 2;
        if (iloscPoPodzialeCalkowitymNaDwa != 0) 
        {
            tabOs1[i] = Produkt(p.nazwaProduktu, iloscPoPodzialeCalkowitymNaDwa);
            tabOs2[i] = Produkt(p.nazwaProduktu, iloscPoPodzialeCalkowitymNaDwa);
            i++; //zwiekszamy i o 1

            if (p.iloscProduktow - iloscPoPodzialeCalkowitymNaDwa * 2 == 1) 
            {
                tabGos[j] = Produkt(p.nazwaProduktu, 1);
                j++; //zwiekszamy j o 1
            }
        }

        else 
        {
            tabGos[j] = Produkt(p.nazwaProduktu, 1);
            j++; //zwiekszamy j o 1
        }

    }

    printOutAll(tabOs1, i, "Pierwszy wspollokator");
    printOutAll(tabOs2, i, "Drugi wspollokator");
    printOutAll(tabGos, j, "Gospodarz");

    return 0; //zwracamy liczbe calkowita poniewaz tego wymaga od nas metoda
}

//definiujemy metode do wypisywania wszystkich produktow azeby nie wypisywac tego samego 3 razy
void printOutAll(Produkt* tab, int size, string title) //void nie wymaga zwracania zmiennych, nie zajmuje tez miejsca w pamieci
{
    cout << title << ": " << endl;
    for (int i = 0; i < size; i++) {
        cout << tab[i].nazwaProduktu << " " << tab[i].iloscProduktow << endl;
    }
    cout << endl;
    //podwojne zakonczenie linijki zeby zrobic linijke przerwy
}

