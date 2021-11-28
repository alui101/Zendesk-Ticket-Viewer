package MVC;

import java.util.Scanner;

import org.json.JSONException;

public class Controller {
    public static void main(String[] args) {
        RequestTickets tickets = new RequestTickets();
        JsonFormatter formatter = new JsonFormatter();
        int page = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("                                    Welcome to the Zendesk Ticket Viewer!\n");
        System.out.println("Please enter: \n1 to view tickets\n2 to quit\n");
        int optionSelected = sc.nextInt();
        if (optionSelected == 1) {
            try {
                formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                        tickets.getAllTickets().getJSONArray("tickets").length());
            } catch (JSONException e) {
                System.out.println("Error occured when trying to print tickets");
            }
        } else if (optionSelected == 2) {
            System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.1");
        }
        System.out.println("Please enter: \n1 to select a ticket\n2 to quit\n3 to change page number\n");
        optionSelected = sc.nextInt();
        if (optionSelected == 1) {
            try {
                formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                        tickets.getAllTickets().getJSONArray("tickets").length());
            } catch (JSONException e) {
                System.out.println("Error occured when trying to print tickets");
            }
        } else if (optionSelected == 2) {
            System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.1");
        } else if (optionSelected == 2) {
            System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.1");
        sc.close();
    }
}
