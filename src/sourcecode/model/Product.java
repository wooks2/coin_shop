package sourcecode.model;

public class Product {
	 private int intId;
	 private int intCustomerId;
	 private String strName;
	 private String strInformation;
	 private int intPrice;
	 private int intCategoryId;
	 private String strCategoryName;
	 private String strProductStatus; // READY, ORDER
	 private int intShipmentId;
	 
	 public Product() {
		 
	 }
	 
	 public int getId() {
	        return intId;
	    } 

	 public void setId(int id) {
	        this.intId = id;
	    }
	 
	 public int getCustomerId() {
	        return intCustomerId;
	    } 

	 public void setCustomerId(int id) {
	        this.intCustomerId = id;
	    } 
	 
	 public int getPrice() {
	        return intPrice;
	    } 

	 public void setPrice(int price) {
	        this.intPrice = price;
	    }
	 
	 public String getProductName() {
		 return strName;
	 }
	 
	 public void setProductName(String name) {
		 this.strName = name;
	 }
	 
	 public String getInfo() {
		 return strInformation;
	 }
	 
	 public void setInfo(String information) {
		 this.strInformation = information;
	 }
	 
	 public String getName() {
		 return strName;
	 }
	 
	 public void setName(String name) {
		 this.strName = name;
	 }
}