package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.tablePackage.MainTable;
import sample.tablePackage.table;

import java.sql.*;
import java.sql.Connection;

public class Controller
{


    public Label MateriaID;
    public TextField Delivery_Amount_Truck;
    public TextField Delivery_Amount;
    public TextField Delivery_To;
    public TextField Delivery_Plate;
    public Button Delivery_add;
    public ComboBox comobox;

    public ComboBox NrContractBuy;
    public ComboBox NrContractSell;

    public TextField NrBuyContract;
    public TextField AmountContractBuy;
    public TextField TruckContractBuy;
    public ChoiceBox ChoiceContractBuy;
    public ChoiceBox choiceMaterialBuyContract;
    public Button addBuy;

    public ChoiceBox ChoiceContractSell;
    public TextField NrSellContract;
    public TextField AmountContractSell;
    public TextField TruckContractSell;
    public Button addSell;
    public ChoiceBox choiceMaterialSellContract;
    public ChoiceBox ChoiceContractBuyToSell;
    public DatePicker DataPicker;
    public Label datapickererror;



    @FXML
    private TableView<table> tables;
    @FXML
    private TableColumn<table, String> ColumnData;
    @FXML
    private TableColumn<table, String> ColumnMaterial;
    @FXML
    private TableColumn<table, String> ColumnPlate;
    @FXML
    private TableColumn<table, String> ColumnAmount;
    @FXML
    private TableColumn<table, String> ColumnFinal_Amount;
    @FXML
    private TableColumn<table, String> ColumnFroms;
    @FXML
    private TableColumn<table, String> ColumnTos;
    @FXML
    private TableColumn<table, String> ColumnTruck;
    @FXML
    private TableColumn<table, String> ColumnOrder;
    @FXML
    private TableColumn<table, String> ColumnVk;
    @FXML
    private TableColumn<table, String> ColumnEk;
    @FXML
    private TableColumn<table, String> ColumnDoc;

    @FXML
    private TableView<TableContractOpenBuy> TableContractOpenBuy;
    @FXML
    private TableColumn<table, String> ColumnCustomerName;
    private ObservableList<TableContractOpenBuy>data2;

    @FXML
    private TableView<TableContractOpenSell> TableContractOpenSell;
    @FXML
    private TableColumn<table, String> ColumnCustomerNameSell;
    private ObservableList<TableContractOpenSell>OpenSellData;



    private ObservableList<table>data;

    ObservableList BuyToSellList = FXCollections.observableArrayList();  ObservableList Combolist = FXCollections.observableArrayList();
    ObservableList Mchecklist = FXCollections.observableArrayList();
    ObservableList Cchecklist = FXCollections.observableArrayList(); ObservableList ContractSellList = FXCollections.observableArrayList();

    int Customerid;
    String MaterialName;


   /* public void datapickecheck()
    {
        Object  datapicker = DataPicker.getValue();
        String datapickerString = String.valueOf(datapicker);
        System.out.println("ilość znaków" + datapickerString.length() + "jakie znaki"+datapickerString);
        datapickererror.setText("źle");
        if (((datapickerString.length()) >0) &&(datapickerString != "null") )
        {
            System.out.println("działa");
        }
        else {
            System.out.println("dalej nie");
        }

    }*/

    public void materiallist()
    {
        Material materiallist= new Material();
        materiallist.material();
        Mchecklist = materiallist.Mchecklist;
        choiceMaterialBuyContract.setItems(Mchecklist);
        choiceMaterialSellContract.setItems(Mchecklist);
    }
//    combo C;
    @FXML
    public void initialize()
    {
//        C = new combo(comobox, ChoiceContractBuy, ChoiceContractSell, Combolist);
        Object MainTable = new MainTable(data,tables,ColumnData,ColumnMaterial,ColumnPlate,ColumnAmount,ColumnFinal_Amount,ColumnFroms,ColumnTos,ColumnTruck,ColumnOrder,ColumnVk,ColumnEk,ColumnDoc);



        Connection c = null;
        Statement stmt = null;



        data2 = FXCollections.observableArrayList();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM all_view;" );

            while ( rs.next() )
            {
                data2.add(new TableContractOpenBuy
                        (
                                rs.getString(4)

                        ));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        ColumnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));


