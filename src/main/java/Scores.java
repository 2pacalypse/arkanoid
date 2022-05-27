package main.java;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class Scores {
	public static final String[] columnNames = {"First Name", "Last Name","Sport", "# of Years", "Vegetarian"};
	
	private JPanel panel;
	private JTable table;
	private JScrollPane jsp;

	Scores(){
		
		panel = new JPanel();
		table = new JTable();
		
		DefaultTableModel tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tableModel.addColumn("Player");
		tableModel.addColumn("Time");
		tableModel.addColumn("Date");
		tableModel.addColumn("Score");
		table.setModel(tableModel);
		tableModel.addRow(new Object[]{"murat", "a", "b", 100});
		jsp = new JScrollPane(table);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		panel.add(jsp);
		
		
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	

}
