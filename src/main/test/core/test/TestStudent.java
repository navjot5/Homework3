
package core.test;
//Navjot Singh 11/14/2017

import core.api.IAdmin;
import core.api.IInstructor;
import core.api.IStudent;

import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestStudent {
	private IInstructor instructor;
	private IAdmin admin;
	private IStudent student;
	
	@Before
    public void setup() {
		this.admin = new Admin();
		this.instructor = new Instructor();
        this.student = new Student();
    } 
	// class being registered exists. 
	@Test
	public void testRegisterClassExists() {
		this.admin.createClass("Test", 2017, "Instructor", 10);
		this.student.registerForClass("Student", "Test", 2017);
		assertTrue(this.student.isRegisteredFor("Student", "Test", 2017));
	}

	
	@Test
	// class does not exist
	public void classNotExist() {
        this.student.registerForClass("Student", "Class", 2017);
        assertFalse(this.student.isRegisteredFor("Student", "Class", 2017));
    }
	
	@Test
	// student can drop class 
	public void testDropClass() {
		this.admin.createClass("Class", 2017, "Instructor", 20);
		this.student.registerForClass("Student", "Class", 2017);
		this.student.dropClass("Student", "Class", 2017);
		assertFalse(this.student.isRegisteredFor("Student", "Class", 2017));
	}
	
	@Test
	//hw exists
	public void testSubmitHomeworkExists() {
		this.admin.createClass("Test", 2017, "Instructor", 20);
		this.student.registerForClass("Student", "Test" , 2017);
		this.instructor.addHomework("Instructor", "Test", 2017, "HW");
		this.student.submitHomework("Student", "HW", "string", "Test", 2017);
		assertTrue( this.student.hasSubmitted("Student", "HW", "Test", 2017) );
	}
	
	@Test
	//hw DNE
	public void testSubmitHomeworkDoesNotExist() {
		this.admin.createClass("Test", 2017, "Instructor", 20);
		this.student.registerForClass("Student", "Test" , 2017);
		this.student.submitHomework("Student", "HW", "string", "Test", 2017);
		assertFalse( this.student.hasSubmitted("Student", "HW", "Test", 2017) );
	}
}
	
