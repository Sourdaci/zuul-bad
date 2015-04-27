
/**
 * Write a description of class RoomWithTeleport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoomWithTeleport
{
    // instance variables - replace the example below with your own
    private Room destino;
    private String mensaje;

    /**
     * Constructor for objects of class RoomWithTeleport
     */
    public RoomWithTeleport(Room destino, String mensaje){
        this.destino = destino;
        this.mensaje = mensaje;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public Room getDestinationRoom(){
        return destino;
    }
    
    public String getMensajeTeleport(){
        return mensaje;
    }
}
