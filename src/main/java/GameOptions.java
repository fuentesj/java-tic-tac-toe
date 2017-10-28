import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Jonathan on 10/24/17.
 */
public class GameOptions extends JPanel {

    public GameOptions() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel playerCharacterButtonPanel = new JPanel();
        playerCharacterButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerCharacterButtonPanel.add(new JLabel("PlayerMark:"));
        JRadioButton xRadioButton = new JRadioButton("X");
        JRadioButton yRadioButton = new JRadioButton("O");
        ButtonGroup playerCharacterButtonGroup = new ButtonGroup();
        playerCharacterButtonGroup.add(xRadioButton);
        playerCharacterButtonGroup.add(yRadioButton);
        xRadioButton.setSelected(true);
        playerCharacterButtonPanel.add(xRadioButton, gridBagConstraints);
        playerCharacterButtonPanel.add(yRadioButton, gridBagConstraints);
        playerCharacterButtonPanel.setToolTipText("Choose to play as either X or O.");
        this.add(playerCharacterButtonPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(new JLabel("Game Difficulty:"));
        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton hard = new JRadioButton("Hard");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(easy);
        radioButtonGroup.add(hard);
        easy.setSelected(true);
        buttonPanel.add(easy, gridBagConstraints);
        buttonPanel.add(hard, gridBagConstraints);
        buttonPanel.setToolTipText("Choose whether the computer should play easy or hard.");
        this.add(buttonPanel);

        Border optionsPanelBorder = BorderFactory.createTitledBorder("Game Options");
        this.setBorder(optionsPanelBorder);
    }
}
