
/**
 * Enumeration class GameText - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum GameText{
    // CommandWords
    AVAILABLE_COMMANDS ("Comandos disponibles"),
    
    // Room
    AVAILABLE_DIRECTIONS ("Direcciones disponibles"),
    YOU_ARE_IN_PLACE ("Estas en"),
    NOT_INTERESTING_OBJECTS ("No ves objetos que llamen la atencion"),
    
    // Player
    DIRECTION_NOT_INDICATED ("Si no indicas donde, no te vas a mover"),
    DIRECTION_INVALID ("No atraviesas paredes ni abres ventanas, listo..."),
    WHEN_PLAYER_EATS ("Con las necesidades que tienes, comer puede esperar"),
    WHEN_CANT_GO_BACK ("No puedes volver atras..."),
    INVENTORY_HEADER ("Objetos en el inventario"),
    INVENTORY_LOAD ("Carga actual"),
    INVENTORY_IS_EMPTY ("Inventario vacio"),
    PICKING_INEXISTENT_OBJECT ("Lo intentas coger y no puedes porque es un espejismo"),
    PICKING_OBJECT_AND_OVERWEIGHT ("Si coges eso tambien te partes la espalda"),
    PICKING_OBJECT_SUCCESSFULLY ("Has cogido"),
    PICKING_UNPICKABLE_OBJECT ("Aunque eso fuera util... ¿Para que lo quieres?"),
    PICKING_OBJECT_WITHOUT_OBJECT ("Coger... ¿que? ¿Que coges?"),
    DROPPING_INEXISTENT_OBJECT ("Intentas dejar algo que no tienes. Simplemente brillante..."),
    DROPPING_OBJECT_SUCCESSFULLY ("Dejas en la habitacion "),
    DROPPING_OBJECT_WITHOUT_OBJECT ("Soltar... ¿que? ¿Que dejas?"),
    
    // Game
    GOODBYE_MESSAGE ("Gracias por jugar. Hasta la proxima"),
    WELCOME_MESSAGE_PART1 ("Estas en casa de tu amigo Greg. Quieres... no, NECESITAS ir al servicio"),
    WELCOME_MESSAGE_PART2 ("No te ha dicho donde esta, solo que no entres en otro servicio de la casa"),
    WELCOME_MESSAGE_PART3 ("Escribe '" + Option.HELP.getCommandOrder() + "' si andas perdido"),
    UNRECOGNIZED_COMMAND ("Te quedas en el sitio aguantando"),
    GAME_HELP ("Necesitas encontrar un servicio en condiciones\nen casa de tu colega Greg urgentemente.\n"),
    QUIT_WITH_WORDS ("No te rindas, aun puedes llegar...");
    
    
    private String text;
    
    GameText(String text){
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
}
