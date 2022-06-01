.data
str1: .space 32
str2: .space 32
str3: .space 32
str4: .space 32
description: .asciiz "1 - zaladuj string | 2 - wypisz string | 3 - strlen | 4 - strcmp | 5 - strcat | 6 - strfind | 0 - zakoncz program \n"
choiceMessage: .asciiz "twoj wybor: "
endOfProgramMess: .asciiz "%KONIEC PROGRAMU%"
.text
# s0 przechowuje wartosc zmiennej iterowanej
# s1 przechowuje rejestr powrotu -> $ra
main:
	mainStart:
	li $v0, 4 # 4 -> print_str
	la $a0, description # wypisanie instrukcji dzialania programy
	syscall
	
	mainLoop:
	li $v0, 4 # 4 -> print_str
	la $a0, choiceMessage # wypisanie wiadomosci wyboru
	syscall
	
	li $v0, 5 # operacja wczytania integer
	syscall
	move $s0, $v0
	
	#beq $s0, 1, strload  	#ladowanie stringa
	#beq $s0, 2, strprint 	#wypisanie stringa
	#beq $s0, 3, strlen	#dlugosc stringa	
	#beq $s0, 4, strcmp	#porownanie dwoch stringow
	#beq $s0, 5, strcat	#laczy dwa napisy
	#beq $s0, 6, strfind	#znajduje pozycje pierwszego wystapienia danego znaku
	beqz $s0, mainEnd  	#zakonczenie petli
	j mainLoop
	
	mainEnd:
	li $v0, 4 # 4 -> print_str
	la $a0, endOfProgramMess # wypisanie wiadomosci zakonczenia
	syscall
	
	li $v0, 10
	syscall
	