package client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;

import com.sun.jdi.InvalidTypeException;

import bus.Account;
import bus.CheckingAccount;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;
import bus.EnumCurrency;
import bus.EnumTransactionType;
import bus.Person;
import bus.RaiseException;
import bus.SavingAccount;
import bus.Transactions;
import data.ConnectionDB;
import data.DataCollection;

public class BankManager {

	private JFrame frmFortisBank;
	Connection connection = ConnectionDB.getInstance();



	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;
	private JTextField textFieldSIN;
	private JTextField textFieldIntAmt;
	private JTextField textFieldExtrafee;
	private JTextField textFieldLimit;
	private JComboBox accountP3CB;
	
	private DefaultTableModel jtModel;
	private JTable jt = new JTable();
	
	private JTable aacsTable = new JTable();
	private DefaultTableModel aacsTableModel;
	
	private DefaultTableModel txnsModel;
	private JTable tracTable = new JTable();
	
	private String account_selected = "";
	private String withdraw_acc_selected = "";
	private String deposit_acc_selected = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BankManager window = new BankManager();
					window.frmFortisBank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public BankManager() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frmFortisBank = new JFrame();
		frmFortisBank.setTitle("Fortis Bank");
		frmFortisBank.setBounds(100, 100, 700, 535);
		frmFortisBank.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmFortisBank.getContentPane().setLayout(null);

		BufferedImage dbStatus = null;
		JLabel dbIcon = null;

		if (connection != null) {
			dbStatus = ImageIO.read(this.getClass().getResource("dbsuccess.png"));
			dbIcon = new JLabel(new ImageIcon(dbStatus));
			dbIcon.setToolTipText("Connected to Database");
		} else {
			dbStatus = ImageIO.read(this.getClass().getResource("dbfailed.png"));
			dbIcon = new JLabel(new ImageIcon(dbStatus));
			dbIcon.setToolTipText("Not connected to Database!");
		}

		dbIcon.setBounds(638, 448, 30, 30);
		dbIcon.setHorizontalAlignment(SwingConstants.CENTER);

		BufferedImage wPic = ImageIO.read(this.getClass().getResource("bank.png"));
		JLabel wIcon = new JLabel(new ImageIcon(wPic));

		wIcon.setBounds(240, 5, 150, 150);
		wIcon.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblBankName = new JLabel("Fortis Bank");
		lblBankName.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblBankName.setBounds(250, 140, 150, 30);

		JButton btnManagerLogout = new JButton("Logout");
		btnManagerLogout.setBounds(580, 11, 90, 32);


