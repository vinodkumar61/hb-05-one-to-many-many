package com.geeks.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.geeks.hibernate.demo.entity.Course;
import com.geeks.hibernate.demo.entity.Instructor;
import com.geeks.hibernate.demo.entity.InstructorDetail;
import com.geeks.hibernate.demo.entity.Review;
import com.geeks.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		// create session factory
		 SessionFactory factory= new Configuration()
				 .configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Instructor.class)
				 .addAnnotatedClass(InstructorDetail.class)
				 .addAnnotatedClass(Course.class)
				 .addAnnotatedClass(Review.class)
				 .addAnnotatedClass(Student.class)
				 .buildSessionFactory();
		// create session 
		Session session = factory.getCurrentSession();
		try {
	        						
			// start a transaction
			session.beginTransaction();
			
			// create a course 
			  
			Course tempCourse = new Course("JavaEE");
			
			// save the course 
			System.out.println("\nSaving the Course...");
			 session.save(tempCourse);
			 System.out.println("Saved the course "+ tempCourse);
			 
			 // create the students
			 Student tempStudent1 = new Student("Johan","Deo","johan@360factors.com");
			 Student tempStudent2 = new Student("Mary","public","mary@360factors.com");
			 // add students to the course 
			 tempCourse.addStudent(tempStudent1);
			 tempCourse.addStudent(tempStudent2);
			 // save the students
			 
				System.out.println("\nSaving the Students...");
				 session.save(tempStudent1);
				 session.save(tempStudent2);
				 System.out.println("Saved Stdeunts:  "+ tempCourse.getStudents());
			 
			
			// commit transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
			 
		} finally {
			
			// add clean up code 
			session.close();
			factory.close();
		}

	}

}
