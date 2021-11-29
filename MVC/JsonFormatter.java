package MVC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************** View ************************************
 */
public class JsonFormatter {

    /**
     * Prints 25 or less tickets to be viewed by user
     * 
     * @param tickets      tickets that user has on his account
     * @param page         page number user is on
     * @param numOfTickets number of tickets that user has on his account
     */
    public void printPage(JSONArray tickets, int page, int numOfTickets) {
        System.out.println("  ID  Status Priority           Subject              Time Created");
        // holds the index of the last ticket in the page
        int lastid = page * 25;
        if (lastid > numOfTickets)
            lastid = numOfTickets;

        // holds the index of the first ticket in the page
        int firstId = (page - 1) * 25;

        // prints the tickets in a list view
        for (int i = firstId; i < lastid; i++) {
            printLine();
            try {
                printTicket(tickets.getJSONObject(i));
            } catch (JSONException e) {
                System.out.println("Error occured when trying to print tickets");
            }
        }
    }

    // Helper method to print a line
    private void printLine() {
        System.out.println("______________________________________________________________________________");
    }

    /**
     * Helper method that prints a ticket with minimal information.
     * 
     * @param ticket ticket being printed
     */
    private void printTicket(JSONObject ticket) {
        try {
            System.out.print("| " + ticket.getString("id") + " |");

            System.out.print(" " + ticket.getString("status") + " |");
            System.out.print(" " + ticket.getString("priority") + " |");
            // Makes sure the ticket viewer is asthetically pleasing
            String subject = ticket.getString("subject");
            if (subject.length() > 30 && ticket.getString("id").length() == 1)
                subject = subject.substring(0, 27) + "...";
            else if (subject.length() > 30 && ticket.getString("id").length() == 2)
                subject = subject.substring(0, 26) + "... ";
            else if (subject.length() > 30 && ticket.getString("id").length() == 3)
                subject = subject.substring(0, 25) + "... ";
            else if (subject.length() < 30) {
                while (subject.length() < 30)
                    subject = subject + " ";
            } else if (subject.length() < 30) {
                while (subject.length() < 30)
                    subject = subject + " ";
            }

            System.out.print(" " + subject + " |");
            // Format date into a user friendly format
            String fulldate = ticket.getString("created_at").replace('T', ' ').replaceFirst("Z", "");
            String[] dates = fulldate.split(" ");
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(dates[1]);
            System.out.println(" " + dates[0] + " at " + new SimpleDateFormat("K:mm a").format(dateObj) + " |");
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints an indivisual ticket with extra details
     * 
     * @param ticket ticket being printed
     */
    public void printFullTicket(JSONObject ticket) {
        String id = "";
        try {
            id = ticket.getString("id");
            System.out.print("Subject: " + ticket.getString("subject") + "                Status: ");
            System.out.print(ticket.getString("status") + "      ");
            // Format date into a user friendly format
            String fulldate = ticket.getString("created_at").replace('T', ' ').replaceFirst("Z", "");
            String[] dates = fulldate.split(" ");
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = sdf.parse(dates[1]);
            System.out.println("Created " + dates[0] + " at " + new SimpleDateFormat("K:mm a").format(dateObj));
            System.out.println();
            System.out.println("Description: " + ticket.getString("description"));
            String fullUpdateddate = ticket.getString("created_at").replace('T', ' ').replaceFirst("Z", "");
            String[] datesUpdated = fullUpdateddate.split(" ");
            SimpleDateFormat sdf2 = new SimpleDateFormat("H:mm");
            Date dateObj2 = sdf2.parse(datesUpdated[1]);
            System.out.println(
                    "                                                                   Last updated: "
                            + datesUpdated[0] + " at " + new SimpleDateFormat("K:mm a").format(dateObj2));
        } catch (JSONException | ParseException e) {
            System.out.println("Unable to view ticket with id" + id);
        }
    }
}
