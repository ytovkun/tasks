package report;

import logic.CoursesStatus;
import studentInfo.Student;
import taskItems.TrainingCenterTimetable;

import java.time.LocalDateTime;

public class ReportShort implements ITestReport {

    @Override
    public void printReport(Student[] students, LocalDateTime currentTime) {

        for (Student currentStudent:students){

            System.out.print(currentStudent.getStudentFullName() + " - " +
                    currentStudent.getCourseTimetable().getNameTimetable() + ". ");

            if (CoursesStatus.timeProgramPassed(currentStudent, currentTime)) {
                System.out.print("Program complete: Passed time - ");
            }
            else {
                System.out.print("Program not complete. Time for complete - ");
            }

            int workhours = CoursesStatus.getDiffWorkTimeInHours(currentStudent, currentTime);

            int day=workhours/(TrainingCenterTimetable.ENDING_AT-TrainingCenterTimetable.STARTING_AT);
            int hours=workhours%(TrainingCenterTimetable.ENDING_AT-TrainingCenterTimetable.STARTING_AT);

            System.out.println(day+ " day " + hours + " hours");
        }
    }
}