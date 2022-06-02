.data
str1: .space 32
str2: .space 32
str3: .space 32
str4: .space 32
strAsm: .space 63
#stringi do wyswietlania
description: .asciiz "1 - zaladuj string | 2 - wypisz string | 3 - strlen | 4 - strcmp | 5 - strcat | 6 - strfind | 0 - zakoncz program \n"
choiceMessage: .asciiz "Twoj wybor: "
endOfProgramMess: .asciiz "%KONIEC PROGRAMU%"
choiceIndex1Mess: .asciiz "Pierwszy indeks: "
choiceIndex2Mess: .asciiz "Drugi indeks: "
wrongChoiceMess: .asciiz "Zly wybor, powtorz\n"
TypeInStringMess: .asciiz "Wpisz ciag znakow: "
.text

#Ogolny spis wykorzystania rejestrow (moze sie roznic dla poszczegolnych metod -> w tym wypadku zostanie to nadpisane w odpowiedniej metodzie)
# $s0 przechowuje wartosc zmiennej iterowanej
# $s1 przechowuje rejestr powrotu -> $ra
# $s2 przechowuje wartosci zwracane (czy to adresy czy to wartosci)
main:
	mainStart:
	# 4 -> print_str
	li $v0, 4
	la $a0, description # wypisanie instrukcji dzialania programy
	syscall
	
	mainLoop:
	# 4 -> print_str
	li $v0, 4 
	la $a0, choiceMessage # wypisanie wiadomosci wyboru
	syscall
	# 5 -> operacja wczytania integer
	li $v0, 5 
	syscall
	move $s0, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	
	beq $s0, 1, strLoad  	# ladowanie stringa
	beq $s0, 2, strPrint 	# wypisanie stringa
	beq $s0, 3, strLen	# dlugosc stringa	
	beq $s0, 4, strCmp	# porownanie dwoch stringow
	beq $s0, 5, strCat	# laczy dwa napisy
	#beq $s0, 6, strfind	# znajduje pozycje pierwszego wystapienia danego znaku
	beqz $s0, mainEnd  	# zakonczenie petli
	
	j mainLoop
	
	mainEnd:
	li $v0, 4 # 4 -> print_str
	la $a0, endOfProgramMess # wypisanie wiadomosci zakonczenia
	syscall
	
	li $v0, 10
	syscall

strLoad:
	# $t1 tutaj przekazany jest pierwotny adres
	# $t2 przechowuje pierwszy index wewnetrzny stringa
	# $s2  - tutaj przekazuje zmienne badz adresy zwracane
	 
	# wybieramy ktorego stringa wykorzystac oraz wczytujemy do niego lancuch znakow
	strLoadStart:
	
	strLoadLoop: 
	li $v0, 4
	la $a0, choiceIndex1Mess
	syscall
	# 5 -> operacja wczytania integer
	li $v0, 5 
	syscall
	move $t2, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t2, 4, strActualLoad
	li $v0, 4
	la $a0, wrongChoiceMess
	syscall 
	
	j strLoadLoop
	
	#wczytujemy napis
	# $a0 -> poczatek adresu pamieci w ktorej przechowujemy string
	# $a1 -> maksymalna 	dlugosc wczytywanego napisu (32 - 1 na znak konczacy stringa)
	strActualLoad:
	# adres konkretnego stringa
	la $t1, str1 # laduje adres poczatkowy str1
	subiu $t2, $t2, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t2, $t2, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t1, $t1, $t2 # adres + przeniesienie  
	
	li $v0, 8 # 8 -> load_str
	move $a0, $t1 # -> przekazujemy adres 
	li $a1, 32 # przekazujemy dlugosc maksymalna do a1
	syscall
	
	strFixSearchLoop: # szukamy bajtu 0x0a
	lb $t2, ($t1)
	beq $t2, 0x0a, strFix # jak rowne 0x0a to naprawiamy
	addiu $t1, $t1, 1 #inkrementacja
	j strFixSearchLoop
	
	strFix: #zamieniamy bajt 0x0a na 0x00
	li $t2, 0x00 #zapisujemy do $t1 bajt 0x00
	sb $t2, ($t1) #korekcja
	
	strLoadEnd:
	move $ra, $s1
	j mainStart
	
