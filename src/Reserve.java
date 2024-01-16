import java.time.LocalDate;

public class Reserve {
    private Passenger passenger;
    private Room room;
    Employee employee;
    private LocalDate date;
    private String room_price;
    private int length_reserve;
    private int room_id;
    static int id=1;

    public Reserve(Passenger passenger, Room room, Employee employee, LocalDate date, String room_price, int length_reserve, int room_id) {
        this.passenger = passenger;
        this.room = room;
        this.employee = employee;
        this.date = date;
        this.room_price = room_price;
        this.length_reserve = length_reserve;
        this.room_id = room_id;
    }
}
