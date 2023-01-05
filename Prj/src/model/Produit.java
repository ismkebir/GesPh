package model;

/**
 *
 * @author X
 */
public class Produit {
    private int code;
    private String drugname;
    private int qty;
    private int price;

    public Produit() {
    }

    
    public Produit(int code, String drugname, int qty, int price) {
        this.code = code;
        this.drugname = drugname;
        this.qty = qty;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Produit{" + "code=" + code + ", drugname=" + drugname + ", qty=" + qty + ", price=" + price + '}';
    }

    
    
    
    
}
