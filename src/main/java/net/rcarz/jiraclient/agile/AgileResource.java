/**
 * jira-client - a simple JIRA REST client
 * Copyright (c) 2013 Bob Carroll (bob.carroll@alum.rit.edu)
 * <p>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.rcarz.jiraclient.agile;

import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.RestClient;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
<<<<<<< Updated upstream
import org.apache.commons.lang.BooleanUtils;
=======
>>>>>>> Stashed changes
import org.apache.commons.lang.math.NumberUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
<<<<<<< Updated upstream
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> Stashed changes

/**
 * A base class for Agile resources.
 *
<<<<<<< Updated upstream
=======
 * @author pldupont
>>>>>>> Stashed changes
 * @see "https://docs.atlassian.com/jira-software/REST/cloud/"
 */
public abstract class AgileResource {

    public static final String ATTR_ID = "id";
    public static final String ATTR_NAME = "name";
    public static final String ATTR_SELF = "self";

    public static final String RESOURCE_URI = "/rest/agile/1.0/";

    private RestClient restclient = null;
    private long id = 0;
    private String name;
    private String self;
<<<<<<< Updated upstream
    private Map<String, Object> attributes = new HashMap<String, Object>();
=======
    private JSONObject attributes = new JSONObject();
>>>>>>> Stashed changes

