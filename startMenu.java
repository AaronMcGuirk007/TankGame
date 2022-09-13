import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

/**
 * 
 */
public class startMenu{

    
    public static void main(String[] args){
        JFrame f = new JFrame("Pick a mode");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton singlePlayer = new JButton("Break the targets");
        JButton multiPlayer = new JButton("Player vs Player");
        JButton freeForAll = new JButton("Free for All");
        JLabel greenInfo = new JLabel("Green Tank: W = up \n A = left \n S = down \n D = right \n G = fire");
        JLabel redInfo = new JLabel("Red Tank: I = up \n J = left \n K = down \n L = right \n L = fire");
        JLabel title = new JLabel("Tank Game! (v1.0)");

        title.setFont(new Font("Calibri", Font.PLAIN, 18));
        title.setBounds(120, 20, 400, 30);

        singlePlayer.setBounds(130,100,135,30);
        singlePlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                javax.swing.SwingUtilities.invokeLater(new Game());
                f.setVisible(false);
            }
        });


        multiPlayer.setBounds(130,140,135,30);
        multiPlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                javax.swing.SwingUtilities.invokeLater(new PvP());
                f.setVisible(false);
            }
        });

        freeForAll.setBounds(130,180,135,30);
        freeForAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                javax.swing.SwingUtilities.invokeLater(new FreeForAll());
                f.setVisible(false);
            }
        });

        greenInfo.setBounds(5, 110, 400, 400);
        redInfo.setBounds(15, 140, 400, 400);

        f.add(title);
        f.add(singlePlayer);
        f.add(multiPlayer);
        f.add(freeForAll);
        f.add(greenInfo);
        f.add(redInfo);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }




    
}