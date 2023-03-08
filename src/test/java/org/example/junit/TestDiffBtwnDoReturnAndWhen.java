package org.example.junit;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class TestDiffBtwnDoReturnAndWhen {

    @Test
    public void testDoReturnWithMockito() {


        MyClass myClass = PowerMockito.spy(new MyClass());
        // would work fine
        doReturn("test").when(myClass).anotherMethodInClass();

       // would throw a NullPointerException
        when(myClass.anotherMethodInClass()).thenReturn("test");
    }

    @Test
    public void testDoReturnWithPowerMockito() {


        MyClass myClass = PowerMockito.spy(new MyClass());
        // would work fine
        PowerMockito.doReturn("test").when(myClass).anotherMethodInClass();

        // would throw a NullPointerException
        PowerMockito.when(myClass.anotherMethodInClass()).thenReturn("test");
    }
}
