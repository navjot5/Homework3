package core.test;

import core.api.IAdmin;
import core.api.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {

    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    //Test class creating
    @Test
    public void testMakeClass1() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    //Test creating a class in the past
    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 20);
        assertFalse(this.admin.classExists("Test", 2016));
    }
    
    //Test creating a class in the future
    @Test
    public void testMakeClass3() {
        admin.createClass("Test", 2018, "Instructor", 20);
        assertTrue(admin.classExists("Test", 2018));
    }
    
    //Test creating with capacity of zero
    @Test
    public void testMakeClass4() {
        this.admin.createClass("Test", 2017, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2017));
    }
    
    //Test creating with negative capacity / class DNE
    @Test
    public void testMakeClass5() {
        this.admin.createClass("Test", 2017, "Instructor", -5);
        assertFalse(this.admin.classExists("Test", 2017));
    }
    @Test
    // empty string for class name
    public void testMakeClass6() {
        this.admin.createClass("", 2017, "Instructor", 20);
        assertFalse(this.admin.classExists("", 2017));
    }
    
    @Test
    // empty string for instructor
    public void testMakeClass7() {
        this.admin.createClass("Test", 2017, "", 20);
        assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    // unique pair of class and year
    public void testMakeClass8() {
        this.admin.createClass("Test", 2017, "Instructor1", 15);
        this.admin.createClass("Test", 2017, "Instructor2", 20);
        assertEquals("Instructor1", this.admin.getClassInstructor("Test", 2017));
    }
    @Test
    //instructor assigned to more than 2 courses
    public void testMakeClass9() {
        this.admin.createClass("Class1", 2017, "Instructor", 20);
        this.admin.createClass("Class2", 2017, "Instructor", 20);
        this.admin.createClass("Class3", 2017, "Instructor", 20);
        assertFalse(this.admin.classExists("Class3", 2017));
    }
      //Test changing capacity of a class
    @Test
    public void testChangeCapacity1() {
        admin.createClass("Test", 2017, "Instructor", 20);
        admin.changeCapacity("Test", 2017, 21);
        assertTrue(admin.getClassCapacity("Test", 2017) == 21);
    }
      //Test changing capacity to lower
    @Test
    public void testChangeCapacity2() {
        admin.createClass("Test", 2017, "Instructor", 20);
        admin.changeCapacity("Test", 2017, 19);
        assertTrue(admin.getClassCapacity("Test", 2017) == 19);
    }
     @Test
    // changing capacity greater than students enrolled, true
    public void testChangeCapacity3() {
            this.admin.createClass("Test", 2017, "Instructor", 15);
            this.admin.changeCapacity("Test", 2017, 16);
            assertEquals(16, this.admin.getClassCapacity("Test", 2017));
    }
     //Test changing capacity of a class that DNE does not create a class.
    @Test
    public void testChangeCapacity4() {
        admin.changeCapacity("Test", 2017, 15);
        assertFalse(admin.classExists("Test", 2017));
    }
}