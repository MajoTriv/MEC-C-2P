import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CodigoParcial extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;

    private final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime horaActual = LocalDateTime.now();
    private final Timer temporizador = new Timer(1000, this);
    private int velocidad = 1000;
    private boolean detener = false;

    private JButton btnIniciar, btnDetener;
    private JSlider sldVelocidad;
    private JLabel lblHora;

    public CodigoParcial() {
        super("PARCIAL SEGUNDO CORTE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnIniciar = new JButton("Iniciar");
        btnDetener = new JButton("Detener");
        btnIniciar.setBackground(Color.BLUE);
        btnDetener.setBackground(Color.BLUE);
        btnIniciar.setForeground(Color.WHITE);
        btnDetener.setForeground(Color.WHITE);
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        btnIniciar.addActionListener(this);
        btnDetener.addActionListener(this);

        JPanel panelVelocidad = new JPanel(new BorderLayout());
        sldVelocidad = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        sldVelocidad.setMajorTickSpacing(1);
        sldVelocidad.setPaintTicks(true);
        sldVelocidad.setPaintLabels(true);
        sldVelocidad.setInverted(true);
        sldVelocidad.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sldVelocidad.setBackground(Color.PINK);
        sldVelocidad.addChangeListener(this);
        panelVelocidad.add(sldVelocidad, BorderLayout.CENTER);

        lblHora = new JLabel(horaActual.format(formatoHora));
        lblHora.setHorizontalAlignment(JLabel.CENTER);
        lblHora.setVerticalAlignment(JLabel.CENTER);
        lblHora.setFont(new Font("Arial", Font.BOLD, 48));
        lblHora.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblHora.setForeground(Color.PINK);
        add(lblHora, BorderLayout.CENTER);

        add(panelBotones, BorderLayout.NORTH);
        add(panelVelocidad, BorderLayout.SOUTH);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            detener = false;
            temporizador.start();
        } else if (e.getSource() == btnDetener) {
            detener = true;
            temporizador.stop();
        } else if (e.getSource() == temporizador) {
            horaActual = horaActual.plusSeconds(1);
            lblHora.setText(horaActual.format(formatoHora));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        velocidad = (11 - sldVelocidad.getValue()) * 100;
        temporizador.setDelay(velocidad);
    }

    public static void main(String[] args) {
        new CodigoParcial();
    }
}
