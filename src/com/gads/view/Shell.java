package com.gads.view;

import java.io.BufferedReader; 
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gads.controller.ExistingRouteException;
import com.gads.controller.GeoController;
import com.gads.controller.MissingAVDException;
import com.gads.controller.MissingRouteException;
import com.gads.controller.NoNavigationException;
import com.gads.controller.NoRoutesException;
import com.gads.controller.OfflineException;
import com.gads.controller.ViewBridge;
import com.gads.controller.WrongPathException;
import com.gads.view.parser.CommandsBaseListener;
import com.gads.view.parser.CommandsErrorListener;
import com.gads.view.parser.CommandsLexer;
import com.gads.view.parser.CommandsParser;
import com.gads.view.parser.CommandsParser.*;

public class Shell extends CommandsBaseListener implements ViewBridge {

	private Main shell;
	private static ShellMessageManager manager;
	private GeoController controller;

	//Input-output managers
	private BufferedReader br;
	boolean quit = false;
	CommandsParser parser; // share single parser instance
	File currentDir = new File("."); //$NON-NLS-1$

	Shell() {
		br = new BufferedReader(new InputStreamReader(System.in));
		parser = new CommandsParser(null); // share single parser instance
		parser.removeErrorListeners();
		parser.addErrorListener(new CommandsErrorListener());
		manager = new ShellMessageManager();
		controller = new GeoController(this);
	}

	// Commands implementation

	@Override
	public void exitEMPTY(EMPTYContext ctx) {
		// do nothing
	}

	@Override
	public void exitEMULATOR(EMULATORContext ctx) {
		String e = ctx.ADDRESS().getText();
		try {
			controller.startEmulator(e);
		} catch (WrongPathException e1) {
			// TODO Auto-generated catch block
			println(manager.getMsg("WRONGPATH.exception"));
		}
	}

