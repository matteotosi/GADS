// Generated from Commands.g4 by ANTLR 4.0

  package com.gads.view.parser; 

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface CommandsListener extends ParseTreeListener {
	void enterEXIT(CommandsParser.EXITContext ctx);
	void exitEXIT(CommandsParser.EXITContext ctx);

	void enterHELP(CommandsParser.HELPContext ctx);
	void exitHELP(CommandsParser.HELPContext ctx);

	void enterPATH(CommandsParser.PATHContext ctx);
	void exitPATH(CommandsParser.PATHContext ctx);

	void enterNAVIGATION(CommandsParser.NAVIGATIONContext ctx);
	void exitNAVIGATION(CommandsParser.NAVIGATIONContext ctx);

	void enterPORT(CommandsParser.PORTContext ctx);
	void exitPORT(CommandsParser.PORTContext ctx);

	void enterNEXT(CommandsParser.NEXTContext ctx);
	void exitNEXT(CommandsParser.NEXTContext ctx);

	void enterNEWROUTE(CommandsParser.NEWROUTEContext ctx);
	void exitNEWROUTE(CommandsParser.NEWROUTEContext ctx);

	void enterCommand(CommandsParser.CommandContext ctx);
	void exitCommand(CommandsParser.CommandContext ctx);

	void enterSENDROUTE(CommandsParser.SENDROUTEContext ctx);
	void exitSENDROUTE(CommandsParser.SENDROUTEContext ctx);

	void enterROUTELIST(CommandsParser.ROUTELISTContext ctx);
	void exitROUTELIST(CommandsParser.ROUTELISTContext ctx);

	void enterEMULATOR(CommandsParser.EMULATORContext ctx);
	void exitEMULATOR(CommandsParser.EMULATORContext ctx);

	void enterQUIT(CommandsParser.QUITContext ctx);
	void exitQUIT(CommandsParser.QUITContext ctx);

	void enterEMPTY(CommandsParser.EMPTYContext ctx);
	void exitEMPTY(CommandsParser.EMPTYContext ctx);

	void enterGEOFIX(CommandsParser.GEOFIXContext ctx);
	void exitGEOFIX(CommandsParser.GEOFIXContext ctx);
}