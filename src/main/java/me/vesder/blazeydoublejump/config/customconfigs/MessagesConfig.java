package me.vesder.blazeydoublejump.config.customconfigs;

import lombok.Getter;
import me.vesder.blazeydoublejump.config.ConfigUtils;
import me.vesder.blazeydoublejump.config.CustomConfig;

public class MessagesConfig extends CustomConfig {

    @Getter
    private String configEditorSuccess;
    @Getter
    private String configEditorInvalidNumber;

    @Override
    public String getName() {
        return "messages.yml";
    }

    @Override
    public void loadValues() {
        configEditorSuccess = ConfigUtils.getStringConfig(getName(), "configEditor.success");
        configEditorInvalidNumber = ConfigUtils.getStringConfig(getName(), "configEditor.invalid-number");
    }
}
