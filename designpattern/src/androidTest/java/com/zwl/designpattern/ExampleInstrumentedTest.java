package com.zwl.designpattern;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.zwl.designpattern.clone.Professor;
import com.zwl.designpattern.clone.Student;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.zwl.designpattern.test", appContext.getPackageName());
    }

    @Test
    public void cloneTest() {

        Student student = new Student("zhang", 19);
        Student s = (Student) student.Clone();
        s.setAge(18);
        s.setName("weilong");
        assertEquals(student, s);

    }


    @Test
    public void deepCloneTest() {
        Professor professor = new Professor("professor1");
        Student student = new Student("name1", 18, professor);
        Student s = (Student) student.Clone();
        s.setAge(99);
        s.setProfessor(new Professor("jfeiji"));
        assertEquals(student, s);
    }

}
