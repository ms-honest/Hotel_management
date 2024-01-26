public class Room {
    private double price;
    private int bed_num;
    private int isave;
    private int room_num;
    static int num=1;

    public Room(double price,int bed_num, int isave) {
        this.room_num = num;
        this.price = price;
        this.bed_num = bed_num;
        this.isave = isave;
        num++;
    }

    public double getPrice() {
        return price;
    }

    public int getBed_num() {
        return bed_num;
    }

    public int getIsave() {
        return isave;
    }

    public void setIsave(int isave) {
        this.isave = isave;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBed_num(int bed_num) {
        this.bed_num = bed_num;
    }


}
