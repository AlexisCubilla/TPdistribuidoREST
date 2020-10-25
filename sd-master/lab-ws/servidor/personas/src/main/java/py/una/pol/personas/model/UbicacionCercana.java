package py.una.pol.personas.model;

public class UbicacionCercana {
    Integer coordX;
    Integer coordY;
    Integer distancia;

    public UbicacionCercana(){}
    public UbicacionCercana(Integer coordX, Integer coordY, Integer distancia) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.distancia= distancia;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }


}
