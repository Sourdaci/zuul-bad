
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
    private boolean pelear, objetoEncontrado;
    private int vida, vidaRestante;
    private int ataque;
    private int defensa;
    private CollectableItem buscado;
    private String fraseObjeto;
    private String direccion;
    private Room origen;
    private Room destino;
    private String victoria, derrota;

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
        objetoEncontrado = false;
        vida = -1;
        vidaRestante = -1;
        ataque = -1;
        defensa = -1;
        direccion = null;
        origen = null;
        destino = null;
    }

    public void setAtributos(int pv, int pa, int pd, String victoria, String derrota){
        pelear = true;
        vida = pv;
        vidaRestante = pv;
        ataque = pa;
        defensa = pd;
        this.victoria = victoria;
        this.derrota = derrota;
    }
    
    public void setObjeto(CollectableItem item, String encontrado, Room origen, String direccion, Room destino){
        buscado = item;
        fraseObjeto = encontrado;
        this.origen = origen;
        this.direccion = direccion;
        this.destino = destino;
    }
    
    public void hablar(Player player){
        if(buscado != null){
            System.out.println(frase);
            if(!objetoEncontrado){
                if(!frase.contains(GameText.NPC_ASK_FOR_OBJECT.getText())){
                    frase = GameText.NPC_ASK_FOR_OBJECT.getText() + ": " + buscado.getDescripcion();
                    System.out.println(frase);
                }
                if(player.enInventario(buscado)){
                    System.out.println(GameText.NPC_GETS_OBJECT.getText());
                    frase = fraseObjeto;
                    objetoEncontrado = true;
                    player.entregarObjetoNPC(buscado);
                    origen.setExit(direccion, destino);
                    System.out.println(frase);
                }
            }
        }else{
            System.out.println(frase);
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public boolean pelea(){
        System.out.println(fraseAtaque);
        return pelear;
    }
    
    public int getVitalidad(){
        return vidaRestante;
    }
    
    public int getVitalidadTotal(){
        return vida;
    }
    
    public int getAtaque(){
        return ataque;
    }
    
    public int getDefensa(){
        return defensa;
    }
    
    public int getID(){
        return iD;
    }
    
    public String getVictoria(){
        return victoria;
    }
    
    public String getDerrota(){
        return derrota;
    }
    
    public void setVidaRestante(int num){
        vidaRestante = num;
    }
    
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s", iD, nombre);
    }
}
