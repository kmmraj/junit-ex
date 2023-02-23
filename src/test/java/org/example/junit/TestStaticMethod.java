package org.example.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Properties;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;

@PrepareForTest({IdGenerator.class, ServiceRegistrar.class, Configuration.class, SomeClass.class})
@RunWith(PowerMockRunner.class)
public class TestStaticMethod {

    @Test
    public void test_MockStaticMethod_OK() {
        // Given
        ServiceRegistrar serviceRegistrar = new ServiceRegistrar();
        PowerMockito.mockStatic(IdGenerator.class);
        PowerMockito.when(IdGenerator.generateNewId()).thenReturn(42L);

        // When
        long id = serviceRegistrar.registerService(new Object());

        // Then
        Assert.assertEquals("Not equal to 42L", 42L, id);
    }


    @Test
    public void test_doCallRealMethod_Example_OK() throws Exception {
        // Given
        mockStatic(Configuration.class);

        Whitebox.setInternalState(Configuration.class, "enabled", (Object) null);

        Properties properties = new Properties();
        properties.put("enabled", "true");

        doReturn(properties).when(Configuration.class, "readProperties"); // simulate reading properties from file
        doCallRealMethod().when(Configuration.class, "isEnabled");
        doCallRealMethod().when(Configuration.class, "loadFromProperties");

        // When
        int result = SomeClass.add(1, 5);

        // Then
        Assert.assertEquals("Not equal to 6", 6, result);
    }

    @Test
    public void test_callingStaticMethodFromInstanceContext_Example_OK() throws Exception {
        // Given
        mockStatic(Configuration.class);

        Whitebox.setInternalState(Configuration.class, "enabled", (Object) null);

        Properties properties = new Properties();
        properties.put("enabled", "true");

        doReturn(properties).when(Configuration.class, "readProperties");
        doCallRealMethod().when(Configuration.class, "isEnabled");
        doCallRealMethod().when(Configuration.class, "loadFromProperties");

        // When
        int result = new Configuration().add(1, 5); // Note: calling static method from instance context

        // Then
        Assert.assertEquals("Not equal to 6", 6, result);
    }

    @Test
    public void test_callingStaticMethodFromInstanceContextFromTheSameClass_Example_OK() throws Exception {
        // Given
        mockStatic(Configuration.class);

        Whitebox.setInternalState(Configuration.class, "enabled", (Object) null);

        Properties properties = new Properties();
        properties.put("enabled", "true");

        doReturn(properties).when(Configuration.class, "readProperties");
        doCallRealMethod().when(Configuration.class, "isEnabled");
        doCallRealMethod().when(Configuration.class, "loadFromProperties");

        SomeClass someClassMock = PowerMockito.mock(SomeClass.class); //#A -- Asked to mock the class and return the mocked instance
        PowerMockito.mockStatic(SomeClass.class); //#B

        PowerMockito.doCallRealMethod().when(someClassMock).add(2, 5, 1); //#A -- Real Method
        PowerMockito.doReturn(10).when(SomeClass.class, "add", 2, 5); //#B -- Stubbing

        // When
        int result = new SomeClass().add(2, 5, 1); // Note: calling static method from instance context

        // Then
        Assert.assertEquals("Not equal to 11", 11, result);
    }


}
