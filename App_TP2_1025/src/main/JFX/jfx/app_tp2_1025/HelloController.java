package jfx.app_tp2_1025;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    public TableView coursesTable;
    public TableColumn coursesCode;
    public TableColumn coursesNames;
    public ChoiceBox choiceBox;
    public Button loadButton;
    public Button sendButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField matriculate;

    @FXML
    private Label testText;


    @FXML
    protected void send(ActionEvent event) {
        String fn = firstName.getText();
        String ln = lastName.getText();
        String mail = email.getText();
        String mat = matriculate.getText();

        testText.setText(fn + ln + mail + mat);
        System.out.println(fn + ln + mail + mat);
    }


}