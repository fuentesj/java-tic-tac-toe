import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Jonathan on 10/24/17.
 */
public class GameOptions extends JPanel {

    private ButtonGroup playerCharacterButtonGroup;
    private ButtonGroup gameDifficultyRadioButtonGroup;

    public GameOptions(Map<String, String> configurationMap) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JPanel playerCharacterButtonPanel = new JPanel();
        playerCharacterButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerCharacterButtonPanel.add(new JLabel("PlayerMark:"));
        JRadioButton xRadioButton = new JRadioButton("X");
        JRadioButton yRadioButton = new JRadioButton("O");
        this.playerCharacterButtonGroup = new ButtonGroup();
        this.playerCharacterButtonGroup.add(xRadioButton);
        this.playerCharacterButtonGroup.add(yRadioButton);
        if ("X".equals(configurationMap.get("selectedPlayerMark"))) {
            xRadioButton.setSelected(true);
        } else if ("O".equals(configurationMap.get("selectedPlayerMark"))) {
            yRadioButton.setSelected(true);
        } else {
            xRadioButton.setSelected(true);
        }
        playerCharacterButtonPanel.add(xRadioButton, gridBagConstraints);
        playerCharacterButtonPanel.add(yRadioButton, gridBagConstraints);
        playerCharacterButtonPanel.setToolTipText("Choose to play as either X or O.");
        this.add(playerCharacterButtonPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.add(new JLabel("Game Difficulty:"));
        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton hard = new JRadioButton("Hard");
        this.gameDifficultyRadioButtonGroup = new ButtonGroup();
        this.gameDifficultyRadioButtonGroup.add(easy);
        this.gameDifficultyRadioButtonGroup.add(hard);
        easy.setSelected(true);
        buttonPanel.add(easy, gridBagConstraints);
        buttonPanel.add(hard, gridBagConstraints);
        buttonPanel.setToolTipText("Choose whether the computer should play easy or hard.");
        this.add(buttonPanel);

        Border optionsPanelBorder = BorderFactory.createTitledBorder("Game Options");
        this.setBorder(optionsPanelBorder);
    }

    public String getSelectedPlayerMark() {

        String selectedButtonText = null;
        for (Enumeration<AbstractButton> buttons = playerCharacterButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton abstractButton = buttons.nextElement();
            if (abstractButton.isSelected()) {
                selectedButtonText = abstractButton.getText();
            }
        }
        return selectedButtonText;
    }

    public String getSelectedPlayerDifficulty() {
        String selectedButtonText = null;
        for (Enumeration<AbstractButton> buttons = gameDifficultyRadioButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton abstractButton = buttons.nextElement();
            if (abstractButton.isSelected()) {
                selectedButtonText = abstractButton.getText();
            }
        }
        return selectedButtonText;
    }
}
