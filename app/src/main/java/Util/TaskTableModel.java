package Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author Bruno
 */
public class TaskTableModel extends AbstractTableModel{
    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluida", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList<>();
    
    
           
    @Override
    public int getRowCount() {
        // indica quantas linhas devem ser exibidas
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        // quantas colunas devem ter
        return columns.length ;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return columns[columnIndex];
    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // metodo que retorna um objeto que corresponde uma linha e coluna especifica
        //System.out.println(rowIndex + " " + columnIndex);
        
        switch(columnIndex) {
            case 0: 
                return tasks.get(rowIndex).getName();                
            case 1:
                return tasks.get(rowIndex).getDescription();            
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get(rowIndex).getDeadLine());                                
            case 3:
                return tasks.get(rowIndex).isCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados Não encontrados";
        }              

    } 
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        //permite que uma coluna seje editável
        return columnIndex == 3;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        // retorna qual a classe do componente em determinada coluna (string, int)
        // por pradrao retorna String
        if (tasks.isEmpty()){
            // se a lista de tarefas é vazia
            return Object.class;
        }
        
        return this.getValueAt(0, columnIndex).getClass();
    
    }
    
    @Override
    public void setValueAt(Object avalue, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setCompleted((boolean) avalue);
    }
    

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
  
}
