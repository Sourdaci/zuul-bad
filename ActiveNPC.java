
/**
 * Write a description of class PassiveNPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActiveNPC
{
    private static int currentID = 2;
    private int iD;
    private String nombre;
    private String frase, fraseAtaque;
    private boolean pelear;
    private int vida;
    private int ataque;
    private int defensa;
    private CollectableItem buscado;
    private String fraseObjeto;

    /**
     * Constructor for objects of class ActiveNPC
     */
    public ActiveNPC(String nombre, String frase, String fraseAtaque)
    {
        this.nombre = nombre;
        this.frase = frase;
        this.fraseAtaque = fraseAtaque;
        iD = currentID;
        currentID += 2;
        pelear = false;
        buscado = null;
        fraseObjeto = null;
    }

    public void setAtributos(int pv, int pa, int pd){
        pelear = true;
        vida = pv;
        ataque = pa;
        defensa = pd;
    }
    
    public void setObjeto(CollectableItem item, String encontrado){
        buscado = item;
        fraseObjeto = encontrado;
    }
    
    public void hablar(){
        System.out.println(frase);
    }
    
    public boolean pelea(){
        System.out.println(fraseAtaque);
        return pelear;
    }
    
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s", iD, nombre);
    }
}
