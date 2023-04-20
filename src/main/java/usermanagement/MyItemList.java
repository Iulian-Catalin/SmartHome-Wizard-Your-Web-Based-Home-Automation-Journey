package usermanagement;

import java.time.LocalDate;

public class MyItemList {

    private int id;
    private String itemName;
    private LocalDate itemDate;
    private int iduser;

    private int room;

    private int watts;

    private boolean on;

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

    public MyItemList(String itemName, LocalDate itemDate, int iduser, int room, int watts, boolean on) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.iduser = iduser;
        this.room = room;
        this.watts = watts;
        this.on = on;
    }

    @Override
    public String toString() {
        return "MyItemList{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDate=" + itemDate +
                ", iduser=" + iduser +
                ", room=" + room +
                ", watts=" + watts +
                ", on=" + on +
                '}';
    }

    public MyItemList() {
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
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
