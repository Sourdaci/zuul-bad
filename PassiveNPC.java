
/**
 * Write a description of class PassiveNPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PassiveNPC
{
    private static int currentID = 1;
    private int iD;
    private String nombre;
    private String frase, fraseAtaque;

    /**
     * Constructor for objects of class PassiveNPC
     */
    public PassiveNPC(String nombre, String frase, String fraseAtaque)
    {
        this.nombre = nombre;
        this.frase = frase;
        this.fraseAtaque = fraseAtaque;
        iD = currentID;
        currentID += 2;
        if(currentID > 12 && currentID < 14){
            currentID += 2;
        }
    }

    public void hablar(){
        System.out.println(frase);
    }
    
    public boolean pelea(){
        System.out.println(fraseAtaque);
        return false;
    }
    
    public int getID(){
        return iD;
    }
    
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s", iD, nombre);
    }
}
