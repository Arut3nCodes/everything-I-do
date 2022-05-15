package baza;

import java.util.Iterator;
import java.util.ListIterator;

// metody z parametrem index rzucają wyjątkiem IndexOutOfBoundException
// w przypadku błędu zakresu jego wartości

public interface IList<T> extends Iterable<T> {
    // dodanie do kolekcji, gdzie - zależy od typu kolekcji
    // zwraca true, jeśli element został dodany
    boolean add(T value);
    // dodanie do kolekcji na określoną pozycję
    // zwraca wynika jak dla poprzedniego add
    boolean add(int index, T value);
    // czyści kolekcję
    void clear();
    // zwraca, czy kolekcja zawiera podaną wartość
    boolean contains(T value);
    // pobiera (bez usuwania) wartośc spod podanej pozycji
    T get(int index);
    // ustawia nową wartośc na podanej pozycji, zwraca starą wartość
    T set(int index, T value);
    // wzraca pozycję podanej wartości lub -1
    int	indexOf(T value);
    // zwraca, czy kolekcja jest pusta
    boolean	isEmpty();
    // zwraca zwykły iterator dla tej kolekcji
    Iterator<T>	iterator();
    // zwraca dwukierunkowy iterator dla listy
    ListIterator<T>	listIterator();
    // usuwa element z podaje pozycji, zwracając jego wartość
    // lub null jeśli go nie ma
    T remove(int index);
    // usuwa wartość oraz zwraca true lub zwraca false
    boolean	remove(T value);
    // zwraca liczbę elementów kolekcji
    int size();
}
