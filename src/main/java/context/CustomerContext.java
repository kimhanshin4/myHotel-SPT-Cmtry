package context;

public class CustomerContext {
    private final String name;
    private final String phoneNumber;
    private double money; // 소지금

    // 생성자
    public CustomerContext(String name, String phoneNumber, double money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    // Getter,Setter 메서드
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // 서비스 메서드
    public void refundMoney(double amount) {
        money += amount;
    }
}
