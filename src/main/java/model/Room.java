package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String roomType;
    private final double roomFee;
    private final int roomSize;
    private List<LocalDate> occupiedDateList;

    // 생성자
    public Room(String roomType, double roomFee, int roomSize) {
        this.roomType = roomType;
        this.roomFee = roomFee;
        this.roomSize = roomSize;
        this.occupiedDateList = new ArrayList<>();
    }

    // Getter, Setter 메서드
    public String getRoomType() {
        return roomType;
    }
    public double getRoomFee() {
        return roomFee;
    }

    // 서비스 메서드
    public boolean isOccupied(LocalDate localDate) {
        return this.occupiedDateList.contains(localDate);
    }

    public void cancelOccupancy(LocalDate date) {
        this.occupiedDateList.remove(date);
    }
    public void accupy(LocalDate date) {
        this.occupiedDateList.add(date);
    }

    public void displayRoomInfo() {
        System.out.println("예약 불가능한 날짜: " + occupiedDateList);
        System.out.println("객실 타입: " + roomType);
        System.out.println("객실 요금: " + roomFee + " 원");
        System.out.println("객실 크기: " + roomSize + " m^2");

        System.out.println();
    }
}



