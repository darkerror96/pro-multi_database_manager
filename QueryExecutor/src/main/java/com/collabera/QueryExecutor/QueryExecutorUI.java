/**
 * 
 */
package com.collabera.QueryExecutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 * @author rutpatel
 *
 */
public class QueryExecutorUI {

	private static JFrame queryFrame = new JFrame("Query Executor");
	private static JPanel getDbPanel, getQueryPanel;

	private static Connection con;

	private static String selectedSchemaName, selectedTableName, databaseUrl, userName, pwd;

	private static JTable outpuTable = new JTable();

	private static JTextField queryJtf;

	private static JPasswordField passwordJtf;

	private static JComboBox<String> tableList;

	private static JPanel bottomPanel;

	private static JButton showSQLBtn;

	public static JFrame buildUI() {

		queryFrame.setSize(700, 500);
		queryFrame.setResizable(false);
		queryFrame.setVisible(true);
		queryFrame.setBackground(new Color(80, 80, 80));

		queryFrame.setLayout(null);

		getDbPanel = buildBPanel();

		queryFrame.add(getDbPanel);

		queryFrame.setContentPane(getDbPanel);
		queryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queryFrame.setVisible(true);
		queryFrame.validate();
		queryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		return queryFrame;
	}

	private static JPanel buildBPanel() {
		JPanel dbPanel = new JPanel();
		dbPanel.setLayout(new BorderLayout(0, 50));
		dbPanel.setBackground(new Color(80, 80, 80));

		// --------------------Name Panel--------------------
		JPanel namePanel = new JPanel();
		namePanel.setBackground(new Color(80, 80, 80));
		Border nameBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 6);
		namePanel.setBorder(nameBorder);

		ImageIcon logo = new ImageIcon("Eagle_Logo.png");

		JLabel nameL = new JLabel("  Query Xecutor", logo, JLabel.LEFT);
		nameL.setForeground(Color.WHITE);
		nameL.setFont(new Font("Serif", Font.BOLD, 50));

		namePanel.add(nameL);

		// --------------------Login Panel--------------------
		JPanel connectPanel = new JPanel();
		connectPanel.setLayout(new BorderLayout(0, 0));
		connectPanel.setBackground(new Color(80, 80, 80));

