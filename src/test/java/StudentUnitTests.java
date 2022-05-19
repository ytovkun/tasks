import logic.CoursesStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import studentInfo.Student;
import taskItems.Course;
import taskItems.TrainingCenterTimetable;

import java.time.LocalDateTime;

import static logic.CoursesStatus.getDiffWorkTimeInHours;
import static logic.CoursesStatus.timeProgramPassed;

public class StudentUnitTests {

    private CoursesStatus coursesStatus;
    private LocalDateTime todayTime = LocalDateTime.now();
    private LocalDateTime todayPlusTenDaysTime = LocalDateTime.now().plusDays(10);
    private static final int expectedWorkingHoursDiff = 32;
    Course[] courseList = new Course[] {
            new Course("Test Design", 16),
            new Course("Page Object", 16) };

    TrainingCenterTimetable j2EEDeveloper =
            new TrainingCenterTimetable ("J2EE Developer", courseList[0], courseList[1]);

    Student student =
            new Student("Ivanov Ivan", j2EEDeveloper, todayTime);

    @Before
    public void setup() {
        coursesStatus = new CoursesStatus();
    }

    @Test
    public void timeProgramPassedTest() {
        Assert.assertFalse("Program is passed", timeProgramPassed(student, todayTime));
        Assert.assertTrue("Program isn't passed", timeProgramPassed(student, todayPlusTenDaysTime));
    }

    @Test
    public void workingHoursBetweenTwoDateTest() {
        Assert.assertEquals("Working hours are not correct",
                expectedWorkingHoursDiff, getDiffWorkTimeInHours(student, todayTime));
    }
}