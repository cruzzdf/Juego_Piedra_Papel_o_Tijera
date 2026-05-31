import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameFrame extends JFrame {

    private int tiempoRestante = 300; // 5 minutos = 300 segundos
    private Timer temporizadorPrincipal;

    private JLabel etiquetaTiempo;
    private JLabel etiquetaResultado;
    private JLabel etiquetaPuntaje;

    private final String[] movimientos = {
        "Piedra", "Papel", "Tijera",
        "Nebulosa", "Fuego", "Agua"
    };

    private final Random random = new Random();
    private final Player jugador = new Player("Jugador");
    private int puntajeComputadora = 0;

    // Cooldown de cada botón (en segundos)
    private final int COOLDOWN_SEGUNDOS = 15;
    private final Map<JButton, Integer> cooldownTimers = new HashMap<>();

    public GameFrame() {
        super("Piedra, Papel o Tijera️");

        // Configurar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);

        // Etiqueta tiempo
        etiquetaTiempo = new JLabel("Tiempo restante: 05:00", SwingConstants.CENTER);
        etiquetaTiempo.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(etiquetaTiempo, BorderLayout.NORTH);

        // Etiqueta puntaje
        etiquetaPuntaje = new JLabel("Jugador: 0  |  Computadora: 0", SwingConstants.CENTER);
        etiquetaPuntaje.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(etiquetaPuntaje, BorderLayout.SOUTH);

        // Panel botones (cartas)
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear botones con imágenes
        for (String move : movimientos) {
            JButton boton = crearBotonConImagen(move);
            boton.addActionListener(e -> jugarTurno(move, boton));
            panelBotones.add(boton);
        }

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Etiqueta resultado
        etiquetaResultado = new JLabel("Haz tu elección...", SwingConstants.CENTER);
        etiquetaResultado.setFont(new Font("Arial", Font.BOLD, 20));
        panelPrincipal.add(etiquetaResultado, BorderLayout.EAST);

        setVisible(true);
        iniciarTemporizadorPrincipal();
    }

    private JButton crearBotonConImagen(String move) {
        String nombreArchivo = move.toLowerCase()
                .replace(" ", "_")
                .replace("ó", "o") // por si hay acentos
                .replace("á", "a");

        String ruta = "/images/" + nombreArchivo + ".jpg";

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(getClass().getResource(ruta));
            Image image = icon.getImage().getScaledInstance(225, 225, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + ruta);
        }

        JButton boton = new JButton(move, icon);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(240, 240, 240));
        return boton;
    }

    private void iniciarTemporizadorPrincipal() {
        temporizadorPrincipal = new Timer(1000, e -> {
            tiempoRestante--;
            int minutos = tiempoRestante / 60;
            int segundos = tiempoRestante % 60;
            etiquetaTiempo.setText(String.format("Tiempo restante: %02d:%02d", minutos, segundos));

            if (tiempoRestante <= 0) {
                ((Timer) e.getSource()).stop();
                terminarJuegoPorTiempo();
            }

            actualizarCooldowns();
        });
        temporizadorPrincipal.start();
    }

    private void jugarTurno(String movimientoJugador, JButton botonUsado) {
        if (cooldownTimers.containsKey(botonUsado) && cooldownTimers.get(botonUsado) > 0) {
            etiquetaResultado.setText("Esa carta está en enfriamiento.");
            return;
        }

        String movimientoPC = movimientos[random.nextInt(movimientos.length)];
        String resultado = determinarGanador(movimientoJugador, movimientoPC);

        etiquetaResultado.setText("<html>Tu elección: " + movimientoJugador
                + "<br>Computadora: " + movimientoPC
                + "<br><b>" + resultado + "</b></html>");

        etiquetaPuntaje.setText("Jugador: " + jugador.getPuntaje()
                + "  |  Computadora: " + puntajeComputadora);

        cooldownTimers.put(botonUsado, COOLDOWN_SEGUNDOS);
        botonUsado.setEnabled(false);
        botonUsado.setText(movimientoJugador + " (" + COOLDOWN_SEGUNDOS + "s)");
    }

    private void actualizarCooldowns() {
        for (Map.Entry<JButton, Integer> entry : cooldownTimers.entrySet()) {
            JButton boton = entry.getKey();
            int tiempo = entry.getValue();

            if (tiempo > 0) {
                tiempo--;
                entry.setValue(tiempo);
                boton.setText(boton.getText().split(" ")[0] + " (" + tiempo + "s)");
            }

            if (tiempo <= 0) {
                boton.setEnabled(true);
                boton.setText(boton.getText().split(" ")[0]);
            }
        }
    }

    private String determinarGanador(String j, String c) {
        if (j.equals(c)) {
            return "Empate";
        }

        switch (j) {
            case "Piedra":
                if (c.equals("Tijera") || c.equals("Fuego")) {
                    jugador.sumarPunto();
                    return "Ganaste";
                } else {
                    puntajeComputadora++;
                    return "Perdiste";
                }
            case "Papel":
                if (c.equals("Piedra") || c.equals("Agua")) {
                    jugador.sumarPunto();
                    return "Ganaste";
                } else {
                    puntajeComputadora++;
                    return "Perdiste";
                }
            case "Tijera":
                if (c.equals("Papel") || c.equals("Agua")) {
                    jugador.sumarPunto();
                    return "Ganaste";
                } else {
                    puntajeComputadora++;
                    return "Perdiste";
                }
            case "Nebulosa":
                jugador.sumarPunto();
                return "Ganaste con Nebulosa";
            case "Fuego":
                if (c.equals("Papel") || c.equals("Tijera")) {
                    jugador.sumarPunto();
                    return "Ganaste";
                } else {
                    puntajeComputadora++;
                    return "Perdiste";
                }
            case "Agua":
                if (c.equals("Fuego")) {
                    jugador.sumarPunto();
                    return "Ganaste";
                } else if (c.equals("Nebulosa")) {
                    puntajeComputadora++;
                    return "Perdiste";
                } else {
                    puntajeComputadora++;
                    return "Perdiste";
                }
        }
        return "Error de lógica";
    }

    private void terminarJuegoPorTiempo() {
        JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo!");
        if (jugador.getPuntaje() > puntajeComputadora) {
            JOptionPane.showMessageDialog(this, "¡Ganaste con " + jugador.getPuntaje() + " puntos!");
        } else if (puntajeComputadora > jugador.getPuntaje()) {
            JOptionPane.showMessageDialog(this, "La computadora gana con " + puntajeComputadora + " puntos!");
        } else {
            JOptionPane.showMessageDialog(this, "¡Empate!");
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