		// --------------------Login Top Panel--------------------
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(80, 80, 80));

		JLabel welcomeL = new JLabel("Connect To Database Server");
		welcomeL.setForeground(Color.WHITE);
		welcomeL.setFont(new Font("Serif", Font.BOLD, 25));

		welcomePanel.add(welcomeL);

		// --------------------Login Center Panel--------------------
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout(0, 5));
		loginPanel.setBackground(new Color(80, 80, 80));
		Border loginBorder = BorderFactory.createLineBorder(Color.BLACK, 4);
		loginPanel.setBorder(loginBorder);

		// --------------------Login Center DB UN PWD CONNECT Panel--------------------
		JPanel loginUNPanel = new JPanel();
		loginUNPanel.setLayout(null);
		loginUNPanel.setBackground(new Color(80, 80, 80));

		JLabel databaseL = new JLabel("Select Database Type ");
		databaseL.setForeground(Color.WHITE);
		databaseL.setBounds(157, 0, 150, 100);

		String[] dbArr = { "MySQL", "H2", "Oracle" };
		final JComboBox<String> dbList = new JComboBox<String>(dbArr);
		dbList.setBounds(300, 37, 150, 30);
		dbList.setSelectedIndex(0);

		// --------------------Login Labels Panel--------------------
		JLabel hostNameL = new JLabel("Host Name ");
		hostNameL.setForeground(Color.WHITE);
		hostNameL.setBounds(220, 35, 100, 100);

		JLabel portL = new JLabel("Port ");
		portL.setForeground(Color.WHITE);
		portL.setBounds(240, 70, 100, 100);

		JLabel userNameL = new JLabel("Username ");
		userNameL.setForeground(Color.WHITE);
		userNameL.setBounds(225, 125, 100, 100);

		JLabel passwordL = new JLabel("Password ");
		passwordL.setForeground(Color.WHITE);
		passwordL.setBounds(225, 160, 100, 100);

		// --------------------Login TextField Panel--------------------
		final JTextField hostNameJtf = new JTextField("localhost");
		hostNameJtf.setBounds(300, 70, 150, 30);
		hostNameJtf.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				hostNameJtf.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		final JTextField portJtf = new JTextField("3306");
		portJtf.setBounds(300, 105, 100, 30);
		portJtf.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				portJtf.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		final JTextField userNameJtf = new JTextField("root");
		userNameJtf.setBounds(300, 160, 150, 30);
		userNameJtf.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				userNameJtf.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		passwordJtf = new JPasswordField();
		passwordJtf.setBounds(300, 195, 150, 30);
		passwordJtf.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				passwordJtf.setText("");
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		passwordJtf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (Character.toString(e.getKeyChar()).equals("\n")) {
					// System.out.println("Enter Pressed");
				}
			}
		});

		dbList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (dbList.getSelectedItem() == "Oracle") {
					portJtf.setText("1521");
				} else if (dbList.getSelectedItem() == "H2") {
					hostNameJtf.setText("test");
					portJtf.setText("8082");
					userNameJtf.setText("sa");
				} else if (dbList.getSelectedItem() == "MySQL") {
					hostNameJtf.setText("localhost");
					portJtf.setText("3306");
					userNameJtf.setText("root");
				}
			}
		});

		// --------------------Login Connect Button Panel--------------------
		JButton connectBtn = new JButton("Connect");
		connectBtn.setBounds(320, 240, 100, 100);
		connectBtn.setSize(new Dimension(110, 32));

		connectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String hostName = hostNameJtf.getText().trim(), port = portJtf.getText().trim();
				userName = userNameJtf.getText().trim();
				pwd = new String(passwordJtf.getPassword()).trim();

				String dbType = (String) dbList.getSelectedItem();

				if (!hostName.isBlank() && !port.isBlank() && !userName.isBlank()) {

					if (hostName.contains(".com")) {
						showDialog("TIMEOUT. The driver has not received any packets from the server.");
					} else {
						String classForName = "com.mysql.cj.jdbc.Driver";
						databaseUrl = "jdbc:mysql://" + hostName + ":" + port;

						try {
							if (dbType == "Oracle") {
								classForName = "oracle.jdbc.driver.OracleDriver";
								databaseUrl = "jdbc:oracle:thin:@" + hostName + ":" + port + ":xe";
							} else if (dbType == "H2") {
								classForName = "org.h2.Driver";
								databaseUrl = "jdbc:h2:~/" + hostName;
							}

							Class.forName(classForName);

							con = DriverManager.getConnection(databaseUrl + "?serverTimezone=EST5EDT", userName, pwd);

							if (con != null) {
								getQueryPanel = buildQPanel();
								queryFrame.add(getQueryPanel);

								queryFrame.setContentPane(getQueryPanel);
								getQueryPanel.revalidate();

							} else {
								showDialog("Connection to Database Failed.");
							}
						} catch (ClassNotFoundException err) {
							showDialog(err.getMessage());
						} catch (SQLException err) {
							showDialog(err.getMessage());
						}
					}
				} else {
					if (hostName.isBlank()) {
						showDialog("Enter Host Name...");
					} else if (port.isBlank()) {
						showDialog("Enter Port...");
					} else if (userName.isBlank()) {
						showDialog("Enter Username...");
					}
				}
			}
		});

		loginUNPanel.add(databaseL);
		loginUNPanel.add(dbList);
		loginUNPanel.add(hostNameL);
		loginUNPanel.add(hostNameJtf);
		loginUNPanel.add(portL);
		loginUNPanel.add(portJtf);
		loginUNPanel.add(userNameL);
		loginUNPanel.add(userNameJtf);
		loginUNPanel.add(passwordL);
		loginUNPanel.add(passwordJtf);
		loginUNPanel.add(connectBtn);

		loginPanel.add(loginUNPanel, BorderLayout.CENTER);

		connectPanel.add(welcomePanel, BorderLayout.NORTH);
		connectPanel.add(loginPanel, BorderLayout.CENTER);

		dbPanel.add(namePanel, BorderLayout.NORTH);
		dbPanel.add(connectPanel, BorderLayout.CENTER);

		dbPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (hostNameJtf.getText().isBlank()) {
					hostNameJtf.setText("localhost");
				}

				if (portJtf.getText().isBlank()) {
					if (dbList.getSelectedItem() == "Oracle") {
						portJtf.setText("1521");
					} else if (dbList.getSelectedItem() == "H2") {
						portJtf.setText("8082");
					} else if (dbList.getSelectedItem() == "MySQL") {
						portJtf.setText("3306");
					}
				}

				if (userNameJtf.getText().isBlank()) {
					userNameJtf.setText("root");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		return dbPanel;
	}

	private static JPanel buildQPanel() {
		JPanel queryPanel = new JPanel();
		queryPanel.setLayout(new BorderLayout(0, 0));
		queryPanel.setBackground(new Color(80, 80, 80));
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3);

		// --------------------Top Panel--------------------
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(80, 80, 80));
		topPanel.setBorder(border);

		JLabel selecTableL = new JLabel("Select Schema and Table -");
		selecTableL.setForeground(Color.WHITE);
		selecTableL.setFont(new Font("Serif", Font.BOLD, 20));

		// --------------------Populate Dropdown List with Tables--------------------
		int tableCount = 0;
		String[] table = { "TABLE" }, tableListTemp = new String[100], tableListMain = null;
		try {
			DatabaseMetaData dbmd = con.getMetaData();

			ResultSet rsTables = dbmd.getTables(null, null, null, table);
			while (rsTables.next()) {
				tableListTemp[tableCount++] = rsTables.getString(1) + " - " + rsTables.getString(3);
			}

			tableListMain = new String[tableCount];

			for (int i = 0; i < tableCount; i++) {
				tableListMain[i] = tableListTemp[i];
			}
		} catch (SQLException err) {
			showDialog(err.getMessage());
		}

		tableList = new JComboBox<String>(tableListMain);

		selectedSchemaName = ((String) tableList.getSelectedItem()).split("-")[0].trim().toUpperCase();
		selectedTableName = ((String) tableList.getSelectedItem()).split("-")[1].trim().toUpperCase();

		JButton closeConBtn = new JButton("Close Connection");
		closeConBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int dialogResult = JOptionPane.showConfirmDialog(queryFrame, "Would you like to Close the Connection?",
						"Warning", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					con = null;
					selectedSchemaName = null;
					selectedTableName = null;
					databaseUrl = null;
					userName = null;
					pwd = null;

					passwordJtf.setText("");

					queryFrame.setContentPane(getDbPanel);
					getDbPanel.revalidate();
				}
			}
		});

		topPanel.add(selecTableL);
		topPanel.add(tableList);
		topPanel.add(closeConBtn);

		// --------------------Center Panel--------------------
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(10, 20));
		centerPanel.setBackground(new Color(80, 80, 80));

		String[] columnNames = { "                                           This is the Scrollable Output Window" };
		String[][] data = { { "" },
				{ "--------------------------- 4 Easy Steps to Execute Query -------------------------" }, { "" },
				{ "             (1) Select Table from above Dropdown List" }, { "" },
				{ "             (2) Click on below given SQL Query buttons to auto-generate Query" }, { "" },
				{ "             (3) Edit auto-generated SQL Query" }, { "" },
				{ "             (4) Press Execute button to get Output here" }, { "" } };

		outpuTable = new JTable(data, columnNames);

		outpuTable.setForeground(Color.WHITE);
		outpuTable.setBackground(new Color(80, 80, 80));
		outpuTable.setFont(new Font("Serif", Font.PLAIN, 14));
		outpuTable.setGridColor(Color.BLACK);

		outpuTable.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				bottomPanel.setVisible(false);
				showSQLBtn.setVisible(true);
			}
		});

		JScrollPane tableScroll = new JScrollPane(outpuTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Border scrollBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		tableScroll.setBorder(scrollBorder);

		centerPanel.add(tableScroll);

		// --------------------Bottom Panel--------------------
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		bottomPanel.setBackground(new Color(80, 80, 80));
		bottomPanel.setBorder(border);

		// --------------------Bottom Panel 1--------------------
		JPanel bQueryPanel = new JPanel();
		bQueryPanel.setBackground(new Color(80, 80, 80));

		queryJtf = new JTextField(45);

		tableList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedSchemaName = ((String) tableList.getSelectedItem()).split("-")[0].trim().toUpperCase();
				selectedTableName = ((String) tableList.getSelectedItem()).split("-")[1].trim().toUpperCase();

				queryJtf.setText("");
			}
		});

		JButton executeBtn = new JButton("Execute");
		executeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sqlQuery = queryJtf.getText().trim();
				if (!sqlQuery.isBlank()) {
					executeQuery(sqlQuery);
				} else {
					showDialog("SQL Query Field is empty.");
				}
			}
		});

		bQueryPanel.add(queryJtf);
		bQueryPanel.add(executeBtn);

		// --------------------Bottom Panel 2--------------------
		JPanel bBtnPanel = new JPanel();
		bBtnPanel.setBackground(new Color(80, 80, 80));

		JButton insertBtn = new JButton("INSERT");
		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queryJtf.setText("INSERT INTO " + selectedTableName + " VALUES( )");
			}
		});
		JButton updateBtn = new JButton("UPDATE");
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queryJtf.setText("UPDATE " + selectedTableName + " SET " + " WHERE ");
			}
		});
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queryJtf.setText("DELETE FROM " + selectedTableName + " WHERE ");
			}
		});
		JButton selectBtn = new JButton("SELECT");
		selectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queryJtf.setText("SELECT * FROM " + selectedTableName);
			}
		});

		JPanel showSQLBtnPanel = new JPanel();
		showSQLBtnPanel.setLayout(new FlowLayout());
		showSQLBtnPanel.setBackground(new Color(80, 80, 80));

		showSQLBtn = new JButton("Show SQL Query Bar");
		showSQLBtn.setVisible(false);
		showSQLBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bottomPanel.setVisible(true);
				showSQLBtn.setVisible(false);
			}
		});

		showSQLBtnPanel.add(showSQLBtn);

		bBtnPanel.add(insertBtn);
		bBtnPanel.add(updateBtn);
		bBtnPanel.add(deleteBtn);
		bBtnPanel.add(selectBtn);

		bottomPanel.add(bQueryPanel);
		bottomPanel.add(bBtnPanel);

		// --------------------Adding Components--------------------
		queryPanel.add(topPanel, BorderLayout.NORTH);
		queryPanel.add(centerPanel, BorderLayout.CENTER);
		queryPanel.add(showSQLBtnPanel, BorderLayout.EAST);
		queryPanel.add(bottomPanel, BorderLayout.SOUTH);

		return queryPanel;
	}

	private static void executeQuery(String sqlQuery) {

		String sqlQueryTemp = sqlQuery.toUpperCase();

		if (sqlQuery.contains(selectedTableName)) {
			try {
				con = DriverManager.getConnection(databaseUrl + "/" + selectedSchemaName + "?serverTimezone=EST5EDT",
						userName, pwd);
				Statement statement = con.createStatement();

				if (sqlQueryTemp.contains("INSERT") | sqlQueryTemp.contains("UPDATE")
						| sqlQueryTemp.contains("DELETE")) {
					int rowUpdated = statement.executeUpdate(sqlQuery);

					if (rowUpdated == 1) {
						showDialog("1 Row Updated...");
					} else if (rowUpdated == 0) {
						showDialog("No Rows Updated...");
					} else {
						showDialog(rowUpdated + " Rows Updated...");
					}
					showSelectQueryTable(selectedTableName, false, null);
				} else if (sqlQueryTemp.contains("SELECT")) {
					if (sqlQueryTemp.contains("*") & !sqlQueryTemp.contains("JOIN") & !sqlQueryTemp.contains("WHERE")
							& !sqlQueryTemp.contains("GROUP BY")) {
						showSelectQueryTable(selectedTableName, false, null);
					} else {
						showSelectQueryTable(null, true, sqlQuery);
					}
				} else if (sqlQueryTemp.contains("CREATE") | sqlQueryTemp.contains("ALTER")
						| sqlQueryTemp.contains("DROP") | sqlQueryTemp.contains("TRUNCATE")
						| sqlQueryTemp.contains("COMMENT") | sqlQueryTemp.contains("RENAME")) {
					showComplexQueryTable(sqlQuery);
				}
			} catch (SQLException err) {
				showDialog(err.getMessage());
			}
		} else {
			showComplexQueryTable(sqlQuery);
		}
	}

	private static void showSelectQueryTable(String tableName, boolean flag, String sqlQuery) {
		try {
			Statement statement = con.createStatement();
			ResultSet rs;
			if (flag) {
				rs = statement.executeQuery(sqlQuery);
			} else {
				rs = statement.executeQuery("SELECT * FROM " + tableName);
			}
			populateTable(rs);
		} catch (SQLException err) {
			showDialog(err.getMessage());
		}
	}

	private static void showComplexQueryTable(String sqlQuery) {

		String sqlQueryTemp = sqlQuery.toUpperCase();
		try {
			con = DriverManager.getConnection(databaseUrl + "/" + selectedSchemaName + "?serverTimezone=EST5EDT",
					userName, pwd);
			Statement statement = con.createStatement();

			if (sqlQueryTemp.contains("SELECT") | sqlQueryTemp.contains("SHOW")) {
				ResultSet rs = statement.executeQuery(sqlQuery);
				populateTable(rs);
			} else if (sqlQueryTemp.contains("INSERT") | sqlQueryTemp.contains("UPDATE")
					| sqlQueryTemp.contains("DELETE")) {
				int rowUpdated = statement.executeUpdate(sqlQuery);
				if (rowUpdated == 1) {
					showDialog("1 Row Updated...");
				} else if (rowUpdated == 0) {
					showDialog("No Rows Updated...");
				} else {
					showDialog(rowUpdated + " Rows Updated...");
				}
			} else if (sqlQueryTemp.contains("CREATE") | sqlQueryTemp.contains("ALTER") | sqlQueryTemp.contains("DROP")
					| sqlQueryTemp.contains("TRUNCATE") | sqlQueryTemp.contains("COMMENT")
					| sqlQueryTemp.contains("RENAME")) {
				if (statement.execute(sqlQuery)) {

					if (sqlQuery.contains("CREATE")) {
						showDialog("Table Created...");
					} else if (sqlQuery.contains("ALTER")) {
						showDialog("Table Altered...");
					} else if (sqlQuery.contains("DROP")) {
						showDialog("Table Dropped...");
					} else if (sqlQuery.contains("TRUNCATE")) {
						showDialog("Table Truncated...");
					} else if (sqlQuery.contains("RENAME")) {
						showDialog("Table Renamed...");
					}
				}
			} else {
				if (statement.execute(sqlQuery)) {
					showDialog("SQL Query Executed");
					ResultSet rs = statement.executeQuery(sqlQuery);

					if (rs != null) {
						populateTable(rs);
					}
				}
			}
		} catch (SQLException err) {
			showDialog(err.getMessage());
		}
	}

	private static void populateTable(ResultSet resultSet) {

		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			String[] colNames = new String[columnCount];

			for (int i = 1; i <= columnCount; i++) {
				String name = rsmd.getColumnName(i);
				colNames[i - 1] = name;
			}

			HashMap<Integer, String[]> hashMap = new HashMap<Integer, String[]>();

			int counter = 0;
			while (resultSet.next()) {
				counter++;

				String[] rowData = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = resultSet.getString(i);
				}

				hashMap.put(counter, rowData);
			}
			String[][] dataArray = new String[hashMap.size()][columnCount];

			for (int i = 0; i < hashMap.size(); i++) {
				dataArray[i] = hashMap.get(i + 1);
			}

//			System.out.println(Arrays.deepToString(colNames));
//			System.out.println(Arrays.deepToString(dataArray));

			DefaultTableModel dtm = new DefaultTableModel(dataArray, colNames);
			outpuTable.setModel(dtm);

		} catch (SQLException err) {
			showDialog(err.getMessage());
		}
	}

	private static void showDialog(String errorMessage) {

		String errMsg = errorMessage.replace("exception: ", "exception:\n");
		errMsg = errMsg.replace("Failed", "\nFailed");
		errMsg = errMsg.replace("MySQL", "\nMySQL");
		errMsg = errMsg.replace("near", "\nnear");
		errMsg = errMsg.replace("ago. ", "ago.\n");
		errMsg = errMsg.replace("contains ", "contains\n");
		errMsg = errMsg.replace("dependent ", "dependent\n");
		errMsg = errMsg.replace("; ", ";\n");

		JOptionPane.showMessageDialog(queryFrame, errMsg, "SQL Error", JOptionPane.ERROR_MESSAGE);
	}
}
