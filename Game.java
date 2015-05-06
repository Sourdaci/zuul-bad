import java.util.Random;
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
 * @author  Michael KÃ¶lling and David J. Barnes, modified by Sourdaci
 * @version 2015-04-30 18
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room startRoom;
    private boolean playerDead;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player(startRoom, 6F, 100, 20, 15);
        playerDead = false;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room recibidor, pasillo, dormServicio, salaEstar, barSecreto, biblioteca, cocina, comedor, 
            servEmpleados, pasilloTrasero, dormPadres, servPadres, dormGreg, servGreg;
            
        Room cueva, descampado, bosque, barranco, lindeBosque, caminoTierra, puente, laguna, 
            caminoEmpedrado, pasaje, fondoBarranco, riachuelo, cavidad, gruta, caminoMonte, 
            rutaEscarpada, refugio, portal;
            
        Room entradaPueblo, posada, calle, casucha, casa, huerto, pozo;
        
        // Creando la mazmorra
        cueva = new Room("Cueva abrupta\nHay un fuerte olor a humedades", null, null, false);
        descampado = new Room("Entrada de la cueva\nHay algunos huesos mordisqueados", null, null, false);
        bosque = new Room("Entrada a un bosque ennegrecido\nSolo de pensar en entrar se te pone la piel de gallina", null, null, false);
        barranco = new Room("Un barranco bastante profundo\nUna caida significa una muerte segura", null, null, false);
        lindeBosque = new Room("Lateral del bosque ennegrecido\nSigue siendo terrorifico", null, null, false);
        caminoTierra = new Room("Camino de tierra\nAncho y en cuesta", null, null, false);
        puente = new Room("Un firme puente de madera\nAunque los tablones crujen si los pisoteas", null, null, false);
        laguna = new Room("Laguna de aguas cristalinas\nTan cristalinas que podrias ver a un pez echando la quiniela", null, null, false);
        caminoEmpedrado = new Room("Camino burdamente adoquinado\nTe recuerda a la plaza del pueblo", null, null, false);
        pasaje = new Room("Pasaje angosto. Muy angosto.\nEsta plagado de charquitos", null, null, false);
        fondoBarranco = new Room("Fondo del barranco\nEl riachuelo parece alimentar al pozo del pueblo", null, null, false);
        riachuelo = new Room("Rio muy poco profundo. Se pierde en la lejania y en una pared fracturada\nSu lecho tiene un brillo especial", null, null, false);
        cavidad = new Room("Oquedad en una pared pedregosa", null, null, false);
        gruta = new Room("Una cueva debilmente iluminada por la luz que se refleja en las paredes", null, null, false);
        caminoMonte = new Room("Camino que conecta Midgard y el Monte del Destino", null, null, false);
        rutaEscarpada = new Room("Senda estrecha\nOcasionalmente caen piedrecitas\nNo es apta para gente con vertigo", null, null, false);
        refugio = new Room("Una casa tallada en la piedra y bien decorada\nMuy limpia para no tener puertas ni ventanas", null, null, false);
        
        // Creando la casa
        recibidor = new Room("Entrada de la Casa", null, null, false);
        pasillo = new Room("Pasillo Delantero", null, null, false);
        dormServicio = new Room("Habitaciones de los Empleados", null, null, false);
        salaEstar = new Room("Salon", null, null, false);
        barSecreto = new Room("Bar oculto de los padres de Greg.\nHay MUCHO alcohol aqui...", null, null, false);
        biblioteca = new Room("Biblioteca. Muchos libros", null, null, false);
        cocina = new Room("Cocina. La cena huele bien...\nPero la naturaleza te llama IMPERIOSAMENTE", null, null, false);
        comedor = new Room("Comedor. Estan preparando la mesa para cenar", null, null, false);
        servEmpleados = new Room("'Aliviaderos' de los Empleados. Huele a muerto. Y mucho", null, null, false);
        pasilloTrasero = new Room("Pasillo Trasero", null, null, false);
        dormPadres = new Room("Dormitorio de los padres de Greg", null, null, false);
        servPadres = new Room("Servicio de los padres de Greg", null, null, false);
        dormGreg = new Room("Dormitorio de Greg", null, null, false);
        servGreg = new Room("Servicio de Greg", cueva, "Al cerrar la puerta todo se oscurece\n" + 
                "Te sientes aliviado, hasta ver que estas en un lugar desconocido\n" + 
                "Al menos ya no necesitas ir al servicio, y tu ropa interior esta impoluta", false);
        
        // Creando el pueblo
        entradaPueblo = new Room("La entrada a un pueblecito\nEn el desvencijado cartel cuesta leer 'Midgard'", null, null, false);
        posada = new Room("Una posada abandonada llamada 'El burro escarchador'\nEs imposible acceder, la puerta ni se inmuta", null, null, false);
        calle = new Room("La unica calle del pueblo\nMires donde mires, solo hay desolacion", null, null, false);
        casucha = new Room("Una casa derruida, asolada por el paso del tiempo\nY por un meteorito", null, null, false);
        casa = new Room("El unico edificio en condiciones del pueblo\nEl interior esta misticamente iluminado", null, null, false);
        huerto = new Room("Este huerto es el sustento de Vivi\nPuedes distinguir lechugas, zanahorias, tomacco, athelas...", null, null, false);
        pozo = new Room("Pozo. Con su cubo, cuerda, y poleas\nPuede usarse como un ascensor impulsado por biceps", null, null, false);
        
        // Final del juego
        portal = new Room("Portal de vuelta", dormGreg, "Una luz cegadora inunda tu mirada. Al desaparecer, oyes una puerta cerrarse detras tuyo\n" + 
                "Greg esta tirado en su cama. Te mira con gesto de ligera impaciencia y cachondeo\n" + 
                "'Ya era hora, hombre... Espero que te hayas lavado las manos, nos estan esperando'" + 
                "Estas flipando. Todo esta como debe ser. Nunca entenderas que ha ocurrido" + 
                "CONGRATULEISIONS, has salido vivo y con la mente relativamente sana!!!!", true);
        
        // Objetos de la casa
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
        
        // Objetos de la mazmorra
        cueva.addEquipment("Costillar encordado", "Si lo agitas, oyes a las ratas alborotarse", 0, 1, false);
        barranco.addItem("Chapa metalica oxidada", 0.09F, false, "Es una matricula de California que pone 'OUTATIME'");
        CollectableItem flauta = new CollectableItem("Pokeflauta", 0.06F, true, "Sientes la tentacion de tocarla y de echar la siesta");
        lindeBosque.addItemToRoom(flauta);
        caminoEmpedrado.addEquipment("Rama", "UN PALOOOOO!!!!!!!!!!!", 1, 0, true);
        laguna.addItem("Una caja con enseres raros", 16.9F, false, "Destacan las botellas de ron 'BlackPearl' y el LP de 'Saca el whisky Cheli'");
        riachuelo.addItem("Pepitas de oro", 0.02F, true, null);
        gruta.addEquipment("La vara de la verdad", "Da el derecho de gobernar el universo", 96, 67, true);
        rutaEscarpada.addItem("Cartelon indicador", 34.2F, false, "Lo unico legible es... 'Highway to hell'... Que cachondo el tio....");
        
        // Objetos del pueblo
        posada.addEquipment("Coraza de cuero desgastado", "Ha visto dias mejores", 0, 7, false);
        casucha.addItem("Rara roca de aspecto extraterrestre", 0.11F, false, "¿Es un... ¡Nokia 3210 fosilizado!?");
        huerto.addItem("Espantapajaros con un colador por sombrero", 9.54F, false, "Hay un hueco en el pecho que dice 'Insertar corazon aqui'");
        
        // Salidas de la casa
        recibidor.setExit("norte", pasillo);
        pasillo.setExit("norte", comedor);
        pasillo.setExit("este", salaEstar);
        pasillo.setExit("sur", recibidor);
        pasillo.setExit("oeste", dormServicio);
        dormServicio.setExit("norte", cocina);
        dormServicio.setExit("este", pasillo);
        salaEstar.setExit("norte", biblioteca);
        salaEstar.setExit("sureste", barSecreto);
        salaEstar.setExit("oeste", pasillo);
        barSecreto.setExit("noroeste", salaEstar);
        biblioteca.setExit("sur", salaEstar);
        cocina.setExit("norte", servEmpleados);
        cocina.setExit("este", comedor);
        cocina.setExit("sur", dormServicio);
        comedor.setExit("norte", pasilloTrasero);
        comedor.setExit("sur", pasillo);
        comedor.setExit("oeste", cocina);
        servEmpleados.setExit("sur", cocina);
        pasilloTrasero.setExit("norte", dormGreg);
        pasilloTrasero.setExit("este", dormPadres);
        pasilloTrasero.setExit("sur", comedor);
        dormPadres.setExit("sur", servPadres);
        dormPadres.setExit("oeste", pasilloTrasero);
        servPadres.setExit("norte", dormPadres);
        dormGreg.setExit("sur", pasilloTrasero);
        dormGreg.setExit("oeste", servGreg);
        servGreg.setExit("este", dormGreg);
        
        // Salidas de la mazmorra
        cueva.setExit("norte", descampado);
        descampado.setExit("sur", cueva);
        descampado.setExit("oeste", bosque);
        descampado.setExit("noroeste", lindeBosque);
        descampado.setExit("norte", caminoTierra);
        descampado.setExit("este", barranco);
        bosque.setExit("este", descampado);
        bosque.setExit("norte", lindeBosque);
        lindeBosque.setExit("norte", laguna);
        lindeBosque.setExit("este", descampado);
        lindeBosque.setExit("sur", bosque);
        barranco.setExit("oeste", descampado);
        caminoTierra.setExit("sur", descampado);
        caminoTierra.setExit("oeste", laguna);
        caminoTierra.setExit("norte", puente);
        laguna.setExit("este", caminoTierra);
        laguna.setExit("sur", lindeBosque);
        puente.setExit("este", caminoEmpedrado);
        puente.setExit("sur", caminoTierra);
        caminoEmpedrado.setExit("oeste", puente);
        caminoEmpedrado.setExit("este", entradaPueblo);
        pasaje.setExit("arriba", pozo);
        pasaje.setExit("sur", fondoBarranco);
        fondoBarranco.setExit("norte", pasaje);
        fondoBarranco.setExit("este", cavidad);
        fondoBarranco.setExit("cauce", riachuelo);
        riachuelo.setExit("barranco", fondoBarranco);
        cavidad.setExit("oeste", fondoBarranco);
        cavidad.setExit("este", gruta);
        gruta.setExit("oeste", cavidad);
        caminoMonte.setExit("oeste", calle);
        caminoMonte.setExit("norte", rutaEscarpada);
        rutaEscarpada.setExit("sur", caminoMonte);
        rutaEscarpada.setExit("oeste", refugio);
        refugio.setExit("este", rutaEscarpada);
        
        // Salidas del pueblo
        entradaPueblo.setExit("oeste", caminoEmpedrado);
        entradaPueblo.setExit("este", calle);
        calle.setExit("oeste", entradaPueblo);
        calle.setExit("norte", posada);
        calle.setExit("noreste", casucha);
        calle.setExit("este", caminoMonte);
        calle.setExit("sureste", casa);
        posada.setExit("sur", calle);
        casucha.setExit("suroeste", calle);
        casa.setExit("noroeste", calle);
        huerto.setExit("norte", casa);
        huerto.setExit("esquina", pozo);
        pozo.setExit("norte", huerto);
        pozo.setExit("abajo", pasaje);
        
        // PNJ Pasivos
        recibidor.addPassiveNPC(new PassiveNPC("Mayordomo Jeffrey", "Bienvenido a la mansion Banks", "Caballero, le ruego no obstaculice la entrada"));
        dormServicio.addPassiveNPC(new PassiveNPC("Mayordomo tirado en la cama", "nnnnnmnnnnmnnn.....", ".....cinco minutos mas, mama....."));
        cocina.addPassiveNPC(new PassiveNPC("Cocinero", "No molestes", "Toca las gambas y te toco con el cuchillo jamonero"));
        cocina.addPassiveNPC(new PassiveNPC("Cocinera", "Estoy ocupada, chaval...", "Vete antes de que te convierta en un cochinillo al horno"));
        cocina.addPassiveNPC(new PassiveNPC("Cocinero", "Por favor, espera fuera", "Si no estuvieran aqui los jefes te diria algo mas que\n:" + 
            "Aparta esas zarpas del postre, rapaz"));
        
        // PNJ Activos
        ActiveNPC vivi = new ActiveNPC("Vivi", "Soy Vivi, un anciano mago negro que mora aqui\nY tu eres aquel que derrotara al malvado brujo", 
            "¿A que me conservo en forma? Anda, sueltame el biceps antes de que te enamores");
        vivi.setObjeto(flauta, "Encontraras un arma mistica que solo tu puedes usar en\n" + 
            "algun lugar del fondo del barranco\nLlegaras alli por mi pozo");
        vivi.setAbrirPuerta(casa, "sur", huerto, false, true);
        casa.addActiveNPC(vivi);
        ActiveNPC rata = new ActiveNPC("Rata", "IIIIHH....", "IIIIHHHHHH!!!!!!");
        rata.setAtributos(25, 10, 10, "Oyes una voz que dice: Ahora nadie podra detenerme....", "IIIIIiiiiigh........");
        descampado.addActiveNPC(rata);
        ActiveNPC rata2 = new ActiveNPC("Rata", "IIIIHH....", "IIIIHHHHHH!!!!!!");
        rata.setAtributos(25, 10, 10, "Oyes una voz que dice: Ahora nadie podra detenerme....", "IIIIIiiiiigh........");
        pasaje.addActiveNPC(rata2);
        ActiveNPC ganondorf = new ActiveNPC("Ganondorf", "Adelante, elegido, te estoy esperando", "¡Ha llegado tu final");
        ganondorf.setAtributos(300, 90, 59, "Ahora nadie podra detenerme....\nLo ultimo que oyes antes de hundirte en las sombras\n" + 
            "son los desgarradores gritos de dolor de Greg al otro lado del portal", "¡¡¡Naaaaaaarrrrggghhhhh!!!\n" + 
            "Ves como tu rival se desintegra envuelto en llamas verdes retorciendose de dolor");
        ganondorf.setAbrirPuerta(refugio, "portal", portal, true, false);
        refugio.addActiveNPC(ganondorf);
        
            
        
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
            if(!finished){
                finished = player.roomEndsGame();
            }
            if(playerDead){
                finished = true;
            }
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
     * 
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
            case TALK:
                talkWithNPC(command);
                break;
            case FIGHT:
                battle(command);
                break;
            case EQUIP:
                takeEquip(command);
                break;
            case UNEQUIP:
                dropEquip(command);
                break;
            case STATUS:
                player.getStatus();
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
        if(!player.roomEndsGame()){
            player.lookRoom();
        }
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
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void lookItemOnRoom(Command command){
        player.lookItemOnRoom(secondWord(command));
    }
    
    /**
     * El jugador intenta observar un objeto de su inventario. 
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void lookItemOnInventory(Command command){
        player.lookItemOnInventory(secondWord(command));
    }
    
    /**
     * El jugador intenta hablar con un NPC de la zona en la que se encuentra
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void talkWithNPC(Command command){
        player.talkWith(secondWord(command));
    }
    
    /**
     * El jugador intenta combatir con un NPC de la zona en la que se encuentra
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void battle(Command command){
        ActiveNPC enemigo = player.battle(secondWord(command));
        if(enemigo != null){
            battleWithNPC(enemigo);
        }
    }
    
    /**
     * El jugador combate con un NPC activo. 
     * El combate acaba cuando la salud de alguno sea inferior a 1 (considerado muerto).
     * Si el jugador muere la partida acaba
     * Si el NPC muere, se le pide que abra una puerta (si la tiene programada) y se elimina de la zona
     * 
     * @param enemigo El NPC con el que combatir
     */
    private void battleWithNPC(ActiveNPC enemigo){
        Random dado = new Random();
        while(player.getVitalidad() > 0 && enemigo.getVitalidad() > 0){
            int tirada = dado.nextInt(20) + 1;
            int ataquePlayer = player.getAtaque();
            int defensaNPC = enemigo.getDefensa();
            int resultado = 0;
            switch (tirada){
                case 1:
                    System.out.println(GameText.PLAYER_EPIC_FAIL.getText());
                    break;
                case 20:
                    System.out.println(GameText.PLAYER_CRITICAL_ATTACK.getText());
                    resultado = (ataquePlayer * 2 + tirada) - defensaNPC;
                    if(resultado < 1){
                        System.out.println(GameText.PLAYER_CANT_DAMAGE_FOE.getText());
                    }else{
                        System.out.println(GameText.PLAYER_DO_DAMAGE.getText() + ": " + resultado);
                        enemigo.setVidaRestante(enemigo.getVitalidad() - resultado);
                    }
                    break;
                default:
                    resultado = ataquePlayer - defensaNPC + tirada;
                    if(resultado < 1){
                        System.out.println(GameText.PLAYER_CANT_DAMAGE_FOE.getText());
                    }else{
                        System.out.println(GameText.PLAYER_DO_DAMAGE.getText() + ": " + resultado);
                        enemigo.setVidaRestante(enemigo.getVitalidad() - resultado);
                    }
                    break;
            }
            if(enemigo.getVitalidad() > 0){
                tirada = dado.nextInt(20) + 1;
                int ataqueNPC = enemigo.getAtaque();
                int defensaPlayer = player.getDefensa();
                switch (tirada){
                    case 1:
                        System.out.println(GameText.FOE_EPIC_FAIL.getText());
                        break;
                    case 20:
                        System.out.println(GameText.FOE_CRITICAL_ATTACK.getText());
                        resultado = (ataqueNPC * 2 + tirada) - defensaPlayer;
                        if(resultado < 1){
                            System.out.println(GameText.FOE_CANT_DAMAGE_FOE.getText());
                        }else{
                            System.out.println(GameText.FOE_DO_DAMAGE.getText() + ": " + resultado);
                            player.setVidaRestante(player.getVitalidad() - resultado);
                        }
                        break;
                    default:
                        resultado = ataqueNPC - defensaPlayer + tirada;
                        if(resultado < 1){
                            System.out.println(GameText.FOE_CANT_DAMAGE_FOE.getText());
                        }else{
                            System.out.println(GameText.FOE_DO_DAMAGE.getText() + ": " + resultado);
                            player.setVidaRestante(player.getVitalidad() - resultado);
                        }
                        break;
                }
            }
        }
        if(player.getVitalidad() < 1){
            String cadena = enemigo.getVictoria();
            if(cadena != null){
                System.out.println(cadena);
            }
            System.out.println(GameText.PLAYER_LOSE_GAME.getText());
            playerDead = true;
        }else{
            String cadena = enemigo.getDerrota();
            if(cadena != null){
                System.out.println(cadena);
            }
            System.out.println(GameText.PLAYER_WINS_BATTLE.getText() + ": " + enemigo.getNombre());
            enemigo.abrirPuerta();
            player.enemigoDerrotado(enemigo);
        }
    }
    
    /**
     * El jugador intenta coger un equipo de la zona
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void takeEquip(Command command){
        player.takeEquipment(secondWord(command));
    }
    
    /**
     * El jugador intenta dejar un equipo en la zona
     * 
     * @param command Instruccion escrita por el jugador
     */
    private void dropEquip(Command command){
        player.dropEquipment(secondWord(command));
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
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
