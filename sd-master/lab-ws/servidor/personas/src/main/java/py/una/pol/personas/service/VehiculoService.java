package py.una.pol.personas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.dao.PersonaDAO;
import py.una.pol.personas.dao.UbicacionDAO;
import py.una.pol.personas.dao.VehiculoDAO;
import py.una.pol.personas.model.Persona;
import py.una.pol.personas.model.Ubicacion;
import py.una.pol.personas.model.UbicacionCercana;
import py.una.pol.personas.model.Vehiculo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class VehiculoService {
    @Inject
    private Logger log;

    @Inject
    private UbicacionDAO Ubidao;

    @Inject
    private VehiculoDAO Vehdao;

    public void registrarVehiculo(Vehiculo v) throws Exception {
        log.info("Insertando Vehiculo: " + v.getTipo() + " " + v.getId());
        try {
            Vehdao.insert(v);
        }catch(Exception e) {
            log.severe("ERROR al insertar el vehiculo: " + e.getLocalizedMessage() );
            throw e;
        }
        log.info("Vehiculo insertado con éxito: " + v.getTipo() + " " + v.getId() );
    }
    public void registrarUbicacion(Ubicacion u) throws Exception {
        log.info("Registrando la ubicacion del vehiculo      "+ "id: "+ u.getVehiculoid() +"       pos:     " +u.getCoordX() + " , " + u.getCoordY());
        try {
            Ubidao.insert(u);
        }catch(Exception e) {
            log.severe("ERROR al registrar la ubicacion del vehiculo " + e.getLocalizedMessage() );
            throw e;
        }
        log.info("Ubicación del Vehiculo insertado con éxito: " + "id: "+ u.getVehiculoid() +"       pos:     " +u.getCoordX() + " , " + u.getCoordY() );
    }
    public List<Vehiculo> listarVehiculos() { return Vehdao.seleccionar(); }
    public List<Ubicacion> listarUbicaciones() { return Ubidao.listarUbicacion(); }

    public List<Ubicacion> Ubicacioncercana(UbicacionCercana u) throws SQLException {

        List<Ubicacion> result = new ArrayList<Ubicacion>();
        try {
            List<Ubicacion> lista = Ubidao.listarUbicacion();

            for ( Ubicacion l : lista ) {
                if( cercania( u.getCoordX(), u.getCoordY(), u.getDistancia(), l.getCoordX(),u.getCoordY() ) )
                {
                    result.add(l);
                }
            }
        }catch(Exception e) {

            throw e;
        }finally {

            return result;
        }

    }
    static boolean cercania(int x, int y, int radio, int x2, int y2)
    {


        if ( Math.pow(x2 - x,2) + Math.pow(y2 - y,2) <= Math.pow(radio,2) )
            return true;
        else
            return false;
    }



}
