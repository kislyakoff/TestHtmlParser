package by.kislyakoff.TestHtmlParser.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class MatchData {

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDateTime time;
	private String teamName1;
	private String teamName2;
	private String tournament;
	private String sport;
	private String link;

	public MatchData(LocalDateTime time, String teamName1, String teamName2, String tournament, String sport,
			String link) {
		this.time = time;
		this.teamName1 = teamName1;
		this.teamName2 = teamName2;
		this.tournament = tournament;
		this.sport = sport;
		this.link = link;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public String getTeamName1() {
		return teamName1;
	}

	public String getTeamName2() {
		return teamName2;
	}

	public String getTournament() {
		return tournament;
	}

	public String getSport() {
		return sport;
	}

	public String getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "MatchData [time=" + time + ", teamName1=" + teamName1 + ", teamName2=" + teamName2 + ", tournament="
				+ tournament + ", sport=" + sport + ", link=" + link + "]";
	}
	
	

}
