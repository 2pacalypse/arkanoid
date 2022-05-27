package main.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class Scores {
	public static final Vector<String> columnNames = new Vector<String>(Arrays.asList(new String[]{"Player", "Time","Date", "Score"}));
	
	private JPanel panel;
	private JTable table;
	private JScrollPane jsp;

	Scores(){
		
		
		panel = new JPanel();
		table = new JTable();
		jsp = new JScrollPane(table);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		panel.add(jsp);
		
		


		
		
		
		DefaultTableModel tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		table.setModel(tableModel);
		
		Path path = Paths.get("./bin/scores");
		if (Files.exists(path)) {
			load();
		}else {
			tableModel.setColumnIdentifiers(columnNames);
			addScore("murat", 100);
			save();
		}
		

		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );

		
		
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
	
	private void load() {
		try {
			FileInputStream fileIn = new FileInputStream("./bin/scores");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Vector<Vector<?>> obj = (Vector<Vector<?>>) objectIn.readObject();
			objectIn.close();

			((DefaultTableModel) table.getModel()).setDataVector(obj, columnNames);
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		System.out.println("Loaded");
	}
	
	private void save() {
		
		
        try {
        	Object obj = ((DefaultTableModel) table.getModel()).getDataVector();
        	 
            FileOutputStream fileOut = new FileOutputStream("./bin/scores");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void addScore(Object player, Object score) {
		Date dt = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormatter= new SimpleDateFormat("dd MMMM yyyy");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
		
		
		String date = dateFormatter.format(dt);
		String time = timeFormatter.format(dt);
        
        ((DefaultTableModel) table.getModel()).addRow(new Object[]{player, date, time, score});
		
	}
	
	
	

}
