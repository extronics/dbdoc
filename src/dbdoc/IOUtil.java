package dbdoc;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jk
 * 
 */
public class IOUtil {

	/**
	 * <p>
	 * Attempts to open an input stream to the given resource url. The url may
	 * either be in a format suitable to be fed into java.net.URL or a string,
	 * starting with the resource:// protocol and continuing with a java
	 * resource path.
	 * </p>
	 * <p>
	 * For URLs starting with the resource:// protocol, the method will try to
	 * load the resource through the current context class loader. All other
	 * resources will be opened through java.net.URL.
	 * </p>
	 * 
	 * @param url
	 *            The resource url.
	 * @return An InputStream to the given resource.
	 * @throws IOException
	 *             If the stream can't be opened.
	 * @throws MalformedURLException
	 *             If the url format isn't recognized.
	 */
	public static InputStream getResourceStream(String url) throws IOException,
			MalformedURLException {
		try {
			URL locator = new URL(url);
			return locator.openStream();
		} catch (MalformedURLException ex) {
			int colon = url.indexOf(':');

			if (colon == -1) {
				throw ex;
			}

			String proto = url.substring(0, colon);

			if ("resource".equals(proto)) {
				String resource = url.substring(colon + 3);
				InputStream stream = Thread.currentThread()
						.getContextClassLoader().getResourceAsStream(resource);
				if (stream == null) {
					throw new IOException("Resource '" + url + "' not found.",
							ex);
				}
				return stream;
			} else {
				throw ex;
			}
		}
	}

	private IOUtil() {
	}
}
