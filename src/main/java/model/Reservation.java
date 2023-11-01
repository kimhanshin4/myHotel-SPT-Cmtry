package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation{
    private final int roomIndex;
    private final LocalDate reservationDate;     //예약 날짜
    private final String reservationNumber;
    private final String customerName;
    private final String customerPhoneNumber;

    // 생성자
    public Reservation(String name, String phoneNumber, int roomIndex, LocalDate reservationDate, String reservationNumber) {
        this.customerName = name;
        this.customerPhoneNumber = phoneNumber;
        this.roomIndex = roomIndex;
        this.reservationDate = reservationDate;
        this.reservationNumber = reservationNumber;
    }

    // Getter, Setter 메서드
    public int getRoomIndex() {
        return roomIndex;
    }
    public LocalDate getReservationDate() {
        return reservationDate;
    }
    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    // 서비스 메서드
    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
        return  " 예약번호: " + reservationNumber + "\n" +
                " 이름: " + customerName + "\n" +
                " 핸드폰번호: " + customerPhoneNumber + "\n" +
                " 방 번호: " + (roomIndex+1) + "\n" +
                " 예약날짜: "  + reservationDate.format(dateTimeFormatter) + "\n";
    }
}
