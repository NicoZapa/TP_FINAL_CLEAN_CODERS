import java.util.*;

public class MesaJuego {

    MazoUno mazo;
    List<Jugador> jugadores = new ArrayList<>();

    public CartaUno getCartaActiva() {
        return cartaActiva;
    }

    private CartaUno cartaActiva;
    String colorCarta;
    int turnoJugador;

    public void setCartaJugada(boolean cartaJugada) {
        this.cartaJugada = cartaJugada;
    }

    private boolean cartaJugada;

    public boolean isCartaJugada() {
        return cartaJugada;
    }

    public MesaJuego() {

        //MAZO
        mazo = new MazoUno();

        //JUGADORES
        jugadores.add(new Jugador("Jugador 1"));
        jugadores.add(new Jugador("Jugador 2"));


        //REPARTIMOS LAS CARTAS A CADA JUGADOR
        repartir();
            cartaActiva = mazo.darCarta();
            while(cartaActiva.getNumero() == 13 || cartaActiva.getNumero() == 14) {
                cartaActiva = mazo.darCarta();
                break;
            }
    }

    private void repartir() {
        for (Jugador jugador : jugadores) { //CANTIDAD DE JUGADORES
            int CARTASxJUGADOR = 7;
            for (int j = 0; j < CARTASxJUGADOR; j++) { //CANTIDAD DE CARTAS x JUGADOR
                jugador.setCartasMano(mazo.darCarta());
            }

        }

    }

    public void setPilaDescarte(CartaUno carta) {
        mazo.setPilaDescarte(carta);
    }

    public void mostrarCartaActiva() {
        System.out.println("\nCARTA ACTIVA \n" + cartaActiva);
    }

    public void setTurnoJugador(int turno) {
        this.turnoJugador = turno;
    }

    public void efectoCarta(CartaUno carta, int turnoJugador) {
        if (carta.isCarta0al9(cartaActiva)) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            cartaJugada = true;
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else if (carta.isCambioColor()) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            cartaActiva.setColor(colorCarta);
            cartaJugada = true;
            efectoCambioColor();
            cartaActiva.setNumero(14);
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else if (carta.isMasDos(cartaActiva)) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            efectoMasDos();
            cartaActiva.setNumero(11);
            cartaJugada = true;
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else if (carta.isMasCuatro()) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            efectoMasCuatro();
            cartaActiva.setNumero(13);
            cartaJugada = true;
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else if (carta.isBloqueoTurno(cartaActiva)) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            cartaActiva.setNumero(12);
            cartaJugada = true;
            efectoBloqueoCambioSentido();
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else if (carta.isCambioSentido(cartaActiva)) {
            this.setPilaDescarte(cartaActiva);
            cartaActiva = carta;
            cartaActiva.setNumero(10);
            cartaJugada = true;
            efectoBloqueoCambioSentido();
            eliminarCarta(jugadores.get(turnoJugador - 1), carta);
        } else {
            System.out.println("La carta " + carta + " no se puede jugar. Intente con otra.");
            cartaJugada = false;
        }
    }

    public void eliminarCarta(Jugador jugador, CartaUno carta) {
        jugador.getCartasMano().remove(carta);
    }

    public void efectoMasDos() {
        System.out.println("USASTE UN MÁS DOS!");
        int cartas = 0;
        while (cartas < 2) {
            if (turnoJugador == 1) {
                jugadores.get(1).setCartasMano(mazo.darCarta());
            } else if (turnoJugador == 2) {
                jugadores.get(0).setCartasMano(mazo.darCarta());
            }
            cartas++;
        }
    }

    public void efectoMasCuatro() {
        System.out.println("USASTE UN MÁS CUATRO!");
        int cartas = 0;
        while (cartas < 4) {
            if (turnoJugador == 1) {
                jugadores.get(1).setCartasMano(mazo.darCarta());
            } else if (turnoJugador == 2) {
                jugadores.get(0).setCartasMano(mazo.darCarta());
            }
            cartas++;
        }
        efectoCambioColor();
    }

    public void efectoBloqueoCambioSentido() {
        if(cartaActiva.getNumero() == 10) {
            System.out.println("USASTE UN CAMBIO DE SENTIDO!");
        } else {
            System.out.println("USASTE UN BLOQUEO DE TURNO!");
        }
        if (turnoJugador == 1) {
            this.setTurnoJugador(2);
        } else {
            this.setTurnoJugador(1);
        }
    }

    public void efectoCambioColor() {
        if (cartaActiva.isCambioColor()) {
            System.out.println("USASTE UN CAMBIO DE COLOR!");
        }
        Scanner in = new Scanner(System.in);
        int color;
        cartaJugada = true;
        while (true) {
            System.out.println("Ingrese el color a jugar");
            System.out.println("1. Rojo | 2. Amarillo | 3. Azul | 4. Verde");
            color = in.nextInt();
            in.nextLine();
            if (color >= 1 && color <= 4) {
                cartaActiva.setColor(CartaUno.COLORES[color - 1]);
                System.out.println("Color elegido: " + cartaActiva.getColor());
                break;
            } else {
                System.out.println("Ese color no es valido. Vuelva a intentarlo.");
            }
        }
    }
        public void cambiarTurno () {
            if (turnoJugador == 1) {
                this.setTurnoJugador(2);
            } else {
                this.setTurnoJugador(1);
            }
        }
    }