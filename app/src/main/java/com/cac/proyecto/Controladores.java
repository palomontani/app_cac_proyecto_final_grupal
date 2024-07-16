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
        
            
            String query = "INSERT INTO peliculas (titulo, genero, duracion, imagen) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getGenero());
            statement.setString(3, pelicula.getDuracion());
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
        
         // Configurar cabeceras CORS
         response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
         response.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
         response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
         Conexion conexion = new Conexion();  // Crear una nueva conexión a la base de datos
         Connection conn = conexion.getConnection();  // Obtener la conexión establecida
 
         try {
             // Consulta SQL para seleccionar todas las películas de la tabla 'peliculas'
             String query = "SELECT * FROM peliculas";
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query);  // Ejecutar la consulta y obtener los resultados
 
             List<Peliculas> peliculas = new ArrayList<>();  // Crear una lista para almacenar objetos Pelicula
 
             // Iterar sobre cada fila de resultados en el ResultSet
             while (resultSet.next()) {
                 // Crear un objeto Pelicula con los datos de cada fila
                 Peliculas pelicula = new Peliculas(
                     resultSet.getInt("id_pelicula"),
                     resultSet.getString("titulo"),  
                     resultSet.getString("genero"),
                     resultSet.getString("duracion"),
                     resultSet.getString("imagen")
                 );
                 peliculas.add(pelicula);  // Agregar el objeto Pelicula a la lista
             }
 
             ObjectMapper mapper = new ObjectMapper();  // Crear un objeto ObjectMapper para convertir objetos Java a JSON
             String json = mapper.writeValueAsString(peliculas);  // Convertir la lista de películas a formato JSON
 
             response.setContentType("application/json");  // Establecer el tipo de contenido de la respuesta como JSON
             response.getWriter().write(json);  // Escribir el JSON en el cuerpo de la respuesta HTTP
         } catch (SQLException e) {
             e.printStackTrace();  // Imprimir el error en caso de problemas con la base de datos
             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Configurar el código de estado de la respuesta HTTP como 500 (INTERNAL_SERVER_ERROR)
         } finally {
             conexion.close();  // Cerrar la conexión a la base de datos al finalizar la operación
         }
    }
}