		JLabel lblNewLabel = new JLabel("Add Customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 91, 220, 20);

		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstname.setBounds(20, 20, 83, 17);

		textFieldFirstname = new JTextField();
		textFieldFirstname.setBounds(112, 20, 128, 20);

		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastname.setBounds(20, 60, 83, 17);

		textFieldLastname = new JTextField();
		textFieldLastname.setColumns(10);
		textFieldLastname.setBounds(112, 60, 128, 20);

		JLabel lblFirstname_1_1 = new JLabel("SIN");
		lblFirstname_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstname_1_1.setBounds(20, 100, 83, 17);

		textFieldSIN = new JTextField();
		textFieldSIN.setColumns(10);
		textFieldSIN.setBounds(112, 100, 128, 20);

		JLabel lblCheckingAccountDetails_1 = new JLabel("Checking Account Details");
		lblCheckingAccountDetails_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCheckingAccountDetails_1.setBounds(20, 142, 220, 30);

		JLabel lblFirstname_1_1_1 = new JLabel("Initial Amount");
		lblFirstname_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstname_1_1_1.setBounds(20, 189, 83, 17);

		textFieldIntAmt = new JTextField();
		textFieldIntAmt.setColumns(10);
		textFieldIntAmt.setBounds(112, 189, 128, 20);

		JLabel lblFirstname_1_1_1_1 = new JLabel("Extra Fee");
		lblFirstname_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstname_1_1_1_1.setBounds(20, 229, 83, 17);

		textFieldExtrafee = new JTextField();
		textFieldExtrafee.setColumns(10);
		textFieldExtrafee.setBounds(112, 229, 128, 20);

		JLabel lblFirstname_1_1_1_1_1 = new JLabel("Limit");
		lblFirstname_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstname_1_1_1_1_1.setBounds(20, 269, 83, 17);

		textFieldLimit = new JTextField();
		textFieldLimit.setColumns(10);
		textFieldLimit.setBounds(112, 269, 128, 20);

		JButton btnCustomerAdd = new JButton("ADD");
		btnCustomerAdd.setBounds(112, 309, 89, 23);

	

		JLabel lblManager = new JLabel("Hello, Manager");
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblManager.setBounds(10, 11, 180, 30);

		btnCustomerAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					
					
						String firstName = textFieldFirstname.getText();
						String lastName = textFieldLastname.getText();
						long sin = Long.parseLong(textFieldSIN.getText());
						double initialBalance = Double.parseDouble(textFieldIntAmt.getText());
						double extraFee = Double.parseDouble(textFieldExtrafee.getText());
						int limit = Integer.parseInt(textFieldLimit.getText());

						
						Person p = new Person(firstName, lastName);
						CheckingAccount checkingAccount = new CheckingAccount(initialBalance, extraFee, limit);
						Customer cust = new Customer(p, sin, checkingAccount);

						boolean didAdd = DataCollection.addCustomer(cust);
						
						if (didAdd) {
							JOptionPane.showMessageDialog(null, "Customer added!");
							textFieldFirstname.setText("");
							textFieldLastname.setText("");
							textFieldSIN.setText("");
							textFieldIntAmt.setText("");
							textFieldExtrafee.setText("");
							textFieldLimit.setText("");
							
						} else {
							JOptionPane.showMessageDialog(null, "Sorry couldn't add customer, please enter all fields!");
						}

				} catch (RaiseException exep) {
					JOptionPane.showMessageDialog(null, exep.getMessage());
				} 
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please enter all values and in their valid format!!!");
				} 
				
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}

			}
		});


		// Manager View START

		// New Customer
		JScrollPane p1 = new JScrollPane();

		p1.add(lblFirstname);
		p1.add(textFieldFirstname);
		p1.add(lblLastname);
		p1.add(textFieldLastname);

		p1.add(lblFirstname_1_1);
		p1.add(textFieldSIN);
		p1.add(lblCheckingAccountDetails_1);
		p1.add(lblFirstname_1_1_1);
		p1.add(textFieldIntAmt);
		p1.add(lblFirstname_1_1_1_1);
		p1.add(textFieldExtrafee);
		p1.add(lblFirstname_1_1_1_1_1);
		p1.add(textFieldLimit);
		p1.add(btnCustomerAdd);

		p1.setLayout(null);

		JScrollPane p2 = new JScrollPane();

		JLabel lblNewAcc_ForCust = new JLabel("Customer Id");
		lblNewAcc_ForCust.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_ForCust.setBounds(20, 5, 150, 17);

		JTextField tfNewAcc_ForCust = new JTextField();
		tfNewAcc_ForCust.setBounds(150, 5, 150, 23);

		JLabel lblSelectAcc = new JLabel("Select Account");
		lblSelectAcc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSelectAcc.setBounds(20, 30, 100, 17);

		String[] accountStrs = { "  - Select Account -  ", "Checking", "Saving", "Currency", "Credit" };
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(accountStrs);
		JComboBox accountCB = new JComboBox(comboModel);
		accountCB.setBounds(150, 30, 150, 25);

		JLabel lblNewAcc_Balance = new JLabel("Initial Balance");
		lblNewAcc_Balance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_Balance.setBounds(20, 60, 150, 17);

		JTextField tfNewAcc_Bal = new JTextField();
		tfNewAcc_Bal.setBounds(200, 60, 200, 23);

		lblNewAcc_Balance.setVisible(false);
		tfNewAcc_Bal.setVisible(false);

		JLabel lblNewAcc_Extra = new JLabel("Extra Fee");
		lblNewAcc_Extra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_Extra.setBounds(20, 100, 150, 20);

		JTextField tfNewAcc_Extra = new JTextField();
		tfNewAcc_Extra.setBounds(200, 100, 200, 23);

		lblNewAcc_Extra.setVisible(false);
		tfNewAcc_Extra.setVisible(false);

		// Checking Account & Credit Account
		JLabel lblNewAcc_Limit = new JLabel("Limit");
		lblNewAcc_Limit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_Limit.setBounds(20, 140, 150, 17);

		JTextField tfNewAcc_Limit = new JTextField();
		tfNewAcc_Limit.setBounds(200, 140, 200, 23);

		lblNewAcc_Limit.setVisible(false);
		tfNewAcc_Limit.setVisible(false);

		// Saving Account
		JLabel lblNewAcc_Intrate = new JLabel("Interest Rate");
		lblNewAcc_Intrate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_Intrate.setBounds(20, 180, 150, 17);

		JTextField tfNewAcc_Intrate = new JTextField();
		tfNewAcc_Intrate.setBounds(200, 180, 200, 23);

		lblNewAcc_Intrate.setVisible(false);
		tfNewAcc_Intrate.setVisible(false);

		JLabel lblNewAcc_AGain = new JLabel("Annual Gain");
		lblNewAcc_AGain.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_AGain.setBounds(20, 220, 150, 17);

		JTextField tfNewAcc_AGain = new JTextField();
		tfNewAcc_AGain.setBounds(200, 220, 200, 23);

		lblNewAcc_AGain.setVisible(false);
		tfNewAcc_AGain.setVisible(false);

		// Currency Account
		JLabel lblNewAcc_Curry = new JLabel("Choose Currency");
		lblNewAcc_Curry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewAcc_Curry.setBounds(20, 180, 150, 17);

		String[] curncyStrs = { "  - Select Currency -  ", "USD", "INR", "GBP" };
		DefaultComboBoxModel<String> cbModelCry = new DefaultComboBoxModel<String>(curncyStrs);
		JComboBox curncyCB = new JComboBox(cbModelCry);
		curncyCB.setBounds(200, 180, 200, 25);

		lblNewAcc_Curry.setVisible(false);
		curncyCB.setVisible(false);

		JButton btnNewAcc = new JButton("Add Account");
		btnNewAcc.setBounds(200, 260, 150, 30);

		btnNewAcc.setVisible(false);

		accountCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e1) {
				// TODO Auto-generated method stub

				String deselected = "";

				if (e1.getStateChange() == ItemEvent.SELECTED)
					account_selected = (String) e1.getItem();
				else
					deselected = (String) e1.getItem();

				System.out.println(account_selected);

				if (account_selected.equalsIgnoreCase("Checking")) {
					lblNewAcc_Intrate.setVisible(false);
					tfNewAcc_Intrate.setVisible(false);
					lblNewAcc_AGain.setVisible(false);
					tfNewAcc_AGain.setVisible(false);
					lblNewAcc_Curry.setVisible(false);
					curncyCB.setVisible(false);

					lblNewAcc_Balance.setVisible(true);
					tfNewAcc_Bal.setVisible(true);
					lblNewAcc_Extra.setVisible(true);
					tfNewAcc_Extra.setVisible(true);

					lblNewAcc_Limit.setVisible(true);
					tfNewAcc_Limit.setVisible(true);

					btnNewAcc.setVisible(true);

				} else if (account_selected.equalsIgnoreCase("Saving")) {
					lblNewAcc_Limit.setVisible(false);
					tfNewAcc_Limit.setVisible(false);
					lblNewAcc_Curry.setVisible(false);
					curncyCB.setVisible(false);

					lblNewAcc_Balance.setVisible(true);
					tfNewAcc_Bal.setVisible(true);
					lblNewAcc_Extra.setVisible(true);
					tfNewAcc_Extra.setVisible(true);

					lblNewAcc_Intrate.setVisible(true);
					tfNewAcc_Intrate.setVisible(true);
					lblNewAcc_AGain.setVisible(true);
					tfNewAcc_AGain.setVisible(true);

					btnNewAcc.setVisible(true);
				} else if (account_selected.equalsIgnoreCase("Currency")) {

					lblNewAcc_Limit.setVisible(false);
					tfNewAcc_Limit.setVisible(false);
					lblNewAcc_Intrate.setVisible(false);
					tfNewAcc_Intrate.setVisible(false);
					lblNewAcc_AGain.setVisible(false);
					tfNewAcc_AGain.setVisible(false);

					lblNewAcc_Balance.setVisible(true);
					tfNewAcc_Bal.setVisible(true);
					lblNewAcc_Extra.setVisible(true);
					tfNewAcc_Extra.setVisible(true);

					lblNewAcc_Curry.setVisible(true);
					curncyCB.setVisible(true);

					btnNewAcc.setVisible(true);
				} else if (account_selected.equalsIgnoreCase("Credit")) {

					lblNewAcc_Intrate.setVisible(false);
					tfNewAcc_Intrate.setVisible(false);
					lblNewAcc_AGain.setVisible(false);
					tfNewAcc_AGain.setVisible(false);
					lblNewAcc_Curry.setVisible(false);
					curncyCB.setVisible(false);

					lblNewAcc_Balance.setVisible(true);
					tfNewAcc_Bal.setVisible(true);
					lblNewAcc_Extra.setVisible(true);
					tfNewAcc_Extra.setVisible(true);

					lblNewAcc_Limit.setVisible(true);
					tfNewAcc_Limit.setVisible(true);

					btnNewAcc.setVisible(true);

				} else {

					lblNewAcc_Balance.setVisible(false);
					tfNewAcc_Bal.setVisible(false);
					lblNewAcc_Extra.setVisible(false);
					tfNewAcc_Extra.setVisible(false);

					lblNewAcc_Limit.setVisible(false);
					tfNewAcc_Limit.setVisible(false);

					lblNewAcc_Intrate.setVisible(false);
					tfNewAcc_Intrate.setVisible(false);
					lblNewAcc_AGain.setVisible(false);
					tfNewAcc_AGain.setVisible(false);

					lblNewAcc_Curry.setVisible(false);
					curncyCB.setVisible(false);

					btnNewAcc.setVisible(false);
				}
			}

		});

		btnNewAcc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tfNewAcc_ForCust.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter customer id");
					return;
				} else {
					if (account_selected.equalsIgnoreCase("Checking")) {

						try {
							long custId = Long.parseLong(tfNewAcc_ForCust.getText());
							double initialBalance = Double.parseDouble(tfNewAcc_Bal.getText());
							double extraFee = Double.parseDouble(tfNewAcc_Extra.getText());
							int limit = Integer.parseInt(tfNewAcc_Limit.getText());

							CheckingAccount checkingAccount = new CheckingAccount(initialBalance, extraFee, limit);

							boolean didAdd = DataCollection.addAccount(custId, checkingAccount);

							if (didAdd) {
								JOptionPane.showMessageDialog(null, account_selected + " Account added!");
								
								tfNewAcc_Bal.setText("");
								tfNewAcc_Extra.setText("");
								tfNewAcc_Limit.setText("");
							} else {
								JOptionPane.showMessageDialog(null,"Invalid customer id!!!");
							}

						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Invalid values entered!!");
						}catch (Exception exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage());
						}

					} else if (account_selected.equalsIgnoreCase("Saving")) {

						try {
							long custId = Long.parseLong(tfNewAcc_ForCust.getText());
							double initialBalance = Double.parseDouble(tfNewAcc_Bal.getText());
							double extraFee = Double.parseDouble(tfNewAcc_Extra.getText());
							double intRate = Double.parseDouble(tfNewAcc_Intrate.getText());
							double aGain = Double.parseDouble(tfNewAcc_AGain.getText());

							SavingAccount savingAccount = new SavingAccount(initialBalance, extraFee, intRate, aGain);

							System.out
									.println(custId + " " + initialBalance + " " + extraFee + " " + intRate + " " + aGain);
							boolean didAdd = DataCollection.addAccount(custId, savingAccount);

							if (didAdd) {
								JOptionPane.showMessageDialog(null, account_selected + " Account added!");
								tfNewAcc_Bal.setText("");
								tfNewAcc_Extra.setText("");
								tfNewAcc_Limit.setText("");
								tfNewAcc_AGain.setText("");
							} else {
								JOptionPane.showMessageDialog(null,"Invalid customer id!!!");
							}

						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Please enter values in correct format!");
						}catch (Exception exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage());
						}

					} else if (account_selected.equalsIgnoreCase("Currency")) {

						try {
							long custId = Long.parseLong(tfNewAcc_ForCust.getText());
							double initialBalance = Double.parseDouble(tfNewAcc_Bal.getText());
							double extraFee = Double.parseDouble(tfNewAcc_Extra.getText());
							String currency = (String) curncyCB.getSelectedItem();
							EnumCurrency enumCurrency = null;

							if (currency.equalsIgnoreCase("INR")) {
								enumCurrency = EnumCurrency.INR;
							} else if (currency.equalsIgnoreCase("USD")) {
								enumCurrency = EnumCurrency.USD;
							} else if (currency.equalsIgnoreCase("GBP")) {
								enumCurrency = EnumCurrency.GBP;
							} else {
								JOptionPane.showMessageDialog(null, "Please select the currency!");
								enumCurrency = EnumCurrency.UNDEFINED;
								return;
							}

							CurrencyAccount currencyAccount = new CurrencyAccount(initialBalance, extraFee, enumCurrency);

							boolean didAdd = DataCollection.addAccount(custId, currencyAccount);

							if (didAdd) {
								JOptionPane.showMessageDialog(null, account_selected + " Account added!");
								
								tfNewAcc_Bal.setText("");
								tfNewAcc_Extra.setText("");
								curncyCB.setSelectedIndex(0);
								
							} else {
								JOptionPane.showMessageDialog(null,"Invalid customer id!!!");
							}

						}  catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Invalid values enterred!");
						}catch (Exception exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage());
						}

					} else if (account_selected.equalsIgnoreCase("Credit")) {

						try {
							long custId = Long.parseLong(tfNewAcc_ForCust.getText());
							double initialBalance = Double.parseDouble(tfNewAcc_Bal.getText());
							double extraFee = Double.parseDouble(tfNewAcc_Extra.getText());

							CreditAccount checkingAccount = new CreditAccount(initialBalance, extraFee, initialBalance);

							boolean didAdd = DataCollection.addAccount(custId, checkingAccount);

							if (didAdd) {
								JOptionPane.showMessageDialog(null, account_selected + " Account added!");
								tfNewAcc_Bal.setText("");
								tfNewAcc_Extra.setText("");
							} else {
								JOptionPane.showMessageDialog(null,"Invalid customer id!!!");
							}

						}   catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Please recheck the values entered, and try again!");
						}catch (Exception exception) {
							JOptionPane.showMessageDialog(null, exception.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(frmFortisBank, "Choose type of account!");

					}
				}
				

			}

		});

		p2.add(lblSelectAcc);
		p2.add(accountCB);
		p2.add(lblNewAcc_ForCust);
		p2.add(tfNewAcc_ForCust);

		p2.add(lblNewAcc_Balance);
		p2.add(tfNewAcc_Bal);
		p2.add(lblNewAcc_Extra);
		p2.add(tfNewAcc_Extra);

		// checking & credit
		p2.add(lblNewAcc_Limit);
		p2.add(tfNewAcc_Limit);

		// saving
		p2.add(lblNewAcc_Intrate);
		p2.add(tfNewAcc_Intrate);
		p2.add(lblNewAcc_AGain);
		p2.add(tfNewAcc_AGain);

		// currency
		p2.add(lblNewAcc_Curry);
		p2.add(curncyCB);

		// currency add
		p2.add(btnNewAcc);

		p2.setLayout(null);

		JScrollPane p3 = new JScrollPane();

		JLabel lblCustIdP3 = new JLabel("Search Customer");
		lblCustIdP3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustIdP3.setBounds(20, 20, 140, 17);

		JTextField textFieldP3CustId = new JTextField();
		textFieldP3CustId.setBounds(140, 20, 128, 20);

		JButton btnP3Search = new JButton("Search");
		btnP3Search.setBounds(140, 60, 90, 23);

		JLabel lblAccIdsP3 = new JLabel("Accounts");
		lblAccIdsP3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAccIdsP3.setBounds(20, 120, 140, 17);

		String[] accountIdStrs = { "- Select Account -" };
		DefaultComboBoxModel<String> comboModelIds = new DefaultComboBoxModel<String>(accountIdStrs);
		accountP3CB = new JComboBox(comboModelIds);
		accountP3CB.setBounds(140, 120, 150, 25);

		JButton btnP3Delete = new JButton("Delete");
		btnP3Delete.setBounds(140, 160, 90, 23);

		btnP3Search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textFieldP3CustId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter customer ID to search!!!");
					return;
				}else {
					try {
						long custId = Long.parseLong(textFieldP3CustId.getText());

						List<Account> listOfAccounts = DataCollection.getAllCustomerAccounts(custId);

						comboModelIds.removeAllElements();
						comboModelIds.addElement("- Select Account -");

						if (listOfAccounts.size() > 0) {
							for (Account a : listOfAccounts) {
								String acc_type = a.getType();

								comboModelIds.addElement(a.getAccountNum() + " : " + acc_type);
							}

							lblAccIdsP3.setVisible(true);
							accountP3CB.setVisible(true);
							btnP3Delete.setVisible(true);

						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No Accounts found for user!");
							lblAccIdsP3.setVisible(false);
							accountP3CB.setVisible(false);
							btnP3Delete.setVisible(false);
						}

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Please enter valid customer ID!!!");
					}catch (Exception exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
				}
				
			}

		});

		p3.add(lblCustIdP3);
		p3.add(textFieldP3CustId);
		p3.add(btnP3Search);
		p3.add(lblAccIdsP3);
		p3.add(accountP3CB);
		p3.add(btnP3Delete);

		lblAccIdsP3.setVisible(false);
		accountP3CB.setVisible(false);
		btnP3Delete.setVisible(false);

		btnP3Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String acc = (String) accountP3CB.getSelectedItem();
					String[] accSplit = acc.split(" : ");

					long accNum = Long.parseLong(accSplit[0]);

					if (accSplit[1].equalsIgnoreCase("PRIMARY")) {
						JOptionPane.showMessageDialog(frmFortisBank, "You can't delete the primary account!");
					} else {
						boolean didDelete = DataCollection.deleteAccount(accNum);

						if (didDelete) {
							JOptionPane.showMessageDialog(frmFortisBank, "Account deleted!");
							lblAccIdsP3.setVisible(false);
							accountP3CB.setVisible(false);
							btnP3Delete.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "Sorry, Couldn't delete account!");
						}
					}

				}  catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Please enter values in correct format!!!");
				}catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}

		});

		p3.setLayout(null);

		JScrollPane p4 = new JScrollPane();

		JLabel lblCustIdP4 = new JLabel("Customer Id to Delete");
		lblCustIdP4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustIdP4.setBounds(20, 20, 180, 17);

		JTextField textFieldP4CustId = new JTextField();
		textFieldP4CustId.setBounds(180, 20, 128, 20);

		JButton btnP4Delete = new JButton("Delete");
		btnP4Delete.setBounds(180, 60, 90, 23);

		p4.add(lblCustIdP4);
		p4.add(textFieldP4CustId);
		p4.add(btnP4Delete);

		btnP4Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textFieldP4CustId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter customer ID to delete!!!");
					return;
				} else {
					try {
						long custId = Long.parseLong(textFieldP4CustId.getText());

						boolean didDelete = DataCollection.deleteCustomer(custId);

						if (didDelete) {
							JOptionPane.showMessageDialog(frmFortisBank, "Customer removed!");
							textFieldP4CustId.setText("");
						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No Customer found with this ID!!!");
						}

					}  catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Invalid customer ID format!!!");
					}catch (Exception exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
				}
				
			}
		});

		p4.setLayout(null);
		
		jtModel = new DefaultTableModel(0, 0){

		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		String header[] = new String[] { "Customer ID", "First Name", "Last Name",
				"SIN"};
		jtModel.setColumnIdentifiers(header);
	    jt.setModel(jtModel);
	    jt.setCellSelectionEnabled(true);
		jt.setBounds(30, 40, 400, 150);

		JScrollPane p5 = new JScrollPane(jt);

		JTabbedPane managerTp = new JTabbedPane();
		
		
		managerTp.setBounds(10, 70, 600, 400);

		managerTp.add("New Customer", p1);
		managerTp.add("New Account", p2);
		managerTp.add("Close Account", p3);
		managerTp.add("Remove Customer", p4);
		managerTp.add("View All Customers", p5);

		lblManager.setVisible(false);
		btnManagerLogout.setVisible(false);
		managerTp.setVisible(false);

		managerTp.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		        int selectedIndex = tabbedPane.getSelectedIndex();
		        //JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
		        
		        if(selectedIndex == 1) {
		        	
		        } else if(selectedIndex == 2) {
		        	
		        } else if(selectedIndex == 3) {
		        	
		        }
		        else if(selectedIndex == 4) {
		        	try {
		        		if (jtModel.getRowCount() > 0) {
		        		    for (int i = jtModel.getRowCount() - 1; i > -1; i--) {
		        		    	jtModel.removeRow(i);
		        		    }
		        		}
		        		//jtModel.setRowCount(0);
		        		List<Customer> listOfCustomers = DataCollection.getAllCustomers();
		        		
		        		if(listOfCustomers.size() > 0) {
		        			for(Customer c: listOfCustomers) {
		        				Vector<Object> data = new Vector<Object>();
	        			        data.add(c.getIdentificationNum());
	        			        data.add(c.getName().getFirstName());
	        			        data.add(c.getName().getLastName());
	        			        data.add(c.getPinNum());
	        			        
	        			        
	        			        jtModel.addRow(data);
		        			}
		        			
		        		} else {
		        			JOptionPane.showMessageDialog(frmFortisBank, "No customers in bank!");
		        		}
		        	} catch(Exception ex) {
		        		System.out.println(ex.getMessage());
		        	}
		        }
			}
		});
		// END - Manager View

		JLabel lblStartupMessage = new JLabel("Login");
		lblStartupMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartupMessage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblStartupMessage.setBounds(244, 220, 146, 30);

		

		JButton btnManagerLogin = new JButton("MANAGER");
		btnManagerLogin.setBounds(244, 261, 150, 35);

		

		btnManagerLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				managerTp.setSelectedIndex(0);
				lblManager.setVisible(true);
				btnManagerLogout.setVisible(true);
				managerTp.setVisible(true);

				lblStartupMessage.setVisible(false);
				
				btnManagerLogin.setVisible(false);
				wIcon.setVisible(false);
				lblBankName.setVisible(false);
				
			}
		});

		btnManagerLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblManager.setVisible(false);
				btnManagerLogout.setVisible(false);
				managerTp.setVisible(false);

				lblStartupMessage.setVisible(true);
				
				btnManagerLogin.setVisible(true);
				wIcon.setVisible(true);
				lblBankName.setVisible(true);
				
			}
		});

		

		frmFortisBank.getContentPane().add(lblManager);
		
		frmFortisBank.getContentPane().add(btnManagerLogout);
	

		frmFortisBank.getContentPane().add(lblStartupMessage);
		frmFortisBank.getContentPane().add(btnManagerLogin);

		frmFortisBank.getContentPane().add(managerTp);
		
		frmFortisBank.getContentPane().add(lblBankName);
		frmFortisBank.getContentPane().add(wIcon);
		frmFortisBank.getContentPane().add(dbIcon);
	}
}
