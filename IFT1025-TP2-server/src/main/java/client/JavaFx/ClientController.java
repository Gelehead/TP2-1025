package client.JavaFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import server.models.*;
import utils.FilesManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Client controller.
 */
public class ClientController implements Initializable {

    /**
     * The Courses table. Tableau des cours
     */
    @FXML public TableView<Course> coursesTable;

    /**
     * The Courses code. Code des cours
     */
    @FXML public TableColumn<Course, String> coursesCode;

    /**
     * The Courses names. Nom des cours
     */
    @FXML public TableColumn<Course, String> coursesNames;

    /**
     * The Choice box. Le menu deroulant pour choisir la session
     */
    @FXML public ChoiceBox<String> choiceBox;
    private String[] sessions = {"Automne", "Ete", "Hiver"};

    /**
     * The Send button. Le bouton pour envoyer le tout au serveur
     */
    @FXML public Button sendButton;

    /**
     * The Matriculate. Le matricule de l élève
     */
    @FXML public TextField matriculate;

    @FXML
    private TextField firstName;
    /**
     * nom de famille pour les boites de texte
     */
    @FXML
    private TextField lastName;
    /**
     * email pour les textbox
     */
    @FXML
    private TextField email;



    /**
     * Send. Prend toutes les donnees selectionnees et entrees dans les champs disponibles
     * et les envoie
     * 
     * @param event sert lors de l utilisation (lorsqu il est clicke)
     */
    @FXML
    protected void send(ActionEvent event) throws IOException {
        String fn = firstName.getText();
        String ln = lastName.getText();
        String mail = email.getText();
        String mat = matriculate.getText(); 

        RegistrationForm form = new RegistrationForm(fn, ln, mail, mat, getChosenCourse());
        utils.FilesManager.formToFile("IFT1025-TP2-server/src/main/java/server/data/inscription.txt",form);
        System.out.println("ajouté au" + utils.FilesManager.formToString(form));
    }


    /**
     * The Course list. la liste des cours, sera affichée sur le tableau
     */
    ObservableList<Course> courseList = FXCollections.observableArrayList();

    /** Initialise le tableau
     *
     * @param url            necessaire pour créer la fonction avec @Override
     * @param resourceBundle    necessaire pour créer la fonction avec @Override
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            coursesCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            coursesNames.setCellValueFactory(new PropertyValueFactory<>("name"));
            coursesTable.setItems(courseList);
            setupCourseList("IFT1025-TP2-server/src/main/java/server/data/cours.txt");
            coursesTable.refresh();

            choiceBox.getItems().addAll(sessions);
            //choiceBox.setOnAction(this::getSession);
    }

    /**
     * donne la session que l utilisateur a choisi
     *
     * @return retourne la session choisie
     */
    public String getSession(){
        return choiceBox.getValue();
    }

    /**
     * Setup course list. Met en place la liste des cours au premier lancement de l'application
     *
     * @param filePath the file path, le chemin (relatif, à partir de la racine : TP2_1025 )
     */
    public void setupCourseList(String filePath){
        coursesTable.getItems().clear();
        ArrayList<Course> listOfCourse = FilesManager.getCourseList(filePath);
        for (Course course : listOfCourse) {
            coursesTable.getItems().add(course);
        }
        coursesTable.refresh();
    }

    public void setupCourseList(String filePath, String session){
        coursesTable.getItems().clear();
        ArrayList<Course> listOfCourse = FilesManager.getCourseList(filePath, session);
        for (Course course : listOfCourse) {
            coursesTable.getItems().add(course);
        }
        coursesTable.refresh();
    }


    public Course getChosenCourse(){
        return coursesTable.getSelectionModel().getSelectedItem();

    }

    public void loadCourses(MouseEvent mouseEvent) {
        setupCourseList("IFT1025-TP2-server/src/main/java/server/data/cours.txt", getSession());
    }
}