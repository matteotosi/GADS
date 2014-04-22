// Generated from Commands.g4 by ANTLR 4.0

  package com.gads.view.parser; 

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandsLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, NEWLINE=13, INT=14, ADDRESS=15, IDENTIFIER=16, 
		WS=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'navigation'", "'quit'", "'exit'", "'emul'", "'newroute'", "'next'", 
		"'routelist'", "'sendroute'", "'path'", "'help'", "'port'", "'geofix'", 
		"NEWLINE", "INT", "ADDRESS", "IDENTIFIER", "WS"
	};
	public static final String[] ruleNames = {
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "NEWLINE", "INT", "ADDRESS", "IDENTIFIER", "LETTER", 
		"WS"
	};


	public CommandsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Commands.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 17: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\23\u009d\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\5\16{\n\16\3\16\3\16\3\17\6\17\u0080\n\17\r"+
		"\17\16\17\u0081\3\20\3\20\3\20\3\20\7\20\u0088\n\20\f\20\16\20\u008b\13"+
		"\20\3\20\3\20\3\21\3\21\6\21\u0091\n\21\r\21\16\21\u0092\3\22\3\22\3\23"+
		"\6\23\u0098\n\23\r\23\16\23\u0099\3\23\3\23\2\24\3\3\1\5\4\1\7\5\1\t\6"+
		"\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35"+
		"\20\1\37\21\1!\22\1#\2\1%\23\2\3\2\7\3\62;\3$$\3\62;\4C\\c|\5\13\f\17"+
		"\17\"\"\u00a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2%\3\2\2\2\3\'\3\2\2\2\5\62\3\2\2\2\7\67\3\2\2\2\t<\3\2\2\2\13A\3"+
		"\2\2\2\rJ\3\2\2\2\17O\3\2\2\2\21Y\3\2\2\2\23c\3\2\2\2\25h\3\2\2\2\27m"+
		"\3\2\2\2\31r\3\2\2\2\33z\3\2\2\2\35\177\3\2\2\2\37\u0083\3\2\2\2!\u0090"+
		"\3\2\2\2#\u0094\3\2\2\2%\u0097\3\2\2\2\'(\7p\2\2()\7c\2\2)*\7x\2\2*+\7"+
		"k\2\2+,\7i\2\2,-\7c\2\2-.\7v\2\2./\7k\2\2/\60\7q\2\2\60\61\7p\2\2\61\4"+
		"\3\2\2\2\62\63\7s\2\2\63\64\7w\2\2\64\65\7k\2\2\65\66\7v\2\2\66\6\3\2"+
		"\2\2\678\7g\2\289\7z\2\29:\7k\2\2:;\7v\2\2;\b\3\2\2\2<=\7g\2\2=>\7o\2"+
		"\2>?\7w\2\2?@\7n\2\2@\n\3\2\2\2AB\7p\2\2BC\7g\2\2CD\7y\2\2DE\7t\2\2EF"+
		"\7q\2\2FG\7w\2\2GH\7v\2\2HI\7g\2\2I\f\3\2\2\2JK\7p\2\2KL\7g\2\2LM\7z\2"+
		"\2MN\7v\2\2N\16\3\2\2\2OP\7t\2\2PQ\7q\2\2QR\7w\2\2RS\7v\2\2ST\7g\2\2T"+
		"U\7n\2\2UV\7k\2\2VW\7u\2\2WX\7v\2\2X\20\3\2\2\2YZ\7u\2\2Z[\7g\2\2[\\\7"+
		"p\2\2\\]\7f\2\2]^\7t\2\2^_\7q\2\2_`\7w\2\2`a\7v\2\2ab\7g\2\2b\22\3\2\2"+
		"\2cd\7r\2\2de\7c\2\2ef\7v\2\2fg\7j\2\2g\24\3\2\2\2hi\7j\2\2ij\7g\2\2j"+
		"k\7n\2\2kl\7r\2\2l\26\3\2\2\2mn\7r\2\2no\7q\2\2op\7t\2\2pq\7v\2\2q\30"+
		"\3\2\2\2rs\7i\2\2st\7g\2\2tu\7q\2\2uv\7h\2\2vw\7k\2\2wx\7z\2\2x\32\3\2"+
		"\2\2y{\7\17\2\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7\f\2\2}\34\3\2\2\2~\u0080"+
		"\t\2\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\36\3\2\2\2\u0083\u0089\7$\2\2\u0084\u0085\7$\2\2\u0085"+
		"\u0088\7$\2\2\u0086\u0088\n\3\2\2\u0087\u0084\3\2\2\2\u0087\u0086\3\2"+
		"\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7$\2\2\u008d \3\2\2\2\u008e"+
		"\u0091\5#\22\2\u008f\u0091\t\4\2\2\u0090\u008e\3\2\2\2\u0090\u008f\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\"\3\2\2\2\u0094\u0095\t\5\2\2\u0095$\3\2\2\2\u0096\u0098\t\6\2\2\u0097"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\23\2\2\u009c&\3\2\2\2\n\2z\u0081"+
		"\u0087\u0089\u0090\u0092\u0099";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}