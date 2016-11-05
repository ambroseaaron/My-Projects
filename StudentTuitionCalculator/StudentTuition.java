/**
 * This class contains the information for every student
 * @author Aaron Ambrose
 */
public class StudentTuition extends Object{
    private final String firstName;
    private final String lastName;
    private final String studentID;
    private final int hours;
    private int baseTuition;

    public StudentTuition(String firstName, String lastName, String studentID, int hours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.hours = hours;
        this.baseTuition = setBaseTuition(350);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getHours() {
        return hours;
    }

    public int getBaseTuition() {
        return baseTuition;
    }

    /**
     * Sets the base tuition to a new value
     * @param baseTuition new tuition rate
     * @return new tuition
     */
    public int setBaseTuition(int baseTuition) {
        this.baseTuition = baseTuition;
        return baseTuition;
    }
}
