package sourcecode.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sourcecode.MainApp;
import sourcecode.model.Person;
public class LoginLayoutController implements Initializable {
	MainApp mainApp; 
	Scene thisScene;
	
	@FXML private TextField tfID;
	@FXML private PasswordField tfPW; 
	@FXML private Button btnLogin;
	@FXML private Button btnRegisterMember;
	@FXML private Button btnExit;
	private boolean bID;
	private boolean bPW;
	private boolean bLoginSuccess;
	@FXML
	void onBtnClickedLogin(ActionEvent event) {
		System.out.println("로그인 버튼 클릭");
		String id = tfID.getText();
		String pw = tfPW.getText();
		
		System.out.println(id + ", " + pw);
		//if login success 단 id, pw따로 확인할것
	
		/*Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("login Error");
		if(!bID) {
			alert.setHeaderText("아이디가 존재하지 않습니다");
		}
		else if(bID && !bPW) {
			alert.setHeaderText("비밀번호가 틀렸습니다");
		}
		alert.showAndWait();*/
		bLoginSuccess = true;
		
		if(bLoginSuccess) {
			Stage pop = (Stage)btnLogin.getScene().getWindow();
			pop.close();
		}
			
			
	}
	
	@FXML
	void onBtnClickedRegisterMember(ActionEvent event) {
		System.out.println("회원가입 버튼 클릭");
		showRegisterMemberDialog("Register");
	}
	

	@FXML
	void onBtnClickedExit(ActionEvent event) {
		Stage pop = (Stage)tfID.getScene().getWindow();
		Platform.exit();
		pop.close();
	}
	
	public boolean isLoginSuccess() {
		return bLoginSuccess;
	}
	
	public MainApp getMainApp() {
		return mainApp;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	
	public boolean showRegisterMemberDialog(String title) {
		thisScene = tfID.getScene();

		try {
			
			FXMLLoader loader = new FXMLLoader();
	
			loader.setLocation(MainApp.class.getResource("view/fxml/RegisterMemberLayout.fxml"));

			AnchorPane page = (AnchorPane) loader.load();
			Stage stageRegMember = new Stage();
			stageRegMember.setTitle(title);
			stageRegMember.initModality(Modality.WINDOW_MODAL);
			stageRegMember.initOwner(thisScene.getWindow());
			
			Scene scene = new Scene(page);
			stageRegMember.setScene(scene);
			stageRegMember.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/adicionar.png")));

			RegisterMemberDialogController controller = loader.getController();
			controller.setTitle(title);
			controller.setDialogStage(stageRegMember);
			//controller.setPerson(person);

			stageRegMember.showAndWait();
			return true;
			//return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		

	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
}
