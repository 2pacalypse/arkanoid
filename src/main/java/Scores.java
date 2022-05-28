package main.java;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class Scores {
	private Vector<String> columnNames;
	private JPanel panel;
	private JTable table;
	private JScrollPane jsp;

	Scores(){
		columnNames = new Vector<String>(Arrays.asList(Game.SCORE_TABLE_COLUMNS));
		
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
		bg.setIcon(new ImageIcon(getClass().getResource(Game.GAME_BG_PATH)));
		
		panel = new JPanel();

		

		
		
		panel.setLayout(null);
		table = new JTable();
		jsp = new JScrollPane(table);
		SwingUtilities.updateComponentTreeUI(jsp);
		
		jsp.setBorder(BorderFactory.createEmptyBorder());
		jsp.setBounds(100, 50, 400, 400);
		
		panel.add(jsp);
		
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(Game.SCORE_TABLE_ROW_HEIGHT);


		
		DefaultTableModel tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;
			Class[] types = { String.class, String.class, String.class, Integer.class };

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
			
		    @Override
		    public Class getColumnClass(int columnIndex) {
		        return this.types[columnIndex];
		    }
		};
		
		table.setModel(tableModel);
		
		Path path = Paths.get(Game.SCORE_SAVE_PATH);
		if (Files.exists(path)) {
			load();
		}else {
			tableModel.setColumnIdentifiers(columnNames);
			save();
		}
		

		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingUtilities.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);
		table.getTableHeader().setForeground(Color.black);
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().toggleSortOrder(3);
		table.getRowSorter().toggleSortOrder(3);
		table.getTableHeader().setEnabled(false);
		table.setRowSelectionAllowed(false);
		
		
		
		
		getPanel().add(bg);
		getPanel().setComponentZOrder(bg, panel.getComponentCount() - 1);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		
		
		
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
	}
	
	private void save() {
		
		
        try {
        	Object obj = ((DefaultTableModel) table.getModel()).getDataVector();
        	 
            FileOutputStream fileOut = new FileOutputStream("./bin/scores");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
 
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
		
		if (table.getModel().getRowCount() == 10) {
			int min_score = (int) table.getModel().getValueAt(9, 3);
			if ((int) score >= min_score) {
				((DefaultTableModel) (table.getModel())).removeRow(9);
			}
		}
		
		
        
		((DefaultTableModel) table.getModel()).addRow(new Object[]{player, date, time, score});
        save();
		
	}
	
	
	

}
