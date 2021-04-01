package sourcecode.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import sourcecode.model.Person;
import sourcecode.model.DAO;

import sourcecode.util.DateUtil;


public class RegisterMemberDialogController implements Initializable {

	@FXML private Label lblTitle;
    @FXML private JFXTextField tfID;
    @FXML private JFXPasswordField pfPW;
    @FXML private JFXPasswordField pfPW_check;
    @FXML private JFXTextField tfPhoneNumber;
    @FXML private JFXTextField tfZipCode;
    @FXML private JFXTextField tfVolunteerTime;
    @FXML private Text textPoint;
   
    
    
    private boolean bIsCheckedID;
    private boolean bIsCheckedPW;
    private boolean bIsCheckedPoint;
    private int nPoint;
    
    private String title;
    private String strID;
    private String strPW;
    private String strPW_check;
    private String strPhoneNumber;
    private String strZipCode;
    private int nVolunteerTime;
   
    
    private Stage currentStage;
    private Person person;
    private boolean okClicked = false;
    private String insertionType;
    
    //dialog init
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	tfVolunteerTime.setOnKeyPressed(event -> {
    		bIsCheckedPoint = false;
    		String volunteerTime = "";
    		
    		String value = event.getText();
    		try {
    			if(Character.isDigit(value.charAt(0))) {
        			return;
        		} else {
        			Alert alert = new Alert(AlertType.WARNING);
            		alert.setTitle("Warning!!");
            		alert.setHeaderText("숫자만 입력하세요");
            		alert.showAndWait();
            		volunteerTime = tfVolunteerTime.getText();
            		volunteerTime = volunteerTime.substring(0, volunteerTime.length() - 1);

        		}
    		} catch(NullPointerException e) {
    			
    		} finally {
    			
    			if(volunteerTime.length() == 0)
    				return;
        		tfVolunteerTime.setText(volunteerTime);
    		}
    		
    	});
    	
    	System.out.println(pfPW.getText());
    }  

    
    private boolean isValidInput() {
    	String str = "";
    	
    	if(tfID.getText() != null && pfPW.getText() != null
    			&& tfPhoneNumber.getText() != null && tfZipCode.getText() != null
    			&& tfVolunteerTime.getText() != null && textPoint.getText() != null) {
    		if(bIsCheckedID && bIsCheckedPW && bIsCheckedPoint) {
    			return true;
    		} else {
    			
    			Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Warning!!");
        		
        		if(!bIsCheckedID) {
        			str = "ID 중복확인을 하세요";
        		} else if(!bIsCheckedPW) {
        			str = "PW을 확인 하세요";
        		} else {
        			str = "포인트를 확인하세요";
        		}
        		
        		alert.setHeaderText("입력 에러");
        		alert.setContentText(str);
        		alert.show();
        		return false;
    		}
    	}
    	else {
    		str = "모든 폼을 작성하세요";
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning!!");
    		alert.setHeaderText("입력 에러");
    		alert.setContentText(str);
    		alert.show();
    		return false;
    	}
    }
    @FXML
    private void registerNewMember(ActionEvent event) {
        
    	if(isValidInput()) {
    		//register member data callableStatement
    		currentStage.close();
    	}
    }
    
    @FXML
    private void exitMemberRegisterForm(ActionEvent event) {
    	currentStage.close();
    }
    @FXML
    private boolean onBtnClickedCheckID(ActionEvent event) {
    	//is ID exists? callableStatement
    	/*
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Warning !!");
		alert.setHeaderText("중복되는 ID입니다");
		alert.showAndWait();
		*/
    	strID = tfID.getText();
    	bIsCheckedID = true;
    	return bIsCheckedID;
    }
    @FXML
    private boolean onBtnClickedCheckPassword(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING);
    	if(pfPW.getText() == null || pfPW.getText().length() < 8) {
    		alert.setTitle("Warning !!");
    		alert.setHeaderText("입력 에러");
    		alert.setContentText("비밀번호는 8자리 이상 입력하세요");
    		alert.show();
    		bIsCheckedPW = false;
    			return bIsCheckedPW;
    	}
    	
    	if(pfPW.getText().equals(pfPW_check.getText())) {
    		strPW = pfPW.getText();
    		alert.setAlertType(AlertType.CONFIRMATION);
    		alert.setTitle("Confirm");
    		alert.setHeaderText("비밀번호가 일치합니다");
    		alert.setContentText("");
    		alert.show();
    		bIsCheckedPW = true;
    	} else {
    		alert.setAlertType(AlertType.WARNING);
    		alert.setTitle("Confirm");
    		alert.setHeaderText("비밀번호가 일치하지 않습니다");
    		alert.setContentText("");
    		alert.show();
    	}
    	return bIsCheckedPW;
    }
    @FXML
    private void onBtnClickedCheckVolunteerTime(ActionEvent event) {
    	String volunteerTime = tfVolunteerTime.getText();
    	nPoint = Integer.parseInt(volunteerTime) * 10;
    	textPoint.setText(Integer.toString(nPoint));
    	bIsCheckedPoint = true;
    	return;
    }
    @FXML
    void handleCancel(ActionEvent event) {
        currentStage.close();
    }
    
     
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        lblTitle.setText("Register a New Member");
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.currentStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
 
    public void setPerson(Person person){
        this.person = person;
        
    }
    
    public Person getPerson(){
        return person;
    }
}