    /**
     * Creates a new Agile resource.
     *
     * @param restclient REST client instance
     * @param json       JSON payload
<<<<<<< Updated upstream
     */
    public AgileResource(RestClient restclient, JSONObject json) {
=======
     * @throws JiraException when the retrieval fails
     */
    public AgileResource(RestClient restclient, JSONObject json) throws JiraException {
>>>>>>> Stashed changes
        this.restclient = restclient;
        if (json != null) {
            deserialize(json);
        }
    }

    /**
     * Gets an Agile resource from the given object.
     *
     * @param type       Resource data type
     * @param r          a JSONObject instance
     * @param restclient REST client instance
     * @return a Resource instance or null if r isn't a JSONObject instance
<<<<<<< Updated upstream
     */
    private static <T extends AgileResource> T getResource(
=======
     * @throws JiraException when the retrieval fails
     */
    protected static <T extends AgileResource> T getResource(
>>>>>>> Stashed changes
            Class<T> type, Object r, RestClient restclient) throws JiraException {

        if (!(r instanceof JSONObject)) {
            throw new JiraException("JSON payload is malformed");
        }

        T result = null;

        if (!((JSONObject) r).isNullObject()) {
            try {
                Constructor<T> constructor = type.getDeclaredConstructor(RestClient.class, JSONObject.class);
                result = constructor.newInstance(restclient, r);
            } catch (Exception e) {
<<<<<<< Updated upstream
                throw new JiraException("Failed to deserialize object array.");
=======
                throw new JiraException("Failed to deserialize object.", e);
>>>>>>> Stashed changes
            }
        }

        return result;
    }

    /**
     * Gets a list of GreenHopper resources from the given object.
     *
     * @param type       Resource data type
     * @param ra         a JSONArray instance
     * @param restclient REST client instance
<<<<<<< Updated upstream
     * @return a list of Resources found in ra
     */
    private static <T extends AgileResource> List<T> getResourceArray(
            Class<T> type, Object ra, RestClient restclient) throws JiraException {
=======
     * @param listName   The name of the list of items from the JSON result.
     * @return a list of Resources found in ra
     * @throws JiraException when the retrieval fails
     */
    protected static <T extends AgileResource> List<T> getResourceArray(
            Class<T> type, Object ra, RestClient restclient, String listName) throws JiraException {
>>>>>>> Stashed changes
        if (!(ra instanceof JSONObject)) {
            throw new JiraException("JSON payload is malformed");
        }

        JSONObject jo = (JSONObject) ra;

<<<<<<< Updated upstream
        if (!jo.containsKey("values") || !(jo.get("values") instanceof JSONArray)) {
            throw new JiraException(type.getSimpleName() + " result is malformed");
=======
        if (!jo.containsKey(listName) || !(jo.get(listName) instanceof JSONArray)) {
            throw new JiraException("No array found for name '" + listName + "'");
>>>>>>> Stashed changes
        }

        List<T> results = new ArrayList<T>();

<<<<<<< Updated upstream
        for (Object v : (JSONArray) jo.get("values")) {
            T item = getResource(type, v, restclient);
            if (item != null)
                results.add(item);
=======
        for (Object v : (JSONArray) jo.get(listName)) {
            T item = getResource(type, v, restclient);
            if (item != null) {
                results.add(item);
            }
>>>>>>> Stashed changes
        }

        return results;
    }

    /**
     * Retrieves all boards visible to the session user.
     *
     * @param restclient REST client instance
<<<<<<< Updated upstream
     * @return a list of boards
     * @throws JiraException when the retrieval fails
     */
    static <T extends AgileResource> List<T> list(RestClient restclient, Class<T> type, String url) throws JiraException {
=======
     * @param type       The type of the object to deserialize.
     * @param url        The URL to call.
     * @return a list of boards
     * @throws JiraException when the retrieval fails
     */
    static <T extends AgileResource> List<T> list(
            RestClient restclient, Class<T> type, String url) throws JiraException {
        return list(restclient, type, url, "values");
    }

    /**
     * Retrieves all boards visible to the session user.
     *
     * @param restclient REST client instance
     * @param type       The type of the object to deserialize.
     * @param url        The URL to call.
     * @param listName   The name of the list of items in the JSON response.
     * @return a list of boards
     * @throws JiraException when the retrieval fails
     */
    static <T extends AgileResource> List<T> list(
            RestClient restclient, Class<T> type, String url, String listName) throws JiraException {
>>>>>>> Stashed changes

        JSON result;
        try {
            result = restclient.get(url);
        } catch (Exception ex) {
            throw new JiraException("Failed to retrieve a list of " + type.getSimpleName() + " : " + url, ex);
        }

        return getResourceArray(
                type,
                result,
<<<<<<< Updated upstream
                restclient
=======
                restclient,
                listName
>>>>>>> Stashed changes
        );
    }

    /**
     * Retrieves all boards visible to the session user.
     *
     * @param restclient REST client instance
     * @return a list of boards
     * @throws JiraException when the retrieval fails
     */
    static <T extends AgileResource> T get(RestClient restclient, Class<T> type, String url) throws JiraException {

        JSON result;
        try {
            result = restclient.get(url);
        } catch (Exception ex) {
            throw new JiraException("Failed to retrieve " + type.getSimpleName() + " : " + url, ex);
        }

        return getResource(
                type,
                result,
                restclient
        );
    }

    /**
<<<<<<< Updated upstream
=======
     * Extract from a sub list the Resource array, if present.
     *
     * @param type         Resource data type
     * @param subJson      a JSONObject instance
     * @param resourceName The name of the list of items from the JSON result.
     * @param <T>          The type of Agile resource to return.
     * @return The list of resources if present.
     * @throws JiraException when the retrieval fails
     */
    <T extends AgileResource> List<T> getSubResourceArray(
            Class<T> type, JSONObject subJson, String resourceName) throws JiraException {
        List<T> result = null;
        if (subJson.containsKey(resourceName)) {
            result = getResourceArray(type, subJson.get(resourceName), getRestclient(), resourceName + "s");
        }
        return result;
    }

    /**
     * Extract from a sub list the Resource, if present.
     *
     * @param type         Resource data type
     * @param subJson      a JSONObject instance
     * @param resourceName The name of the item from the JSON result.
     * @param <T>          The type of Agile resource to return.
     * @return The resource if present.
     * @throws JiraException when the retrieval fails
     */
    <T extends AgileResource> T getSubResource(
            Class<T> type, JSONObject subJson, String resourceName) throws JiraException {
        T result = null;
        if (subJson.containsKey(resourceName) && !subJson.get(resourceName).equals("null")) {
            result = getResource(type, subJson.get(resourceName), getRestclient());
        }
        return result;
    }

    /**
>>>>>>> Stashed changes
     * @return Internal JIRA ID.
     */
    public long getId() {
        return id;
    }

    /**
     * @return The resource name.
     */
    public String getName() {
        return name;
    }

    /**
<<<<<<< Updated upstream
=======
     * @param name Setter for the resource name. In some case, the name is called something else.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
>>>>>>> Stashed changes
     * @return The resource URL.
     */
    public String getSelfURL() {
        return self;
    }

    /**
     * @return The REST client used to access the current resource.
     */
    protected RestClient getRestclient() {
        return restclient;
    }

    /**
     * Retrieve the specified attribute as a generic object.
     *
     * @param name The name of the attribute to retrieve.
     * @return The value of the attribute.
     */
<<<<<<< Updated upstream
    String getAttribute(String name) {
        return (String) attributes.get(name);
    }

    /**
     * Retrieve the specified attribute as a generic object.
     *
     * @param name The name of the attribute to retrieve.
     * @return The value of the attribute.
     */
    int getAttributeAsInt(String name) {
        return NumberUtils.toInt(getAttribute(name), 0);
    }

    /**
     * Retrieve the specified attribute as a generic object.
     *
     * @param name The name of the attribute to retrieve.
     * @return The value of the attribute.
     */
    boolean getAttributeAsBoolean(String name) {
        return BooleanUtils.toBoolean(getAttribute(name));
=======
    public Object getAttribute(String name) {
        return attributes.get(name);
>>>>>>> Stashed changes
    }

    /**
     * Deserialize the json to extract standard attributes and keep a reference of
     * other attributes.
     *
     * @param json The JSON object to read.
     */
<<<<<<< Updated upstream
    protected void deserialize(JSONObject json) {
=======
    void deserialize(JSONObject json) throws JiraException {
>>>>>>> Stashed changes

        id = getLong(json.get("id"));
        name = Field.getString(json.get("name"));
        self = Field.getString(json.get("self"));
<<<<<<< Updated upstream
=======
        addAttributes(json);
    }

    /**
     * Allow to add more attributes.
     *
     * @param json The json object to extract attributes from.
     */
    void addAttributes(JSONObject json) {
>>>>>>> Stashed changes
        attributes.putAll(json);
    }

    long getLong(Object o) {
        if (o instanceof Integer || o instanceof Long) {
<<<<<<< Updated upstream
            return Field.getInteger(o);
=======
            return Field.getLong(o);
>>>>>>> Stashed changes
        } else if (o instanceof String && NumberUtils.isDigits((String) o)) {
            return NumberUtils.toLong((String) o, 0L);
        } else {
            return 0L;
        }
    }

    @Override
    public String toString() {
        return String.format("%s{id=%s, name='%s'}", getClass().getSimpleName(), id, name);
    }
}

