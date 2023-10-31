package Model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import context.CustomerContext;

public class Reservation{
    private final int roomIndex;
    private final LocalDateTime reservationDate;     //예약 날짜
    private final String reservationNumber;
    private final CustomerContext customerContext;

    public Reservation(String name, String phoneNumber, int roomIndex, LocalDateTime reservationDate, String reservationNumber) {
        this.customerContext = new CustomerContext(name,phoneNumber);
        this.roomIndex = roomIndex;
        this.reservationDate = reservationDate;
        this.reservationNumber = reservationNumber;
    }
    public int getRoomIndex() {
        return roomIndex;
    }
    public LocalDateTime getReservationDate() {
        return reservationDate;
    }
    public String getReservationNumber() {
        return reservationNumber;
    }

    public String getCustomerName() {
        return customerContext.getName();
    }

    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
        return  " 예약번호: " + reservationNumber + "\n" +
                " 이름: " + customerContext.getName() + "\n" +
                " 핸드폰번호: " + customerContext.getPhoneNumber() + "\n" +
                " 방 번호: " + (roomIndex+1) + "\n" +
                " 예약날짜: "  + reservationDate.format(dateTimeFormatter);
    }
}
