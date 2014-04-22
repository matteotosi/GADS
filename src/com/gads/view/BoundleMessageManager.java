package com.gads.view;


import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * A message manager allows to build parametrised messages starting from
 * message-formats stored in a resource bundle. Message-formats are stored in a
 * resource bundle in the usual form and are accessible through their key.
 * 
 * @author Mauro Ferrari
 */
public class BoundleMessageManager {

	private final ResourceBundle MESSAGES;

	// Managing messages

	/**
	 * Constructs a message manager reading messages from the specified bundle.
	 * @param bundle the bundle containing messages.
	 */
	public BoundleMessageManager(ResourceBundle bundle) {
		MESSAGES = bundle;
	}

	/**
	 * Build a message manager reading messages from the bundle with the specified
	 * name.
	 * @param bundleName the bundle containing messages.
	 */
	public BoundleMessageManager(String bundleName) {
		this(ResourceBundle.getBundle(bundleName));
	}

	/**
	 * Returns the message built from the message-format with the specified key
	 * and the specified arguments.
	 * @param key the key of the message in the bundle.
	 * @param args the arguments for the message.
	 * @return the message.
	 * @throws MissingResourceException
	 * @throws MissingFormatArgumentException
	 */
	public String getMsg(String key, Object... args) throws MissingResourceException,
	    MissingFormatArgumentException {
		String message = null;
		String m = MESSAGES.getString(key);
		message = String.format(m, (Object[]) args);
		return message;
	}

	/**
	 * Returns the message for the given key.
	 * @param key the key.
	 * @return the message.
	 * @throws MissingResourceException
	 */
	public String getMsg(String key) throws MissingResourceException {
		return MESSAGES.getString(key);
	}

}
