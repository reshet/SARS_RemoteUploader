package base_connectivity;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;


public class MyBooleanTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3642414324068332819L;
	private MSS_RQ_Admin reqHandlerAdmin;
	public static String URL = "http://54.225.84.44/MSS/MainGate.php";
	public static String login = "10000";
	public static String pswd = "mysecret";
	//private String URL = ToolMainWidget.URL;
	public MyBooleanTableCellEditor()
	{
		cbox = new JCheckBox();
		cbox.setBackground(new Color(90,90,90));
		cbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		reqHandlerAdmin = new MSS_RQ_Admin("Tool", login, pswd);
	}
	private JCheckBox cbox;
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int col) {
		cbox.setSelected(!(Boolean)value);
		if (isSelected){cbox.setBackground(table.getSelectionBackground());}
		else {cbox.setBackground(table.getBackground());}
		int ID = Integer.parseInt((String)table.getValueAt(row, 1));
		if (col == 3){
			if (cbox.isSelected())
			{
				MSS_RQ_Request.http_request(reqHandlerAdmin.shareTask(ID),URL);	
			} else
			{
				MSS_RQ_Request.http_request(reqHandlerAdmin.hideTask(ID),URL);
			}
		}
		if (col == 4){
			if (cbox.isSelected())
			{
				MSS_RQ_Request.http_request(reqHandlerAdmin.openTask(ID),URL);	
			} else
			{
				MSS_RQ_Request.http_request(reqHandlerAdmin.closeTask(ID),URL);
			}
		}
		cbox.setSelected((Boolean)value);
		return cbox;
	}
	@Override
	public Object getCellEditorValue() {
		return cbox.isSelected();
	}
}