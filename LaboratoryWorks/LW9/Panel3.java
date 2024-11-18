package LW9;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Panel3 extends JPanel{
    public Panel3() {
        super(new GridLayout(5, 1));

        JLabel label = new JLabel("МИСТЕР ФПМИ");
        Icon BorisIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\Boris.png");
        Icon VladIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\Vlad.png");
        Icon MaxIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\Max.png");
        Icon DanTONIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\DanTon.png");
        Icon selectedIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\curent.png");
        Icon rolloverIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\rollover.png");
        Icon pressedIcon = new ImageIcon("C:\\git_boris\\demo\\LaboratoryWorks\\LW9\\icons\\pressed.png");
        
        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButton1 = createRButton("Boris", BorisIcon, selectedIcon, rolloverIcon, pressedIcon);
        JRadioButton radioButton2 = createRButton("Max", MaxIcon, selectedIcon, rolloverIcon, pressedIcon);
        JRadioButton radioButton3 = createRButton("DanTON", DanTONIcon, selectedIcon, rolloverIcon, pressedIcon);
        JRadioButton radioButton4 = createRButton("Vlad", VladIcon, selectedIcon, rolloverIcon, pressedIcon);
        
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);
        group.add(radioButton4);
        add(label);
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
        add(radioButton4);
    }

    private JRadioButton createRButton(String text, Icon personIcon, Icon selectedIcon, Icon rolloverIcon, Icon pressedIcon) {
        JRadioButton result = new JRadioButton(text);
        result.setIcon(personIcon);
        result.setSelectedIcon(selectedIcon);
        result.setRolloverIcon(rolloverIcon);
        result.setPressedIcon(pressedIcon);
        return result; 
    }
}
