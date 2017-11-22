package bwdm;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;

public class JUnit5UserGuide {
    
    @BeforeAll
    static void initAll() {
        
    }
    
    @BeforeEach
    void init() {
        
    }
    
    @Test
    @Disabled("SucceedingTest")
    void succeedingTest() {
        
    }
    
    //@Test
    //void failingTest() {
    //    fail("failing test");
    //}
    
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        //not executed
    }
    
    @AfterEach
    void tearDown() {
        
    }
    
    @AfterAll 
    static void tearDownAll() {
        
    }
    
    
    
}
