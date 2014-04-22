package com.gads.view.parser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CommandsErrorListener extends BaseErrorListener {

	/*
	 * @see
	 * org.antlr.v4.runtime.BaseErrorListener#syntaxError(org.antlr.v4.runtime
	 * .Recognizer, java.lang.Object, int, int, java.lang.String,
	 * org.antlr.v4.runtime.RecognitionException)
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
	    int charPositionInLine, String msg, RecognitionException e) {

		// TODO: migliorare
		System.out.println("Wrong command!");
		//System.out.println("MSG: " + msg);
	}

}
