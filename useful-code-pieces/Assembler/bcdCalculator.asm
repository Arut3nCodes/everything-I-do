.data
number1: .byte 0x33 0x33 0x33 0x33 0x0f
number2: .byte 0x76 0x33 0x33 0x33 0x0f
result: .space 10
#Do czego wykorzystuje poszczegolne rejestry (w poczatkowej fazie tworzenia programu - wszystkie lokalne nadpisy zmieniaja )
# a0 -> pierwsza liczba
# a1 -> druga liczba
# a2 -> wynik sumy

# t0 -> tymczasowa
# t1 -> tymczasowa zmienna
# t2 -> tymczasowy adres liczby pierwszej
# t3 -> tymczasowy adres liczby drugiej
# t4 -> dlugosc liczb
# t9 -> adres powrotu
.text
main:
	la $a0, number1
	la $a1, number2
	la $a2, result 
	jal bcdAdd
	jal bcdPrint
	la $a2, number1
	jal bcdPrint
	la $a2, number2
	jal bcdPrint
	
	li $v0, 10
	syscall
bcdAdd:
	countLength:
		move $t2, $a0 
		move $t3, $a1
		move $t9, $ra 
		
	lengthLoop:
		lbu $t0, ($t2)
		andi $t1, $t0, 0xf0 	# gorna czesc bajtu
		
		subiu $t2, $t2, 1	# nastepny bajt
		subiu $t3, $t3, 1
		beq $t1, 0xf0, lengthLoopExit
		addiu $t2, $t2, 1	# nastepny bajt
		addiu $t3, $t3, 1
		addi $t4, $t4, 1 	# zwiekszenie dlugosci o jeden
		andi $t1, $t0, 0x0f 	# dolna czesc bajtu
		beq $t1, 0x0f, lengthLoopExit
		addi $t4, $t4, 1 	# zwiekszenie dlugosci o jeden
		addiu $t2, $t2, 1	# nastepny bajt
		addiu $t3, $t3, 1	# jako ze operujemy na liczbach o tej samej ilosci cyfr, mozemy tez dazyc do znalezienia adresu konca drugiej liczby
		b lengthLoop
	lengthLoopExit:
		jal bcdAddition
		jr $t9
bcdAddition:

	#Przygotowujemy wynik (max rozmiar o pó³bajt wiêkszy)
	# $t0 -> nasz iterator (rozmiar liczby + 1 dla mozliwosci przechowania bajtu przeniesienia)
	# $t1 -> tymczasowy dostep do pamieci wyniku
	# $t5 -> wartosc bajtu (0)
	addiu $t0, $t4, 2
	li $t5, 0
	move $t1, $a2
	prepareResultLoop:
		sb $t5, ($t1)
		beqz $t0, prepareResultLoopExit1
		subiu $t0, $t0, 1
		beqz $t0, prepareResultLoopExit2
		subiu $t0, $t0, 1
		addiu $t1, $t1, 1
		b prepareResultLoop
		
	prepareResultLoopExit1: #jezeli koniec jest na starszej czesci bajtu
	lbu $t0, ($t1) # pobor wartosci w danej pamieci
	or $t0, $t0, 0xf0
	sb $t0, ($t1) #zapis do bajtu
	subiu $t1, $t1, 1
	j addition
	
	prepareResultLoopExit2: #jezeli koniec jest na mlodszej czesci bajtu
	lbu $t0, ($t1) # pobor wartosci w danej pamieci
	or $t0, $t0, 0x0f
	sb $t0, ($t1) #zapis do bajtu
	j addition
	
	#faktyczne dodawanie
	# $t6 -> bit do przeniesienia
	# $t7 -> flaga przeniesienia
	# $t8 -> cyfra liczby 1
	# $t0 -> 
	# $v0 -> 
	# $v1 -> 
	
	addition: #dodawanie
	li $t6, 0
	andi $t7, $t4, 0x01
	
	additionLoop: #petla
	lbu $t8, ($t2)
	lbu $t0, ($t3)
	bnez $t7, additionOlderPart #bnez jezeli starsza czesc
	andi $v0, $t8, 0x0f
	andi $v1, $t0, 0x0f
	j additionStep
	
		additionOlderPart: #przesuwanie o 4 w prawo jesli starsza
		srl $v0, $t8, 4
		srl $v1, $t0, 4
		
	additionStep: #krok dodawania
	addu $t0, $v0, $v1
	addu $t0, $t0, $t6	
	li $t6, 0
	blt $t0, 10, noCarry
	#z przernoszeniem
	li $t6, 1
	subiu $t0, $t0, 10
	
	noCarry: #bez przenoszenia
	bnez $t7, additionSaveOlder #zapis starszej czesci
	sb $t0, ($t1)
	j additionGrandeFinale #zakonczenie iteracji badz metody
	
	additionSaveOlder: #zapis starszej czesci
	lbu $v0, ($t1) 
	sll $t0, $t0, 4
	or $v0, $v0, $t0 #dodanie obu czesci
	sb $v0, ($t1)  
	subiu $t1, $t1, 1
	subiu $t2, $t2, 1
	subiu $t3, $t3, 1
	#ruszamy cyfre
	
	additionGrandeFinale: #zakonczenie iteracji badz metody
	xori $t7, $t7, 1
	bgt $t1, $a2, additionLoop
	sb $t6, ($t1)
	beqz $t6, cutIfZero
	additionGrandeGrandeFinale:
	jr $t9	
	
	
	cutIfZero: #obcina zerowy bajt z przodu
	# $t6 to liczba bitow do przesuniecia -> jest ona rowna liczbie cyfr skladnikow + 1
	# $t7 to adres nastepnego bajta
	# $t8 przechowuje wartosc nastepnego bajta
	move $t6, $t4
	addiu $t6, $t6, 1
	cutIfZeroLoop:
	beqz $t6, additionGrandeGrandeFinale
	addiu $t7, $t1, 1
	lbu $t8, ($t7)
	sb $t8, ($t1)
	move $t1, $t7
	subiu $t6, $t6, 1
	b cutIfZeroLoop
	
bcdPrint:
	# Registers:
	# t0 - temporary register
	# t1 - upper half-byte
	# t2 - lower half-byte
	# t3 - AND mask
	li	$v0,	11		# service 11 - print character
	loopPrint:			# calculate length of the first term
		lbu	$t0,	($a2)		# load next byte to $t0
		srl	$t1,	$t0,	4	# store first digit
		andi	$t2,	$t0,	0x0f	# store second digit
		xori	$t0,	$t0,	0xff	# flip all bytes (to make 0xF equal 0x0)
		andi	$t3,	$t0,	0xf0
		beqz	$t3,	endLoopPrint
		addi	$a0,	$t1,	48	# 48 - ASCII for 0
		syscall
		andi	$t3,	$t0,	0x0f
		beqz	$t3,	endLoopPrint
		addi	$a0,	$t2,	48	# 48 - ASCII for 0
		syscall
		addiu	$a2,	$a2,	1	# go to next byte
		b	loopPrint
	endLoopPrint:
	li	$a0,	10
	syscall
	jr	$ra		# return

	

	
	
	
	
	
		
		
	
	
	
	
	
	
	
	
		
	
		
		
	
		
		
	
	
	
	
		
	
