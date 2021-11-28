package MVC;

import java.util.Scanner;

import org.json.JSONException;

/**
 * Controls the input and the output for the user
 */
public class Controller {
    public static void main(String[] args) {
        RequestTickets tickets = new RequestTickets();
        JsonFormatter formatter = new JsonFormatter();
        // Viewer starts at page number 1
        int page = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("                                    Welcome to the Zendesk Ticket Viewer!\n");
        System.out.println("Please enter: \n1 to view tickets\n2 to quit\n");
        int optionSelected = sc.nextInt();
        System.out.println();
        if (optionSelected == 1) {
            try {
                // If we are unable to request the tickets let user know and quit
                if (tickets.getAllTickets() == null) {
                    System.out.println(
                            "Unable to request tickets, please make sure the url and the credentials entered are valid.");
                    sc.close();
                    return;
                }
                // if user does not have any tickets on their account then simply let them know
                // and quit.
                if (tickets.getAllTickets().getJSONArray("tickets").length() <= 0) {
                    System.out.println("There are no tickets to view, goodbye.");
                    sc.close();
                    return;
                }
                formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                        tickets.getAllTickets().getJSONArray("tickets").length());
                // prints page number
                System.out.println("                               Page " + String.valueOf(page));
            } catch (JSONException e) {
                System.out.println("Error occured when loading tickets.");
            }
        } else if (optionSelected == 2) {
            System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.");
            sc.close();
            return;
        }
        // Continues the application untill user decides to quit
        while (true) {
            boolean ticketError = false;
            System.out.println("Please enter: \n1 to select a ticket\n2 to quit\n3 to change page number\n");
            optionSelected = sc.nextInt();
            // If a ticket is selected then expand that ticket
            if (optionSelected == 1) {
                System.out.println("Select ticket id:");
                optionSelected = sc.nextInt();
                try {
                    formatter
                            .printFullTicket(
                                    tickets.getAllTickets().getJSONArray("tickets").getJSONObject(optionSelected - 1));
                } catch (JSONException e) {
                    System.out.println("Error occured when loading ticket. Make sure the id is valid");
                    ticketError = true;
                }
                if (!ticketError) {
                    System.out.println("Please enter: \n1 to go back\n2 to quit\n");
                    optionSelected = sc.nextInt();
                    if (optionSelected != 1 && optionSelected != 2)
                        System.out.println("Invalid option, going back to main menu.");
                }

                // go back to page if user chooses so
                if (optionSelected == 1) {
                    try {
                        formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                                tickets.getAllTickets().getJSONArray("tickets").length());
                    } catch (JSONException e) {
                        System.out.println("Error occured when going back.");
                    }
                    // prints page number
                    System.out.println("                               Page " + String.valueOf(page));
                    // if user chooses to quit then thank them and quit
                } else if (optionSelected == 2) {
                    System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.");
                    sc.close();
                    return;
                }
                // if user chooses to quit then thank them and quit
            } else if (optionSelected == 2) {
                System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.");
                sc.close();
                return;
                // if user wants to view another page then load that specific page
            } else if (optionSelected == 3) {
                System.out.println("Select page number:");
                page = sc.nextInt();
                try {
                    formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                            tickets.getAllTickets().getJSONArray("tickets").length());
                } catch (JSONException e) {
                    System.out.println("Error occured when changing pages.");
                }
                try {
                    if (page <= tickets.getAllTickets().getJSONArray("tickets").length() / 25)
                        // prints page number
                        System.out.println("                               Page " + String.valueOf(page));
                } catch (JSONException e) {
                }
            } else
                System.out.println("Invalid selection");
        }
    }
}
