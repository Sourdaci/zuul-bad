
/**
 * Equipo del juego: World Of Greg
 * 
 * @author Sourdaci
 * @version 2015-04-30 01
 */
public class Equipment extends GameObject
{
    private int bonoAtaque;
    private int bonoDefensa;
    private boolean tipoEquipo;

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
        super(nombre, desc);
        bonoAtaque = at;
        bonoDefensa = def;
        tipoEquipo = tipo;
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
     * Devuelve las caracteristicas del equipo en formato texto
     * 
     * @return Representacion textual del equipo
     */
    @Override
    public String toString(){
        String cadena;
        if(tipoEquipo){
            cadena = GameText.EQUIPMENT_IS_WEAPON.getText();
        }else{
            cadena = GameText.EQUIPMENT_IS_ARMOR.getText();
        }
        return String.format(">> (%s id %3d) %s", cadena, getID(), getNombre());
    }
}
