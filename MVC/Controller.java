package MVC;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONArray;
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
        while (true) {
            System.out.println("Please enter: \n1 to view tickets\n2 to quit\n");
            int optionSelected = 0;
            try {
                optionSelected = sc.nextInt();
            } catch (InputMismatchException e) {
                optionSelected = 7;
            }
            sc.nextLine();
            System.out.println();
            // Print the page if the user chooses to do so
            if (optionSelected == 1) {
                try {
                    // If we are unable to request the tickets let user know and quit
                    if (tickets.getAllTickets() == null) {
                        System.out.println(
                                "Error: Unable to request tickets, please make sure the url and the credentials entered are valid.");
                        sc.close();
                        return;
                    }
                    JSONArray arrTickets = new JSONArray();
                    arrTickets = tickets.getAllTickets().getJSONArray("tickets");
                    // if user does not have any tickets on their account then simply let them know
                    // and quit.
                    if (arrTickets.length() <= 0) {
                        System.out.println("There are no tickets to view, goodbye.");
                        sc.close();
                        return;
                    }
                    formatter.printPage(arrTickets, page, arrTickets.length());
                    // prints page number
                    System.out.println("                               Page " + String.valueOf(page));
                } catch (JSONException | IllegalArgumentException e) {
                    System.out.println(
                            "Error occured when loading tickets. Please make sure the url and the credentials entered are valid.");
                    sc.close();
                    return;
                }
                break;
                // Quits if user chooses to do so
            } else if (optionSelected == 2) {
                System.out.println("Thank you for using the Zendesk Ticket Viewer, goodbye.");
                sc.close();
                return;
            } else
                System.out.println("Invalid selection, please choose one of the options displayed.");
        }
        // Continues the application until user decides to quit
        while (true) {
            boolean ticketError = false;
            JSONArray arrTickets = new JSONArray();
            System.out.println("Please enter: \n1 to select a ticket\n2 to quit\n3 to change page number\n");
            int optionSelected = 0;
            try {
                optionSelected = sc.nextInt();
            } catch (InputMismatchException e) {
                optionSelected = 7;
            }
            sc.nextLine();
            // If a ticket is selected then expand that ticket
            if (optionSelected == 1) {
                // if user doesn't input a number let them know to do so
                boolean invalidTicketId = false;
                System.out.println("Select ticket id:");
                try {
                    optionSelected = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid ticket ID, please enter a number.");
                    invalidTicketId = true;
                    sc.nextLine();
                }

                try {
                    System.out.println();
                    if (!invalidTicketId)
                        formatter.printFullTicket(
                                tickets.getAllTickets().getJSONArray("tickets").getJSONObject(optionSelected - 1));
                } catch (JSONException e) {
                    System.out.println("Error occured when loading ticket. Make sure the id is valid");
                    ticketError = true;
                }
                if (!ticketError) {
                    System.out.println("Please enter: \n1 to go back\n2 to quit\n");
                    try {
                        optionSelected = sc.nextInt();
                    } catch (InputMismatchException e) {
                        optionSelected = 7;
                        sc.nextLine();
                    }
                    if (optionSelected != 1 && optionSelected != 2)
                        System.out.println("Invalid option, going back to main menu.");
                }

                // go back to page if user chooses so
                if (optionSelected == 1) {
                    try {
                        arrTickets = tickets.getAllTickets().getJSONArray("tickets");
                    } catch (JSONException e1) {
                        System.out.println("Error: Unable to load tickets.");
                        sc.close();
                        return;
                    }
                    formatter.printPage(arrTickets, page, arrTickets.length());
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
                int oldPageNum = page;
                int numOfTickets = 0;
                System.out.println("Select page number:");
                try {
                    page = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please choose a number. Going back to main menu.");
                    page = oldPageNum;
                    sc.nextLine();
                }
                try {
                    System.out.println();
                    numOfTickets = tickets.getAllTickets().getJSONArray("tickets").length();
                    // If page entered is invalid let user know
                    if (page > numOfTickets / 25 + 1) {
                        System.out.println("Page number chosen is invalid, going back to main menu. Valid pages: from "
                                + "1 to "
                                + String.valueOf(numOfTickets / 25 + 1));
                        System.out.println();
                    } else
                        formatter.printPage(tickets.getAllTickets().getJSONArray("tickets"), page,
                                numOfTickets);
                } catch (JSONException e) {
                    System.out.println("Error occured when changing pages.");
                }
                if (page <= numOfTickets / 25)
                    System.out.println("                               Page " + String.valueOf(page));
                else
                    page = oldPageNum;
            } else
                System.out.println("Invalid selection, please choose one of the options displayed.");
        }
    }
}
