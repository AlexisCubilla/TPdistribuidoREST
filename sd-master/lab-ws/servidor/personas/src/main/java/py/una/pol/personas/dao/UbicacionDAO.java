package py.una.pol.personas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.model.Ubicacion;
import py.una.pol.personas.model.Vehiculo;
@Stateless
public class UbicacionDAO {


    @Inject
    private Logger log;


    public void insert(Ubicacion u) throws SQLException {

        String SQL;
        SQL = "INSERT INTO ubicacion (coordx,coordy,id_movil) VALUES(?,?,?)";

        Connection conn = null;

        try
        {
            conn = Bd.connect();

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, u.getCoordX() );
            pstmt.setInt(2, u.getCoordY() );
            pstmt.setInt(3, u.getVehiculo().getId() );

            int affectedRows = pstmt.executeUpdate();

            System.out.println("Se ha insertado >>" + u.getCoordX() + ","
                    + u.getCoordY() + "," + u.getVehiculo().getId() );


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
    }

    public List<Ubicacion> listarUbicacion() {
        String consulta = "SELECT * FROM ubicacion";
        Integer id_movil=0;
        String consulta2= "SELECT tipo FROM vehiculo WHERE id_movil=";
        String consulta3="";
        List<Ubicacion> lista = new ArrayList<Ubicacion>();

        Connection conn = null;
        try
        {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(consulta);
            ResultSet rs_movil;
            while(rs.next()) {
                Ubicacion u = new Ubicacion();
                Vehiculo v = new Vehiculo();

                u.setCoordX( rs.getInt(1) );
                u.setCoordY( rs.getInt(2) );
                //
                v.setId( rs.getInt(3) );
                id_movil = v.getId();

                consulta3=consulta2+id_movil;
                rs_movil = conn.createStatement().executeQuery(consulta3);
                rs_movil.next();
                v.setTipo( rs_movil.getString(1) );

                u.setVehiculo( v );
                lista.add( u );
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
