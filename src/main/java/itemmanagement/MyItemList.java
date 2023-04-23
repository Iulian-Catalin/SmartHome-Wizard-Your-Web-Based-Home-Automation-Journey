package itemmanagement;

import java.time.LocalDate;

public class MyItemList {

    private int id;

    private int idDB;

    private String itemName;
    private LocalDate itemDate;
    private int iduser;

    private int room;

    private int watts;

    private boolean power;

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getWatts() {
        return watts;
    }

    public void setWatts(int watts) {
        this.watts = watts;
    }

    public int getIdDB() {
        return idDB;
    }

    public MyItemList(int idDB) {
        this.idDB = idDB;
    }

    public void setIdDB(int idDB) {
        this.idDB = idDB;
    }

    public MyItemList(String itemName, LocalDate itemDate, int iduser, int room, int watts) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.iduser = iduser;
        this.room = room;
        this.watts = watts;
    }

    public MyItemList(int idDB, boolean power) {
        this.idDB = idDB;
        this.power = power;
    }

    @Override
    public String toString() {
        return "MyItemList{" +
                "id=" + id +
                ", idDB=" + idDB +
                ", itemName='" + itemName + '\'' +
                ", itemDate=" + itemDate +
                ", iduser=" + iduser +
                ", room=" + room +
                ", watts=" + watts +
                ", power=" + power +
                '}';
    }

    public MyItemList() {
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public int getIduser() {
        return iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }
}
