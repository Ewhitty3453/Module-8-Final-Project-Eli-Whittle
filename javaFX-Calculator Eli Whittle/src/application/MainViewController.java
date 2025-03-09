package application;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainViewController {

    @FXML private TextArea display;
    @FXML private Button one, two, three, four, five, six, seven, eight, nine, zero, times, divided, plus, minus, percent, change, buttonDelete;

    private boolean[] operator = new boolean[5]; 
    private double[] temporary = {0, 0};

    private Main main;

    public void setMain(Main main) {
        this.main = main;
        display.setEditable(false);
        display.setText("0");

        for (int i = 0; i < operator.length; i++) {
            operator[i] = false;
        }
    }

    @FXML
    public void handleClick(Event event) {
        String currentText = display.getText();
        
        if (currentText.equals("0")) {
            buttonDelete.setText("C");
            display.setText("");
        }

        Button btn = (Button) event.getSource();
        display.appendText(btn.getText());
    }

    @FXML
    public void delete(Event event) {
        buttonDelete.setText("AC");
        display.setText("0");

        for (int i = 0; i < temporary.length; i++) {
            temporary[i] = 0;
        }
        for (int i = 0; i < operator.length; i++) {
            operator[i] = false;
        }
    }

    @FXML
    public void operation(Event event) {
        if (display.getText().isEmpty()) {
            return;
        }

        Button btn = (Button) event.getSource();
        int operationIndex = switch (btn.getId()) {
            case "plus" -> 1;
            case "minus" -> 2;
            case "times" -> 3;
            case "divided" -> 4;
            default -> -1;
        };

        if (operationIndex != -1) {
            for (int i = 0; i < operator.length; i++) {
                operator[i] = false;
            }
            operator[operationIndex] = true;
            temporary[0] = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    @FXML
    public void changeSign() {
        if (!display.getText().isEmpty()) {
            try {
                double number = Double.parseDouble(display.getText());
                display.setText(Double.toString(-number));
            } catch (NumberFormatException e) {
                display.setText("Error");
            }
        }
    }

    @FXML
    public void result(Event event) {
        if (display.getText().isEmpty()) {
            return;
        }

        temporary[1] = Double.parseDouble(display.getText());
        double result = 0;

        if (operator[1]) result = temporary[0] + temporary[1];
        else if (operator[2]) result = temporary[0] - temporary[1];
        else if (operator[3]) result = temporary[0] * temporary[1];
        else if (operator[4]) {
            if (temporary[1] != 0) {
                result = temporary[0] / temporary[1];
            } else {
                display.setText("Error");
                return;
            }
        }

        display.setText(Double.toString(result));
    }
}
