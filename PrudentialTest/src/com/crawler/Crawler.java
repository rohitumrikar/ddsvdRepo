package com.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private List<String> links = new ArrayList<String>(0);
	private List<String> extlinks = new ArrayList<String>(0);
	private Document htmlDocument;

	public boolean crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document htmlDocument = connection.get();
			this.htmlDocument = htmlDocument;
			if (!connection.response().contentType().contains("text/html")) {
				System.out
						.println("**Failure** Retrieved something other than HTML");
				return false;
			}
			Elements linksOnPage = htmlDocument.select("a[href]");
			System.out.println("Found (" + linksOnPage.size() + ") links");
			for (Element link : linksOnPage) {
				String linkfound = link.absUrl("href");
				if (linkfound != null
						&& !linkfound.isEmpty()
						&& (linkfound.contains("linkedin")
								|| linkfound.contains("google")
								|| linkfound.contains("facebook") || linkfound
									.contains("twitter"))) {
					System.out.println("External Links Found " + linkfound);
					extlinks.add(linkfound);
				} else {
					this.links.add(linkfound);
				}
			}
			return true;
		} catch (IOException ioe) {
			System.err.println("IOException occured while crawling "
					+ ioe.getMessage());
			return false;
		}
	}

	public boolean searchForWord(String searchWord) {
		if (this.htmlDocument == null) {
			System.out
					.println("ERROR! Call crawl() before performing analysis on the document");
			return false;
		}
		System.out.println("Searching for the word " + searchWord + "...");
		String bodyText = this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}

	public List<String> getLinks() {
		return this.links;
	}

	public List<String> getExtLinks() {
		return this.extlinks;
	}
}