strPrint:
	# $t1 tutaj przekazany jest pierwotny adres
	# $t2 przechowuje pierwszy index wewnetrzny stringa
	
	strPrintInitLoop:
	li $v0, 4
	la $a0, choiceIndex1Mess
	syscall
	# 5 -> operacja wczytania integer
	li $v0, 5 
	syscall
	move $t2, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t2, 4, strActualPrint
	j strPrintInitLoop
	
	strActualPrint:
	# adres konkretnego stringa
	la $t1, str1 # laduje adres poczatkowy str1
	subiu $t2, $t2, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t2, $t2, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t1, $t1, $t2 # adres + przeniesienie  
	
	li $v0, 4 # 4 -> print_str
	move $a0, $t1 # -> przekazujemy adres stringa do wypisania
	syscall
	jal newLine
	
	strPrintEnd:
	move $ra, $s1
	j mainStart
	
strLen:
	# $t1 - tutaj przekazany jest pierwotny adres oraz adres zmodyfikowany
	# $t2 - przechowuje pierwszy index wewnetrzny stringa
	# $t3 - przechowuje znak pod adresem
	# $t4 - przechowuje dlugosc slowa
	# $t6 - zmienna tymczasowa
	# $s2 -> tutaj przekazuje zmienne badz adresy zwracane
	strLenInitLoop:
	li $v0, 5 
	syscall
	move $t2, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t2, 4, strLenFinalInit # jak dobry indeks to lecimy dalej
	j strLenInitLoop
	
	strLenFinalInit:
	la $t1, str1 # laduje adres poczatkowy str1
	subiu $t2, $t2, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t2, $t2, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t1, $t1, $t2 # adres + przeniesienie  
	li $t4, 0 #inicjalizacja $t4
	
	strLenCalcLoop:
	lb $t3, ($t1) #ladujemy znak pod $t1
	beqz $t3,  strLenEnd #jak znak konca to konczymy obliczanie
	addi $t4, $t4, 1 # zwiekszamy dlugosc o jeden
	addi $t1, $t1, 1 # nastepny slot w tablicy
	j strLenCalcLoop 
	
	strLenEnd:
	beq $s0, 3, strLenPrintOut
	strLenEndFinal:
	move $s2, $t4
	j mainStart
	
	strLenPrintOut:
	# 1 -> wypisuje integer
	li $v0, 1
	move $a0, $t4
	syscall
	jal newLine
	j strLenEndFinal
strCmp:	
	# $t1 przetrzymuje pierwotny oraz zmodyfikowany adres tablicy slowa 1
	# $t2 przechowuje pierwotny oraz zmodyfikowany adres tablicy slowa 2
	# $t3 przechowuje znak slowa t1
	# $t4 przechowuje znak dlowa t2
	# $t6 przechowuje tymczasowa zmienna pomocnicza
	
	
	str1CmpInitLoop:
	li $t6, 0
	li $v0, 4
	la $a0, choiceIndex1Mess
	syscall
	#wczytanie liczby
	li $v0, 5 
	syscall
	move $t6, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t6, 4, str1CmpInitFinal # jak dobry indeks to lecimy dalej
	j str1CmpInitLoop
	
	str1CmpInitFinal:
	la $t1, str1 # laduje adres poczatkowy str1
	subiu $t6, $t6, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t6, $t6, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t1, $t1, $t6 # adres + przeniesienie  
	
	str2CmpInitLoop:
	li $v0, 4
	la $a0, choiceIndex2Mess
	syscall
	#wczytanie liczby
	li $v0, 5 
	syscall
	move $t6, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t6, 4, str2CmpInitFinal # jak dobry indeks to lecimy dalej
	j str2CmpInitLoop
	
	str2CmpInitFinal:
	la $t2, str1 # laduje adres poczatkowy str2
	subiu $t6, $t6, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t6, $t6, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t2, $t2, $t6 # adres + przeniesienie
	
	strCmpFinalLoop:
	lb $t3, ($t1)
	lb $t4, ($t2)
	
	bgt $t3, $t4, strCmpFinalLessThan # str1 > str2
	blt $t3, $t4, strCmpFinalGreaterThan #str1 < str2
	beqz $t3, strCmpFinalEqual #jezeli do tego doszlo to znaczy ze oba wyrazy na pewno sa takie same
	
	addiu $t1, $t1, 1
	addiu $t2, $t2, 1
	
	j strCmpFinalLoop
	
	strCmpFinalGreaterThan:
	li $s2, 1
	j strCmpFinalPrintAndReturn
	
	strCmpFinalLessThan:
	li $s2, -1
	j strCmpFinalPrintAndReturn
	
	
	strCmpFinalEqual:
	li $s2, 0
	j strCmpFinalPrintAndReturn
	
	strCmpFinalPrintAndReturn:
	li $v0, 1
	move $a0, $s2
	syscall
	jal newLine
	j mainStart
	
