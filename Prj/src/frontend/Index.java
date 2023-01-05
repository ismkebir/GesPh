
package frontend;

import Dao.DaoProduit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author X
 */
public class Index extends javax.swing.JFrame implements ActionListener{
    public DefaultTableModel tbModel;
    
    /**
     * Creates new form Index
     */
    public Index() {
        initComponents();
        Connect();  
        AjouterButton.addActionListener(this);
        AfficherToutButton.addActionListener(this);
        SupprimerButton.addActionListener(this);
        ModifierButton.addActionListener(this);
        ChercherButton.addActionListener(this);
        tbModel = (DefaultTableModel) tableProduit.getModel();
        afficherTout();
        
    }
    Connection conn;
    PreparedStatement pst,pst1;
    ResultSet rs;
    DefaultTableModel df;
    
    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacie","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    public void sales(){
        String totalcost = txttcost.getText();
        String pay = txtpay.getText();
        String bal = txtbal.getText();
        
        int lastid = 0;
        
        
        try {
            String query = "insert into ventes(subtotal, pay, bal) values(?,?,?)";
            pst = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, totalcost);
            pst.setString(2, pay);
            pst.setString(3, bal);
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                lastid = rs.getInt(1);
            }
            int rows = jTable1.getRowCount();
            String query1 = "insert into ventes_produits(ventes_id, nomM, prix,qty,total) values(?,?,?,?,?)";
            pst1 = conn.prepareStatement(query1);
            
