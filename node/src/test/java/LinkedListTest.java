import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList linkedList;

    @Before
    public void setUp(){
        linkedList = new LinkedList();
    }

    @Test
    public void detectLoopListIsLooped(){
        // given
        Node first = linkedList.push(1);
        linkedList.push(2);
        Node three = linkedList.push(3);
        linkedList.push(4);
        first.setNext(three);

        // when
        boolean isLooped = linkedList.detectLoop();

        // then
        assertTrue(isLooped);
    }

    @Test
    public void detectOddLoopListIsNotLooped(){
        // given
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        linkedList.push(4);
        linkedList.push(5);

        // when
        boolean isLooped = linkedList.detectLoop();

        // then
        assertEquals(5, linkedList.getHead().getData());
        assertFalse(isLooped);
    }

    @Test
    public void detectEvenLoopListIsNotLooped(){
        // given
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        linkedList.push(4);

        // when
        boolean isLooped = linkedList.detectLoop();

        // then
        assertEquals(4, linkedList.getHead().getData());
        assertFalse(isLooped);
    }
}
