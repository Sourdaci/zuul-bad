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
    private Room startRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player(startRoom, 0.15F);
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
        barSecreto.addItem("Tamiz-Molecular", 0.01F, false);
        biblioteca.addItem("Kleenex", 0.006F, true);
        biblioteca.addItem("CazaPesca-1664", 0.095F, true);
        servEmpleados.addItem("Papel-higienico-barato", 0.02F, true);
        pasilloTrasero.addItem("Estatua-Marmol", 402.79F, true);
        servPadres.addItem("Papel-higienico-Deluxe", 0.04F, true);
        
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
        
        // Set initial room
        startRoom = recibidor;
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
        System.out.println(GameText.GOODBYE_MESSAGE.getText());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("\n" + GameText.WELCOME_MESSAGE_PART1.getText());
        System.out.println(GameText.WELCOME_MESSAGE_PART2.getText());
        System.out.println(GameText.WELCOME_MESSAGE_PART3.getText() + "\n");
        player.lookRoom();
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
            System.out.println(GameText.UNRECOGNIZED_COMMAND.getText());
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch (commandWord){
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                player.lookRoom();
                break;
            case EAT:
                player.eat();
                player.lookRoom();
                break;
            case BACK:
                player.goBack();
                player.lookRoom();
                break;
            case ITEMS:
                player.listItems();
                break;
            case TAKE:
                takeItem(command);
                break;
            case DROP:
                dropItem(command);
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
        System.out.println(GameText.GAME_HELP.getText());
        parser.getValidCommandWords();
    }

    /**
     * Cuando se procesa un comando en un metodo de jugador, devuelve la segunda palabra si existe
     * 
     * @param command Los comandos introducidos por el jugador
     * @return Segundo comando del jugador, null si NO hay segundo comando
     */
    private String secondWord(Command command){
        String orden;
        if(!command.hasSecondWord()) {
            orden = null;
        }else{
            orden = command.getSecondWord();
        }
        return orden;
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * 
     * @param command Los comandos introducidos, el primero es 'go'
     */
    private void goRoom(Command command) 
    {
        player.goRoom(secondWord(command));
        player.lookRoom();
    }
    
    /**
     * El jugador intenta coger un objeto de la habitacion. Se le indica 
     * el nombre de ese objeto.
     * 
     * @param command Los comandos introducidos, el primero es 'take'
     */
    private void takeItem(Command command){
        player.takeItem(secondWord(command));
    }
    
    /**
     * El jugador intenta dejar un objeto en la habitacion. Se le indica 
     * el nombre de ese objeto.
     * 
     * @param command Los comandos introducidos, el primero es 'drop'
     */
    private void dropItem(Command command){
        player.dropItem(secondWord(command));
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println(GameText.QUIT_WITH_WORDS.getText());
            return false;
        }else{
            return true;  // signal that we want to quit
        }
    }
}
