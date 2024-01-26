import java.time.LocalDate;

public class decline {
    private int declineid;
    private String passengercode;
    private String employeecode;
    private LocalDate date;
    static int id=1;

    public decline(String passengercode, String employeecode) {
        this.declineid = id;
        this.passengercode = passengercode;
        this.employeecode = employeecode;
        date=LocalDate.now();
        id++;
    }

    public int getDeclineid() {
        return declineid;
    }

    public String getPassengercode() {
        return passengercode;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public LocalDate getDate() {
        return date;
    }
}
