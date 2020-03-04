/*
package com.example.itext_pdf.PhantomJS;

public class PhantomEx2
{
	public static final String SCRIPT = "var page = require('webpage').create();\n" +
										"page.open('@@URL@@', function() {\n" +
										"  page.render('@@FILE@@');\n" +
										"});\n";

	public static void main(String[] args) {

		final String url = args[0];
		final String file = args[1];
		final String script = SCRIPT.replace("@@URL@@", url).replace("@@FILE@@", file);

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
								   "/path/to/phantomjs/bin/phantomjs");
		try {
			PhantomJSDriver phantomJSDriver = new PhantomJSDriver(capabilities);
			phantomJSDriver.executePhantomJS(script);
		} finally {
			phantomJSDriver.close();
		}
	}
}
*/
