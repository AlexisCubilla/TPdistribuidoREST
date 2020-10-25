package py.una.pol.personas.model;

public class Ubicacion  {

    Vehiculo vehiculo;
    Integer coordX;
    Integer coordY;

    public Ubicacion(){}

    public Ubicacion( Vehiculo vehiculo ,Integer coordX , Integer coordY)
    {
        this.vehiculo= vehiculo;
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public Integer getCoordX() {
        return coordX;
    }
    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }
    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }
    public Integer getCoordY() {
        return coordY;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public Integer getVehiculoid() { return vehiculo.getId();}
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
}

