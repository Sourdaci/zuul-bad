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
 * @author  Michael KÃ¶lling and David J. Barnes
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
            
        Room cueva, descampado, bosque, barranco;
      
        // Creando la mazmorra
        cueva = new Room("Cueva abrupta\nHay un fuerte olor a humedades", null, null);
        descampado = new Room("Entrada de la cueva\nHay algunos huesos mordisqueados", null, null);
        bosque = new Room("Entrada a un bosque ennegrecido\nSolo de pensar en entrar se te pone la piel de gallina", null, null);
        barranco = new Room("Un barranco bastante profundo\nUna caida significa una muerte segura", null, null);
            
        // create the rooms
        recibidor = new Room("Entrada de la Casa", null, null);
        pasillo = new Room("Pasillo Delantero", null, null);
        dormServicio = new Room("Habitaciones de los Empleados", null, null);
        salaEstar = new Room("Salon", null, null);
        barSecreto = new Room("Bar oculto de los padres de Greg.\nHay MUCHO alcohol aqui...", null, null);
        biblioteca = new Room("Biblioteca. Muchos libros", null, null);
        cocina = new Room("Cocina. La cena huele bien...\nPero la naturaleza te llama IMPERIOSAMENTE", null, null);
        comedor = new Room("Comedor. Estan preparando la mesa para cenar", null, null);
        servEmpleados = new Room("'Aliviaderos' de los Empleados. Huele a muerto. Y mucho", null, null);
        pasilloTrasero = new Room("Pasillo Trasero", null, null);
        dormPadres = new Room("Dormitorio de los padres de Greg", null, null);
        servPadres = new Room("Servicio de los padres de Greg", null, null);
        dormGreg = new Room("Dormitorio de Greg", null, null);
        servGreg = new Room("Servicio de Greg", cueva, "Al cerrar la puerta todo se oscurece\nTe sientes aliviado, hasta ver que estas en un lugar desconocido");
        
        // add objects to rooms
        dormServicio.addItem("Bolsa de 'Oregano'", 0.1F, false, "Huele igual que la habitacion del conserje del instituto");
        dormServicio.addItem("Poster de Jay y Bob el Silencioso", 0.05F, false, "Esas gabardinas, ese cinturon de Batman... que estilo");
        barSecreto.addItem("Tamiz Molecular", 0.01F, false, "Hay residuos blancos. ¿Habran hecho magdalenas?");
        biblioteca.addItem("Paquete de Kleenex", 0.006F, true, null);
        biblioteca.addItem("Caza y Pesca num 1664", 0.095F, true, "Anuncian un fascinante articulo sobre pesca con mosca en el Artico");
        cocina.addItem("Caja de cerillas", 0.095F, true, "Tiene 4 cerillas y bastante uso, pero parece funcionar");
        comedor.addItem("Delicadas servilletas de seda", 0.009F, false, "Con bordados florales y las iniciales de la familia");
        servEmpleados.addItem("Papel higienico de 1 capa", 0.02F, true, "El aspecto de papel de lija esta bien conseguido");
        pasilloTrasero.addItem("Estatua de marmol macizo", 402.79F, true, null);
        dormPadres.addItem("Espada de Juego de Tronos", 12.3F, false, "Numero 482. ¡Solo se han hecho 8000 de este modelo!");
        servPadres.addItem("Papel higienico Deluxe 4 capas", 0.04F, true, "Es como acariciar una nube");
        dormGreg.addItem("Batman contra 'El tio del Monopoly'", 0.08F, true, "Controvertido numero 922, Batman pierde al poker por no tener suficientes fondos");
        dormGreg.addItem("Cuadro de Sheldon Cooper", 2.6F, false, "En la placa pone:\nVe al norte... estas en un bosque\nVe al norte... estas en un bosque\nVe al norte... estas en un bosque\n¡Porras, me he perdido!");
        
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
            case LOOK_CLOSELY:
                lookItemOnRoom(command);
                break;
            case EXAMINE:
                lookItemOnInventory(command);
                break;
            case AUX:
                printDetailedHelp();
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
     * Print out lots of help information.
     */
    private void printDetailedHelp() 
    {
        System.out.println(GameText.GAME_HELP.getText());
        parser.getDetailedCommandWords();
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
     * el ID de ese objeto.
     * 
     * @param command Los comandos introducidos, el primero es 'take'
     */
    private void takeItem(Command command){
        player.takeItem(secondWord(command));
    }
    
    /**
     * El jugador intenta dejar un objeto en la habitacion. Se le indica 
     * el ID de ese objeto.
     * 
     * @param command Los comandos introducidos, el primero es 'drop'
     */
    private void dropItem(Command command){
        player.dropItem(secondWord(command));
    }
    
    /**
     * El jugador intenta observar un objeto de la habitacion. 
     * Se le indica el ID de ese objeto
     * 
     */
    private void lookItemOnRoom(Command command){
        player.lookItemOnRoom(secondWord(command));
    }
    
    /**
     * El jugador intenta observar un objeto de su inventario. 
     * Se le indica el ID de ese objeto
     * 
     */
    private void lookItemOnInventory(Command command){
        player.lookItemOnInventory(secondWord(command));
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
