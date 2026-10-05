public class Objednavka {
    private String name;
    private String email;
    private String produkt;
    private double price;
    private int quantity;

    public Objednavka(String name, String email, String produkt, double price, int quantity) {
        this.name = name;
        this.email = email;
        this.produkt = produkt;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProdukt() {
        return produkt;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Objednavka{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", produkt='" + produkt + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
