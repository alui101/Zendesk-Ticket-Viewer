package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import MVC.RequestTickets;

/**
 * Does basic testing on the RequestTickets class
 */
public class RequestTicketsTest {
    private String subDomain = System.getenv("SUBDOMAIN");
    private String oAuthToken = System.getenv("OAUTHTOKEN");

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private RequestTickets request = new RequestTickets();

    @Test(expected = NumberFormatException.class)
    public void getTicket_With_String_Id_Test() {
        String id = "id";
        request.getTicket(Integer.parseInt(id), subDomain, oAuthToken);
    }

    @Test
    public void getTicket_PriorityTest() throws JSONException {
        assertEquals("normal", request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(0)
                .getString("priority"));
    }

    @Test
    public void getTicket_StatusTest() throws JSONException {
        assertEquals("open", request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(0)
                .getString("status"));
        assertEquals("open", request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(1)
                .getString("status"));
        assertEquals("open", request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(2)
                .getString("status"));
    }

    @Test
    public void getTicket_IDTest() throws JSONException {
        assertEquals("1",
                request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(0).getString("id"));
        assertEquals("2",
                request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(1).getString("id"));
        assertEquals("3",
                request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(2).getString("id"));
    }

    @Test
    public void getTicket_SubjectTest() throws JSONException {
        assertEquals("Sample ticket: Meet the ticket",
                request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(0)
                        .getString("subject"));
        assertEquals("ad sunt qui aute ullamco",
                request.getAllTickets(subDomain, oAuthToken).getJSONArray("tickets").getJSONObject(3)
                        .getString("subject"));
    }

    @Test
    public void getTicket_WrongSubDomain() throws JSONException {
        request.getTicket(1, subDomain + "45asd55ad", oAuthToken);
        String errorMess = "Invalid credentials or invalid id entered, please double check them and try again.\n";
        assertTrue(errorMess.equals(systemOutRule.getLogWithNormalizedLineSeparator()));
    }

    @Test
    public void getTicket_WrongToken() {
        request.getTicket(1, subDomain, oAuthToken + "4");
        String errorMess = "Invalid credentials or invalid id entered, please double check them and try again.\n";
        assertTrue(errorMess.equals(systemOutRule.getLogWithNormalizedLineSeparator()));
    }

    @Test
    public void getAllTicket_WrongSubDomain() throws JSONException {
        request.getAllTickets(subDomain + "45a789", oAuthToken);
        String errorMess = "Invalid credentials entered, please double check them and try again.\n";
        assertTrue(errorMess.equals(systemOutRule.getLogWithNormalizedLineSeparator()));
    }

    @Test
    public void getAllTicket_WrongToken() {
        request.getAllTickets(subDomain, oAuthToken + "7");
        String errorMess = "Invalid credentials entered, please double check them and try again.\n";
        assertTrue(errorMess.equals(systemOutRule.getLogWithNormalizedLineSeparator()));
    }

}
