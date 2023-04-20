package usermanagement;

public class MyRoomList {

    private int id;
    private String roomName;

    private int iduser;

    public MyRoomList(String roomName, int iduser) {
        this.roomName = roomName;
        this.iduser = iduser;
    }

    public MyRoomList() {
    }

    @Override
    public String toString() {
        return "MyRoomList{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
