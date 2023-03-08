package org.example.junit;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertArrayEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCompareCollection {


    @Test
    public void compareList() {
        List<String> expected = Arrays.asList("String A", "String B");
        List<String> actual = Arrays.asList("String B", "String A");

        assertThat("List equality without order",
                actual, containsInAnyOrder(expected.toArray()));

//        assertArrayEquals("List equality without order",
//                actual.toArray(), expected.toArray());
    }

}
