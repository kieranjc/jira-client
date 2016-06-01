package net.rcarz.jiraclient.agile;

import static org.junit.Assert.assertEquals;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue.SearchResult;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

import org.junit.BeforeClass;
import org.junit.Test;

public class IssueTest {

	private static JiraClient jira;
	private static AgileClient ac;
	private static Sprint sprint;
	private static Board board;

	@BeforeClass
	public static void setup() {
        BasicCredentials creds = new BasicCredentials("kcummins", "h0lysh1ttas");
        try {
			jira = new JiraClient("https://thetasgroup.atlassian.net", creds);
			ac = new AgileClient(jira);
			board = ac.getBoard(92);
			sprint = board.getSprints().get(3);
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
	
	@Test
	public void getIssues() throws JiraException {
		long boardId = board.getId();
		long sprintId = sprint.getId();
		String jql = "issuetype = Story";
		SearchResult sr = Issue.getIssues(jira.getRestClient(), boardId, sprintId, jql, "name,summary,key", null, 10, 0);
		assertEquals(10, sr.issues.size());
	}

}
