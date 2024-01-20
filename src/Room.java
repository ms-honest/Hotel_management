public class Room {
    private double price;
    private String bed_type;
    private int bed_num;
    private boolean isVip;
    private int room_num;
    static int num=1;

    public Room(double price, String bed_type, int bed_num, boolean isVip) {
        this.room_num = num;
        this.price = price;
        this.bed_type = bed_type;
        this.bed_num = bed_num;
        this.isVip = isVip;
        num++;
    }
}
