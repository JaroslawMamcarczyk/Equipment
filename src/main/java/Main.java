import Dao.productDao.SwitchDao;
import building.Building;
import building.BuildingDao;
import building.Room;
import building.RoomDao;
import controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    }
}
