package AGS_CINEMAS.ags_cinemas.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AGS_CINEMAS.ags_cinemas.ENTITY.ShowEntity;
import AGS_CINEMAS.ags_cinemas.ENTITY.TicketEntity;
import AGS_CINEMAS.ags_cinemas.SERVICE.ShowService;

@RestController
public class ShowController {

	@Autowired
	ShowService showService;
	
	@PostMapping("insertShow")
	public ShowEntity insertShow(@RequestBody ShowEntity showEntity)
	{
		return showService.insertShow(showEntity);
	}
	
	@GetMapping("shows")
	public List<ShowEntity> getAllShow()
	{
		return showService.getAllShow();
	}
	
	@GetMapping("shows/{showId}")
	public ShowEntity getAllShow(@PathVariable(value="showId") int id)
	{
		return showService.getShow(id);
	}
	
	@PostMapping("book")
	public TicketEntity book(@RequestBody TicketEntity ticketEntity)
	{
		return showService.book(ticketEntity);
	}
}
