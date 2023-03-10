package controller;

import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;

/**
 *
 * @author Bruno
 */
public class ProjectController {
    
    
    public void saveProject(Project project){
        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt)"
                + "VALUES(?, ?, ?, ?)"; 
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setString(1,project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar um projeto. "+ e.getMessage() + e);
        }finally{
               ConnectionFactory.closeConnection(conn, statement);
        }

    }
    
    public void updateProject(Project project){
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ?"
                + "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setString(1,project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar um projeto. " + e.getMessage()+ e);
        
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
       
        }

    }
    
    public void deletebyIdProject(int projectId){
        String sql = "DELETE FROM projects Where id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setInt(1, projectId);
            statement.execute();
                       
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar um projeto. " + e.getMessage()+ e);
        
        }finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Project> getAllProjects(){
        
        // lista de projetos
        List<Project> listProjects = new ArrayList<>();
        
        String sql = "SELECT * FROM projects";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            // executar o comando e vai trazer a lista de projetos
            resultSet = statement.executeQuery();
            
            
            
            while (resultSet.next()) {
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                listProjects.add(project);
            }
                                 
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os projetos. " + e.getMessage()+ e);
        }finally{
            ConnectionFactory.closeConnection(conn, statement, resultSet);    
        }
    
        return listProjects;
    }
}
