package py.una.pol.personas.dao;


import py.una.pol.personas.model.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VehiculoDAO{
    private Logger log;
    public long insert (Vehiculo V) throws SQLException {
        String SQL = "INSERT INTO vehiculo(id_movil, tipo) " + "VALUES(?,?)";

        long id = 0;
        Connection conn = null;

        try
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, V.getId());
            pstmt.setString(2, V.getTipo());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    throw ex;
                }
            }
        } catch (SQLException ex) {
            throw ex;
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }

        return id;
    }
    public List<Vehiculo> seleccionar() {

        String consulta = "SELECT id_movil, tipo FROM vehiculo ORDER BY id_movil";

        List<Vehiculo> lista = new ArrayList<Vehiculo>();

        Connection conn = null;
        try
        {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(consulta);

            while(rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setId(rs.getInt(1));
                v.setTipo(rs.getString(2));

                lista.add(v);
            }

        } catch (SQLException ex) {
            log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return lista;

    }
}