strCat:
	# $t1 przetrzymuje pierwotny oraz zmodyfikowany adres tablicy w ktorej przechowywane bedzie slowo zlaczone
	# $t2 przetrzymuje pierwotny oraz zmodyfikowany adres tablicy slowa 1
	# $t3 przechowuje pierwotny oraz zmodyfikowany adres tablicy slowa 2
	# $t4 tymczasowe przechowanie bajtu
	# $t5 na potrzebny koncowego wypisania i zwrocenia przekazuje tutaj adres pierwszej tablicy
	# $t6 przechowuje tymczasowo index str1 i str2
	
	strCompCatInit:
	la $t1, strAsm
	move $t5, $t1
	
	str1CatInitLoop:
	li $t6, 0
	li $v0, 4
	la $a0, choiceIndex1Mess
	syscall
	#wczytanie liczby
	li $v0, 5 
	syscall
	move $t6, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t6, 4, str1CatInitFinal # jak dobry indeks to lecimy dalej
	j str1CatInitLoop
	
	str1CatInitFinal:
	la $t2, str1 # laduje adres poczatkowy str1
	subiu $t6, $t6, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t6, $t6, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t2, $t2, $t6 # adres + przeniesienie  
	
	str2CatInitLoop:
	li $v0, 4
	la $a0, choiceIndex2Mess
	syscall
	#wczytanie liczby
	li $v0, 5 
	syscall
	move $t6, $v0 #przeniesienie wartosci zmiennej sterujacej programem
	ble $t6, 4, str2CatInitFinal # jak dobry indeks to lecimy dalej
	j str2CatInitLoop
	
	str2CatInitFinal:
	la $t3, str1 # laduje adres poczatkowy str2
	subiu $t6, $t6, 1 # zmniejszamy o jeden, bo ma nas przeniesc o (n-1) do przodu, na nastepny string
	mul $t6, $t6, 32 # mnozymy razy 32 * (n-1), zeby wiedziec o ile slow do przodu chcemy sie przeniesc
	add $t3, $t3, $t6 # adres + przeniesienie  
	
	strCombinePart1:
	lb $t4, ($t2) # wczytujemy znak z str1[j]
	beqz $t4, strCombinePart2
	sb $t4, ($t1) # zapisujemy znak do strAsm[i]
	addi $t1, $t1, 1 # i + 1
	addi $t2, $t2, 1  # k + 1
	j strCombinePart1 # przechodzimy do drugiego slowa
	
	strCombinePart2:
	lb $t4, ($t3) # wczytujemy znak z str2[k]
	beqz $t4, strCombineFinal
	sb $t4, ($t1) # zapisujemy znak do strAsm[i]
	addi $t1, $t1, 1 # i + 1
	addi $t3, $t3, 1 # k + 1
	j strCombinePart2
	
	strCombineFinal:
	#zapisuje znak konca bajtu
	sb $zero, ($t1)
	#wypisz slowo
	li $v0, 4
	move $a0, $t5
	syscall
	jal newLine
	
	j mainStart
strFind:

	
newLine:  #szybka nowa linijka
	# $t9 holds char
	li $v0, 11
	li $a0, 0x0a
	syscall
	jr $ra
	
