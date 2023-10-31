public class Room {
    private final String roomType;
    private final double roomFee;
    private final int roomSize;
    boolean reservationStatus;

    public Room(String roomType, double roomFee, int roomSize, boolean reservationStatus) {
        this.roomType = roomType;
        this.roomFee = roomFee;
        this.roomSize = roomSize;
        this.reservationStatus = reservationStatus;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getRoomFee() {
        return roomFee;
    }
    public void setReservationStatus() {
        this.reservationStatus = !reservationStatus;
    }

    public void displayRoomInfo() {
        System.out.println((reservationStatus ? "   -예약 가능-" : "***예약 불가능***"));
        System.out.println("객실 타입: " + roomType);
        System.out.println("객실 요금: " + roomFee + " 원");
        System.out.println("객실 크기: " + roomSize + " m^2");

        System.out.println();
    }
}



