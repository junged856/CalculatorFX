package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
	@FXML
    private Button addBtn;

    @FXML
    private Button btn0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button divideBtn;

    @FXML
    private Button enterBtn;

    @FXML
    private Button minusBtn;

    @FXML
    private Button multiplyBtn;

    @FXML
    private Label prevCalc;

    @FXML
    private Label resultLabel;
    
    @FXML
    private ImageView btnClose;

    @FXML
    private ImageView btnMinimize;
    
    @FXML
    private Pane titlePane;
    
    private String operator;
    private double num1;
    private double num2;
    private double result; // container which temporarily stores the result of a math operation until its displayed
    private boolean newCalcAvailable = false;
    private String lastBtnPressed = "";
    private Button buttonPressed;
    private String numPressed;
    private double x, y;
    
    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
    
    public void setOperator(String operator) {
		this.operator = operator;
	}
	
	// simply calculates the result based on the operator 
	public void performOperation(double num1, double num2) {
		
		switch(operator) {
		
		case "+": 
			result = num1 + num2;
			break;
			
		case "-": 
			result = num1 - num2;
			break;
			
		case "*": 
			result = num1 * num2;
			break;
			
		case "/":
			result = num1 / num2;
			break;
		}
	}
	
	// updates the display to show the result
	public void showResult() {
		resultLabel.setText(String.valueOf(result));
	}
	
	// appends the number pressed to the display
	public void appendNumToDisplay(String numPressed) {
		resultLabel.setText(resultLabel.getText() + numPressed);
	}
   
   public String getOperator(Button buttonPressed) {
	   if (buttonPressed == addBtn) {
		   return "+";
	   } else if (buttonPressed == minusBtn) {
		   return "-";	
	   } else if (buttonPressed == multiplyBtn) {
		   return "*";
	   } else {
		   return "/";
	   }
   }
   
   public void operatorPressed(ActionEvent e) {
	   buttonPressed = (Button) e.getSource();
	   
	   if (newCalcAvailable) {
	   		num2 = Double.parseDouble(resultLabel.getText());
	   		performOperation(num1, num2);
	   		showResult();
	   		num1 = result;
	   		
	   		newCalcAvailable = false;
	   	}
	   	
	   	else {
	   		num1 = Double.parseDouble(resultLabel.getText());
	   	}
	   	
	    operator = getOperator(buttonPressed);
	   	setOperator(operator);
	   	lastBtnPressed = "operator";
   }
    
   public String getNum(Button buttonPressed) {
	   if (buttonPressed == btn1) {
		   return "1";
	   } else if (buttonPressed == btn2) {
		   return "2";
	   } else if (buttonPressed == btn3) {
		   return "3";
	   } else if (buttonPressed == btn4) {
		   return "4";
	   } else if (buttonPressed == btn5) {
		   return "5";
	   } else if (buttonPressed == btn6) {
		   return "6";
	   } else if (buttonPressed == btn7) {
		   return "7";
	   } else if (buttonPressed == btn8) {
		   return "8";
	   } else if (buttonPressed == btn9) {
		   return "9";
	   } else {
		   return "0";
	   }
   }
    
    public void numberPressed(ActionEvent e) {
    	buttonPressed = (Button) e.getSource();
    	numPressed = getNum(buttonPressed);
    	
    	if(lastBtnPressed.equals("operator")) {
    		newCalcAvailable = true;
    		resultLabel.setText("");
    	} else if (lastBtnPressed.equals("equals") || resultLabel.getText().equals("0.0")){
    		resultLabel.setText("");
    	} 
    	
    	appendNumToDisplay(numPressed);
    	
    	lastBtnPressed = "number";
    }
    
    public void equalsPressed() {
    	if (newCalcAvailable) {
	   		num2 = Double.parseDouble(resultLabel.getText());
	   		performOperation(num1, num2);
	   		showResult();
	   		num1 = result;
	   		
	   		newCalcAvailable = false;
	   	}
	   	
	   	else {
	   		operator = "";
	   	}
    	
    	lastBtnPressed = "equals";
    }
    
    public void clearBtnPressed() {
    	resultLabel.setText("0.0");
    	lastBtnPressed = "clear";
    	newCalcAvailable = false;
    }
}
