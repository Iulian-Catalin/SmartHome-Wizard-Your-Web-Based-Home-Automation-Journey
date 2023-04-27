package itemmanagement;

import java.sql.Timestamp;

public class MyItemList {

    private int id;

    private int idDB;

    private String itemName;
    private Timestamp itemTimeStamp;
    private int iduser;

    private int room;

    private String roomName;

    private int watts;

    private boolean power;

    private int quantity;

    private float consumption;

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

    public MyItemList(String itemName, int iduser, String roomName, int watts, int quantity) {
        this.itemName = itemName;
        this.iduser = iduser;
        this.roomName = roomName;
        this.watts = watts;
        this.quantity = quantity;
    }

    public MyItemList(int idDB, int quantity) {
        this.idDB = idDB;
        this.quantity = quantity;
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
                ", itemTimeStamp=" + itemTimeStamp +
                ", iduser=" + iduser +
                ", room=" + room +
                ", roomName='" + roomName + '\'' +
                ", watts=" + watts +
                ", power=" + power +
                ", quantity=" + quantity +
                ", consumption=" + consumption +
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

    public Timestamp getItemTimeStamp() {
        return itemTimeStamp;
    }

    public void setItemTimeStamp(Timestamp itemTimeStamp) {
        this.itemTimeStamp = itemTimeStamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }
}
