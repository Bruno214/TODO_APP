
package Util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author Bruno
 */
public class DeadLineColumnCellRenderer extends DefaultTableCellRenderer{
    // classe para renderizar a tabela (celulas)de acordo com o meu projeto
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                          boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label; 
        label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // CENTRALIZAR O TEXTO NO CENTRO
        label.setHorizontalAlignment(CENTER);
        TaskTableModel taskModel = (TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);

        if (task.getDeadLine().after(new Date())) {
            label.setBackground(Color.green);
            
        
        }else{
            label.setBackground(Color.red);
        }
        
        return label;
    }   
}
