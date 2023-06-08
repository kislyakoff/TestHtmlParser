package by.kislyakoff.TestHtmlParser.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import by.kislyakoff.TestHtmlParser.models.MatchData;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HtmlParserService {
	
	private static final String DATE_FORMAT = "dd MMM HH:mm";
	private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern(DATE_FORMAT)
			.parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
			.toFormatter(Locale.US);
	
	@Value("${test.url}")
	private String url;
	
	public List<MatchData> parse() {
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements matchList = doc.select(".highlights--item.full-width");
			
			List<MatchData> data = new ArrayList<>();
			
			matchList.forEach(match -> {
				
				String timeString = match.select(".time").first().ownText();
				LocalDateTime time = LocalDateTime.parse(timeString, DATE_FORMATTER);
				
				Elements clubs = match.select(".clubs span");
				
				String teamName1 = clubs.get(0).text();
				String teamName2 = clubs.get(1).text();
				
				String[] sportAndTournament = match.select(".meta span").text().split("/", 2);
				
				String sport = sportAndTournament[0].trim();
				String tournament = sportAndTournament[1].trim();
				
				String link = match.select("td a").first().attr("href");
				
				MatchData matchData = new MatchData(time, teamName1, teamName2, tournament, sport, link);
				
				data.add(matchData);
				
			});
			
			return data;
			
		} catch (IOException ex) {
			log.error("Parser error, url:{}", url, ex);
			throw new ParserException(ex);
		}
	}
	
}
