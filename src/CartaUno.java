public class CartaUno {
    private String color;
    private int numero;

    public static final String[] COLORES = {"ROJO", "AMARILLO", "AZUL", "VERDE", "NEGRO"};
    public static final int CARTASxCOLOR = 28;

    public CartaUno(String color, int numero) {
        this.color = color;
        this.numero = numero;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return this.numero + " " + this.color;
    }

    public boolean isCarta0al9(CartaUno cartaActiva) {
        if (this.getNumero() <= 9) {
            return cartaActiva.getNumero() == this.getNumero() ||
                    cartaActiva.getColor().equals(this.getColor());
        } else {
            return false;
        }
    }

    public boolean isMasDos(CartaUno cartaActiva) {
        if (this.getNumero() == 11) {
            return cartaActiva.getNumero() == this.getNumero() ||
                    cartaActiva.getColor().equals(this.getColor());
        } else {
            return false;
        }
    }

    public boolean isMismoColor(CartaUno cartaActiva) {
        return cartaActiva.getColor().equals(this.getColor());
    }

    public boolean isMismoNumero(CartaUno cartaActiva) {
        return cartaActiva.getNumero() == this.getNumero();
    }

    public boolean isMasCuatro() {
        return this.getNumero() == 13;
    }

    public boolean isCambioColor() {
        return this.getNumero() == 14;
    }

    public boolean isCambioSentido(CartaUno cartaActiva) {
        if (this.getNumero() == 10) {
            return cartaActiva.getNumero() == this.getNumero() ||
                    cartaActiva.getColor().equals(this.getColor());
        }
        return false;
    }

    public boolean isBloqueoTurno(CartaUno cartaActiva) {
        if (this.getNumero() == 12) {
            return cartaActiva.getNumero() == this.getNumero() ||
                    cartaActiva.getColor().equals(this.getColor());
        }
        return false;
    }
}