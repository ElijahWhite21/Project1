public class Student {

    int birthday;

    // Assigns a random birthday from 1 to 365
    public Student() {
        birthday = (int) (Math.random() * 365) + 1;
    }

    // Getter method for birthday
    public int getBirthday() {
        return birthday;
    }
}
