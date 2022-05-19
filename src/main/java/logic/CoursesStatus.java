package logic;

import studentInfo.Student;
import taskItems.TrainingCenterTimetable;

import java.time.*;

public class CoursesStatus {

    /** int Array contain holiday day in 2022 year */
    private static final int[] HOLIDAY = { 1, 3, 7, 67, 114, 121, 129, 163, 179, 235, 287, 359 };
    /** int Array contain holiday day in 2022 year */
    private static final int[] DAYOFF = { 105 };

    public static LocalDateTime getStartDate(LocalDateTime startDate) {

        LocalDateTime tmpDate = startDate;
        int currentHour = tmpDate.getHour();

        while ((currentHour < TrainingCenterTimetable.STARTING_AT) ||
                (currentHour >= TrainingCenterTimetable.ENDING_AT) ||
                (CoursesStatus.isHoliday(tmpDate))) {

            tmpDate = tmpDate.plusHours(1);
            currentHour = tmpDate.getHour();
        }
        return tmpDate;
    }

    /**
     * Procedure calculate end time for current student.
     *
     * @param startDate
     *            - when student start to learn curriculum
     * @param timetable
     *            - curriculum of student
     * @return time when student finished timetable
     */
    public static LocalDateTime getEndDate(LocalDateTime startDate, TrainingCenterTimetable timetable) {

        LocalDateTime tmpDate = startDate;
        int amountWorkHours = timetable.getSumHours();
        int currentHour = 0;

        while (!(amountWorkHours == 0)) {

            tmpDate = tmpDate.plusHours(1);
            currentHour = tmpDate.getHour();

            if ((currentHour > TrainingCenterTimetable.STARTING_AT)
                    && (currentHour <= TrainingCenterTimetable.ENDING_AT)) {
                amountWorkHours--;
            }
        }
        return tmpDate;
    }

    /**
     * Procedure return true if current time more than end time of learning.
     *
     * @param student
     * @param currentTime
     * @return return true if current time more than end time of learning
     */
    public static boolean timeProgramPassed(Student student, LocalDateTime currentTime) {

        boolean timeProgramPassed = false;

        long endDateStudentMS = student.getEndDate().toInstant(ZoneOffset.UTC).toEpochMilli();
        long currentDateMS = currentTime.toInstant(ZoneOffset.UTC).toEpochMilli();

        if (currentDateMS > endDateStudentMS) {
            timeProgramPassed = true;
        }
        return timeProgramPassed;
    }

    /**
     * Procedure return count of work hours between two date - end date and
     * current date.
     *
     * @param student
     * @param currentTime
     * @return return count of work hours between two date
     */
    public static int getDiffWorkTimeInHours(Student student, LocalDateTime currentTime) {

        LocalDateTime tmpDate1;
        LocalDateTime tmpDate2;

        if (timeProgramPassed(student, currentTime)) {
            tmpDate1 = student.getEndDate();
            tmpDate2 = currentTime;
        } else {
            tmpDate1 = currentTime;
            tmpDate2 = student.getEndDate();
        }

        int workHoursBetween = 0;
        int currentHour = 0;

        while (tmpDate1.isBefore(tmpDate2)) {

            tmpDate1 = tmpDate1.plusHours(1);
            currentHour = tmpDate1.getHour();

            if ((currentHour > TrainingCenterTimetable.STARTING_AT)
                    && (currentHour <= TrainingCenterTimetable.ENDING_AT)) {
                workHoursBetween++;
            }
        }
        return workHoursBetween;
    }

    private static boolean isHoliday(LocalDateTime day) {

        for (int i = 0; i < CoursesStatus.HOLIDAY.length; i++) {
            if (day.getDayOfYear() == HOLIDAY[i]) {
                return true;
            }
        }

        for (int i = 0; i < CoursesStatus.DAYOFF.length; i++) {
            if (day.getDayOfYear() == DAYOFF[i]) {
                return false;
            }
        }

        if (day.getDayOfWeek() == DayOfWeek.SATURDAY || (day.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            return true;
        }
        return false;
    }
}