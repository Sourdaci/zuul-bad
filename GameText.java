
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
    ROOM_WITHOUT_OBJECTS ("Esta habitacion no tiene objetos analizables"),
    NOT_INTERESTING_OBJECTS ("No ves objetos que llamen la atencion"),
    
    // Player
    DIRECTION_NOT_INDICATED ("Si no indicas donde, no te vas a mover"),
    DIRECTION_INVALID ("No atraviesas paredes ni abres ventanas, listo..."),
    WHEN_PLAYER_EATS ("Con las necesidades que tienes, comer puede esperar"),
    WHEN_CANT_GO_BACK ("No puedes volver atras..."),
    INVENTORY_HEADER ("Objetos en el inventario"),
    INVENTORY_LOAD ("Carga actual"),
    INVENTORY_IS_EMPTY ("Inventario vacio"),
    PICKING_OBJECT_AND_OVERWEIGHT ("Si coges eso, te sobrecargas"),
    PICKING_OBJECT_SUCCESSFULLY ("Has cogido"),
    PICKING_UNPICKABLE_OBJECT ("Aunque eso fuera util... �Para que lo quieres?"),
    DROPPING_INEXISTENT_OBJECT ("Intentas dejar un objeto que no tienes"),
    DROPPING_OBJECT_SUCCESSFULLY ("Dejas en la habitacion"),
    DROPPING_OBJECT_WITHOUT_OBJECT ("Soltar... �que? No has dicho que quieres soltar"),
    
    // Game
    GOODBYE_MESSAGE ("Gracias por jugar. Hasta la proxima"),
    WELCOME_MESSAGE_PART1 ("Estas en casa de tu amigo Greg. Quieres... no, NECESITAS ir al servicio"),
    WELCOME_MESSAGE_PART2 ("No te ha dicho donde esta, solo que no entres en otro servicio de la casa"),
    WELCOME_MESSAGE_PART3 ("Escribe '" + Option.HELP.getCommandOrder() + "' si andas perdido"),
    UNRECOGNIZED_COMMAND ("Te quedas en el sitio aguantando"),
    GAME_HELP ("Necesitas encontrar un servicio en condiciones\nen casa de tu colega Greg urgentemente.\n"),
    QUIT_WITH_WORDS ("No te rindas, aun puedes llegar..."),
    
    // CollectableItem
    DESCRIPTION_UNAVAILABLE ("No hay detalles ampliables sobre el objeto"),
    
    // Generic
    PICKING_INEXISTENT_OBJECT ("Ese objeto no existe"),
    OBJECT_ID_NOT_NUMBER ("El id del objeto es un numero"),
    PICKING_OBJECT_WITHOUT_OBJECT ("Coger... �que? No has dicho que quieres coger"),
    WEIGHT_UNIT ("Kg");
    
    
    private String text;
    
    GameText(String text){
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
}
