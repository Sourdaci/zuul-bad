
/**
 * Write a description of class PassiveNPC here.
 * 
 * @author Sourdaci
 * @version 2015-04-30 05
 */
public class ActiveNPC
{
    private static int currentID = 1;
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
    boolean abrirAlMorir, abrirSiObjeto;

    /**
     * Crea los NPC activos del juego
     * 
     * @param nombre Nombre del NPC
     * @param frase Texto del NPC cuando se habla con el 
     * @param fraseAtaque Texto del NPC cuando se le intenta atacar
     */
    public ActiveNPC(String nombre, String frase, String fraseAtaque)
    {
        this.nombre = nombre;
        this.frase = frase;
        this.fraseAtaque = fraseAtaque;
        iD = currentID;
        currentID++;
        if(currentID > 12 && currentID < 14){
            currentID++;
        }
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
        abrirAlMorir = false;
        abrirSiObjeto = false;
    }

    /**
     * Indica que el NPC entablara batalla si se le ataca
     * 
     * @param pv Vida del NPC
     * @param pa Ataque del NPC
     * @param pd Defensa del NPC
     * @param victoria Texto del NPC si gana el combate
     * @param derrota Texto del NPC si pierde el combate
     */
    public void setAtributos(int pv, int pa, int pd, String victoria, String derrota){
        pelear = true;
        vida = pv;
        vidaRestante = pv;
        ataque = pa;
        defensa = pd;
        this.victoria = victoria;
        this.derrota = derrota;
    }
    
    /**
     * Indica que el NPC abrira una puerta en una zona del juego al cumplirse un requisito
     * 
     * @param origen Zona donde se abrira la puerta
     * @param direccion Nombre de la puerta
     * @param destino Zona a la que conduce la puerta
     * @param muerte Si la puerta se abre al matar este NPC
     * @param recibe Si la puerta se abre al entregar un objeto a este NPC
     */
    public void setAbrirPuerta(Room origen, String direccion, Room destino, boolean muerte, boolean recibe){
        this.origen = origen;
        this.direccion = direccion;
        this.destino = destino;
        abrirAlMorir = muerte;
        abrirSiObjeto = recibe;
    }
    
    /**
     * Indica el objeto que busca el NPC para ayudar al jugador
     * 
     * @param item El objeto que busca el NPC
     * @param encontrado Frase del NPC cuando se le entregue el objeto
     */
    public void setObjeto(CollectableItem item, String encontrado){
        buscado = item;
        fraseObjeto = encontrado;
    }
    
    /**
     * Se intenta hablar con el NPC. 
     * Su texto varia dependiendo de si busca un objeto y si lo tiene
     * Si el NPC busca un objeto y el Jugador lo tiene, lo cogera y abrira la puerta programada
     * 
     * @param player Jugador (para buscar el objeto en su inventario automaticamente)
     */
    public void hablar(Player player){
        System.out.println(frase);
        if(abrirSiObjeto){
            if(!objetoEncontrado){
                if(!frase.contains(GameText.NPC_ASK_FOR_OBJECT.getText())){
                    frase = GameText.NPC_ASK_FOR_OBJECT.getText() + ": " + buscado.getNombre();
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
        }
    }
    
    /**
     * Devuelve el nombre de este NPC
     * 
     * @return Nombre NPC
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Indica si el NPC es combativo o no para iniciar la pelea
     * 
     * @return true si el NPC va a combatir, false en caso contrario
     */
    public boolean pelea(){
        System.out.println(fraseAtaque);
        return pelear;
    }
    
    /**
     * Devuelve la vida restante del NPC
     * 
     * @return Salud restante del NPC, -1 si el NPC no es combativo
     */
    public int getVitalidad(){
        return vidaRestante;
    }
    
    /**
     * Devuelve la vida maxima del NPC
     * 
     * @return Salud maxima del NPC, -1 si el NPC no es combativo
     */
    public int getVitalidadTotal(){
        return vida;
    }
    
    /**
     * Devuelve el ataque del NPC
     * 
     * @return Ataque del NPC, -1 si no es combativo
     */
    public int getAtaque(){
        return ataque;
    }
    
    /**
     * Devuelve la defensa del NPC
     * 
     * @return Defensa del NPC, -1 si no es combativo
     */
    public int getDefensa(){
        return defensa;
    }
    
    /**
     * Devuelve el ID del NPC
     * 
     * @return ID del NPC
     */
    public int getID(){
        return iD;
    }
    
    /**
     * Devuelve el texto del NPC cuando gana el combate
     * 
     * @return Frase de victoria del NPC
     */
    public String getVictoria(){
        return victoria;
    }
    
    /**
     * Devuelve el texto del NPC cuando pierde el combate
     * 
     * @return Frase de derrota del NPC
     */
    public String getDerrota(){
        return derrota;
    }
    
    /**
     * Si el NPC es combativo y esta programado para abrir una puerta al morir
     */
    public void abrirPuerta(){
        if(abrirAlMorir){
            origen.setExit(direccion, destino);
        }
    }
    
    /**
     * Actualiza la vida restante del NPC
     * 
     * @param num Nuevo valor de salud restante
     */
    public void setVidaRestante(int num){
        vidaRestante = num;
    }
    
    /**
     * Representacion textual sencilla del NPC
     */
    @Override
    public String toString(){
        return String.format(">> (id %3d) %s", iD, nombre);
    }
}
