import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualActionListener implements ActionListener {
    private final JTextField textField;
    private final ScriptEngine scriptEngine;
    private String value;

    public EqualActionListener(JTextField textField) {
        this.textField = textField;
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("Nashorn");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textField.getText() != null) {
            try {
                value = String.valueOf(scriptEngine.eval(textField.getText()));
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
        }
        textField.setText(value);
    }
}
