/*
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MesaPanel  extends JPanel {
    MesaJuego juego;

    List<CartaUno> descarte = new ArrayList<>(); //esto lo queria probar para hacer la pila de descarte. Todavia no lo probé

    //SE CREAN LOS PANELES DONDE TIENE QUE APARECER CADA COSA
    JPanel jugador1Panel; //panel para el nombre del jugador 1
    JPanel mano1Panel; //panel para las cartas del jugador 1
    JPanel mesaPanel; //aca está el mazo y tambien tendría que estar la pila de descarte
    JPanel mazoPanel; //se hace un panel de mazo en especifico, que se agrega a mesaPanel
    JPanel jugador2Panel; //panel para el nombre del jugador 2
    JPanel mano2Panel; //panel para las cartas del jugador 2
    JPanel botonesPanel;//no se si sera necesario, ya que se podria resolver solamente con clics, sin que haya botones


    public MesaPanel() throws IOException {
        this.setPreferredSize(new Dimension(800, 400));

        juego = new MesaJuego();

        //esto forma parte de la grafica
        setLayout(new GridLayout(3, 1));
        jugador1Panel = new JPanel(); jugador1Panel.setLayout(new FlowLayout());
        mano1Panel = new JPanel(); mano1Panel.setLayout(new FlowLayout());
        mesaPanel = new JPanel(); mesaPanel.setLayout(new FlowLayout());
        mazoPanel = new JPanel(); mazoPanel.setLayout(new FlowLayout());
        jugador2Panel = new JPanel(); jugador2Panel.setLayout(new FlowLayout());
        mano2Panel = new JPanel(); mano2Panel.setLayout(new FlowLayout());

        //fondos de los paneles blanco, y los bordes de la "mesa" en gris
        jugador1Panel.setBackground(Color.WHITE);
        mano1Panel.setBackground(Color.WHITE);
        mano1Panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mesaPanel.setBackground(Color.WHITE);
        mesaPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mazoPanel.setBackground(Color.WHITE);
        jugador2Panel.setBackground(Color.WHITE);
        mano2Panel.setBackground(Color.WHITE);
        mano2Panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //se agregan los paneles a la grafica
        add(mano1Panel);
        mano1Panel.add(jugador1Panel);
        add(mesaPanel);
        mesaPanel.add(mazoPanel);
        add(mano2Panel);
        mano2Panel.add(jugador2Panel);

        ponerElementosEnPaneles(); //funcion donde se agregan todos los elementos (nombre jugador, cartas, mazo, etc)
                                    // a sus respectivos paneles
    }

    //PANEL DEL MAZO
    private JPanel getMazoPanel(MesaJuego mazo) throws IOException {
        JPanel mazoPanel = new JPanel(); //se crea el panel
        mazoPanel.setLayout(new GridLayout(1, 1)); //se define el tamaño
        mazoPanel.setBackground(Color.WHITE); //se define el fondo: blanco

        //código para la imagen
        String mazoIcon = "images/mazo.png";
        Image cartaImage = ImageIO.read(new File(mazoIcon));
        ImageIcon cartaIcon = new ImageIcon(cartaImage.getScaledInstance(70, 90, Image.SCALE_SMOOTH));
        JLabel cartaLabel = new JLabel();
        cartaLabel.setIcon(cartaIcon);
        mazoPanel.add(cartaLabel);

        //esto todavia no funciona, pero es para cuando se haga "clic" en el mazo
        mazoPanel.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        return mazoPanel;
    }

    //PANEL DEL JUGADOR: NOMBRES Y LOGOS
    private JPanel getJugadorPanel(Jugador jugador) throws IOException {
        JPanel jugadorPanel = new JPanel();
        jugadorPanel.setLayout(new GridLayout(1, 1));
        jugadorPanel.setBackground(Color.WHITE);

        //para que aparezcan los nombres de los jugadores
        JLabel jugadorNombreLabel = new JLabel(jugador.getNombre());
        jugadorNombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jugadorPanel.add(jugadorNombreLabel);

        //imagen para cada jugador
        String jugadorIcon = null;
            if (juego.jugador1.getNombre().equals("Jugador 1")) {
                jugadorIcon = "images/jugador1.png";
            } else if (juego.jugador2.getNombre().equals("Jugador 2")) {
                jugadorIcon = "images/jugador2.png";
            }
        Image cartaImage = ImageIO.read(new File(jugadorIcon));
        ImageIcon cartaIcon = new ImageIcon(cartaImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel cartaLabel = new JLabel();
        cartaLabel.setIcon(cartaIcon);
        jugadorPanel.add(cartaLabel);
        return jugadorPanel;
    }

    //PANEL DE LAS CARTAS
    private JPanel getCartaPanel(CartaUno carta) throws IOException {
        JPanel cartaPanel = new JPanel();
        cartaPanel.setLayout(new GridLayout(1, 1));

        String imagenCarta = null;
        if (carta.getColor().equals("ROJO")) {
            switch (carta.getNumero()) {
                case 0 -> imagenCarta = "images/cero_rojo.png";
                case 1 -> imagenCarta = "images/uno_rojo.png";
                case 2 -> imagenCarta = "images/dos_rojo.png";
                case 3 -> imagenCarta = "images/tres_rojo.png";
                case 4 -> imagenCarta = "images/cuatro_rojo.png";
                case 5 -> imagenCarta = "images/cinco_rojo.png";
                case 6 -> imagenCarta = "images/seis_rojo.png";
                case 7 -> imagenCarta = "images/siete_rojo.png";
                case 8 -> imagenCarta = "images/ocho_rojo.png";
                case 9 -> imagenCarta = "images/nueve_rojo.png";
                case 10 -> imagenCarta = "images/bloqueoTurno_rojo.png";
                case 11 -> imagenCarta = "images/masDos_rojo.png";
                case 12 -> imagenCarta = "images/cambioSentido_rojo.png";
            }
        }
        if (carta.getColor().equals("AZUL")) {
            switch (carta.getNumero()) {
                case 0 -> imagenCarta = "images/cero_azul.png";
                case 1 -> imagenCarta = "images/uno_azul.png";
                case 2 -> imagenCarta = "images/dos_azul.png";
                case 3 -> imagenCarta = "images/tres_azul.png";
                case 4 -> imagenCarta = "images/cuatro_azul.png";
                case 5 -> imagenCarta = "images/cinco_azul.png";
                case 6 -> imagenCarta = "images/seis_azul.png";
                case 7 -> imagenCarta = "images/siete_azul.png";
                case 8 -> imagenCarta = "images/ocho_azul.png";
                case 9 -> imagenCarta = "images/nueve_azul.png";
                case 10 -> imagenCarta = "images/bloqueoTurno_azul.png";
                case 11 -> imagenCarta = "images/masDos_azul.png";
                case 12 -> imagenCarta = "images/cambioSentido_azul.png";
            }
        }
        if (carta.getColor().equals("AMARILLO")) {
            switch (carta.getNumero()) {
                case 0 -> imagenCarta = "images/cero_amarillo.png";
                case 1 -> imagenCarta = "images/uno_amarillo.png";
                case 2 -> imagenCarta = "images/dos_amarillo.png";
                case 3 -> imagenCarta = "images/tres_amarillo.png";
                case 4 -> imagenCarta = "images/cuatro_amarillo.png";
                case 5 -> imagenCarta = "images/cinco_amarillo.png";
                case 6 -> imagenCarta = "images/seis_amarillo.png";
                case 7 -> imagenCarta = "images/siete_amarillo.png";
                case 8 -> imagenCarta = "images/ocho_amarillo.png";
                case 9 -> imagenCarta = "images/nueve_amarillo.png";
                case 10 -> imagenCarta = "images/bloqueoTurno_amarillo.png";
                case 11 -> imagenCarta = "images/masDos_amarillo.png";
                case 12 -> imagenCarta = "images/cambioSentido_amarillo.png";
            }
        }
        if (carta.getColor().equals("VERDE")) {
            switch (carta.getNumero()) {
                case 0 -> imagenCarta = "images/cero_verde.png";
                case 1 -> imagenCarta = "images/uno_verde.png";
                case 2 -> imagenCarta = "images/dos_verde.png";
                case 3 -> imagenCarta = "images/tres_verde.png";
                case 4 -> imagenCarta = "images/cuatro_verde.png";
                case 5 -> imagenCarta = "images/cinco_verde.png";
                case 6 -> imagenCarta = "images/seis_verde.png";
                case 7 -> imagenCarta = "images/siete_verde.png";
                case 8 -> imagenCarta = "images/ocho_verde.png";
                case 9 -> imagenCarta = "images/nueve_verde.png";
                case 10 -> imagenCarta = "images/bloqueoTurno_verde.png";
                case 11 -> imagenCarta = "images/masDos_verde.png";
                case 12 -> imagenCarta = "images/cambioSentido_verde.png";
            }
        }
        if (carta.getColor().equals("NEGRO")) {
            switch (carta.getNumero()) {
                case 13 -> imagenCarta = "images/masCuatro_negro.png";
                case 14 -> imagenCarta = "images/cambiaColor_negro.png";
            }
        }
        Image cartaImage = ImageIO.read(new File(imagenCarta));
        ImageIcon cartaIcon = new ImageIcon(cartaImage.getScaledInstance(70, 100, Image.SCALE_SMOOTH));
        JLabel cartaLabel = new JLabel();
        cartaLabel.setIcon(cartaIcon);
        cartaPanel.add(cartaLabel);

        //cuando se hace clic en las cartas
        cartaPanel.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {cartaClickeada(carta, cartaPanel);}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        return cartaPanel;
    }

    void ponerElementosEnPaneles() throws IOException {
        for (CartaUno carta : juego.jugadores.get(0).getCartasMano()) {
            mano1Panel.add(getCartaPanel(carta));
        }
        for (CartaUno carta : juego.jugadores.get(1).getCartasMano()) {
            mano2Panel.add(getCartaPanel(carta));
        }
        mazoPanel.add(getMazoPanel(juego));
        jugador1Panel.add(getJugadorPanel(juego.jugador1));
        jugador2Panel.add(getJugadorPanel(juego.jugador2));

    }

    //FUNCION PARA CUANDO SE HACE CLIC EN LAS CARTAS
    void cartaClickeada(CartaUno carta, JPanel panel) {
        if (juego.jugador1.getCartasMano().indexOf(carta) >= 0) {
            mano1Panel.remove(panel);
            mano1Panel.repaint();
            juego.jugador1.getCartasMano().remove(carta);
            mesaPanel.add(panel);
            //descarte.add(carta);
            mesaPanel.repaint();
        } else if (juego.jugador2.getCartasMano().indexOf(carta) >= 0) {
            mano2Panel.remove(panel);
            mano2Panel.repaint();
            juego.jugador2.getCartasMano().remove(carta);
            mesaPanel.add(panel);
            //descarte.add(carta);
            mesaPanel.repaint();
        }
    }
    //ESTO NO FUNCIONA TODAVÍA
   /*void mazoClickeado(MazoUno mazo, JPanel panel) throws IOException {
        juego.jugadores.get(0).setCartasMano(juego.mazo.darCarta());
        jugador1Panel.add(panel);
        jugador1Panel.repaint();
    }
}
*/