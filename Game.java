/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room recibidor, pasillo, dormServicio, salaEstar, barSecreto, biblioteca, cocina, comedor, 
            servEmpleados, pasilloTrasero, dormPadres, servPadres, secretosCuero, dormGreg, servGreg;
      
        // create the rooms
        recibidor = new Room("Entrada de la Casa");
        pasillo = new Room("Pasillo Delantero");
        dormServicio = new Room("Habitaciones de los Empleados");
        salaEstar = new Room("Salon");
        barSecreto = new Room("Bar oculto de los padres de Greg.\nHay MUCHO alcohol aqui...\nSalida por northwest");
        biblioteca = new Room("Biblioteca. Muchos libros");
        cocina = new Room("Cocina. La cena huele bien...");
        comedor = new Room("Comedor. Estan preparando la mesa para cenar");
        servEmpleados = new Room("'Aliviaderos' de los Empleados. Huele a muerto. Y mucho");
        pasilloTrasero = new Room("Pasillo Trasero");
        dormPadres = new Room("Dormitorio de los padres de Greg");
        servPadres = new Room("Servicio de los padres de Greg");
        secretosCuero = new Room("Una habitacion negra con luces rojas\nHuele a sudor\nTodo es de cuero: ropa, mascaras, latigos, columpios....\nSalida por northwest");
        dormGreg = new Room("Dormitorio de Greg");
        servGreg = new Room("Servicio de Greg");
        
        // initialise room exits
        recibidor.setExits(pasillo, null, null, null, null, null, null, null);
        pasillo.setExits(comedor, null, salaEstar, null, recibidor, null, dormServicio, null);
        dormServicio.setExits(cocina, null, pasillo, null, null, null, null, null);
        salaEstar.setExits(biblioteca, null, null, barSecreto, null, null, pasillo, null);
        barSecreto.setExits(null, null, null, null, null, null, null, salaEstar);
        biblioteca.setExits(null, null, null, null, salaEstar, null, null, null);
        cocina.setExits(servEmpleados, null, comedor, null, dormServicio, null, null, null);
        comedor.setExits(pasilloTrasero, null, null, null, pasillo, null, cocina, null);
        servEmpleados.setExits(null, null, null, null, cocina, null, null, null);
        pasilloTrasero.setExits(dormGreg, null, dormPadres, null, comedor, null, null, null);
        dormPadres.setExits(null, null, null, null, servPadres, null, pasilloTrasero, null);
        servPadres.setExits(dormPadres, null, null, secretosCuero, null, null, null, null);
        secretosCuero.setExits(null, null, null, null, null, null, null, servPadres);
        dormGreg.setExits(null, null, null, null, pasilloTrasero, null, servGreg, null);
        servGreg.setExits(null, null, dormGreg, null, null, null, null, null);

        currentRoom = recibidor;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Estas en casa de tu amigo Greg. Quieres... no, NECESITAS ir al servicio");
        System.out.println("No te ha dicho donde esta, solo que no entres en otro servicio de la casa");
        System.out.println("Escribe 'help' si andas perdido");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Te quedas en el sitio aguantando");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Necesitas encontrar un servicio en condiciones");
        System.out.println("en casa de tu colega Greg urgentemente.");
        System.out.println();
        System.out.println("Tus acciones disponibles son:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Si no indicas donde, no te vas a mover");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("northeast")) {
            nextRoom = currentRoom.neExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("southeast")) {
            nextRoom = currentRoom.seExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("southwest")) {
            nextRoom = currentRoom.swExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }
        if(direction.equals("northwest")) {
            nextRoom = currentRoom.nwExit;
        }

        if (nextRoom == null) {
            System.out.println("No atraviesas paredes ni abres ventanas, listo...");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("No te rindas, aun puedes llegar...");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Muestra por pantalla la sala actual del mapa y sus direcciones disponibles
     */
    private void printLocationInfo(){
        System.out.println("Estas en: " + currentRoom.getDescription());
        System.out.print("Direcciones disponibles: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }
}
