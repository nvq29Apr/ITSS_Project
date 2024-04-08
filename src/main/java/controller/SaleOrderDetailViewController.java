package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Window;
import model.SaleOrderItem;
import mysqlsubsystem.MySQLSaleOrderDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SaleOrderDetailViewController implements Initializable {
    @FXML
    private TableView<SaleOrderItem> saleOrderDetailTable;

    @FXML
    private TableColumn<SaleOrderItem, String> idColumn;

    @FXML
    private TableColumn<SaleOrderItem, Integer> quantityColumn;

    @FXML
    private TableColumn<SaleOrderItem, Integer> unitColumn;

    @FXML
    private TableColumn<SaleOrderItem, Date> dateColumn;

    private ObservableList<SaleOrderItem> orderItems;

    @FXML
    private Button updateItemBtn;

    @FXML
    private Button deteleItemBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderItems = FXCollections.observableArrayList(
                new SaleOrderItem("S001", "P001", 500, 1, Date.valueOf("2024-05-01")),
                new SaleOrderItem("S001", "L002", 400, 1, Date.valueOf("2024-06-01")),
                new SaleOrderItem("S001", "P009", 250, 1, Date.valueOf("2024-05-06")),
                new SaleOrderItem("S001", "T001", 5200, 1, Date.valueOf("2024-04-29")),
                new SaleOrderItem("S001", "P006", 600, 1, Date.valueOf("2024-05-25"))
        );

        idColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, String>("merchandiseCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Integer>("quantityOrdered"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Integer>("unit"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<SaleOrderItem, Date>("desiredDeliveryDate"));

        saleOrderDetailTable.setItems(orderItems);
    }

    public void updateItemBtnClicked(ActionEvent e){
        SaleOrderItem selectedItem = saleOrderDetailTable.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("updatedialog.fxml"));
            DialogPane updateDialogPane = fxmlLoader.load();

            UpdateDialogController updateDialogController = fxmlLoader.getController();
            updateDialogController.setValue(selectedItem);

            Dialog updateItemDialog  = new Dialog();
            updateItemDialog.setTitle("Update Sale Order Item");
            updateItemDialog.setDialogPane(updateDialogPane);

            Window window = updateItemDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> updateItemDialog.close());

            updateItemDialog.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

//        updateItemDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
//        updateItemDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
//        updateItemDialog.getDialogPane().setContent(updateForm(selectedItem));
//
////        updateItemDialog.getDialogPane().getButtonTypes().get(1).
//        updateItemDialog.show();
    }
//
//    private Node updateForm(SaleOrderItem selectedItem){
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//
//        Label merchandiseCodeLbl = new Label("Merchandise Code");
//        merchandiseCodeLbl.setFont(Font.font(14));
//
//        Label merchandiseCodeValue = new Label(selectedItem.getMerchandiseCode());
//        merchandiseCodeValue.setFont(Font.font(14));
//
//
//        gridPane.add(merchandiseCodeLbl, 0, 0);
//        gridPane.add(merchandiseCodeValue, 1, 0);
//
//        gridPane.add(new Label("Quantity Ordered"), 0, 1);
//        TextField quantityTf = new TextField(String.valueOf(selectedItem.getQuantityOrdered()));
//        gridPane.add(quantityTf, 1, 1);
//
//        gridPane.add(new Label("Unit"), 0, 2);
//        gridPane.add(new TextField(String.valueOf(selectedItem.getUnit())), 1, 2);
//
//        gridPane.add(new Label("Derised Delivery Date"), 0, 3);
//        gridPane.add(new TextField(String.valueOf(selectedItem.getDesiredDeliveryDate())), 1, 3);
//
//        return gridPane;
//    }

    private void quantityHandler() {
    }

}