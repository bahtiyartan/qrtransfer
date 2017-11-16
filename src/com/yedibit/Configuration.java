package com.yedibit;

public class Configuration {
	private static final String CFG_COMPRESSION = "-compression:";
	private static final String CFG_MAXCHAR = "-maxchar:";

	public boolean UseCompression = false;
	public int MaxChar = 1000; // max character count per qr code

	public Configuration(String[] appParms) {

		for (String string : appParms) {

			if (string.startsWith(CFG_COMPRESSION)) {
				try {
					UseCompression = Boolean.parseBoolean(string.replace(CFG_COMPRESSION, ""));
				} catch (Exception e) {
					this.printUsage();
				}
			}

			if (string.startsWith(CFG_MAXCHAR)) {
				try {
					MaxChar = Integer.parseInt(string.replace(CFG_MAXCHAR, ""));
				} catch (Exception e) {
					this.printUsage();
				}
			}

		}
	}

	public void printUsage() {
		System.out.println(CFG_COMPRESSION + "true|false");
		System.out.println(CFG_MAXCHAR + "{intvalue}");
	}

	public void printCurrentConfig() {
		System.out.println("Current Configuration:");
		System.out.println("\t" + CFG_COMPRESSION + UseCompression);
		System.out.println("\t" + CFG_MAXCHAR + MaxChar);
	}
}
