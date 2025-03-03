public class Student {

    int birthday; // Student's birthday

    // Assigns a random birthday from 1 to 365
    public Student() {
        birthday = (int) (Math.random() * 365) + 1; // Assign random birthday from 1 to 365
    }

    // Getter method for birthday
    public int getBirthday() {
        return birthday; // Return birthday
    }
}
