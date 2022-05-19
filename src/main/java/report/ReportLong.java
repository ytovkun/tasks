package report;

import logic.CoursesStatus;
import studentInfo.Student;
import taskItems.Course;
import taskItems.TrainingCenterTimetable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
public class ReportLong implements ITestReport{

    private static int MIN_WIDE_REPORT = 32;
    private static int LENGTH_WORD_CURRENT_DATE = 15;
    private static int LENGTH_HOURS_COURSE = 4;
    private static int LENGTH_NUMBER_COURSE = 3;

    @Override
    public void printReport(Student[] students, LocalDateTime currentTime) {

        int wideReport = getWideReport(students);
        String line = getLine(wideReport);

        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);

        for (Student currentStudent : students) {

            int minWideSecondColum = wideReport - LENGTH_WORD_CURRENT_DATE;
            String lineFormater = "%-" + LENGTH_WORD_CURRENT_DATE + "s %" + minWideSecondColum + "s\n";

            fmt.format("\n");
            fmt.format(lineFormater, "Student", currentStudent.getStudentFullName());
            fmt.format(lineFormater, "Curriculum",
                    currentStudent.getCourseTimetable().getNameTimetable());
            fmt.format(lineFormater, "Current time", getDateToString(currentTime));
            fmt.format(lineFormater, "Start date", getDateToString(currentStudent.getStartDate()));
            fmt.format(lineFormater, "Finish date", getDateToString(currentStudent.getEndDate()));

            String tmpString = TrainingCenterTimetable.STARTING_AT + ":00 - " + TrainingCenterTimetable.ENDING_AT
                    + ":00";
            fmt.format(lineFormater, "Working time", tmpString);
            fmt.format(line + "\n");

            int i = 1;
            lineFormater = "%-" + (wideReport - LENGTH_HOURS_COURSE - LENGTH_NUMBER_COURSE) + "s %"
                    + LENGTH_HOURS_COURSE + "s\n";

            for (Course currentCourse : currentStudent.getCourseTimetable().getCoursesInTimetable()) {

                fmt.format(i + ". " + lineFormater, currentCourse.getCourseName(), currentCourse.getCourseDuration());
                i++;
            }
            fmt.format(line + "\n");
            fmt.format("TOTAL " + currentStudent.getCourseTimetable().getSumHours() + "h" + "\n");

            if (CoursesStatus.timeProgramPassed(currentStudent, currentTime)) {
                fmt.format("Course completed. Passed time - ");
            } else {
                fmt.format("Course not completed. Complete time - ");
            }

            int workhours = CoursesStatus.getDiffWorkTimeInHours(currentStudent, currentTime);

            int day = workhours / (TrainingCenterTimetable.ENDING_AT - TrainingCenterTimetable.STARTING_AT);
            int hours = workhours % (TrainingCenterTimetable.ENDING_AT - TrainingCenterTimetable.STARTING_AT);

            fmt.format(day + " day " + hours + " hours" + "\n\n");

            System.out.println(sb);

            sb.setLength(0);
        }
        fmt.close();
    }

    private static String getDateToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return time.format(formatter);
    }

    private static int getWideReport(Student[] students) {
        int wideReport = MIN_WIDE_REPORT;
        int tmpWideReport = 0;

        for (Student currentStudent : students) {

            int lengthNameStudent = currentStudent.getStudentFullName().length();
            int lengthCurriculumStudent = currentStudent.getCourseTimetable().getNameTimetable().length();

            tmpWideReport = Math.max(lengthNameStudent, lengthCurriculumStudent);

            if ((tmpWideReport + LENGTH_WORD_CURRENT_DATE) > wideReport) {
                wideReport = tmpWideReport + LENGTH_WORD_CURRENT_DATE;
            }
        }
        return wideReport;
    }

    private static String getLine(int wideReport) {

        StringBuilder line = new StringBuilder(wideReport);
        for (int i = 0; i <= wideReport; i++) {
            line.append("-");
        }
        return line.toString();
    }
}