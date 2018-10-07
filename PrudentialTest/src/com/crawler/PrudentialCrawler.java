package com.crawler;

public class PrudentialCrawler {

	public static void main(String[] args) {
		if (args.length != 1 && !args[0].isEmpty()
				&& !args[0].startsWith("prudential")) {
			System.err.println("Please provide domain starts with prudential");
			System.exit(-1);
		}
		String domain = args[0];
		// Crawler has been provided a word for search hence it could be an
		// end/break for indefinite crawl
		new SearchEngine().search(domain, "computer");
	}

}
