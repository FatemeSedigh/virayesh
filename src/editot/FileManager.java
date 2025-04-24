package editor;

import javax.swing.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            //File file = fileChooser.getSelectedFile();
            //TODO
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
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

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        //TODO
    }
}
