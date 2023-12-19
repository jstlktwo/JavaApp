package your.groupId.javafxappnonmodular;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;


public class NotesScreenController {

    @FXML
    private Button closeBtn;

    @FXML
    private TextArea enterArticle;
    @FXML
    private TextArea enterNote;

    public static int getLineCountByReader() throws IOException {
        try (var lnr = new LineNumberReader(new BufferedReader(new FileReader(MainController.getId() + ".txt")))) {
            while (lnr.readLine() != null) ;
            return lnr.getLineNumber();
        }
    }

    public void getArticleAndNote() throws Exception {
        String stringArticle = String.valueOf(enterArticle.getText());
        String stringNote = String.valueOf(enterNote.getText());

        try {
            File file = new File(MainController.getId() + ".txt");
            if (file.createNewFile()) {
                System.out.println("File's been created");
            } else {
                System.out.println("File has already been created");
            }
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e);
            e.printStackTrace();
        }

        try {
            String id = MainController.getId();
            int lineNumber = getLineCountByReader();

            NoteStructure note = new NoteStructure(lineNumber + 1, id, stringArticle, stringNote);
            BufferedWriter writer = new BufferedWriter(new FileWriter(MainController.getId() + ".txt", true));
            writer.write(note.getInfo());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file");
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
    }

    public void closeNotesOnClick() throws IOException {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationLauncher.class.getResource("views/mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Java App Calendar!");
        stage.setScene(scene);
        stage.show();
    }

    public void backNotesOnClick() throws IOException {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationLauncher.class.getResource("views/notesList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Java App Calendar!");
        stage.setScene(scene);
        stage.show();
    }


}
