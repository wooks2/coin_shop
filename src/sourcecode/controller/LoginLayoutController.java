package sourcecode.controller;

import java.io.IOException;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import sourcecode.model.DAOCustomer;
import sourcecode.model.Customer;

public class LoginLayoutController implements Initializable {
	MainApp mainApp; 
	Scene thisScene;
	
	private enum errCode = {SUCCESS = 0, ID_OVERLAPPED = 1, PW_INCORRECT = 2};
	@FXML private TextField tfID;
	@FXML private PasswordField tfPW; 
	@FXML private Button btnLogin;
	@FXML private Button btnRegisterMember;
	@FXML private Button btnExit;
	
	private boolean bID;
	private boolean bPW;
	private int bLoginSuccess;
	
	@FXML
	void onBtnClickedLogin(ActionEvent event) {
		System.out.println("로그인 버튼 클릭");
		String id = tfID.getText();
		String pw = tfPW.getText();
		
		System.out.println(id + ", " + pw);
		bLoginSuccess = funcCheckLogin(id, pw);

		//if login success 단 id, pw따로 확인할것
		if(bLoginSuccess == 1) {
			//ID 중복
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("login Error");
			alert.setHeaderText("아이디가 존재하지 않습니다");
			alert.showAndWait();
		}
		else if(bLoginSuccess == 2){ 
			// PW 오류
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("login Error");
			alert.setHeaderText("비밀번호가 틀렸습니다");
			alert.showAndWait();
		}
		else { // 로그인 성공
			Stage pop = (Stage)btnLogin.getScene().getWindow();
			pop.close();
		}	
	}
	
	   private int funcCheckLogin(String strID, String strPW) {
	    	
			String runP = "{ call C_NAMECHECK(?, ?) }";
		
				try {
					Connection conn = DBConnection.getConnection();
					Statement stmt = conn.createStatement();
					CallableStatement callableStatement = conn.prepareCall(runP.toString());
					callableStatement.setString(1, strID);
					callableStatement.setString(2, strPW);
				
					callableStatement.executeUpdate();	
					
					int check = callableStatement.getInt(2);
					if(check == 1) {
						System.out.println("아이디 중복");
						return 1;	
					}
					else
						System.out.println("실행 성공");
					
				} catch (SQLException e) {
					System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return 0;
				
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
