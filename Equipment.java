
/**
 * Equipo del juego: World Of Greg
 * 
 * @author Sourdaci
 * @version 2015-04-30 01
 */
public class Equipment
{
    // instance variables - replace the example below with your own
    private String nombre;
    private String descripcion;
    private int bonoAtaque;
    private int bonoDefensa;
    private boolean tipoEquipo;
    private static int currentID = 1;
    private int iD;
    

    /**
     * Crea un equipo para el jugador del juego
     * 
     * @param nombre Nombre del equipo
     * @param desc Descripcion del equipo
     * @param at Ataque que proporciona el equipo
     * @param def Defensa que proporciona el equipo
     * @param tipo true si es arma, false si es armadura
     */
    public Equipment(String nombre, String desc, int at, int def, boolean tipo)
    {
        this.nombre = nombre;
        descripcion = desc;
        bonoAtaque = at;
        bonoDefensa = def;
        tipoEquipo = tipo;
        iD = currentID;
        currentID++;
        // Manias persecutorias
        if (currentID > 12 && currentID < 14){
            currentID++;
        }
    }

    /**
     * Devuelve el ID del equipo
     * 
     * @return ID
     */
    public int getID(){
        return iD;
    }
    
    /**
     * Devuelve el bono de ataque que proporciona el equipo
     * 
     * @return Ataque del equipo
     */
    public int getBonoAtaque(){
        return bonoAtaque;
    }
    
    /**
     * Devuelve el bono de defensa que proporciona el equipo
     * 
     * @return Defensa del equipo
     */
    public int getBonoDefensa(){
        return bonoDefensa;
    }
    
    /**
     * Indica si el equipo es un arma o no
     * 
     * @return true si es arma, false si es armadura
     */
    public boolean esArma(){
        return tipoEquipo;
    }
    
    /**
     * Devuelve la descripcion del equipo
     * 
     * @return Descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Devuelve las caracteristicas del equipo en formato texto
     * 
     * @return Representacion textual del equipo
     */
    public String toString(){
        String cadena;
        if(tipoEquipo){
            cadena = GameText.EQUIPMENT_IS_WEAPON.getText();
        }else{
            cadena = GameText.EQUIPMENT_IS_ARMOR.getText();
        }
        return String.format(">> (%s id %3d) %s", cadena, iD, nombre);
    }
}
