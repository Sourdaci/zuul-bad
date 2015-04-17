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
    private Player player;
        
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
            servEmpleados, pasilloTrasero, dormPadres, servPadres, dormGreg, servGreg;
      
        // create the rooms
        recibidor = new Room("Entrada de la Casa");
        pasillo = new Room("Pasillo Delantero");
        dormServicio = new Room("Habitaciones de los Empleados");
        salaEstar = new Room("Salon");
        barSecreto = new Room("Bar oculto de los padres de Greg.\nHay MUCHO alcohol aqui...");
        biblioteca = new Room("Biblioteca. Muchos libros");
        cocina = new Room("Cocina. La cena huele bien...\nPero la naturaleza te llama IMPERIOSAMENTE");
        comedor = new Room("Comedor. Estan preparando la mesa para cenar");
        servEmpleados = new Room("'Aliviaderos' de los Empleados. Huele a muerto. Y mucho");
        pasilloTrasero = new Room("Pasillo Trasero");
        dormPadres = new Room("Dormitorio de los padres de Greg");
        servPadres = new Room("Servicio de los padres de Greg");
        dormGreg = new Room("Dormitorio de Greg");
        servGreg = new Room("Servicio de Greg");
        
        // add objects to rooms
        biblioteca.addItem("Kleenex", 0.006F);
        biblioteca.addItem("Caza y Pesca 1664", 0.095F);
        servEmpleados.addItem("Papel higienico de una capa", 0.02F);
        servPadres.addItem("Papel higienico triple capa", 0.04F);
        
        // initialise room exits
        recibidor.setExit("north", pasillo);
        pasillo.setExit("north", comedor);
        pasillo.setExit("east", salaEstar);
        pasillo.setExit("south", recibidor);
        pasillo.setExit("west", dormServicio);
        dormServicio.setExit("north", cocina);
        dormServicio.setExit("east", pasillo);
        salaEstar.setExit("north", biblioteca);
        salaEstar.setExit("southeast", barSecreto);
        salaEstar.setExit("west", pasillo);
        barSecreto.setExit("northwest", salaEstar);
        biblioteca.setExit("south", salaEstar);
        cocina.setExit("north", servEmpleados);
        cocina.setExit("east", comedor);
        cocina.setExit("south", dormServicio);
        comedor.setExit("north", pasilloTrasero);
        comedor.setExit("south", pasillo);
        comedor.setExit("west", cocina);
        servEmpleados.setExit("south", cocina);
        pasilloTrasero.setExit("north", dormGreg);
        pasilloTrasero.setExit("east", dormPadres);
        pasilloTrasero.setExit("south", comedor);
        dormPadres.setExit("south", servPadres);
        dormPadres.setExit("west", pasilloTrasero);
        servPadres.setExit("north", dormPadres);
        dormGreg.setExit("south", pasilloTrasero);
        dormGreg.setExit("west", servGreg);
        servGreg.setExit("east", dormGreg);

        // Create Player
        player = new Player(recibidor);
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
        switch (commandWord){
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "look":
                printLocationInfo();
                break;
            case "eat":
                player.eat();
                printLocationInfo();
                break;
            case "back":
                player.goBack();
                printLocationInfo();
                break;
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
        parser.getValidCommandWords();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        String direction;
        if(!command.hasSecondWord()) {
            direction = null;
        }else{
            direction = command.getSecondWord();
        }
        player.goRoom(direction);
        printLocationInfo();
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
        player.lookRoom();
    }
}
