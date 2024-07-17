package com.cac.proyecto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/peliculas")
public class Controladores extends HttpServlet{

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Peliculas pelicula = mapper.readValue(request.getInputStream(), Peliculas.class);
        
            
            String query = "INSERT INTO peliculas (titulo, duracion, genero, imagen) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getDuracion());
            statement.setString(3, pelicula.getGenero());
            statement.setString(4, pelicula.getImagen());
        
            statement.executeUpdate();
        
            
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long idPeli = rs.getLong(1);
                
                
                response.setContentType("application/json");
                String json = mapper.writeValueAsString(idPeli);
                response.getWriter().write(json);
            }
            
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            conexion.close();
        }
        
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         
         response.setHeader("Access-Control-Allow-Origin", "*"); 
         response.setHeader("Access-Control-Allow-Methods", "*");
         response.setHeader("Access-Control-Allow-Headers", "Content-Type"); 
         Conexion conexion = new Conexion(); 
         Connection conn = conexion.getConnection(); 
 
         try {
             
             String query = "SELECT * FROM peliculas";
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
 
             List<Peliculas> peliculas = new ArrayList<>();
 
             
             while (resultSet.next()) {
                 
                 Peliculas pelicula = new Peliculas(
                     resultSet.getInt("idpelicula"),
                     resultSet.getString("titulo"),  
                     resultSet.getString("duracion"),
                     resultSet.getString("genero"),
                     resultSet.getString("imagen")
                 );
                 peliculas.add(pelicula);
             }
 
             ObjectMapper mapper = new ObjectMapper();
             String json = mapper.writeValueAsString(peliculas); 
 
             response.setContentType("application/json");
             response.getWriter().write(json);
         } catch (SQLException e) {
             e.printStackTrace();
             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         } finally {
             conexion.close();
         }
    }
}
