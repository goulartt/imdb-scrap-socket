package br.com.imdb.query.service;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImdbService {
	/**
	 * Scrap imdb website based into query string title of the movie
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public String fetchHtml(String query) throws IOException {
		try {
			String titleFromQuery = getTitleFromQuery(query);
			final Document document = Jsoup.connect("http://www.imdb.com/find?tt=on;q="+URLEncoder.encode(titleFromQuery, "UTF-8")+"&s=tt&ttype=ft").get();
			StringBuilder builder = new StringBuilder();
			int counter = 0;
			builder.append("\n");
			for (Element row : document.select("table.findList tbody tr")) {

				final String title = row.select(".result_text").text();
				builder.append(title.split("aka")[0]);
				builder.append("\n");
				counter++;
			}
			return buildResponse(builder, counter);
		} catch (Exception e) {
			System.err.println("Error: "+e.toString());
			return e.getMessage();
		}

	}
	/**
	 * The protocol request is text based in format queryLenght:query,
	 * so I split the query and get only the query text
	 * @param query
	 * @return title
	 */
	private String getTitleFromQuery(String query) {
		return query.split(":")[1].trim();
	}
	
	/**
	 * The protocol response is text based in format payloadLenght:payload
	 * @param builder
	 * @param counter
	 * @return response
	 */
	private String buildResponse(StringBuilder builder, int counter) {
		if (counter == 0) return null;
		return counter+":"+builder.toString();
	}
}