        TableContractOpenBuy.setItems(null);
        TableContractOpenBuy.setItems(data2);




        OpenSellData = FXCollections.observableArrayList();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ContractsOpenSell;" );

            while ( rs.next() )
            {
                OpenSellData.add(new TableContractOpenSell
                        (
                                rs.getString(4)

                        ));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        ColumnCustomerNameSell.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));


        TableContractOpenSell.setItems(null);
        TableContractOpenSell.setItems(OpenSellData);
    }

    String contractname;
    @FXML
    public void addButton()
    {
        Object  datapicker = DataPicker.getValue();
        String datapickerString = String.valueOf(datapicker);

        String daText = Delivery_Amount.getText();
        String dtText = Delivery_To.getText();
        String dpText = Delivery_Plate.getText();
        if (Delivery_Plate.getText().length() >0) {
            if (Delivery_To.getText().length() >0) {
                if (Delivery_Amount.getText().length() >0) {
                    if (((datapickerString.length()) >0) &&(datapickerString != "null")) {
                        String datText = Delivery_Amount_Truck.getText();
                        System.out.println("sprawdzam środek" + datapickerString.length());

                        Object NameOfCompany = comobox.getValue();
                        String NameToString = String.valueOf(NameOfCompany);

                        Object NameOfMaterial = MateriaID.getText();
                        String MaterialToString = String.valueOf(NameOfMaterial);

                        Object NameOfContract = NrContractBuy.getValue();
                        String ContractToString = String.valueOf(NameOfContract);

                        Object NameOfContractSell = NrContractSell.getValue();
                        String ContractSellToString = String.valueOf(NameOfContractSell);

                        System.out.println(ContractSellToString+"sell kontrakt");

                        if (ContractSellToString.length() >4)
                        {
                            ContractSellToString = contractname;

                        }
                        else
                        {
                            contractname = "dopisz";

                        }
                        System.out.println(contractname);


                        try {
                            int s = Integer.parseInt(daText);
                            System.out.println(s);

                            System.out.println("działa button");

                            Connection c = null;
                            Statement stmt = null;
                            try {
                                Class.forName("org.sqlite.JDBC");
                                c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                                c.setAutoCommit(false);
                                System.out.println("Opened database successfully");
                                try {
                                    stmt = c.createStatement();
                                    String sql = "INSERT INTO All_View (Data,Material,Truck,Amount,Final_Amount,Froms,Tos,Truck_Nr,Transport_Order,Vk,Ek,Ams_doc) " +
                                            "VALUES ('" + (datapickerString) + "','" + (MaterialToString) + "','" + (dpText) + "','" + (daText) + "' , 10000,'" + (NameToString) + "' ,'" + (dtText) + "','" + (datText) + "','12-E','" + (contractname) + "','" + (ContractToString) + "','nic' );";

                                    String sql1 = "UPDATE ContractsOpenBuy set NrTruckContract = (NrTruckContract + '" + (datText) + "')where ContractNAme = '" + (ContractToString) + "'";
                                    String sql2 = "update ContractsOpenBuy set OpenClose = 1 WHERE  NrTruck = NrTruckContract";
                                    String sql3 = "insert into ContractsClose select * From ContractsOpenBuy where openclose =1";
                                    String sql4 = "delete From ContractsOpenBuy where openclose =1";

                                    stmt.execute(sql);
                                    stmt.execute(sql1);
                                    stmt.execute(sql2);
                                    stmt.execute(sql3);
                                    stmt.execute(sql4);
                                } catch (Exception e) {
                                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                    System.exit(0);
                                }


                                stmt.close();
                                c.commit();
                                c.close();
                            } catch (Exception e) {
                                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                                System.exit(0);
                            }


                            Delivery_Plate.setText("");
                            Delivery_Amount_Truck.setText("");
                            Delivery_Amount.setText("");
                            Delivery_To.setText("");
                            MateriaID.setText("");
                            datapickererror.setText("");

                            } catch (NumberFormatException e) {
                                Delivery_Amount.setText("Tylko liczby");
                                System.out.println("nie integer");
                            }
                    }
                    else
                    {
                        datapickererror.setText("Wymagana Data");
                    }
                }
                else
                {
                    Delivery_Amount.setText("Wpisz ilość");
                }
            }
            else
            {
                Delivery_To.setText("Wpisz Odbiorce");
            }
        }
        else
        {
            Delivery_Plate.setText("Wpisz numer rejestracyjny");
        }


    }

    @FXML
    public void combo()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT NAME FROM Customers;" );

            while ( rs.next() )
            {
                Combolist.add(rs.getString("Name"));
            }


            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        comobox.setItems(Combolist);
        ChoiceContractBuy.setItems(Combolist);
        ChoiceContractSell.setItems(Combolist);

    }

    public void contractlist()
    {
        Connection c = null;
        Statement stmt = null;
        System.out.println("combolist"+comobox.getValue());
        String s = String.valueOf(comobox.getValue());
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT id FROM Customers WHERE Name = '" + s + "' ;" );

            while ( rs.next() )
            {
                System.out.println((rs.getInt("id")));
                Customerid = ((rs.getInt("id")));

            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println(Customerid);
        ListchoiceContract();

    }

    public void Materialprepare()
    {
        String NameOfContract = String.valueOf(NrContractBuy.getValue());
        System.out.println("Materialprepare działa"+NameOfContract);


        System.out.println(NameOfContract);
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT idName FROM ContractsOpenBuy Where ContractName = '" + NameOfContract + "';" );

            while ( rs.next() )
            {
                System.out.println((rs.getString("idName")));
                MateriaID.setText((rs.getString("idName")));

            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Nazwa materiału"+MaterialName);
    }

    @FXML
    public void ListchoiceContract()
    {

        Cchecklist.clear();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println("bum"+ Customerid);
            ResultSet rs = stmt.executeQuery( "SELECT ContractName FROM ContractsOpenBuy Where idCustomer = '" + Customerid + "';" );

            while ( rs.next() )
            {
                Cchecklist.add(rs.getString("ContractName"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        NrContractBuy.setItems(Cchecklist);
        System.out.println(Cchecklist);

    }

    @FXML
    public void ListchoiceContractBuyToSell()
    {

        BuyToSellList.clear();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ContractName FROM ContractsOpenSell " );

            while ( rs.next() )
            {
                BuyToSellList.add(rs.getString("ContractName"));
            }

            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        ChoiceContractBuyToSell.setItems(BuyToSellList);
        System.out.println(BuyToSellList);

    }
        int id;
        int idBuyToSell;
    @FXML
    public void addButtonContractBuy()
    {
        String ACBText = AmountContractBuy.getText();
        String TCBText = TruckContractBuy.getText();
        if (AmountContractBuy.getText().length() >0)
        {
            try {
                if (TruckContractBuy.getText().length() > 0) {
                    try {
                        int s = Integer.parseInt(TCBText);
                        System.out.println(s);

                        String NBCText = NrBuyContract.getText();

                        Object NameOfCompanyBuyContract = ChoiceContractBuy.getValue();
                        String NameBuyToString = String.valueOf(NameOfCompanyBuyContract);


                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                            c.setAutoCommit(false);
                            stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT id FROM Customers WHERE NAME = '" + NameBuyToString + "' ;");

                            while (rs.next()) {
                                System.out.println((rs.getInt("id")));
                                idBuyToSell = ((rs.getInt("id")));
                            }
                            rs.close();
                            stmt.close();
                            c.close();
                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }

                        Object NameOfCompanyBuyToSell = ChoiceContractBuyToSell.getValue();
                        String NameBuyToSellString = String.valueOf(NameOfCompanyBuyToSell);

                        try {
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                            c.setAutoCommit(false);
                            stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT id FROM ContractsOpenSell WHERE ContractNAME = '" + NameBuyToSellString + "' ;");

                            while (rs.next()) {
                                System.out.println((rs.getInt("id")));
                                id = ((rs.getInt("id")));

                            }

                            rs.close();
                            stmt.close();
                            c.close();
                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }


                        Object NameOfMaterialBuyContract = choiceMaterialBuyContract.getValue();
                        String MaterialContractBuyToString = String.valueOf(NameOfMaterialBuyContract);


                        try {
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                            c.setAutoCommit(false);
                            System.out.println("Opened database successfully");

                            stmt = c.createStatement();
                            String sql = "INSERT INTO ContractsOpenBuy (idSell,idCustomer,CustomerName,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                                    "VALUES ('" + idBuyToSell + "','" + id + "','"+(NameBuyToString) +"','" + (MaterialContractBuyToString) + "','" + (TCBText) + "' , '0' ,'" + (NBCText) + "','" + (ACBText) + "','0') ;";
                            stmt.executeUpdate(sql);


                            stmt.close();
                            c.commit();
                            c.close();
                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }


                        NrBuyContract.setText("");
                        AmountContractBuy.setText("");
                        TruckContractBuy.setText("");
                        Delivery_To.setText("");

                    } catch (NumberFormatException e) {
                        TruckContractBuy.setText("Tylko liczby");
                        System.out.println("nie integer");
                    }
                } else {
                    TruckContractBuy.setText("Wpisz ilość aut");
                }
            }
            catch (NumberFormatException e)
            {
                AmountContractBuy.setText("Tylko liczby");
                System.out.println("nie integer");
            }


        }
        else
        {
            AmountContractBuy.setText("Wpisz ilość materiału");
        }
        ChoiceContractBuyToSell.valueProperty().set(null);
        ChoiceContractBuy.valueProperty().set(null);
        choiceMaterialBuyContract.valueProperty().set(null);


    }

    @FXML
    public void addButtonContractSell()
    {
        String ACSText = AmountContractSell.getText();
        String TCSText = TruckContractSell.getText();
        if (AmountContractSell.getText().length() >0)
        {
            try {
                if (TruckContractSell.getText().length() > 0) {
                    try {
                        int s = Integer.parseInt(TCSText);
                        System.out.println(s);

                        String NBCText = NrSellContract.getText();

                        Object NameOfCompanyBuyContract = ChoiceContractSell.getValue();
                        String NameSellToString = String.valueOf(NameOfCompanyBuyContract);


                        Connection c = null;
                        Statement stmt = null;
                        try {
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                            c.setAutoCommit(false);
                            stmt = c.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT id FROM Customers WHERE NAME = '" + NameSellToString + "' ;");

                            while (rs.next()) {
                                System.out.println((rs.getInt("id")));
                                id = ((rs.getInt("id")));
                            }

                            rs.close();
                            stmt.close();
                            c.close();
                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }


                        Object NameOfMaterialBuyContract = choiceMaterialSellContract.getValue();
                        String MaterialContractSellToString = String.valueOf(NameOfMaterialBuyContract);


                        try {
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:organizmy.db");
                            c.setAutoCommit(false);
                            System.out.println("Opened database successfully");

                            stmt = c.createStatement();
                            String sql = "INSERT INTO ContractsOpenSell (idCustomer,idName,NrTruck,NrTruckContract,ContractName,Amount,OpenClose) " +
                                    "VALUES ('" + id + "','" + (MaterialContractSellToString) + "','" + (TCSText) + "' , '0' ,'" + (NBCText) + "','" + (ACSText) + "','0') ;";
                            stmt.executeUpdate(sql);


                            stmt.close();
                            c.commit();
                            c.close();
                        } catch (Exception e) {
                            System.err.println(e.getClass().getName() + ": " + e.getMessage());
                            System.exit(0);
                        }


                        NrSellContract.setText("");
                        AmountContractSell.setText("");
                        TruckContractSell.setText("");


                    } catch (NumberFormatException e) {
                        TruckContractSell.setText("Tylko liczby");
                        System.out.println("nie integer");
                    }
                } else {
                    TruckContractSell.setText("Wpisz ilość aut");
                }
            }
            catch (NumberFormatException e)
            {
                AmountContractSell.setText("Tylko liczby");
                System.out.println("nie integer");
            }


        }
        else
        {
            AmountContractSell.setText("Wpisz ilość materiału");
        }


    }

}
