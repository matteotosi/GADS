// Generated from Commands.g4 by ANTLR 4.0

  package com.gads.view.parser; 

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandsParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, NEWLINE=13, INT=14, ADDRESS=15, IDENTIFIER=16, 
		WS=17;
	public static final String[] tokenNames = {
		"<INVALID>", "'navigation'", "'quit'", "'exit'", "'emul'", "'newroute'", 
		"'next'", "'routelist'", "'sendroute'", "'path'", "'help'", "'port'", 
		"'geofix'", "NEWLINE", "INT", "ADDRESS", "IDENTIFIER", "WS"
	};
	public static final int
		RULE_command = 0, RULE_stat = 1;
	public static final String[] ruleNames = {
		"command", "stat"
	};

	@Override
	public String getGrammarFileName() { return "Commands.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommandsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CommandContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(CommandsParser.NEWLINE, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4); stat();
			setState(5); match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class HELPContext extends StatContext {
		public HELPContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterHELP(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitHELP(this);
		}
	}
	public static class EXITContext extends StatContext {
		public EXITContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterEXIT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitEXIT(this);
		}
	}
	public static class PATHContext extends StatContext {
		public TerminalNode ADDRESS() { return getToken(CommandsParser.ADDRESS, 0); }
		public PATHContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterPATH(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitPATH(this);
		}
	}
	public static class NAVIGATIONContext extends StatContext {
		public TerminalNode INT() { return getToken(CommandsParser.INT, 0); }
		public NAVIGATIONContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterNAVIGATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitNAVIGATION(this);
		}
	}
	public static class PORTContext extends StatContext {
		public TerminalNode INT() { return getToken(CommandsParser.INT, 0); }
		public PORTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterPORT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitPORT(this);
		}
	}
	public static class NEXTContext extends StatContext {
		public NEXTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterNEXT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitNEXT(this);
		}
	}
	public static class NEWROUTEContext extends StatContext {
		public NEWROUTEContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterNEWROUTE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitNEWROUTE(this);
		}
	}
	public static class SENDROUTEContext extends StatContext {
		public TerminalNode INT() { return getToken(CommandsParser.INT, 0); }
		public SENDROUTEContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterSENDROUTE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitSENDROUTE(this);
		}
	}
	public static class ROUTELISTContext extends StatContext {
		public ROUTELISTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterROUTELIST(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitROUTELIST(this);
		}
	}
	public static class EMULATORContext extends StatContext {
		public TerminalNode ADDRESS() { return getToken(CommandsParser.ADDRESS, 0); }
		public EMULATORContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterEMULATOR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitEMULATOR(this);
		}
	}
	public static class QUITContext extends StatContext {
		public QUITContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterQUIT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitQUIT(this);
		}
	}
	public static class GEOFIXContext extends StatContext {
		public TerminalNode ADDRESS() { return getToken(CommandsParser.ADDRESS, 0); }
		public GEOFIXContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterGEOFIX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitGEOFIX(this);
		}
	}
	public static class EMPTYContext extends StatContext {
		public EMPTYContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterEMPTY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitEMPTY(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		int _la;
		try {
			setState(28);
			switch (_input.LA(1)) {
			case 4:
				_localctx = new EMULATORContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(7); match(4);
				setState(8); match(ADDRESS);
				}
				break;
			case 12:
				_localctx = new GEOFIXContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(9); match(12);
				setState(10); match(ADDRESS);
				}
				break;
			case 10:
				_localctx = new HELPContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(11); match(10);
				}
				break;
			case 8:
				_localctx = new SENDROUTEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(12); match(8);
				setState(14);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(13); match(INT);
					}
				}

				}
				break;
			case 5:
				_localctx = new NEWROUTEContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(16); match(5);
				}
				break;
			case 1:
				_localctx = new NAVIGATIONContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(17); match(1);
				setState(18); match(INT);
				}
				break;
			case 9:
				_localctx = new PATHContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(19); match(9);
				setState(20); match(ADDRESS);
				}
				break;
			case 11:
				_localctx = new PORTContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(21); match(11);
				setState(22); match(INT);
				}
				break;
			case 7:
				_localctx = new ROUTELISTContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(23); match(7);
				}
				break;
			case 2:
				_localctx = new QUITContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(24); match(2);
				}
				break;
			case 6:
				_localctx = new NEXTContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(25); match(6);
				}
				break;
			case 3:
				_localctx = new EXITContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(26); match(3);
				}
				break;
			case NEWLINE:
				_localctx = new EMPTYContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\23!\4\2\t\2\4\3\t\3\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\21"+
		"\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\37\n\3\3\3\2"+
		"\4\2\4\2\2+\2\6\3\2\2\2\4\36\3\2\2\2\6\7\5\4\3\2\7\b\7\17\2\2\b\3\3\2"+
		"\2\2\t\n\7\6\2\2\n\37\7\21\2\2\13\f\7\16\2\2\f\37\7\21\2\2\r\37\7\f\2"+
		"\2\16\20\7\n\2\2\17\21\7\20\2\2\20\17\3\2\2\2\20\21\3\2\2\2\21\37\3\2"+
		"\2\2\22\37\7\7\2\2\23\24\7\3\2\2\24\37\7\20\2\2\25\26\7\13\2\2\26\37\7"+
		"\21\2\2\27\30\7\r\2\2\30\37\7\20\2\2\31\37\7\t\2\2\32\37\7\4\2\2\33\37"+
		"\7\b\2\2\34\37\7\5\2\2\35\37\3\2\2\2\36\t\3\2\2\2\36\13\3\2\2\2\36\r\3"+
		"\2\2\2\36\16\3\2\2\2\36\22\3\2\2\2\36\23\3\2\2\2\36\25\3\2\2\2\36\27\3"+
		"\2\2\2\36\31\3\2\2\2\36\32\3\2\2\2\36\33\3\2\2\2\36\34\3\2\2\2\36\35\3"+
		"\2\2\2\37\5\3\2\2\2\4\20\36";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}