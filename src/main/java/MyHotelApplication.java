import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import Model.Reservation;
import Model.Room;
import context.CustomerContext;
import context.HotelContext;

public class MyHotelApplication {
    private static HotelContext hotelContext;
    private static CustomerContext customerContext;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        hotelContext = new HotelContext();
        hotelContext.initializeRooms(); // Model.Room 정보 초기화
        displayMainMenu();
    }

    private static void displayMainMenu() {
        System.out.println("[ 나의 호텔에 오신 것을 환영합니다 :) ] ");
        System.out.println("1. 객실 조회");
        System.out.println("2. 객실 예약");
        System.out.println("3. 회원 등록");

        if (customerContext != null) {
            System.out.println(customerContext.getName() + "님의 소지금: " + customerContext.getMoney() + " 원");
        }

        try {
            handleMainMenuInput();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.\n다시 입력해주세요.");
            displayManagerMenu();
        }
    }

    private static void handleMainMenuInput() {

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        switch (input) {
            case 0 -> displayManagerMenu(); // 매니져 모드
            case 1 -> hotelContext.displayRooms(); // 객실 조회
            case 2 -> requestReservationMenu();  // 객실 예약
            case 3 -> requestAddCustomer();  //고객 정보 등록
            default -> System.out.println("잘못된 입력입니다.\n다시 입력해주세요.");
        }

        sleep();
        displayMainMenu();
    }

    private static void sleep() {
        System.out.println("(3초후 돌아갑니다.)");
        try {
            Thread.sleep(3000); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void displayManagerMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("[ 운영자 메뉴 ] ");
        System.out.println("1. 예약 목록 조회");
        System.out.println("2. 돌아가기");
        int input = sc.nextInt();
        switch (input) {
            case 1 -> {
                // 예약 목록 조회 기능 구현
                printAllReservation();
                // revenue 메서드 만들기
                System.out.println("호텔의 총 수익: " + hotelContext.getRevenue() + " 원");
            }
            case 2 -> {} // 돌아가기
            default -> {
                System.out.println("잘못된 입력입니다. 다시 입력하세요");
                displayManagerMenu();
            }
        }
    }
    private static void printAllReservation(){
        // 모든 예약된 정보 출력하기!
        if(hotelContext.getReservationList().isEmpty()) {
            System.out.println("예약된 정보가 없습니다");
        }
        else {
            for(Reservation reservation : hotelContext.getReservationList() ) {
                System.out.println(reservation);
            }
        }
    }
    private static void displayReservationMenu() {
        System.out.println("[ 객실 예약 메뉴 ] ");
        System.out.println("1. 객실 예약");
        System.out.println("2. 예약 목록");
        System.out.println("3. 예약 확인");
        System.out.println("4. 예약 취소");
        System.out.println("5. 돌아가기");

        reservationMenuInputHandle();
    }
    private static void requestAddCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine();
        System.out.print("전화번호를 입력하세요: ");
        String phoneNumber = sc.nextLine();
        System.out.print("소지금을 입력하세요: (원)");
        double money = sc.nextDouble();
        customerContext = new CustomerContext(name, phoneNumber, money);
        System.out.println("고객 정보가 입력되었습니다.");
    }
    private static void requestReservationMenu() {
        if (customerContext == null) {
            System.out.println("고객 정보가 없습니다. 등록 후 이용부탁드립니다.\n");
        } else {
            displayReservationMenu();
        }
    }
    private static void checkReservation() {
        // 해당 손님의 정보 1건만 출력하기
        Scanner checkScanner = new Scanner(System.in);
        boolean checked = false;

        System.out.println("[ 예약 확인 ] ");
        System.out.print("예약번호를 입력해 주세요\n예약번호: ");
        String reservationInput = checkScanner.nextLine();
        for(Reservation printReservation : hotelContext.getReservationList() ) {
            if(printReservation.getReservationNumber().equals(reservationInput)) {
                System.out.println(printReservation);
                checked = true;
                break;
            }
        }

        if(!checked) {
            System.out.println("일치하는 예약 번호가 없습니다.");
        }
    }

    private static void printReservationList() {
        System.out.println("[ 예약 목록 ] ");
        for(Reservation printReservation : hotelContext.getReservationList() ) {
            if(printReservation.getCustomerName().equals(customerContext.getName())) {
                System.out.println(printReservation);
            }
        }
    }

    private static void reservationMenuInputHandle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("번호를 선택해주세요 : ");
        int input = sc.nextInt();
        switch (input) {
            case 1 -> roomReservation();  // 객실 예약 기능
            case 2 -> printReservationList(); // 예약 목록 조회 기능
            case 3 -> checkReservation(); // 예약 확인 기능
            case 4 -> cancelReservation();// 예약 취소 기능
            case 5 -> {} // 상위 메뉴로 돌아가기
            default -> {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
                reservationMenuInputHandle();
            }
        }
    }

    private static void cancelReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 예약 취소 ] ");
        System.out.print("예약코드를 입력해주세요 : ");
        String reservationCode = sc.next();
        hotelContext.cancelRes(reservationCode, customerContext);
    }

    private static void roomReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 객실 예약 ] ");
        hotelContext.displayRooms();
        System.out.print("객실의 번호를 입력해주세요: ");
        int input = sc.nextInt();
        System.out.print("예약할 날짜를 입력해주세요 (ex. 2023-11-1) : ");
        String dateInput = sc.next();
        LocalDate date = LocalDate.parse(dateInput, DATE_TIME_FORMATTER);

        // 호텔의 객실 목록을 가져옴
        ArrayList<Room> roomList = hotelContext.getRoomList();

        // 입력된 객실 타입에 해당하는 객실을 찾음
        int roomIndex = input - 1;
        Room selectedRoom = null;
        try {
            selectedRoom = roomList.get(roomIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요");
            roomReservation();
        }

        // 해당 객실이 예약 가능한지 확인하고 예약 처리
        if (selectedRoom != null && !selectedRoom.isOccupied(date)) {
            System.out.println(input + "번 방을 예약합니다.");
            LocalDate reservationDate = LocalDate.now();

            // 예약한 객실의 가격과 고객의 소지금 비교
            double roomFee = selectedRoom.getRoomFee();
            double customerMoney = customerContext.getMoney();

            if (customerMoney < roomFee) {
                System.out.println("고객의 소지금이 부족하여 예약할 수 없습니다.");
                System.out.println("고객의 소지금: " + customerMoney + " 원");
                System.out.println("객실의 가격: " + roomFee + " 원");
            } else {
                // 예약번호 생성
                String reservationCode = hotelContext.generateReservationCode();
                // 예약 객체 생성
                Reservation reservation = new Reservation(customerContext.getName(), customerContext.getPhoneNumber(), roomIndex, reservationDate,reservationCode);

                // 예약 정보를 예약 목록에 추가
                hotelContext.getReservationList().add(reservation);

                // 매출 업데이트
                hotelContext.addRevenue(roomFee);

                // 예약 상태 변경
                selectedRoom.accupy(reservationDate);

                // 고객의 남은 소지금 계산
                double remainingMoney = customerMoney - roomFee;
                customerContext.setMoney(remainingMoney);

                System.out.println("객실이 예약되었습니다.");
                System.out.println("예약 일자 :" +reservationDate.format(DATE_TIME_FORMATTER)+ " 입니다.");
                System.out.println("예약 코드는 : " +reservationCode+ " 입니다.");
                System.out.println("고객의 남은 소지금: " + remainingMoney + " 원");
            }
        } else {
            System.out.println("해당 객실은 예약할 수 없습니다.");
        }
    }
}
