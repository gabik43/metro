package njackson.com.android;

import com.gabik.metro.model.IdCommunication;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by GaBiK on 23.02.2016.
 */
public class UnitTestIdCommunication {
    @Test
    public void TestEquals(){
        IdCommunication idCommunicationOne = new IdCommunication(2,5);
        IdCommunication idCommunicationTwo = new IdCommunication(5,2);
        IdCommunication idCommunicationThre = new IdCommunication(6,2);
        assertTrue(idCommunicationOne.equals(idCommunicationTwo));
        assertTrue(idCommunicationOne.equals(idCommunicationOne));
        assertTrue(idCommunicationTwo.equals(idCommunicationTwo));

        assertFalse(idCommunicationTwo.equals(idCommunicationThre));
    }

    @Test
    public void TestHash(){
        HashSet<IdCommunication> idCommunicationHashSet = new HashSet<IdCommunication>();
        idCommunicationHashSet.add(new IdCommunication(12,89));
        assertTrue(idCommunicationHashSet.contains(new IdCommunication(89,12)));
        assertTrue(idCommunicationHashSet.contains(new IdCommunication(12,89)));
        assertFalse(idCommunicationHashSet.contains(new IdCommunication(1,89)));
    }
}
