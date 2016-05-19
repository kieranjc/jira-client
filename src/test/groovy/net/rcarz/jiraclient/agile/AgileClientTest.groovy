package net.rcarz.jiraclient.agile

import net.sf.json.JSONSerializer
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Test

import static org.junit.Assert.assertThat
import static org.mockito.Mockito.when

/**
 * Created by pldupont on 2016-05-19.
 */
class AgileClientTest extends AbstractResourceTest {

    @Test
    void "Given an agileClient, when calling getBoards(), then receive a list of Board."() {
        "given an Agile Client"()
        when(mockRestClient.get(AgileResource.RESOURCE_URI + "board"))
                .thenReturn(JSONSerializer.toJSON(JSONResources.LIST_OF_BOARDS))

        List<Board> boards = agileClient.getBoards();

        assertThat boards, new IsNot<>(new IsNull())
        assertThat boards.size(), new IsEqual<Integer>(2)
    }

    @Test
    void "Given an agileClient, when calling getBoard(84), then receive one Board."() {
        "given an Agile Client"()
        when(mockRestClient.get(AgileResource.RESOURCE_URI + "board/84"))
                .thenReturn(JSONSerializer.toJSON(JSONResources.BOARD_84))

        Board board = agileClient.getBoard(84);

        assertThat board, new IsNot<>(new IsNull())
    }
}