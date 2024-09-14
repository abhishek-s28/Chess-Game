package chess.game.pkgfinal.project.models;

import chess.game.pkgfinal.project.chess_board.BoardPanel;
import javax.swing.*;
import java.awt.*;

/**
 * MainFrame.java - This class creates a jFrame with complete chess layout.
 *
 * @author Abhishek Shah
 */
public class MainFrame extends JFrame {
    
    /**
     * Default constructor sets the properties of the JFrame of actual chess screen
     */
    public MainFrame() {
        
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new BoardPanel(), BorderLayout.CENTER);
        mainPanel.add(new Sidebar(), BorderLayout.EAST);

        setContentPane(mainPanel);
        setVisible(true);
        pack(); // sizes the frame 
        setLocationRelativeTo(null);
    }
}
