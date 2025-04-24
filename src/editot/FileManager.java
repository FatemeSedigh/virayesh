package editor;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
    public static void openFile(editor.TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            textEditor.currentFile = file;
            textEditor.setTitle("Simple Text Editor - " + file.getName());

            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor,
                        "Error opening file: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(editor.TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                //TODO
            }
        }
        else{
            //TODO
        }
    }

    public static void newFile(editor.TextEditor textEditor, JTextArea textArea) {
        //TODO
    }
}
