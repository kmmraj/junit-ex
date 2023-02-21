package org.example.junit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class TestSpyMock {

    @Test
    public void test_verify_Mock_behaviour_OK() {
        // Given
        List<String> list = new ArrayList<>();
        List<String> mockList = mock(List.class);

        // When

        // Then
        assertEquals(0, mockList.size());

        doReturn(100).when(mockList).size();
        Assert.assertEquals(100, mockList.size());
    }


    @Test
    public void test_verify_Spy_behaviour_KO() {
        // Given
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        List<String> mockList = mock(List.class);

        // When
        Mockito.doReturn(2).when(mockList).size();

        // Then
        assertEquals(2, mockList.size());
        // When
        doThrow(new IndexOutOfBoundsException()).when(mockList).get(1);
        // Then

        try {
            mockList.get(1);
            fail();
        } catch (IndexOutOfBoundsException exception) {
            Assert.assertEquals("java.lang.IndexOutOfBoundsException", exception.getClass().getTypeName());
        }
    }
}
