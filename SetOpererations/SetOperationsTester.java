import java.util.ArrayList;

public class SetOperationsTester {
    public static void main(String[] args) {

        ArrayList<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        ArrayList<String> weekdays = new ArrayList<>();
        weekdays.add("Monday");
        weekdays.add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");

        ArrayList<String> weekend = new ArrayList<>();
        weekend.add("Saturday");
        weekend.add("Sunday");

        ArrayList<String> unionResult = SetOperations.union(weekdays, weekend);
        System.out.println("Union of weekdays and weekend: " + unionResult);

        ArrayList<String> intersectionResult = SetOperations.intersection(weekdays, weekend);
        System.out.println("Intersection of weekdays and weekend: " + intersectionResult);

        ArrayList<String> complementResult = SetOperations.complement(daysOfWeek, weekdays);
        System.out.println("Complement of weekdays considering full week: " + complementResult);
    }
}