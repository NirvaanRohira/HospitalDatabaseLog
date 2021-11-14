package ui;

import model.ListOfPatientData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//Represents the GUI class using the Java Swing Library
public class GuiSwingClass extends JPanel {
    private static final String JSON_STORE = "./data/patients.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JList list;
    private JFrame frame;
    private final JLabel name = new JLabel("Name: ");
    private final JLabel age = new JLabel("Age: ");
    private final JLabel daysStayed = new JLabel("Days Stayed: ");
    private final JLabel treatmentRecieved = new JLabel("Treatment Recieved: ");
    private final JLabel condition = new JLabel("Condition: ");
    private final JLabel cost = new JLabel("Cost: ");
    private JButton saveButton;
    private JButton loadButton;
    private JButton addButton;
    private JTextField patientName;
    private JTextField patientAge;
    private JTextField patientDaysStayed;
    private JTextField patientTreatmentRecieved;
    private JTextField patientCondition;
    private JTextField patientCost;
    private final DefaultListModel patientList = new DefaultListModel();
    private ListOfPatientData patientList2 = new ListOfPatientData("Patient List");

    //EFFECTS: Creates a Constructor
    public SwingClass() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 700, 700);

        refreshListOfPatientData();
        listScrollPane();
        createPanelWithBoxLayout();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
//FILL THESE IN (DK ABOUT EXCEPTIONO HANDLING RN)
    private void createPanelWithBoxLayout() {

    }

    private void listScrollPane() {

    }

    private void refreshListOfPatientData() {

    }

    //(name, age, stayTime, treatmentRecieved, condition, cost)
    //make all labels
}
