.data
in_i: .word -22
out_f: .float 0

.text
#przyjmujemy ze liczba ktora otrzymujemy w in_i jest liczba kodowana w U2
# $t1 zawiera liczbe poczatkowa
# $t2 zawiera znak
# $t3 zawiera wykladnik
# $t4 zawiera mantyse
# $t5 przechowuje liczbe przygotowana do przeniesienia do rejestru
# $t6 przechowuje adres powrotu
main:
	mainStart:
	jal conversionFromU2toFloat
	
	mainPrint:
	#print binary
	li $v0, 35
	move $a0, $t5
	syscall
	#newline
	jal newLine
	#print float
	li $v0, 2
	mtc1 $t5, $f12 
	syscall
	
	mainEnd:
	li $v0, 10
	syscall
	
conversionFromU2toFloat:
	conversionStart: #rozpoczynamy od sprawdzenia jaki znak ma nasza liczba
	move $t6, $ra 
	lw $t1, in_i #ladujemy do $t1 nasza poczatkowa liczbe
	
	conversionSignCheck:
	beqz $t1, conversionFinale #jezeli jest rowna 0 to liczba jest w porzadku
	blez $t1, ifLessThanZero #jezeli mniejsza niz 0 to trzeba dokonac pewnych zmian
	j ifMoreThanZero # jezeli wieksza od 0 trzeba dokonac pewnych zmian
		ifLessThanZero:
		li $t2, 1 #jako ze ujemna to znak bedzie rowny 1
		neg $t1, $t1 #ustawiamy $t1 jako przeciwienstwo $t2
		j conversionExponentAndMantys #teraz pora na wykladnik

		ifMoreThanZero:
		li $t2, 0
		#liczba jest w odpowiedniej formie do przeksztalcania
		j conversionExponentAndMantys

	# zamierzamy obliczyc wartosc wykladnika, oraz przeksztalcic liczbe podana na poczatku na mantyse
	# wartosc wykladnika daje nam tez dlugosc liczby binarnej, zatem bedziemy wiedziec o ile przesunac mantyse w lewo (23 - num length)
	conversionExponentAndMantys:
		conversionExponentAndMantysStart:
		li $t3, 0 # inicjalizacja wykladnika
		move $t4, $t1 # do mantysy wstawiamy liczbe poczatkowej, z ktorej na samym koncu bedziemy musieli obciac najbardziej znaczaca 1 
		
		conversionExponentAndMantysLoop: # dopoki liczba != 1 to loop 
		beq $t1, 1, conversionExponentAndMantysEnd
		srl $t1, $t1, 1 # obcinamy najmlodszy bit i przesuwamy w prawo
		# wykladnik
		addi $t3, $t3, 1 # wykladnik zwiekszamy o 1
		j conversionExponentAndMantysLoop	
		
		conversionExponentAndMantysEnd:
		# zeby pozbyc sie najstarszej jedynki, wykorzystamy pozostala nam na poczatku jedynke
		# przesuniemy ja w lewo o wartosc wykladnika i zastosujemy operacje alternatywy rozlacznej (XOR). Wyeliminuje nam to zbedna jedynke
		sllv $t1, $t1, $t3 
		xor $t4, $t4, $t1
		
	conversionNumberAssemble:
	li $t5, 0x00000000 #inicjalizujemy $t5
 	# znak znajduje sie na 32 pozycji, zatem przesuwamy go o 31 pozycji w lewo
	sll $t2, $t2, 31
	addu $t5, $t2, $t5 # dodajemy do koncowej liczby
	
	# jako ze do przesuniecia mantysy przyda nam sie wartosc wykladnika, to najpierw przesuniemy mantyse
	# zwolniony rejestr $t2 wykorzystamy do przetrzymania wartosci tego przesuniecia
	li $t2, 23 # poczatkowe przesuniecie jest o 23
	sub $t2, $t2, $t3 # 23 - wykl
	sllv $t4, $t4, $t2 # przesuwamy o (23-wykl) w lewo
	add $t5, $t5, $t4	
	
	# korygujemy wykladnik do excess-127 i przesuwamy o 23 pozycje w lewo
	addi $t3, $t3, 127
	sll $t3, $t3, 23
	add $t5, $t3, $t5 # dodajemy do koncowej liczby
	
	conversionFinale:
	move $ra, $t6
	jr $ra
newLine:  #szybka nowa linijka
	li $v0, 11
	li $a0, 0x0a
	syscall
	jr $ra
