package Model;

public class Room {
    private final String roomType;
    private final double roomFee;
    private final int roomSize;
    boolean available;

    public Room(String roomType, double roomFee, int roomSize, boolean available) {
        this.roomType = roomType;
        this.roomFee = roomFee;
        this.roomSize = roomSize;
        this.available = available;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getRoomFee() {
        return roomFee;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setReservationStatus() {
        this.available = !available;
    }

    public void displayRoomInfo() {
        System.out.println((available ? "   -예약 가능-" : "***예약 불가능***"));
        System.out.println("객실 타입: " + roomType);
        System.out.println("객실 요금: " + roomFee + " 원");
        System.out.println("객실 크기: " + roomSize + " m^2");

        System.out.println();
    }
}



