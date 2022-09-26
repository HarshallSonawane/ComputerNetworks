
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Home {
    JFrame homeFrame;
    GridLayout gridLayout;
    JTextField bits;
    JButton encodeBtn;

    Home() {
        homeFrame = new JFrame();
        gridLayout = new GridLayout(3, 1);
        homeFrame.setLayout(gridLayout);
        homeFrame.setSize(500, 400);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void populateScreen() {
        JPanel temp = new JPanel();
        JLabel lcsLabel = new JLabel("Line Encoding");
        temp.setBackground(Color.yellow);
        temp.add(lcsLabel);
        homeFrame.add(temp);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.yellow);
        JLabel enterBitsLabel = new JLabel("Enter bits");
        bits = new JTextField(20);
        enterBitsLabel.setBounds(10, 10, 150, 30);
        bits.setBounds(10, 10, 10, 30);
        inputPanel.add(enterBitsLabel);
        inputPanel.add(bits);
        homeFrame.add(inputPanel);

        encodeBtn = new JButton("Let's Encode!!");
        encodeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String tempBits = bits.getText().trim();

                if (tempBits.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Ughh Empty Bit String!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    bits.setText("");
                    return;
                }

                ArrayList<Integer> byteStream = new ArrayList<>();

                for (int i = 0; i < tempBits.length(); i++) {
                    if (tempBits.charAt(i) == '0') {
                        byteStream.add(0);
                    } else if (tempBits.charAt(i) == '1') {
                        byteStream.add(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID BIT STREAM", "ERROR", JOptionPane.ERROR_MESSAGE);
                        bits.setText("");
                        return;
                    }
                }

                new EncodingGraphics(byteStream);
            }
        });
        homeFrame.add(encodeBtn);
    }

    public void showHomeScreen() {
        homeFrame.setVisible(true);
    }

}

public class LineCodingSimulator {
    public static void main(String[] args) {
        Home h = new Home();
        h.populateScreen();
        h.showHomeScreen();
    }
}