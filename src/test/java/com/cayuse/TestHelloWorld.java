package com.cayuse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;


public class TestHelloWorld {

    private HelloWorld h;

    @Before
    public void setUp() throws Exception
    {
        h = new HelloWorld();
    }

    @Test
    public void testHelloEmpty()
    {
        assertEquals(h.getName(),"");
        assertEquals(h.getMessage(),"Hello!");
    }

    @Test
    public void testHelloWorld()
    {
        h.setName("World");
        assertEquals(h.getName(),"World");
        assertEquals(h.getMessage(),"Hello World!");
    }

    @Test
    public void testConcatAndConvertString() throws Exception {
        String expectedValue="HELLOWORLD";
        //App app=new App();
        String actualValue=h.concatAndConvertString("Hello", "World");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void test1()  {
        //  create mock
        HelloWorld helloWorldMock = mock(HelloWorld.class);

        // define return value for method getUniqueId()
        when(helloWorldMock.getUniqueId()).thenReturn(43);

        // use mock in test....
        assertEquals(helloWorldMock.getUniqueId(), 43);
    }
}
