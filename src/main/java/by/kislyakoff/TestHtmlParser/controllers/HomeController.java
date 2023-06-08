package by.kislyakoff.TestHtmlParser.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import by.kislyakoff.TestHtmlParser.models.MatchData;
import by.kislyakoff.TestHtmlParser.service.HtmlParserService;

@Controller
@RequestMapping
public class HomeController {
	
	private final HtmlParserService parserService;
	
	public HomeController(HtmlParserService parserService) {
		this.parserService = parserService;
	}

	@GetMapping
	public String home() {
		return "home";
	}
	
	@GetMapping("/parser")
	@ResponseBody
	public List<MatchData> getMatchData() {
		return parserService.parse();
	}

}
