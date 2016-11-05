/**
 * This class contains the information for every in state graduate engineering student
 * @author Aaron Ambrose
 */
public class InStateGE extends GraduateStudentE {
    private final double fullLoadDiscount = 1 - 0.35;
    private final double overFullLoadDiscount = 1 - 0.10;
    private final String residence = "In State";

    public InStateGE(String firstName, String lastName, String studentID, int hours) {
        super(firstName, lastName, studentID, hours);
    }

    /**
     * Calculates the tuition for every student of this type
     * @return total tuition
     */
    public double calculateTuition(){
        double overFullLoadTuition = getHoursOverFullLoad()* getBaseTuition() * getOverFullLoadDiscount();
        double underFullLoadTuition = getHours() - getHoursOverFullLoad();
        underFullLoadTuition = underFullLoadTuition * getBaseTuition() * getFullLoadDiscount();
        return overFullLoadTuition + underFullLoadTuition + calculateFeesAndServices();
    }

    /**
     * Calculates the tuition after changing the base tuition for every student of this type
     * @param baseTuitionRate new tuition rate
     * @return total tuition
     */
    public double calculateNewTuition(int baseTuitionRate){
        double overFullLoadTuition = getHoursOverFullLoad()* baseTuitionRate * getOverFullLoadDiscount();
        double underFullLoadTuition = getHours() - getHoursOverFullLoad();
        underFullLoadTuition = underFullLoadTuition * baseTuitionRate * getFullLoadDiscount();
        return overFullLoadTuition + underFullLoadTuition + calculateFeesAndServices();
    }

    public double getFullLoadDiscount() {
        return fullLoadDiscount;
    }

    public double getOverFullLoadDiscount() {
        return overFullLoadDiscount;
    }

    public String getResidence() {
        return residence;
    }
}
