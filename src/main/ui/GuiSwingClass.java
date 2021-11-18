package ui;

import model.ListOfPatientData;
import model.PatientData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.awt.Color.CYAN;

///tofo:
//Increase Panel Length of all panels
// make them move to new line panels
// Fix Save Feature
// Fix Load Feature
// Figure out sound issue
// Shorten the methods to 25 lines
// figure why load listener isnt being imported
//import com.sun.javafx.fxml.LoadListener; - this one
//display an image (maybe on opening say welcome or smth) - maybe on close?
//ASK TA IF I CAN USE THE @SupressWarnings foor the long methods!!!
//Not showing save option before i press load option and not saving and loading

//Represents the GUI class using the Java Swing Library
public class GuiSwingClass extends JPanel {
    private static final String JSON_STORE = "./data/patients.json";
    private static final String IMAGE = "./data/HQLogo.png";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private ImageIcon image;
    private JLabel label;

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
    public GuiSwingClass() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 700, 700);

        refreshListOfPatientData();
        listScrollPane();
        createBoxPanel();
        loadImage();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setIconImage();
        //frame.setForeground(CYAN);


    }

    // EFFECTS: Create a panel that uses the BoxLayout and adds all the panel functionalities.
    @SuppressWarnings("methodlength")
    public void createBoxPanel() {

        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        saveButton.setEnabled(true);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(name);
        buttonPane.add(patientName);
        buttonPane.add(age);
        buttonPane.add(patientAge);
        buttonPane.add(daysStayed);
        buttonPane.add(patientDaysStayed);
        buttonPane.add(treatmentRecieved);
        buttonPane.add(patientTreatmentRecieved);
        buttonPane.add(condition);
        buttonPane.add(patientCondition);
        buttonPane.add(cost);
        buttonPane.add(patientCost);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }


    //Creates a scrollable list in a panel
    @SuppressWarnings("methodlength")
    public void listScrollPane() {
        newJList();
        setForeground(CYAN);
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        AudioListener audioListener = new AudioListener();
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.addActionListener(audioListener);
        addButton.setEnabled(false);

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());
        saveButton.addActionListener(audioListener);
        saveButton.setEnabled(false);

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());
        loadButton.addActionListener(audioListener);


        patientName = new JTextField();
        patientName.addActionListener(new LoadListener());
        patientName.getDocument().addDocumentListener(addListener);

        patientAge = new JTextField();
        patientAge.addActionListener(new LoadListener());
        patientAge.getDocument().addDocumentListener(addListener);

        patientDaysStayed = new JTextField();
        patientDaysStayed.addActionListener(new LoadListener());
        patientDaysStayed.getDocument().addDocumentListener(addListener);

        patientTreatmentRecieved = new JTextField();
        patientTreatmentRecieved.addActionListener(new LoadListener());
        patientTreatmentRecieved.getDocument().addDocumentListener(addListener);

        patientCondition = new JTextField();
        patientCondition.addActionListener(new LoadListener());
        patientCondition.getDocument().addDocumentListener(addListener);

        patientCost = new JTextField();
        patientCost.addActionListener(new LoadListener());
        patientCost.getDocument().addDocumentListener(addListener);


    }

    // REQUIRES: a java list of patient data
    // MODIFIES: this
    // EFFECTS:  returns a Default List model of patient profiles for JList to process
    private void refreshListOfPatientData() {
        for (PatientData patient : patientList2.getList()) {
            patientList.addElement((patient.getName() + " : " + patient.getAge() + " : " + patient.getDaysStayed()
                    + " : " + patient.getTreatmentReceived() + " : " + patient.getCondition() + " : "
                    + patient.getTreatmentCost()));
        }
    }

    // EFFECTS: Creates a new JList
    public void newJList() {
        list = new JList(patientList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);


    }

    //EFFECTS: Saves Patient Data to File
    private void savePatientData() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientList2);
            jsonWriter.close();
            System.out.println("Saved " + patientList2.getNameList() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found... sorry cannot save " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Loads a list of saved patient data
    private void loadPatientData() {
        try {
            patientList2 = jsonReader.read();
            System.out.println("Loaded " + patientList2.getNameList() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load data from file... sorry  " + JSON_STORE);
        }

    }

    // EFFECTS: Creates an ActionListener object for save button
    class SaveListener implements ActionListener {

        // EFFECTS: returns the action performed by the save button
        public void actionPerformed(ActionEvent e) {

            savePatientData();
            loadImage();

            int size = patientList.getSize();

            //saveButton.setEnabled(size != 0);
        }
    }

    // EFFECTS: Creates an ActionListener object for load button
    class LoadListener implements ActionListener {

        // EFFECTS: returns the action performed by the load button
        public void actionPerformed(ActionEvent e) {


            loadImage();
            loadPatientData();
            refreshListOfPatientData();
            //saveButton.setEnabled(true);

            int size = patientList.getSize();

            if (size == 0) {
                loadButton.setEnabled(true);

            } else {
                loadButton.setEnabled(false);
            }
        }
    }

    // EFFECTS: returns an audio note saved in the computer under soundName;
    //          Or throws an exception.
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Cannot Play the sound... sorry ");
            ex.printStackTrace();
        }
    }

    // EFFECTS: Creates an Audio ActionListener object for save,load and add buttons
    class AudioListener implements ActionListener {

        // EFFECTS: returns the action performed/audio note on pressing the buttons
        public void actionPerformed(ActionEvent e) {
            playSound("./data/blip.wav");
        }
    }


    // EFFECTS: Creates an ActionListener object for the add button and the two text fields
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // EFFECTS: Creates an AddListener constructor
        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: returns the action performed in the class
        @SuppressWarnings("methodlength")
        public void actionPerformed(ActionEvent e) {

            String name = patientName.getText();
            String age = patientAge.getText();
            String daysStayed = patientDaysStayed.getText();
            String treatmentRecieved = patientTreatmentRecieved.getText();
            String condition = patientCondition.getText();
            String cost = patientCost.getText();
            int ageConvert = Integer.parseInt(age);
            int daysStayedConvert = Integer.parseInt(daysStayed);
            int costConvert = Integer.parseInt(cost);
            patientList2.addPatientData(name, ageConvert, daysStayedConvert, treatmentRecieved,
                    condition, costConvert);
            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                patientName.requestFocusInWindow();
                patientName.selectAll();
                return;
            }
            patientList.addElement((patientName.getText() + " : " + patientAge.getText() + " : "
                    + patientDaysStayed.getText() + " : " + patientTreatmentRecieved.getText() + " : "
                    + patientCondition.getText() + " : " + patientCost.getText()));

            patientName.requestFocusInWindow();
            patientName.setText("");

            patientAge.requestFocusInWindow();
            patientAge.setText("");

            patientDaysStayed.requestFocusInWindow();
            patientDaysStayed.setText("");

            patientTreatmentRecieved.requestFocusInWindow();
            patientTreatmentRecieved.setText("");

            patientCondition.requestFocusInWindow();
            patientCondition.setText("");

            patientCost.requestFocusInWindow();
            patientCost.setText("");

        }


        // EFFECTS: Enables the add button when text is inputted
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: Disables the add button when no text inputted
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: Updates the enable state of the button
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: Enables functionality of the button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: Disables functionality of the button
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }

    }

    //    public void BackgroundImage() {
//        setLayout(new FlowLayout());
//
//        image = new ImageIcon(IMAGE);
//        label = new JLabel(image);
//        add(label);
//    }
//
//    class ImagePanel extends JComponent {
//        private Image image;
//        public ImagePanel(Image image) {
//            this.image = image;
//        }
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.drawImage(image, 0, 0, this);
//        }
//    }
//
//    // elsewhere
//
//    BufferedImage myImage;
//
//    {
//        try {
//            myImage = ImageIO.read(new File(IMAGE));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    JFrame myJFrame = new JFrame("Image pane");
//myJFrame.setContentPane(new ImagePanel(myImage));

    private void loadImage() {
        String sep = System.getProperty("file.separator");
        image = new ImageIcon(IMAGE);

    }
}

