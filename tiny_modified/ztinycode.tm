* TINY Compilation to TM Code
* File: ztinycode.tm
* Standard prelude:
  0:     LD  6,0(0) 	load maxaddress from location 0
  1:     ST  0,0(0) 	clear location 0
* End of standard prelude.
  2:     IN  0,0,0 	read integer value
  3:     ST  0,0(5) 	read: store value
* -> if
* -> Op
* -> Const
  4:    LDC  0,0(0) 	load const
* <- Const
  5:     ST  0,0(6) 	op: push left
* -> Id
  6:     LD  0,0(5) 	load id value
* <- Id
  7:     LD  1,0(6) 	op: load left
  8:    SUB  0,1,0 	op <
  9:    JLT  0,2(7) 	br if true
 10:    LDC  0,0(0) 	false case
 11:    LDA  7,1(7) 	unconditional jmp
 12:    LDC  0,1(0) 	true case
* <- Op
* if: jump to else belongs here
* -> assign
* -> Const
 14:    LDC  0,1(0) 	load const
* <- Const
 15:     ST  0,1(5) 	assign: store value
* <- assign
* -> while
* while: jump after body comes back here
* -> Op
* -> Const
 16:    LDC  0,4(0) 	load const
* <- Const
 17:     ST  0,0(6) 	op: push left
* -> Id
 18:     LD  0,0(5) 	load id value
* <- Id
 19:     LD  1,0(6) 	op: load left
 20:    SUB  0,1,0 	op <
 21:    JLT  0,2(7) 	br if true
 22:    LDC  0,0(0) 	false case
 23:    LDA  7,1(7) 	unconditional jmp
 24:    LDC  0,1(0) 	true case
* <- Op
* while: jump to jump to end belongs here
* -> assign
* -> Op
* -> Id
 26:     LD  0,1(5) 	load id value
* <- Id
 27:     ST  0,0(6) 	op: push left
* -> Id
 28:     LD  0,0(5) 	load id value
* <- Id
 29:     LD  1,0(6) 	op: load left
 30:    MUL  0,1,0 	op *
* <- Op
 31:     ST  0,1(5) 	assign: store value
* <- assign
* -> assign
* -> Op
* -> Id
 32:     LD  0,0(5) 	load id value
* <- Id
 33:     ST  0,0(6) 	op: push left
* -> Const
 34:    LDC  0,1(0) 	load const
* <- Const
 35:     LD  1,0(6) 	op: load left
 36:    SUB  0,1,0 	op -
* <- Op
 37:     ST  0,0(5) 	assign: store value
* <- assign
 38:    LDA  7,-23(7) 	jmp to test
 25:    JEQ  0,14(7) 	while: jmp to jmp to end
 39:    LDA  7,0(7) 	jmp to end
* <- while
* -> Id
 40:     LD  0,1(5) 	load id value
* <- Id
 41:    OUT  0,0,0 	write ac
* if: jump to end belongs here
 13:    JEQ  0,29(7) 	if: jmp to else
 42:    LDA  7,0(7) 	jmp to end
* <- if
* End of execution.
 43:   HALT  0,0,0 	
