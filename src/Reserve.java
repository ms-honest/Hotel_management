import java.time.LocalDate;

public class Reserve {
    private String passengercode;
    private double pay;
    private String employeecode;
    private LocalDate date;
    private int length_reserve;
    private int room_id;

    public Reserve(String passengercode, double pay, String employeecode,  int length_reserve, int room_id) {
        this.passengercode = passengercode;
        this.pay = pay;
        this.employeecode = employeecode;
        this.date = LocalDate.now();
        this.length_reserve = length_reserve;
        this.room_id = room_id;
    }

    public String getPassengercode() {
        return passengercode;
    }

    public double getPay() {
        return pay;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLength_reserve() {
        return length_reserve;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setPassengercode(String passengercode) {
        this.passengercode = passengercode;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLength_reserve(int length_reserve) {
        this.length_reserve = length_reserve;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }
}
