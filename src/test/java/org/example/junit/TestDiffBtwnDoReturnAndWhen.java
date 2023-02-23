package org.example.junit;

import org.junit.Test;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TestDiffBtwnDoReturnAndWhen {

    @Spy
    private MyClass myClass;
    @Test
    public void testDoReturnWithMockito() {


        // would work fine
        doReturn("test").when(myClass).anotherMethodInClass();

       // would throw a NullPointerException
        when(myClass.anotherMethodInClass()).thenReturn("test");
    }

    @Test
    public void testDoReturnWithPowerMockito() {


        // would work fine
        PowerMockito.doReturn("test").when(myClass).anotherMethodInClass();

        // would throw a NullPointerException
        PowerMockito.when(myClass.anotherMethodInClass()).thenReturn("test");
    }
}
