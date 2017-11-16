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

public class TestInstructor {
	private IInstructor instructor;
	private IAdmin admin;
	private IStudent student;
	
	@Before
    public void setup() {
    	this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
    }
	@Test
	public void testInvalidClass() {
		//Class does not exist
		instructor.addHomework("Instructor", "Test", 2017, "HW");
		assertFalse(instructor.homeworkExists("Test", 2017, "HW"));
	}
	@Test
	public void testAddHomeworkSameInstructor() {
		this.admin.createClass("Test", 2017, "Instructor" , 20);
		instructor.addHomework("Instructor", "Test", 2017, "HW");
		assertTrue(instructor.homeworkExists("Test", 2017, "HW"));
	}
	
	@Test
	public void testAddHomeworkDiffrentInstructor() {
		this.admin.createClass("Test", 2017, "Instructor" , 20);
		instructor.addHomework("Instructor2", "Test", 2017, "HW");
		assertFalse(instructor.homeworkExists("Test", 2017, "HW"));
	}
	@Test
	//instructor is assigned
	//homework is assigned
	//student submitted the homework
	public void test_gradeTrue() {
		this.admin.createClass("Class", 2017, "Instructor", 20);
        this.instructor.addHomework("Instructor", "Class", 2017, "hw");
        this.student.registerForClass("Student", "Class", 2017);
        this.student.submitHomework("Student", "hw", "solution", "Class", 2017);
        this.instructor.assignGrade("Instructor", "Class", 2017, "hw", "Student", 100);
        assertTrue(this.instructor.getGrade("Class", 2017, "hw", "Student") == 100);
        
	}
}