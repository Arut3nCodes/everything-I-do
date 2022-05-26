.eqv STACK_SIZE 2044

.data
sys_stack_addr: .word 0
stack: .space STACK_SIZE
global_array: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
array_size: .word 10

.text
# wyglad koncowy stosu => | sum_s | sum_i | $ra | wart. zwracana | array_size | adres g_a | main_s |
	sw $sp, sys_stack_addr # zachowanie adresu stosu systemowego
	la $sp, stack+STACK_SIZE # zainicjowanie obszaru stosu
main:
	stackInit:
	# int s = 0;
	subi $sp, $sp, 4 # przygotowujemy miejsce dla zmiennej lokalnej main s, oraz argumentow podprogramu sum -> *array oraz array_size
	# sum(global_array, array_size);
	subi $sp, $sp, 8
	la $t1, global_array # zapisujemy adres tablicy do rejestru
	sw $t1, 4($sp)
	lw $t1, array_size # zapisujemy  rozmiar tablicy 
	sw $t1, ($sp)
	jal sum
	
	#s = sum(global_array, array_size);
	lw $t1, ($sp)
	sw $t1, 12($sp)
	
	addiu $sp, $sp, 12 #cofniecie stosu o zwracana i zmienne lokalne
	
	#print(s);
	subi $sp, $sp, 4 # przygotowujemy miejsce dla argumentu funkcji print => s
	lw $t1, 4($sp)
	sw $t1, ($sp)
	jal print
	
	addiu $sp, $sp, 8 #cofniecie stosu o argumenty funkcji print i main_s
	
	endMain:
	lw $sp, sys_stack_addr # odtworzenie wskaŸnika stosu systemowego
 	li $v0, 10
 	syscall
	
sum:
	start:
	subi $sp, $sp, 8 	# przygotowujemy miejsce dla wartosci zwracanej i wartosci powrotu 
	move $t1, $ra 		#zapisanie do rejestru adresu powrotu
	sw $t1, ($sp) 		#zapisanie adresu z rejestru do wskaŸnika
	
	#int i;
	#int s;
	subi $sp, $sp, 8 	# przygotowujemy miejsce dla zmiennej i i zmiennej s
	
	startSum:
	
	# s = 0;
	move $t1, $zero #zmiana t1 na zero
	sw $t1, ($sp) # s = 0
	
	# i = array_size - 1;
	lw $t1, 16($sp) # pobranie wartosci tablicy
	subi $t1, $t1, 1 # array_size - 1 
	sw $t1, 4($sp)  # i = array_size - 1
	
	# while ( i >= 0 )
	lw $t1, 20($sp) # pobranie wskaznika do tablicy
	addiu $t1, $t1, 36 # ruszamy o 9 * 4
	sw $t1, 20($sp) # jako ze iterujemy od konca to ustawiamy wskaznik na ostatni element tablicy
	
	sumLoop:

	# s = s + array[i];
	lw $t1, ($sp) # pobranie wartosci z s
	lw $t2, 20($sp) # pobranie obecnego adresu global_array[i]
	lw $t2, ($t2) # pobranie wartosci w adresie global_array[i]
	add $t1, $t1, $t2 # s = s + array[i]
	sw $t1, ($sp) # ponowny zapis do rejestru
	
	# i = i - 1;
	lw $t1, 4($sp)
	subi $t1, $t1, 1
	sw $t1, 4($sp)
	bltz $t1, endSum #koniec pêtli -> przerzut do konca metody
	
	#zmiana adresu 
	lw $t1, 20($sp) # pobranie wskaznika do tablicy
	subiu $t1, $t1, 4 # ruszamy o 4 w lewo do poprzedniego elementu
	sw $t1, 20($sp) # jako ze iterujemy od konca to ustawiamy wskaznik na poprzedni element
	j sumLoop
	
	endSum:
	#return s;
	lw $t1, ($sp)
	sw $t1, 12($sp)
	#cofamy stos o zmienne lokalne
	addiu $sp, $sp, 8
	#zwrot adresu powrotu
	lw $t1, ($sp)
	move $ra, $t1
	#cofamy do wartosci zwracanej
	addiu $sp, $sp, 4
	jr $ra
	
print:
	subi $sp, $sp, 4  # przesuwamy o cztery zeby przechowac rejestr powrotu
	move $t1, $ra
	sw $t1, ($sp)
	
	li $v0, 1
	lw $a0, 4($sp) 
	syscall
	
	lw $t1, ($sp)
	move $ra, $t1
	addiu $sp, $sp, 4 #cofamy do argumentow
	jr $ra
	
	
	
	
	
	
	
	
	
	
