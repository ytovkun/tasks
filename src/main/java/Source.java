import studentInfo.Student;
import taskItems.Course;
import taskItems.TrainingCenterTimetable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Source {

    public static Student[] initialize(LocalDateTime localDateTime) {

        LocalDateTime studentTimeOne = localDateTime.minusDays(3).truncatedTo(ChronoUnit.HOURS);
        LocalDateTime studentTimeTwo = localDateTime.plusDays(10).truncatedTo(ChronoUnit.HOURS);

        Course[] courseList = new Course[] { new Course("Java", 16), new Course("JDBC", 24),
                new Course("Spring", 16), new Course("Test Design", 10), new Course("Page Object", 16),
                new Course("Selenium", 16)};

        TrainingCenterTimetable javaDeveloper =
                new TrainingCenterTimetable ("Java Developer", courseList[0], courseList[1], courseList[2]);
        TrainingCenterTimetable aqe =
                new TrainingCenterTimetable ("AQE", courseList[3], courseList[4], courseList[5]);

        return new Student[] {
                new Student("Ivanov Ivan", javaDeveloper, studentTimeOne),
                new Student("Sidorov Ivan", aqe, studentTimeTwo)};
    }
}