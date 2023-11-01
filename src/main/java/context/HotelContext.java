package context;

import java.util.ArrayList;

import model.Reservation;
import model.Room;

public class HotelContext {
    ArrayList<Room> roomList; // 객실 목록
    double revenue; // 매출
    ArrayList<Reservation> reservationList = new ArrayList<>(); // 예약 목록
    private int reservationNumber;	// 예약 번호

    // 생성자
    public HotelContext() {
        roomList = new ArrayList<>();
        revenue = 0.0;
    }

    // Getter,Setter 메서드

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public double getRevenue() {
        return revenue;
    }

    // 서비스 메서드
    public void cancelRes(String resNum, CustomerContext customerContext) {
        boolean canceled = false;
        for (Reservation res : reservationList) {
            if (res.getReservationNumber().equals(resNum)) {
                // 취소할 방 조회
                Room room = roomList.get(res.getRoomIndex());

                // 호텔 매출에서 차감
                this.revenue -= room.getRoomFee();

                System.out.println(room.getRoomType() + "의 가격 : " + room.getRoomFee() + "원이 환불 되었습니다.");

                // 취소한 방 예약날짜 목록에서 제거
                room.cancelOccupancy(res.getReservationDate());

                // 고객 소지금으로 환불
                customerContext.refundMoney(room.getRoomFee());

                canceled = true;

                // 호텔 예약 목록에서 제거
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
