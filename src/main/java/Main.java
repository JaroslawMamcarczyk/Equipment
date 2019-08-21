import Dao.policemanDao.RangeDao;
import Dao.policemanDao.WorkerDao;
import Dao.productDao.ProductDao;
import Dao.productDao.ProductGroupDao;
import Dao.productDao.SwitchDao;
import building.Building;
import building.BuildingDao;
import building.Room;
import building.RoomDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import policeman.Range;
import policeman.Worker;
import product.*;
import java.io.IOException;
import java.math.BigDecimal;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/MainScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("KPP Project");
        stage.setScene(new Scene(root, 1366, 768));
       // String cssPath = this.getClass().getResource("/css/mainScreenCss.css").toExternalForm();
        //setEleemntsTesting();
        //createRange();
        stage.show();
    }

    private void setEleemntsTesting(){
        ProductGroupDao productGroupDao = new ProductGroupDao();
        ProductGroup productGroup = new ProductGroup("Komputer");
        ProductGroup productGroup1 = new ProductGroup("Switch");
        ProductGroup productGroup2 = new ProductGroup("Notebook");
        productGroupDao.save(productGroup);
        productGroupDao.save(productGroup1);
        productGroupDao.save(productGroup2);
        BuildingDao buildingDao = new BuildingDao();
        Building building = new Building("KPP");
        buildingDao.save(building);
        RoomDao roomDao = new RoomDao();
        Room room = new Room("0", Room.Floor.PARTER,building,1,1,"Dyzurka");
        roomDao.save(room);
        ProductDao productDao = new ProductDao();
        Product product1 = new Product("Komputer HP","123", Product.ProductKind.INFORMATYKA,"123","123", BigDecimal.valueOf(23.99),2009,productGroup,"brak",room);
        Product product2 = new Product("Switch Cisco","123", Product.ProductKind.INFORMATYKA,"123","123", BigDecimal.valueOf(23.99),2009,productGroup1,"brak",room);
        Product product = new Product("Notebook Lenovo","123", Product.ProductKind.INFORMATYKA,"123","123", BigDecimal.valueOf(23.99),2009,productGroup2,"brak",room);
        Product product4 = new Product("Router","123", Product.ProductKind.INFORMATYKA,"123","123", BigDecimal.valueOf(23.99),2009,productGroup1,"brak",room);
        productDao.save(product);
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product4);
        SwitchDao switchDao = new SwitchDao();
        for(int i = 0;i<24;i++) {
            ComputerSwitch computerSwitch = new ComputerSwitch("AX-100", Integer.toString(i), product2);
            switchDao.save(computerSwitch);
        }
        for(int j=0;j<30;j++) {
            ComputerSwitch computerSwitch1 = new ComputerSwitch("RX-200", Integer.toString(j), product4);
            switchDao.save(computerSwitch1);
        }
        WorkerDao workerDao = new WorkerDao();
        Worker worker = new Worker("Jan","Kowalski","123456","11111111111");
        workerDao.save(worker);
    }

    public void createRange(){
        RangeDao rangeDao = new RangeDao();
        Range range = new Range("Posterunkowy","/Pics/P.jpg");
        Range range1 = new Range("Starszy Posterunkowy","/Pics/SP.jpg");
        Range range2 = new Range("Sierżant","/Pics/S.jpg");
        Range range3 = new Range("Starszy Sierżant","/Pics/SSS.jpg");
        Range range4 = new Range("Sierżant Sztabowy","/Pics/SS.jpg");
        Range range5 = new Range("Młodszy Aspirant","/Pics/MA.jpg");
        Range range6 = new Range("Aspirant","/Pics/A.jpg");
        Range range7 = new Range("Starszy Aspirant","/Pics/SA.jpg");
        Range range8 = new Range("Aspirant Sztabowy","/Pics/AS.jpg");
        Range range9 = new Range("Podkomisarz","/Pics/PK.jpg");
        Range range10 = new Range("Komisarz","/Pics/K.jpg");
        Range range11 = new Range("Nadkomisarz","/Pics/NK.jpg");
        Range range12 = new Range("Podinspektor","/Pics/PI.jpg");
        Range range13 = new Range("Młodszy Inspektor","/Pics/MI.jpg");
        Range range14 = new Range("Inspektor","/Pics/I.jpg");
        Range range15 = new Range("Nadinspektor","/Pics/NI.jpg");
        Range range16 = new Range("Generalny Inspektor","/Pics/GI.jpg");
        rangeDao.save(range);
        rangeDao.save(range1);
        rangeDao.save(range2);
        rangeDao.save(range3);
        rangeDao.save(range4);
        rangeDao.save(range5);
        rangeDao.save(range6);
        rangeDao.save(range7);
        rangeDao.save(range8);
        rangeDao.save(range9);
        rangeDao.save(range10);
        rangeDao.save(range11);
        rangeDao.save(range12);
        rangeDao.save(range13);
        rangeDao.save(range14);
        rangeDao.save(range15);
        rangeDao.save(range16);    }
}
