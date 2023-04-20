package usermanagement;

import java.time.LocalDate;

public class MyItemList {

    private int id;
    private String itemName;
    private LocalDate itemDate;
    private int iduser;

    public MyItemList(String itemName, LocalDate itemDate, int iduser) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.iduser = iduser;
    }

    public MyItemList() {
    }

    @Override
    public String toString() {
        return "MyItemList{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDate=" + itemDate +
                ", iduser=" + iduser +
                '}';
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
