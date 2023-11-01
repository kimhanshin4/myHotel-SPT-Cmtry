package context;

import java.util.ArrayList;

import Model.Reservation;
import Model.Room;

public class HotelContext {
    ArrayList<Room> roomList;
    double revenue;
    ArrayList<Reservation> reservationList = new ArrayList<>();
    private int reservationNumber;	// 예약 번호

    public HotelContext() {
        roomList = new ArrayList<>();
        revenue = 0.0;
    }
    public void cancelRes(String resNum, CustomerContext customerContext) {
        boolean canceled = false;
        for (Reservation res : reservationList) {
            if (res.getReservationNumber().equals(resNum)) {
                Room room = roomList.get(res.getRoomIndex());
                this.revenue -= room.getRoomFee();
                System.out.println(room.getRoomType() + "의 가격 : " + room.getRoomFee() + "원이 환불 되었습니다.");
                room.cancelOccupancy(res.getReservationDate());
                customerContext.refundMoney(room.getRoomFee());
                canceled = true;
                reservationList.remove(res);
                System.out.println("취소 완료 되었습니다.");
                break;
            }
        }
        if (!canceled) {
            System.out.println("일치하는 예약 번호가 없습니다.");
        }
    }
    public void initializeRooms() {
        Room singleRoom = new Room("싱글룸", 100.0, 16);
        Room doubleRoom = new Room("더블룸", 150.0, 24);
        Room twinRoom = new Room("트윈룸", 200.0, 16);
        Room suiteRoom = new Room("스위트룸", 250.0, 34);

        roomList.add(singleRoom);
        roomList.add(doubleRoom);
        roomList.add(twinRoom);
        roomList.add(suiteRoom);
    }
    public void displayRooms() {
        System.out.println("[ 객실 목록 ]");
        System.out.println();
        for (int i = 0; i < roomList.size(); i++) {
            Room room = roomList.get(i);
            System.out.println("[ 객실 번호: " + (i + 1) + " ]");
            room.displayRoomInfo(); // 객실 정보 출력
        }
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public double getRevenue() {
        return revenue;
    }

    public void addRevenue(double income) {
        this.revenue += income;
    }

    /**
     * 신규 예약코드 조회
     * @return 신규 예약코드
     */
    public String generateReservationCode() {
        reservationNumber++;
        return "MHR" + reservationNumber;
    }
}
