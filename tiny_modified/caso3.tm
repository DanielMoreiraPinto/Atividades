* TINY Compilation to TM Code
* File: caso3.tm
* Standard prelude:
  0:     LD  6,0(0) 	load maxaddress from location 0
  1:     ST  0,0(0) 	clear location 0
* End of standard prelude.
  2:     IN  0,0,0 	read integer value
  3:     ST  0,0(5) 	read: store value
* -> while
* while: jump after body comes back here
* -> Op
* -> Id
  4:     LD  0,0(5) 	load id value
* <- Id
  5:     ST  0,0(6) 	op: push left
* -> Const
  6:    LDC  0,5(0) 	load const
* <- Const
  7:     LD  1,0(6) 	op: load left
  8:    SUB  0,1,0 	op <
  9:    JLT  0,2(7) 	br if true
 10:    LDC  0,0(0) 	false case
 11:    LDA  7,1(7) 	unconditional jmp
 12:    LDC  0,1(0) 	true case
* <- Op
* while: jump to jump to end belongs here
* -> Id
 14:     LD  0,0(5) 	load id value
* <- Id
 15:    OUT  0,0,0 	write ac
* -> assign
* -> Op
* -> Id
 16:     LD  0,0(5) 	load id value
* <- Id
 17:     ST  0,0(6) 	op: push left
* -> Const
 18:    LDC  0,1(0) 	load const
* <- Const
 19:     LD  1,0(6) 	op: load left
 20:    ADD  0,1,0 	op +
* <- Op
 21:     ST  0,0(5) 	assign: store value
* <- assign
 22:    LDA  7,-19(7) 	jmp to test
 13:    JEQ  0,10(7) 	while: jmp to jmp to end
 23:    LDA  7,0(7) 	jmp to end
* <- while
* End of execution.
 24:   HALT  0,0,0 	
