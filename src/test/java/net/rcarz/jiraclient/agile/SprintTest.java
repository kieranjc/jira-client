package net.rcarz.jiraclient.agile;

import static org.junit.Assert.*;

import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

import org.junit.BeforeClass;
import org.junit.Test;

public class SprintTest {
	
	private static JiraClient jira;
	private static AgileClient ac;
	private static Sprint sprint;
	SimpleDateFormat formater = new SimpleDateFormat(Field.DATETIME_FORMAT);

	@BeforeClass
	public static void setUp() {
        BasicCredentials creds = new BasicCredentials("kcummins", "h0lysh1ttas");
        try {
			jira = new JiraClient("https://thetasgroup.atlassian.net", creds);
			ac = new AgileClient(jira);
			sprint = ac.getBoard(92).getSprints().get(0);
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	public void getCompleteDate() {
		assertEquals("2014-12-22T09:43:10.102Z", formater.format(sprint.getCompleteDate()));
	}

	@Test
	public void getEndDate() {
		assertEquals("2014-12-19T11:33:00.000Z", formater.format(sprint.getEndDate()));
	}

	@Test
	public void getName() {
		assertEquals("8.28 Sprint 1", sprint.getName());
	}

	@Test
	public void getSelf() {
		assertEquals("https://thetasgroup.atlassian.net/rest/agile/1.0/sprint/157", sprint.getSelfURL());
	}

	@Test
	public void getStartDate() {
		assertEquals("2014-12-12T11:33:56.158Z", formater.format(sprint.getStartDate()));
	}

	@Test
	public void getState() {
		assertEquals("closed", sprint.getState());
	}
}
