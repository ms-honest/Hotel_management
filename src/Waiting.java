public class Waiting {
    private int staytime;
    private int bednumber;
    private int roomnumber;
    private double pay;
    private String national;
    private int waiting_id;
    static int id=1;

    public Waiting(String national,int staytime, int bednumber, int roomnumber,double pay) {
        waiting_id=id;
        this.national= national;
        this.staytime = staytime;
        this.bednumber = bednumber;
        this.roomnumber = roomnumber;
        this.pay = pay;
        id++;
    }

    public int getStaytime() {
        return staytime;
    }

    public int getBednumber() {
        return bednumber;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public double getPay() {
        return pay;
    }

    public String getNational() {
        return national;
    }

    public int getWaiting_id() {
        return waiting_id;
    }

    public void setStaytime(int staytime) {
        this.staytime = staytime;
    }

    public void setBednumber(int bednumber) {
        this.bednumber = bednumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
