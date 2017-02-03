package net.rcarz.jiraclient.agile;

import net.rcarz.jiraclient.Issue.SearchResult;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.RestClient;
import net.sf.json.JSONObject;

/**
 * Created on 2016-05-20.
 * @author pldupont
 */
public class Issue extends AgileResource {

    /**
     * Creates a new Agile Issue resource.
     *
     * @param restclient REST client instance
     * @param json       JSON payload
     */
    public Issue(RestClient restclient, JSONObject json) {
        super(restclient, json);
    }

    /**
     * Retrieves the issue matching the ID. 
     *
     * @param restclient REST client instance
     * @param id         Internal JIRA ID of the issue
     * @return an issue instance
     * @throws JiraException when the retrieval fails
     */
    public static Issue get(RestClient restclient, long id) throws JiraException {
        return AgileResource.get(restclient, Issue.class, RESOURCE_URI + "issue/" + id);
    }

    /**
     * Retrieves the issue matching the ID.
     *
     * @param restclient REST client instance
     * @param key        JIRA key of the issue
     * @return an issue instance
     * @throws JiraException when the retrieval fails
     */
    public static Issue get(RestClient restclient, String key) throws JiraException {
        return AgileResource.get(restclient, Issue.class, RESOURCE_URI + "issue/" + key);
    }
    
    /**
     * Retrieves all the issues for a given board and sprint.
     * 
     * @param restclient REST client instance
     * @param boardId
     * @param sprintId
     * @param jql
     * @param includedFields
     * @param expandFields
     * @param maxResults
     * @param startAt
     * @return a set of issues
     * @throws JiraException when the retrieval fails
     */
    public static SearchResult getIssues(RestClient restclient, long boardId, long sprintId, String jql,
            String includedFields, String expandFields, Integer maxResults, Integer startAt) throws JiraException {
    	String endpoint = "board/" + boardId + "/sprint/" + sprintId + "/issue";
    	SearchResult sr = new SearchResult(restclient, jql, includedFields, expandFields, maxResults, startAt, endpoint, RESOURCE_URI );
    	
    	return sr;
    }
}
