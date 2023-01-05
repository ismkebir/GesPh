
package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.DbConnection;
import model.Produit;

public class DaoProduit implements Dao<Produit>{
     private Connection connection;

    public DaoProduit() {
        try {
            connection= DbConnection.getInstance().getConnection();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
     

    @Override
    public Produit get(int id) {
        Produit produit=null;
        String requette="SELECT * FROM produit WHERE code ="+id;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(requette);
            while (resultSet.next()){
                int code = resultSet.getInt(1);
                String drugname = resultSet.getString(2);
                int qty = resultSet.getInt(3);
                int price = resultSet.getInt(4);
                produit = new Produit(code, drugname, qty, price);
                //System.out.println(produit);
            }
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("ph");
        }
        return produit;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String querry = "select * from produit";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()){
                Produit produit = new Produit();
                produit.setCode(resultSet.getInt(1));
                produit.setDrugname(resultSet.getString(2));
                produit.setQty(resultSet.getInt(3));
                produit.setPrice(resultSet.getInt(4));
                produits.add(produit);
                //System.out.println(produit+"\n");
            }
        }catch (Exception e){
            System.out.println("oh");
            e.getStackTrace();
            
        }
        return produits;
    }

    @Override
    public void save(Produit t) {
        String querry="INSERT INTO Produit (code,nomM,qty,prix) VALUES ("+t.getCode() +",'"+t.getDrugname()+"',"+t.getQty()+","+t.getPrice()+")";
        try{
            
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(querry);
            //System.out.println("ok");
        }catch (Exception ex){
            System.out.println("OUPS");
            //System.out.println(ex.getMessage());
            ex.getStackTrace();
        }
    }

    @Override
    public void update(Produit t, int code) {
        String querry="UPDATE Produit set nomM= '"+t.getDrugname()+"'"+ ", qty = "+t.getQty()+", prix = "+t.getPrice()+" where code ="+code;
        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(querry);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public void delete(int code) {
        String querry="Delete from Produit where code= "+code;
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(querry);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    public static void main(String[] args) {
        DaoProduit daoProduit=new DaoProduit();
            Produit p = new Produit(200, "Paracetemol", 400, 100);
            daoProduit.getAll();
    }

    
    
}
