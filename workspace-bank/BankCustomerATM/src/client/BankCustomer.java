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
import java.sql.SQLException;
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

import bus.Account;
import bus.Customer;
import bus.EnumTransactionType;
import bus.InsufficientAmountException;
import bus.InvalidInputException;
import bus.Transactions;
import data.ConnectionDB;
import data.DataCollection;
	
public class BankCustomer {

	private JFrame frmFortisBank;
	Connection connection = ConnectionDB.getInstance();

	Customer loggedInUser = null;


	
	private JTable aacsTable = new JTable();
	private DefaultTableModel aacsTableModel;
	
	private DefaultTableModel txnsModel;
	private JTable tracTable = new JTable();

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
					BankCustomer window = new BankCustomer();
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
	public BankCustomer() throws IOException {
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

	
		JButton btnCustomerLogout = new JButton("Logout");
		btnCustomerLogout.setBounds(580, 11, 90, 32);


		JLabel lblCustomerIn = new JLabel("Hello, XXXX");
		lblCustomerIn.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblCustomerIn.setBounds(10, 11, 300, 30);
		lblCustomerIn.setVisible(false);

		
		// Customer View START

		// Home
		JScrollPane c1 = new JScrollPane();

		JLabel lblCustFirstname = new JLabel("Firstname");
		lblCustFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustFirstname.setBounds(20, 20, 83, 17);

		JLabel lblCustFirstnameValue = new JLabel();
		lblCustFirstnameValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustFirstnameValue.setBounds(112, 20, 128, 20);

		JLabel lblCustLastname = new JLabel("Lastname");
		lblCustLastname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustLastname.setBounds(20, 60, 83, 17);

		JLabel lblCustLastnameValue = new JLabel();
		lblCustLastnameValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustLastnameValue.setBounds(112, 60, 128, 20);

		JLabel lblCustSIN = new JLabel("SIN");
		lblCustSIN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustSIN.setBounds(20, 100, 83, 17);

		JLabel lblCustSINValue = new JLabel();
		lblCustSINValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustSINValue.setBounds(112, 100, 128, 20);

		JLabel lblChkAccCust = new JLabel("Checking Account Details");
		lblChkAccCust.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChkAccCust.setBounds(10, 142, 220, 25);

		JLabel lblCustAccNum = new JLabel("Account Num");
		lblCustAccNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAccNum.setBounds(20, 189, 83, 17);

		JLabel lblCustAccNumValue = new JLabel();
		lblCustAccNumValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAccNumValue.setBounds(112, 189, 128, 20);

		JLabel lblCustAmt = new JLabel("Amount");
		lblCustAmt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAmt.setBounds(20, 229, 83, 17);

		JLabel lblCustAmtValue = new JLabel();
		lblCustAmtValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAmtValue.setBounds(112, 229, 128, 20);

		JLabel lblCustExtra = new JLabel("Extra Fee");
		lblCustExtra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustExtra.setBounds(20, 269, 83, 17);

		JLabel lblCustExtraValue = new JLabel();
		lblCustExtraValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustExtraValue.setBounds(112, 269, 128, 20);

		c1.add(lblCustFirstname);
		c1.add(lblCustFirstnameValue);
		c1.add(lblCustLastname);
		c1.add(lblCustLastnameValue);

		c1.add(lblCustSIN);
		c1.add(lblCustSINValue);

		c1.add(lblCustAccNum);
		c1.add(lblCustAccNumValue);
		c1.add(lblChkAccCust);
		c1.add(lblCustAmt);
		c1.add(lblCustAmtValue);
		c1.add(lblCustExtra);
		c1.add(lblCustExtraValue);

		c1.setLayout(null);

		// Accounts
		

		aacsTableModel = new DefaultTableModel(0, 0){

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
		
		String accountsHeader[] = new String[] { "Account Num", "Type", "Balance",
				"Extra Fee"};
		aacsTableModel.setColumnIdentifiers(accountsHeader);
		aacsTable.setModel(aacsTableModel);
	    aacsTable.setCellSelectionEnabled(true);
	    aacsTable.setBounds(30, 40, 400, 150);
	

		JScrollPane c2 = new JScrollPane(aacsTable);

		// Withdraw
		JScrollPane c3 = new JScrollPane();

		JLabel lblAccIdsC3 = new JLabel("Choose Account");
		lblAccIdsC3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAccIdsC3.setBounds(20, 20, 200, 17);


		String[] accIds = { "- Select Account -"};
		DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>(accIds);
		JComboBox accountCBC3 = new JComboBox(cbModel);
		accountCBC3.setBounds(240, 20, 200, 25);

		
		JLabel lblAvailAmt = new JLabel("Available Amount");
		lblAvailAmt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailAmt.setBounds(20, 60, 200, 17);

		JLabel lblAvailAmtValue = new JLabel();
		lblAvailAmtValue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailAmtValue.setBounds(240, 60, 200, 17);

		JLabel lblCustAmtWith = new JLabel("Amount");
		lblCustAmtWith.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAmtWith.setBounds(20, 120, 200, 17);

		JTextField textFieldAmtWith = new JTextField();
		textFieldAmtWith.setBounds(240, 120, 200, 20);

		JLabel lblWithDesc = new JLabel("Description");
		lblWithDesc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWithDesc.setBounds(20, 160, 140, 17);

		JTextField textFieldWithDesc = new JTextField();
		textFieldWithDesc.setBounds(240, 160, 200, 20);

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(240, 220, 90, 23);

		c3.add(lblAccIdsC3);
		c3.add(accountCBC3);
		c3.add(lblAvailAmt);
		c3.add(lblAvailAmtValue);
		c3.add(lblCustAmtWith);
		c3.add(textFieldAmtWith);
		c3.add(lblWithDesc);
		c3.add(textFieldWithDesc);
		c3.add(btnWithdraw);

		c3.setLayout(null);
		
		accountCBC3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e1) {
				// TODO Auto-generated method stub
				String deselected = "";
				withdraw_acc_selected = "";
				
				if (e1.getStateChange() == ItemEvent.SELECTED)
					withdraw_acc_selected = (String) e1.getItem();
				else
					deselected = (String) e1.getItem();
				
				try {
					
					String acc = withdraw_acc_selected;
					String[] accSplit = acc.split(" : ");
					
					
					if(acc.isEmpty()) {
						return;
					} else if(withdraw_acc_selected.equalsIgnoreCase("- Select Account -")) {
						return;
					} else {
						long accNum = Long.parseLong(accSplit[0]);
					
						Account account = DataCollection.getAccountByNum(accNum);
						
						if(account != null) {
							lblAvailAmtValue.setText("$ "+account.getAmount());
						}						
					}
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
				
			}
		});
		
		btnWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String acc = withdraw_acc_selected;
					String[] accSplit = acc.split(" : ");

					long accNum = Long.parseLong(accSplit[0]);
					System.out.println("To account = "+accNum);
					String description = textFieldWithDesc.getText();
					
					String[] avAmt = lblAvailAmtValue.getText().split(" ");
					
					Double amount = Double.parseDouble(textFieldAmtWith.getText());
					Double availAmt =  Double.parseDouble(avAmt[1]);
					
					if(amount <= availAmt) {
						double updatedAmt = availAmt - amount;
						
						Transactions withTxn = new Transactions();
						
						withTxn.setFrom_account(accNum);
						withTxn.setTo_account(accNum);
						withTxn.setAmount(amount);
						withTxn.setDescription(description);
						withTxn.setTransactionType(EnumTransactionType.WITHDRAW);
						
						boolean didWithdraw = DataCollection.withdraw(accNum, withTxn, updatedAmt);
						
						if(didWithdraw) {
							JOptionPane.showMessageDialog(frmFortisBank, "Success! You have withdrawn $ "+amount+", Updated Balance is $ "+updatedAmt);
							lblAvailAmtValue.setText("$ "+updatedAmt);
							
							textFieldWithDesc.setText("");
							textFieldAmtWith.setText("");
							
						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "OOPS!!! Unable to withdraw money!");
						}
					} else {
						JOptionPane.showMessageDialog(frmFortisBank, "You don't have enough funds to withdraw!");
					}
					
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Please enter valid amount value!");
				}catch(InsufficientAmountException inSuffEx) {
					JOptionPane.showMessageDialog(null, "You don't have enough funds to Withdraw!");
				}
				catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		});
		
		// Deposit
		JScrollPane c4 = new JScrollPane();

		JLabel lblAccIdsC4 = new JLabel("Choose Account");
		lblAccIdsC4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAccIdsC4.setBounds(20, 20, 200, 17);

		String[] accIdsDepo = { "- Select Account -"};
		DefaultComboBoxModel<String> cbModelDepo = new DefaultComboBoxModel<String>(accIdsDepo);
		JComboBox accountCBC4 = new JComboBox(cbModelDepo);
		accountCBC4.setBounds(240, 20, 200, 25);

		
		JLabel lblAvailAmtDepo = new JLabel("Available Amount");
		lblAvailAmtDepo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailAmtDepo.setBounds(20, 60, 200, 17);

		JLabel lblAvailAmtValueDepo = new JLabel();
		lblAvailAmtValueDepo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailAmtValueDepo.setBounds(240, 60, 200, 17);

		JLabel lblCustDepoAcc = new JLabel("Account Num");
		lblCustDepoAcc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustDepoAcc.setBounds(20, 120, 200, 17);

		JTextField textFieldDepoAcc = new JTextField();
		textFieldDepoAcc.setBounds(240, 120, 200, 20);

		JLabel lblCustAmtDepo = new JLabel("Amount");
		lblCustAmtDepo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustAmtDepo.setBounds(20, 160, 140, 17);

		JTextField textFieldAmtDepo = new JTextField();
		textFieldAmtDepo.setBounds(240, 160, 200, 20);

		JLabel lblDepoDesc = new JLabel("Description");
		lblDepoDesc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepoDesc.setBounds(20, 220, 90, 23);

		JTextField textFieldDepoDesc = new JTextField();
		textFieldDepoDesc.setBounds(240, 220, 200, 23);

		JButton btnDepodraw = new JButton("Deposit");
		btnDepodraw.setBounds(240, 260, 90, 23);

		c4.add(lblAccIdsC4);
		c4.add(accountCBC4);
		c4.add(lblAvailAmtDepo);
		c4.add(lblAvailAmtValueDepo);
		c4.add(lblCustDepoAcc);
		c4.add(textFieldDepoAcc);
		c4.add(lblCustAmtDepo);
		c4.add(textFieldAmtDepo);
		c4.add(lblDepoDesc);
		c4.add(textFieldDepoDesc);
		c4.add(btnDepodraw);

		c4.setLayout(null);
		accountCBC4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e1) {
				// TODO Auto-generated method stub
				String deselected = "";
				deposit_acc_selected = "";
				
				if (e1.getStateChange() == ItemEvent.SELECTED)
					deposit_acc_selected = (String) e1.getItem();
				else
					deselected = (String) e1.getItem();
				
				try {
					
					String acc = deposit_acc_selected;
					String[] accSplit = acc.split(" : ");
					
					
					if(acc.isEmpty()) {
						return;
					} else if(deposit_acc_selected.equalsIgnoreCase("- Select Account -")) {
						return;
					} else {
						long accNum = Long.parseLong(accSplit[0]);
					
						Account account = DataCollection.getAccountByNum(accNum);
						
						if(account != null) {
							lblAvailAmtValueDepo.setText("$ "+account.getAmount());
						}						
					}
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		});
		
		btnDepodraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String acc = deposit_acc_selected;
					String[] accSplit = acc.split(" : ");

					long accNum = Long.parseLong(accSplit[0]);
					System.out.println("To account = "+accNum);
					String description = textFieldDepoDesc.getText();
					
					String[] avAmt = lblAvailAmtValueDepo.getText().split(" ");
					
					Double amount = Double.parseDouble(textFieldAmtDepo.getText());
					Double availAmt =  Double.parseDouble(avAmt[1]);
					long to_account = Long.parseLong(textFieldDepoAcc.getText());
					
					if(amount <= availAmt) {
						double updatedAmt = availAmt - amount;
						
						Transactions depoTxn = new Transactions();
						
						depoTxn.setFrom_account(accNum);
						depoTxn.setTo_account(to_account);
						depoTxn.setAmount(amount);
						depoTxn.setDescription(description);
						depoTxn.setTransactionType(EnumTransactionType.DEPOSIT);
						
						boolean didDeposit = DataCollection.deposit(accNum, depoTxn, updatedAmt);
						
						if(didDeposit) {
							JOptionPane.showMessageDialog(frmFortisBank, "Success! You have transferred $ "+amount+", Updated Balance is $ "+updatedAmt);
							lblAvailAmtValue.setText("$ "+updatedAmt);
							
							textFieldDepoDesc.setText("");
							
							textFieldAmtDepo.setText("");
							textFieldDepoAcc.setText("");
							
						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No Sender Account Number found!!");
						}
					} else {
						JOptionPane.showMessageDialog(frmFortisBank, "You don't have enough funds to Deposit!");
					}
					
				} catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Please enter valid values to initiate deposit transaction!");
				}
				catch(InsufficientAmountException inSuffEx) {
					JOptionPane.showMessageDialog(null, "You don't have enough funds to Deposit!");
				}
				catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Unable to deposit amount!");
				} catch (SQLException sqlEx) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, sqlEx.getMessage());
				} 
			}
		});
		
		// transactions
		txnsModel = new DefaultTableModel(0, 0){

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
		
		String headerTxns[] = new String[] { "Txn Id", "From", "To",
				"Type","Amount","Description","Txn Date"};
		txnsModel.setColumnIdentifiers(headerTxns);
		tracTable.setModel(txnsModel);
		tracTable.setCellSelectionEnabled(true);
		tracTable.setBounds(30, 40, 400, 150);
		

		JScrollPane c5 = new JScrollPane(tracTable);

		JTabbedPane customerTp = new JTabbedPane();

		customerTp.setBounds(10, 70, 600, 400);

		customerTp.add("Home", c1);
		customerTp.add("Accounts", c2);
		customerTp.add("Withdraw", c3);
		customerTp.add("Deposit", c4);
		customerTp.add("Transactions", c5);

		lblCustomerIn.setVisible(false);
		btnCustomerLogout.setVisible(false);
		customerTp.setVisible(false);
		
		customerTp.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		        int selectedIndex = tabbedPane.getSelectedIndex();
		        //JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
		        
		        //accounts
		        if(selectedIndex == 0) {
		        	try {
						
						Customer c = Customer.getCustomerById(loggedInUser.getIdentificationNum());

						lblCustFirstnameValue.setText(c.getName().getFirstName());
						lblCustLastnameValue.setText(c.getName().getLastName());
						lblCustSINValue.setText(c.getPinNum() + "");

						lblCustAccNumValue.setText(c.getCheckingAccount().getAccountNum() + "");
						lblCustAmtValue.setText("$ " + c.getCheckingAccount().getAmount());
						lblCustExtraValue.setText("$ " + c.getCheckingAccount().getExtraFee());

						lblCustomerIn.setVisible(true);
						btnCustomerLogout.setVisible(true);
						customerTp.setVisible(true);
						
					} catch (Exception ex) {
						// JOptionPane.showMessageDialog(frmFortisBank, "Unable login at the moment!");
					}
		        }
		        else if(selectedIndex == 1) {
		        	try {
		        		
		        		if (aacsTableModel.getRowCount() > 0) {
		        		    for (int i = aacsTableModel.getRowCount() - 1; i > -1; i--) {
		        		    	aacsTableModel.removeRow(i);
		        		    }
		        		}
		        		
		        		//aacsTableModel.setRowCount(0);
		        		List<Account> listOfAccounts = DataCollection.getAllCustomerAccounts(loggedInUser.getIdentificationNum());
		        		
		        		if(listOfAccounts.size() > 0) {
		        			for(Account a: listOfAccounts) {
		        				Vector<Object> data = new Vector<Object>();
	        			        data.add(a.getAccountNum());
	        			        data.add(a.getType());
	        			        data.add(a.getAmount());
	        			        data.add(a.getExtraFee());
	        			        
	        			        aacsTableModel.addRow(data);
		        			}
		        			
		        		} else {
		        			JOptionPane.showMessageDialog(frmFortisBank, "No Accounts for user!");
		        		}
		        	} catch(Exception ex) {
		        		JOptionPane.showMessageDialog(frmFortisBank, ex.getMessage());
		        	}
		        	
		       } else if(selectedIndex == 2) {
		    	   //withdraw
		    	   try {
		    		   lblAvailAmtValue.setText("");
		    		   
		    		   textFieldAmtWith.setText("");
		    		   textFieldWithDesc.setText("");
						long custId = loggedInUser.getIdentificationNum();

						List<Account> listOfAccounts = DataCollection.getAllCustomerAccounts(custId);
						
						cbModel.removeAllElements();
						cbModel.addElement("- Select Account -");

						if (listOfAccounts.size() > 0) {
							for (Account a : listOfAccounts) {
								String acc_type = a.getType();

								cbModel.addElement(a.getAccountNum() + " : " + acc_type);
								
							}


						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No Accounts found for user!");
							
						}

					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
		    	   
		       } else if(selectedIndex == 3) {
		    	   //deposit
		    	   try {
		    		   lblAvailAmtValueDepo.setText("");
		    		   textFieldDepoAcc.setText("");
		    		   textFieldAmtDepo.setText("");
		    		   textFieldDepoDesc.setText("");
		    		   
						long custId = loggedInUser.getIdentificationNum();

						List<Account> listOfAccounts = DataCollection.getAllCustomerAccounts(custId);
						
						cbModelDepo.removeAllElements();
						cbModelDepo.addElement("- Select Account -");

						if (listOfAccounts.size() > 0) {
							for (Account a : listOfAccounts) {
								String acc_type = a.getType();

								cbModelDepo.addElement(a.getAccountNum() + " : " + acc_type);
								
							}


						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No Accounts found for user!");
							
						}

					} catch (Exception exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage());
					}
		       }
		        
		        else if(selectedIndex == 4) {
		        	//Transactions
		        	try {
		        		if (txnsModel.getRowCount() > 0) {
		        		    for (int i = txnsModel.getRowCount() - 1; i > -1; i--) {
		        		    	txnsModel.removeRow(i);
		        		    }
		        		}
		        		//txnsModel.setRowCount(0);
		        		
		        		List<Transactions> listOfTransactions = DataCollection.getAllTransactions(loggedInUser.getIdentificationNum());
		        		
		        		if(listOfTransactions.size() > 0) {
		        			
		        			for(Transactions t: listOfTransactions) {
		        				Vector<Object> data = new Vector<Object>();
	        			        
		        				data.add(t.getTransactionNum());
	        			        data.add(t.getFrom_account());
	        			        data.add(t.getTo_account());
	        			        data.add(t.getTransactionType().name());
	        			        data.add(t.getAmount());
	        			        data.add(t.getDescription());
	        			        data.add(t.getTransactionDate().toString());
	        			        
	        			        txnsModel.addRow(data);
		        			}
		        			
		        		} else {
		        			JOptionPane.showMessageDialog(frmFortisBank, "No Transactions made!");
		        		}
		        	} catch(Exception ex) {
		        		JOptionPane.showMessageDialog(frmFortisBank, ex.getMessage());
		        	}
		        }
			}
		});

		// Customer View END

		

		JLabel lblStartupMessage = new JLabel("Login");
		lblStartupMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartupMessage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblStartupMessage.setBounds(240, 220, 154, 30);

		JButton btnCustomerLogin = new JButton("CUSTOMER");
		btnCustomerLogin.setBounds(240, 261, 150, 35);

		btnCustomerLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String custIdStr = JOptionPane.showInputDialog(frmFortisBank, "Customer Id");
				
				if(custIdStr == null || custIdStr.isEmpty()) {
					JOptionPane.showMessageDialog(frmFortisBank, "Please enter Customer ID to Login");
				
				} else {
					
					try {
						long custId = Long.parseLong(custIdStr);
						Customer c = DataCollection.getCustomer(custId);
						
						if (c != null) {
							loggedInUser = c;
							lblStartupMessage.setVisible(false);
							btnCustomerLogin.setVisible(false);
							
							wIcon.setVisible(false);
							lblBankName.setVisible(false);
							
							lblCustomerIn.setText("Hello, " + c.getName().getLastName());
							
							lblCustFirstnameValue.setText(c.getName().getFirstName());
							lblCustLastnameValue.setText(c.getName().getLastName());
							lblCustSINValue.setText(c.getPinNum() + "");
							
							lblCustAccNumValue.setText(c.getCheckingAccount().getAccountNum() + "");
							lblCustAmtValue.setText("$ " + c.getCheckingAccount().getAmount());
							lblCustExtraValue.setText("$ " + c.getCheckingAccount().getExtraFee());
							
							lblCustomerIn.setVisible(true);
							btnCustomerLogout.setVisible(true);
							customerTp.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frmFortisBank, "No User found!");
						}
					}catch(NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Invalid value for Customer ID!!!");
					} catch (Exception ex) {
						 JOptionPane.showMessageDialog(frmFortisBank, "Unable login at the moment!");
					} 
				}
				

			}
		});


		

		btnCustomerLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customerTp.setSelectedIndex(0);
				lblCustomerIn.setVisible(false);
				btnCustomerLogout.setVisible(false);
				customerTp.setVisible(false);

				lblStartupMessage.setVisible(true);
				btnCustomerLogin.setVisible(true);
				
				wIcon.setVisible(true);
				lblBankName.setVisible(true);

				loggedInUser = null;
			}
		});

		
		frmFortisBank.getContentPane().add(lblCustomerIn);

		
		frmFortisBank.getContentPane().add(btnCustomerLogout);

		frmFortisBank.getContentPane().add(lblStartupMessage);
		frmFortisBank.getContentPane().add(btnCustomerLogin);
	

		
		frmFortisBank.getContentPane().add(customerTp);
		frmFortisBank.getContentPane().add(lblBankName);
		frmFortisBank.getContentPane().add(wIcon);
		frmFortisBank.getContentPane().add(dbIcon);
	}
}
