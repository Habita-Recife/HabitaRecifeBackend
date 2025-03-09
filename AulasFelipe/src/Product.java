public class Produto {

    public String name;
    public double price;
    public int quantity;

    public double totalValueInstock() {
        return price * quantity;
    }

    public void addproducts(int quantity){
        this.quantity += quantity;
    }
}
