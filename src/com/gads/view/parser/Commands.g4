grammar Commands; // grammar for Shell commands 

@header{
  package com.gads.view.parser; 
}
command : stat NEWLINE;

stat: 'emul'   ADDRESS   #EMULATOR
		| 'geofix' ADDRESS   #GEOFIX
		| 'help'   #HELP 	
		| 'sendroute' INT?   #SENDROUTE // Comando opzionale per l'intervallo di tempo
		| 'newroute'  #NEWROUTE 
		| 'navigation' INT #NAVIGATION
		| 'path' ADDRESS      #PATH
		| 'port' INT   #PORT
		| 'routelist'  #ROUTELIST
		| 'quit'       #QUIT
		| 'next'       #NEXT
		| 'exit'       #EXIT
		|              #EMPTY
;  
NEWLINE:'\r'? '\n' ;     // newlines (end-statement signal)
INT    : [0-9]+;

ADDRESS: '"' ('""'|~'"')* '"' ; // quote-quote is an escaped quote
IDENTIFIER : (LETTER | [0-9])+ ;
fragment
LETTER : [a-zA-Z] ;
WS  :   [ \t\n\r]+ -> skip ;