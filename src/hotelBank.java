import java.sql.*;

public class hotelBank {
   private String nationalcode;
   private double bankblance;
   private double resturaunt;
   private double train;
   private double rooms;
   private int bank_id;
   static int id=1;

   public hotelBank(String nationalcode, double bankblance, double resturaunt, double train, double rooms) {
      this.bank_id=id;
      this.nationalcode = nationalcode;
      this.bankblance= bankblance;
      this.resturaunt = resturaunt;
      this.train = train;
      this.rooms = rooms;
      id++;
   }

   public String getNationalcode() {
      return nationalcode;
   }

   public double getResturaunt() {
      return resturaunt;
   }

   public double getTrain() {
      return train;
   }

   public double getRooms() {
      return rooms;
   }

   public double getBankblance() {
      return bankblance;
   }

   public void setTrain(double train) {
      this.train = train;
   }

   public void setRooms(double rooms) {
      this.rooms = rooms;
   }

   public void setBank_id(int bank_id) {
      this.bank_id = bank_id;
   }

   public void setResturaunt(double resturaunt) {
      this.resturaunt = resturaunt;
   }

   public void setBankblance(double bankblance) {
      this.bankblance = bankblance;
   }
}
