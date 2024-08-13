package AGS_CINEMAS.ags_cinemas.SERVICE;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import AGS_CINEMAS.ags_cinemas.ENTITY.ShowEntity;
import AGS_CINEMAS.ags_cinemas.ENTITY.TicketEntity;
import AGS_CINEMAS.ags_cinemas.REPOSITORY.ShowRepository;
import AGS_CINEMAS.ags_cinemas.REPOSITORY.TicketRepository;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    public ShowEntity insertShow(ShowEntity showEntity) {
        return showRepository.save(showEntity);
    }

    public List<ShowEntity> getAllShow() {
        return showRepository.findAll();
    }

    public ShowEntity getShow(int id) {
        return showRepository.findById(id).orElse(null);
    }

    public TicketEntity book(TicketEntity ticketEntity) {
        List<ShowEntity> shows = showRepository.findAll();

        for (ShowEntity x : shows) {
            if (x.getMovieId() == ticketEntity.getMovieId() && x.getShowTime().equals(ticketEntity.getShowTime())) {
                if (x.getAvailableTickets() >= ticketEntity.getNumberOfTickets()) {
                    ticketRepository.save(ticketEntity);
                    x.setAvailableTickets(x.getAvailableTickets() - ticketEntity.getNumberOfTickets());
                    showRepository.save(x);
                    generatePdf(ticketEntity);  // Generate PDF after successful booking
                    return ticketEntity;
                }
            }
        }
        return null;
    }

    private void generatePdf(TicketEntity ticketEntity) {
        String dest = "C:\\Users\\DELL\\Downloads\\AGS BOOKED TICKETS" + ticketEntity.getUserId() + ".pdf";  // Specify the directory

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Movie ID: " + ticketEntity.getMovieId()));
            document.add(new Paragraph("Show Time: " + ticketEntity.getShowTime()));
            document.add(new Paragraph("Number of Tickets: " + ticketEntity.getNumberOfTickets()));
            document.add(new Paragraph("User ID: " + ticketEntity.getUserId()));

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
