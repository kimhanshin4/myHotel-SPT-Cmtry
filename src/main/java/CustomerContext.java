public class CustomerContext {
    private final String name;
    private final String phoneNumber;
    private double money;

    public CustomerContext(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = 0.0; // 초기자금 0으로 설정
    }
    public CustomerContext(String name, String phoneNumber, double money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }
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
    public void refundMoney(double amount) {
        money += amount;
    }
}
