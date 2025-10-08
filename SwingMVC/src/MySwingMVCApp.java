
public class MySwingMVCApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  // Assemble all the pieces of the MVC
		  Model m = new Model("Sylvain", "Saurel");
		  View v = new View("MVC with SSaurel");
		  Controller c = new Controller(m, v);

		  // Create cash register MVC components
		  CashRegister cashRegister = new CashRegister();
		  Display display = new Display();
		  Controller cashRegisterController = new Controller(cashRegister, display);

		  // create scanner and connect to controller
		  Scanner scanner = new Scanner();
		  scanner.setController(cashRegisterController);

		  c.initController();
	}

}