            String drugname = "";
            String price;
            String qty;
            int total = 0;
            
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                drugname = (String)jTable1.getValueAt(i, 0);
                price = (String) jTable1.getValueAt(i, 1);
                qty = (String) jTable1.getValueAt(i, 2);
                total = (int)jTable1.getValueAt(i, 3);
                
                
                pst1.setInt(1, lastid);
                pst1.setString(2, drugname);
                pst1.setString(3, price);
                pst1.setString(4, qty);
                pst1.setInt(5, total);
                pst1.executeUpdate();
               
            }
            JOptionPane.showMessageDialog(this, "La vente est réalisée");
            
            HashMap a = new HashMap();
            a.put("invo", lastid);
            
            try {
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\X\\Documents\\Nouveau dossier\\JavaApplication\\src\\frontend\\report1.jrxml");
                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                
                
                JasperPrint jprint = JasperFillManager.fillReport(jreport, a, conn);
                JasperViewer.viewReport(jprint);
            
            
            
            
            
            } catch (JRException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
    }
    
    
    
    public void afficherTout(){
        int tbModelLength = tbModel.getRowCount();
        for(int i=0;i<tbModelLength;i++){
            tbModel.removeRow(0);
        }
        //List<Etudiant> etudiants= EtudiantDao().getAll();
        List<Produit> l = new DaoProduit().getAll();
        System.out.println(l.size());
        for(int i=0;i<l.size();i++){
            Produit e = l.get(i);
            String code = String.valueOf(e.getCode());
            String nomM =String.valueOf(e.getDrugname());
            String qty =  String.valueOf(e.getQty());
            String price = String.valueOf(e.getPrice());
            String tbData[]= {code,nomM,qty,price};
           tbModel.addRow(tbData);
        }  
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        VenteButton = new javax.swing.JButton();
        ProductionButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Principal = new javax.swing.JTabbedPane();
        prdP = new javax.swing.JPanel();
        jPanelPrd = new javax.swing.JPanel();
        ChercherButton = new javax.swing.JButton();
        ModifierButton = new javax.swing.JButton();
        SupprimerButton = new javax.swing.JButton();
        AfficherToutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduit = new javax.swing.JTable();
        AjouterButton = new javax.swing.JButton();
        codeF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nomMF = new javax.swing.JTextField();
        qtyF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        priceF = new javax.swing.JTextField();
        venteP = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtdcode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtdname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtqty = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        txttcost = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtpay = new javax.swing.JTextField();
        txtbal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VenteButton.setBackground(new java.awt.Color(0, 0, 204));
        VenteButton.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        VenteButton.setForeground(new java.awt.Color(255, 255, 255));
        VenteButton.setText("VENTE");
        VenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VenteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(VenteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 240, 60));

        ProductionButton1.setBackground(new java.awt.Color(0, 0, 204));
        ProductionButton1.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        ProductionButton1.setForeground(new java.awt.Color(255, 255, 255));
        ProductionButton1.setText("PRODUCTION");
        ProductionButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductionButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(ProductionButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 240, 60));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 240, 600));

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 70)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pharmacie");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\X\\Desktop\\p.png")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(257, 257, 257)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 160));

        jPanelPrd.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChercherButton.setBackground(new java.awt.Color(204, 0, 51));
        ChercherButton.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        ChercherButton.setForeground(new java.awt.Color(255, 255, 255));
        ChercherButton.setText("Chercher");
        jPanelPrd.add(ChercherButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 160, -1));

        ModifierButton.setBackground(new java.awt.Color(204, 0, 51));
        ModifierButton.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        ModifierButton.setForeground(new java.awt.Color(255, 255, 255));
        ModifierButton.setText("Modifier");
        jPanelPrd.add(ModifierButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 160, -1));

        SupprimerButton.setBackground(new java.awt.Color(204, 0, 51));
        SupprimerButton.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        SupprimerButton.setForeground(new java.awt.Color(255, 255, 255));
        SupprimerButton.setText("Supprimer");
        jPanelPrd.add(SupprimerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 160, -1));

        AfficherToutButton.setBackground(new java.awt.Color(204, 0, 51));
        AfficherToutButton.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        AfficherToutButton.setForeground(new java.awt.Color(255, 255, 255));
        AfficherToutButton.setText("Tout");
        AfficherToutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfficherToutButtonActionPerformed(evt);
            }
        });
        jPanelPrd.add(AfficherToutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 160, 30));

        tableProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Nom médicament", "Quantité", "Prix"
            }
        ));
        tableProduit.setGridColor(new java.awt.Color(255, 255, 255));
        tableProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProduitMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduit);

        jPanelPrd.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 970, 370));

        AjouterButton.setBackground(new java.awt.Color(204, 0, 51));
        AjouterButton.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        AjouterButton.setForeground(new java.awt.Color(255, 255, 255));
        AjouterButton.setText("Ajouter");
        AjouterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterButtonActionPerformed(evt);
            }
        });
        jPanelPrd.add(AjouterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 160, 30));

        codeF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeFActionPerformed(evt);
            }
        });
        jPanelPrd.add(codeF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 204, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Code");
        jPanelPrd.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 180, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Nom du médicament ");
        jPanelPrd.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 30));

        nomMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomMFActionPerformed(evt);
            }
        });
        jPanelPrd.add(nomMF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 204, 30));

        qtyF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyFActionPerformed(evt);
            }
        });
        jPanelPrd.add(qtyF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 204, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Quantité");
        jPanelPrd.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 180, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Prix");
        jPanelPrd.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 180, 30));

        priceF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFActionPerformed(evt);
            }
        });
        jPanelPrd.add(priceF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 204, 30));

        javax.swing.GroupLayout prdPLayout = new javax.swing.GroupLayout(prdP);
        prdP.setLayout(prdPLayout);
        prdPLayout.setHorizontalGroup(
            prdPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        prdPLayout.setVerticalGroup(
            prdPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Principal.addTab("tab1", prdP);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Code Médicament");

        txtdcode.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtdcode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdcodeMouseClicked(evt);
            }
        });
        txtdcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdcodeActionPerformed(evt);
            }
        });
        txtdcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdcodeKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Nom Médicament");

        txtdname.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtdname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdnameActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Prix");

        txtprice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Quantité");

        txtqty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtqty.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txttcost.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txttcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttcostActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Imprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtpay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayActionPerformed(evt);
            }
        });

        txtbal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtbal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbalActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom Médicament", "Prix", "Quantité", "Total"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(204, 204, 255));
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTable1);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Prix Total");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Montant ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Balance ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdname, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtdcode, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtpay)
                            .addComponent(txtbal)
                            .addComponent(txttcost, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtdcode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdname, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txttcost, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtbal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ventePLayout = new javax.swing.GroupLayout(venteP);
        venteP.setLayout(ventePLayout);
        ventePLayout.setHorizontalGroup(
            ventePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventePLayout.setVerticalGroup(
            ventePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Principal.addTab("tab2", venteP);

        jPanel3.add(Principal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 121, 970, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1228, 763));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void codeFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeFActionPerformed

    private void AfficherToutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfficherToutButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AfficherToutButtonActionPerformed

    private void qtyFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyFActionPerformed

    private void priceFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceFActionPerformed

    private void AjouterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AjouterButtonActionPerformed

    private void nomMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomMFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomMFActionPerformed

    private void tableProduitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProduitMouseClicked
        String code = tableProduit.getValueAt(tableProduit.getSelectedRow(),0).toString();
        String nomM = tableProduit.getValueAt(tableProduit.getSelectedRow(),1).toString();
        String qty = tableProduit.getValueAt(tableProduit.getSelectedRow(),2).toString();
        String prix = tableProduit.getValueAt(tableProduit.getSelectedRow(),3).toString();
        
        codeF.setText(code);
        nomMF.setText(nomM);
        qtyF.setText(qty);
        priceF.setText(prix);
        
    }//GEN-LAST:event_tableProduitMouseClicked

    private void VenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VenteButtonActionPerformed
        Principal.setSelectedIndex(1);
    }//GEN-LAST:event_VenteButtonActionPerformed

    private void ProductionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductionButton1ActionPerformed
        Principal.setSelectedIndex(0);
    }//GEN-LAST:event_ProductionButton1ActionPerformed

    private void txtdnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdnameActionPerformed

    private void txtdcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdcodeKeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                String dcode = txtdcode.getText();
                
                
                pst = conn.prepareStatement("SELECT * FROM produit where code=?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                if(rs.next() == false){
                    JOptionPane.showMessageDialog(this, "Le code est invalide !");
                    
                }else{
                    String dname = rs.getString("nomM");
                    txtdname.setText(dname.trim());
                    
                    String price = rs.getString("prix");
                    txtprice.setText(price.trim());
                    
                    txtqty.requestFocus();
                    
                    
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        
        
    }//GEN-LAST:event_txtdcodeKeyPressed

    private void txtdcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdcodeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        int price,qty,total;
        price = Integer.parseInt(txtprice.getText());
        qty = Integer.parseInt(txtqty.getValue().toString());
        total = price * qty;
        
        df = (DefaultTableModel) jTable1.getModel();
        df.addRow(new Object[]{
            txtdname.getText(),
            txtprice.getText(),
            txtqty.getValue().toString(),
            total
        });
        int sum = 0;
        for(int i=0; i < jTable1.getRowCount();i++){
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 3).toString());
        }
        txttcost.setText(String.valueOf(sum));
        txtdcode.setText("");
        txtdname.setText("");
        txtqty.setValue(0);
        txtprice.setText("");
        
        txtdcode.requestFocus();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txttcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttcostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttcostActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int totalcost = Integer.parseInt(txttcost.getText());
        int pay = Integer.parseInt(txtpay.getText());
        int tot = pay - totalcost;
        
        txtbal.setText(String.valueOf(tot));
        sales();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayActionPerformed

    private void txtbalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbalActionPerformed

    private void txtdcodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdcodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdcodeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AfficherToutButton;
    private javax.swing.JButton AjouterButton;
    private javax.swing.JButton ChercherButton;
    private javax.swing.JButton ModifierButton;
    private javax.swing.JTabbedPane Principal;
    private javax.swing.JButton ProductionButton1;
    private javax.swing.JButton SupprimerButton;
    private javax.swing.JButton VenteButton;
    private javax.swing.JTextField codeF;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelPrd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nomMF;
    private javax.swing.JPanel prdP;
    private javax.swing.JTextField priceF;
    private javax.swing.JTextField qtyF;
    private javax.swing.JTable tableProduit;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtdcode;
    private javax.swing.JTextField txtdname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtprice;
    private javax.swing.JSpinner txtqty;
    private javax.swing.JTextField txttcost;
    private javax.swing.JPanel venteP;
    // End of variables declaration//GEN-END:variables
    
    public void viderChamps(){
        codeF.setText("");
        nomMF.setText("");
        qtyF.setText("");
        priceF.setText("");
    }
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==AjouterButton){
            int code = Integer.parseInt(codeF.getText());
            String nomM = nomMF.getText();
            int qty = Integer.parseInt(qtyF.getText());
            int price = Integer.parseInt(priceF.getText());
            Produit produit = new Produit(code,nomM, qty, price);
            DaoProduit daoProduit = new DaoProduit();
            daoProduit.save(produit);
            afficherTout();
            viderChamps();
        }
        if(e.getSource()==AfficherToutButton){
            afficherTout();
        }
        
        if(e.getSource()==SupprimerButton){
            if(!codeF.equals("")){
                new DaoProduit().delete(Integer.parseInt(codeF.getText()));
                afficherTout();
                viderChamps();
            }
        }
        if(e.getSource()==ModifierButton){
            int code = Integer.parseInt(codeF.getText());
            String nom = nomMF.getText();
            int qty = Integer.parseInt(qtyF.getText());
            int price = Integer.parseInt(priceF.getText());
            Produit p = new Produit(code,nom, qty, price);
            new DaoProduit().update(p, code);
            afficherTout();
            viderChamps();
        }
        
        if(e.getSource()==ChercherButton){
            Produit p = new DaoProduit().get(Integer.parseInt(codeF.getText()));
            String code = String.valueOf(p.getCode());
            String nomM = p.getDrugname();
            String qty = String.valueOf(p.getQty());
            String price = String.valueOf(p.getPrice());
            
            String tab[]= {code,nomM, qty, price};
            int tbModelLength = tbModel.getRowCount();
            for(int i=0;i<tbModelLength;i++){
                tbModel.removeRow(0);
            }
            tbModel.addRow(tab);  
        
        }




    }



}
