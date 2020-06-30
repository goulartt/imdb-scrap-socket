package br.com.imdb.query.service;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImdbService {

	public String fetchHtml(String query) throws IOException {
		String titleFromQuery = getTitleFromQuery(query);
		final Document document = Jsoup.connect("http://www.imdb.com/find?tt=on;q="+URLEncoder.encode(titleFromQuery, "UTF-8")+"&s=tt&ttype=ft").get();
		StringBuilder builder = new StringBuilder();
		int counter = 0;
		for (Element row : document.select("table.findList tbody tr")) {

			final String title = row.select(".result_text").text();
			builder.append(title.split("aka")[0]);
			builder.append("\n");
			counter++;
		}
		return buildResponse(builder, counter);
	}

	private String getTitleFromQuery(String query) {
		return query.split("<query>")[1].replace("<\\query>", "").trim();
	}

	private String buildResponse(StringBuilder builder, int counter) {
		return "<payloadLenght>\n"+counter+"\n<\\payloadLenght>:\n<payload>\n"+builder.toString()+"\n<\\payload>";
	}
}
