/**
 * This class contains the information for every engineering student
 * @author Aaron Ambrose
 */
public class EngineeringStudent extends StudentTuition {
    private final int feesAndServices = 200;
    private final int minHoursWithoutFee = 6;
    private final String collegeEnrolled = "Engineering";

    public EngineeringStudent(String firstName, String lastName, String studentID, int hours) {
        super(firstName, lastName, studentID, hours);
    }

    /**
     * Calculates the fees and services that engineers encounter
     * @return total fees
     */
    public int calculateFeesAndServices() {
        int fees = 0;
        if(getHours()<=getMinHoursWithoutFee()){ return fees; }
        else{ return getFeesAndServices(); }
    }

    public int getFeesAndServices() {
        return feesAndServices;
    }

    public int getMinHoursWithoutFee() {
        return minHoursWithoutFee;
    }

    public String getCollegeEnrolled() {
        return collegeEnrolled;
    }
}
