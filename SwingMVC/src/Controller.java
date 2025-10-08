import javax.swing.JOptionPane;
import java.util.List;

public class Controller {
	 private Model model;
	 private View view;
	 private CashRegister cashRegister;
	 private Display display;

	 public Controller(Model m, View v) {
	  model = m;
	  view = v;
	  initView();
	 }

	 public Controller(CashRegister cashRegister, Display display) {
	  this.cashRegister = cashRegister;
	  this.display = display;
	 }

	 public void initView() {
	  view.getFirstnameTextfield().setText(model.getFirstname());
	  view.getLastnameTextfield().setText(model.getLastname());
	 }

	 public void initController() {
	  view.getFirstnameSaveButton().addActionListener(e -> saveFirstname());
	  view.getLastnameSaveButton().addActionListener(e -> saveLastname());
	  view.getHello().addActionListener(e -> sayHello());
	  view.getBye().addActionListener(e -> sayBye());
	 }

	 public void handleScan(String upcCode) {
	  if (cashRegister != null && display != null) {
	   cashRegister.addItemByUPC(upcCode);

	   // Get the most recently added item
	   List<Product> items = cashRegister.getScannedItems();
	   if (!items.isEmpty()) {
	    Product lastItem = items.get(items.size() - 1);
	    display.addItem(lastItem.toString());
	   }

	   // Update subtotal
	   display.updateSubtotal(cashRegister.getSubtotal());
	  }
	 }

	 private void saveFirstname() {
	  model.setFirstname(view.getFirstnameTextfield().getText());
	  JOptionPane.showMessageDialog(null, "Firstname saved : " + model.getFirstname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }

	 private void saveLastname() {
	  model.setLastname(view.getLastnameTextfield().getText());
	  JOptionPane.showMessageDialog(null, "Lastname saved : " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }

	 private void sayHello() {
	  JOptionPane.showMessageDialog(null, "Hello " + model.getFirstname() + " " + model.getLastname(), "Info", JOptionPane.INFORMATION_MESSAGE);
	 }

	 private void sayBye() {
	  System.exit(0);
	 }

}
