public class Room {
    private double price;
    private String bed_type;
    private int bed_num;
    private boolean isVip;
    private int room_num;
    static int num=1;

    public Room(double price,int bed_num, boolean isVip) {
        this.room_num = num;
        this.price = price;
        this.bed_num = bed_num;
        this.isVip = isVip;
        num++;
    }

    public double getPrice() {
        return price;
    }

    public int getBed_num() {
        return bed_num;
    }


    public boolean isVip() {
        return isVip;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