	@Override
	public void exitGEOFIX(GEOFIXContext ctx) {
		String loc = ctx.ADDRESS().getText();
		try {
			controller.setLocation(loc);
		} catch (MissingAVDException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOAVD.exception", e.getMessage()));
		} catch (OfflineException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("OFFLINE.exception"));
		}
	}

	public void exitHELP(HELPContext ctx) {
		String[] helpMessagesKetys = { "EMULATOR.help", "GEOFIX.help", "HELP.help", "SENDROUTE.help",
		    "NEWROUTE.help", "NAVIGATION.help", "PATH.help", "PORT.help", "NEXT.help", "EXIT.help",
		    "QUIT.help" };

		String availableComands = "";
		for (int i = 0; i < helpMessagesKetys.length; i++)
			availableComands += manager.getMsg(helpMessagesKetys[i])
			    + (i < helpMessagesKetys.length ? "\n" : "");

		println(availableComands);
	}

	@Override
	public void exitNAVIGATION(NAVIGATIONContext ctx) {
		int num = Integer.parseInt(ctx.INT().getText());
		try {
			controller.simulateNavigation(num);
		} catch (MissingAVDException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOAVD.exception", e.getMessage()));
		} catch (MissingRouteException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOROUTE.exception"));
		} catch (NoRoutesException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOROUTES.exception"));
		}
	}

	@Override
	public void exitNEXT(NEXTContext ctx) {

		try {
			controller.nextStep();
		} catch (MissingAVDException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOAVD.exception", e.getMessage()));
		} catch (NoNavigationException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NONAV.exception"));
		}

	}

	@Override
	public void exitEXIT(EXITContext ctx) {

		try {
			controller.exitNav();
		} catch (NoNavigationException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NONAV.exception"));
		}

	}

	@Override
	public void exitNEWROUTE(NEWROUTEContext ctx) {
		String from = manager.readLn("NEWROUTE.start");
		String to = manager.readLn("NEWROUTE.end");
		try {
			controller.newRoute(from, to);
		} catch (OfflineException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("OFFLINE.exception"));
		} catch (ExistingRouteException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("EXISTINGROUTE.exception"));
		}
	}

	@Override
	public void exitPATH(PATHContext ctx) {
		String p = ctx.ADDRESS().getText();
		p.replaceAll("\"", "");
		controller.setPath(p);
	}

	@Override
	public void exitPORT(PORTContext ctx) {
		int port = Integer.parseInt(ctx.INT().getText());
		controller.setPort(port);
	}

	@Override
	public void exitROUTELIST(CommandsParser.ROUTELISTContext ctx) {
		String r="";
		try {
			JSONArray arr = (controller.getRoutes());
			for(int i = 0; i < arr.length() ; i++){
				r+=(i+1)+"]";
				r+="\nStart   : "+arr.getJSONObject(i).getString("start") +
						"\nEnd     : "+ arr.getJSONObject(i).getString("end") +
						"\nDuration: "+ arr.getJSONObject(i).getString("duration") +
						"\nDistance: "+ arr.getJSONObject(i).getString("distance");
				
				r+= "\n----------------------------------------\n";
				
			}
		} catch (NoRoutesException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOROUTES.exception"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			println(manager.getMsg("NOROUTES.exception"));
			e.printStackTrace();
		}
		
		println(r);
	}

	@Override
	public void exitSENDROUTE(SENDROUTEContext ctx) {
		int num = 0;
		if (ctx.INT() != null)
			num = Integer.parseInt(ctx.INT().getText());
		try {
			controller.sendRoute(num);
		} catch (NoRoutesException e) {
			println(manager.getMsg("NOROUTES.exception"));
		} catch (MissingAVDException e) {
			println(manager.getMsg("NOAVD.exception", e.getMessage()));
		} catch (MissingRouteException e) {
			println(manager.getMsg("NOROUTE.exception"));
		}
	}

	@Override
	public void exitQUIT(QUITContext ctx) {

		if (!GeoController.pool.isEmpty())
			controller.stopSendingRoute();
		else {
			println(manager.getMsg("QUIT.output")); //$NON-NLS-1$
			quit = true;
		}

	}

	/**
	 * Returns the string describing the array, where the elements are separated
	 * by the specified separator.
	 * @param a the array.
	 * @param separator the separator.
	 */
	private static String toString(Object[] a, String separator) {
		String str = ""; //$NON-NLS-1$
		for (int i = 0; i < a.length; i++) {
			str += a[i].toString() + (i < a.length - 1 ? separator : ""); //$NON-NLS-1$
		}
		return str;
	}

	/**
	 * Prints the prompt
	 */
	private void prompt() throws IOException {
		System.out.print("[" + currentDir.getCanonicalPath() + "]> "); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void println(String output) {
		System.out.println(output);
	}

	private void print(String output) {
		System.out.print(output);
	}

	private void newln() {
		System.out.println("");
	}

	private ParseTree parse(String str, int line) {
		// create new lexer and token stream for each line (expression)
		ANTLRInputStream input = new ANTLRInputStream(str + "\n"); //$NON-NLS-1$
		CommandsLexer lexer = new CommandsLexer(input);
		lexer.setLine(line); // notify lexer of input position
		lexer.setCharPositionInLine(0);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser.setInputStream(tokens); // notify parser of new token stream
		lexer.reset();
		return parser.command(); // start the parser
	}

	void start() throws Exception {
		int line = 1; // track input expr line numbers
		do { // while we have more expressions
			prompt();
			String cmd = br.readLine(); // read a command
			ParseTree tree = parse(cmd, line);
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(this, tree); // walk parse tree 
		} while (!quit);
	}

	@Override
	public void progress(int percent) {

		if (percent == 0)
			print("Progress: [" + percent + "%");
		else if (percent == 100)
			println(" " + percent + "%]");
		else
			print(" " + percent + "%");

	}

	@Override
	public void navProgress(int percent) {

		if (percent < 100) {
			newln();
			println(manager.getMsg("NAV.step", percent
			    + "%] --> Enter 'next' for the next step, 'exit' to stop the navigation."));
		} else {
			newln();
			println(manager.getMsg("NAV.step", percent + "%]"));
		}

	}

	@Override
	public void onNavigationInterrupted() {
		// TODO Auto-generated method stub
		println(manager.getMsg("NAV.interrupted"));
	}

	@Override
	public void onNavigationStarted() {
		// TODO Auto-generated method stub
		println(manager.getMsg("NAV.started"));
	}

	@Override
	public void onNavigationFinished() {
		// TODO Auto-generated method stub
		println(manager.getMsg("NAV.finished"));
	}

	@Override
	public void onRouteSendingStarded() {
		// TODO Auto-generated method stub
		println(manager.getMsg("ROUTE.started"));
	}

	@Override
	public void onRouteSendingInterrupted() {
		// TODO Auto-generated method stub
		newln();
		println(manager.getMsg("ROUTE.interrupted"));
	}

	@Override
	public void onRouteSendingFinished() {
		// TODO Auto-generated method stub
		println(manager.getMsg("ROUTE.finished"));
	}

}
