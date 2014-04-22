package com.gads.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * The class printing messages.
 * @author Mauro Ferrari
 */
class ShellMessageManager {

	private PrintStream out = System.out;
	private BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
	private BoundleMessageManager manager = new BoundleMessageManager("com.gads.view.shell");

	
	
	
	void print(String key, Object... args) {
		String str = null;
		if (key == null) {
			str = buildStringOf(args, " ");
		} else
			str = manager.getMsg(key, args);
		out.print(str);
	}

	void println(String key, Object... args) {
		String str = null;
		if (key == null) {
			str = buildStringOf(args, " ");
		} else
			str = manager.getMsg(key, args);
		out.println(str);
	}

	void strprintln(String str) {
		out.println(str);
	}

	String getMsg(String key, Object... args) {
		return manager.getMsg(key, args);
	}

	String getMsg(String key) {
		return manager.getMsg(key);
	}

	String readLn(String msgKey) {
		out.print(manager.getMsg(msgKey));
		try {
			return inReader.readLine();
		} catch (Exception e) {
			print("IO.IO.exception", e.getMessage());
			return null;
		}

	}

	/**
	 * Returns the list consisting of the string describing the elements of
	 * <code>a</code> separated by the specified <code>separator</code>. If the
	 * specified array is <code>null</code> the method returns the empty string.
	 * @param a the array.
	 * @param separator the string to use as separator.
	 * @return the string.
	 */
	String buildStringOf(Object[] a, String separator) {
		if (a == null)
			return "";

		String str = "";
		for (int i = 0; i < a.length; i++)
			str += a[i].toString() + (i < a.length - 1 ? separator : "");
		return str;
	}

	/**
	 * Write on <code>file</code> the message built from the specified key and
	 * arguments; if <code>addMode</code> is <code>true</code> the string is
	 * appended to the end of file otherwise the file is rewritten.
	 * @param file the output file.
	 * @param addMode if <code>true</code> the string is appended to the end of
	 * file otherwise the file is rewritten.
	 * @param key the key of the message.
	 * @param args the arguments of the message.
	 */
	void writeToFile(File file, boolean addMode, String key, Object... args) {
		String str = null;
		if (key == null) {
			str = buildStringOf(args, " ");
		} else
			str = getMsg(key, args);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, addMode));
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
