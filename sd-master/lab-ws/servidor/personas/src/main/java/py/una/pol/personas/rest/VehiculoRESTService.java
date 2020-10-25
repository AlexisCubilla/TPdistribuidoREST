package py.una.pol.personas.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.una.pol.personas.model.Persona;
import py.una.pol.personas.model.Ubicacion;
import py.una.pol.personas.model.UbicacionCercana;
import py.una.pol.personas.model.Vehiculo;
import py.una.pol.personas.service.VehiculoService;
@Path("/vehiculos")
@RequestScoped
public class VehiculoRESTService {
    @Inject
    private Logger log;

    @Inject
    VehiculoService vehiculoService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    //public List<Vehiculo> listar() { return vehiculoService.listarVehiculos(); }
    public List<Ubicacion> listarUbicacion() { return vehiculoService.listarUbicaciones(); }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registros")
    public Response registrarVehiculos(Vehiculo v){
        Response.ResponseBuilder builder = null;

        try {

            vehiculoService.registrarVehiculo(v);
            builder = Response.status(201).entity("Movil registrado");

        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registrosUbicacion")
    public Response registrarUbicacion(Ubicacion u ) throws SQLException{
        Response.ResponseBuilder builder = null;
        try {
            vehiculoService.registrarUbicacion(u);
            // Create an "ok" response
            //builder = Response.ok();
            builder = Response.status(201).entity("Ubicacion de movil registrado");

        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    @GET
    @Path("/ubicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ubicacion> ubicacionCercana(UbicacionCercana u) throws SQLException {
        return vehiculoService.Ubicacioncercana(u);
    }


}






