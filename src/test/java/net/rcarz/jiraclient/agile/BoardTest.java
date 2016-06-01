package net.rcarz.jiraclient.agile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {
	
	private static JiraClient jira;
	private static AgileClient ac;

	@BeforeClass
	public static void setup() {
        BasicCredentials creds = new BasicCredentials("kcummins", "h0lysh1ttas");
        try {
			jira = new JiraClient("https://thetasgroup.atlassian.net", creds);
			ac = new AgileClient(jira);
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	

	@Test
	public void get() throws JiraException {
		Board board = ac.getBoard(103);
		assertEquals(103, board.getId());
	}
	
	@Test
	public void getBoards() throws JiraException {
		List <Board> boards = ac.getBoards();
		assertEquals(50, boards.size());
	}	

	@Test
	public void getSprints() throws JiraException {
		List<Sprint> sprints = ac.getBoard(92).getSprints();
		assertEquals(10, sprints.size());
	}

	@Test
	public void getType() throws JiraException {
		Board board = ac.getBoard(103);
		assertEquals("scrum", board.getType());
	}
}
