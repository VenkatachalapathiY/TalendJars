package org.talend.components.vtigercrm.processor;

import java.io.Serializable;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.meta.Documentation;

@GridLayout({
    // the generated layout put one configuration entry per line,
    // customize it as much as needed
    @GridLayout.Row({ "VtigerCRM_URL" }),
    @GridLayout.Row({ "Username" }),
    @GridLayout.Row({ "AccessKey" }),
    @GridLayout.Row({ "Action" }),
    @GridLayout.Row({ "Module" })
})
@Documentation("TODO fill the documentation for this configuration")
public class OutputProcessorConfiguration implements Serializable {
    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String VtigerCRM_URL;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String Username;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String AccessKey;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String Action;

    @Option
    @Documentation("TODO fill the documentation for this parameter")
    private String Module;

    public String getVtigerCRM_URL() {
        return VtigerCRM_URL;
    }

    public OutputProcessorConfiguration setVtigerCRM_URL(String VtigerCRM_URL) {
        this.VtigerCRM_URL = VtigerCRM_URL;
        return this;
    }

    public String getUsername() {
        return Username;
    }

    public OutputProcessorConfiguration setUsername(String Username) {
        this.Username = Username;
        return this;
    }

    public String getAccessKey() {
        return AccessKey;
    }

    public OutputProcessorConfiguration setAccessKey(String AccessKey) {
        this.AccessKey = AccessKey;
        return this;
    }

    public String getAction() {
        return Action;
    }

    public OutputProcessorConfiguration setAction(String Action) {
        this.Action = Action;
        return this;
    }

    public String getModule() {
        return Module;
    }

    public OutputProcessorConfiguration setModule(String Module) {
        this.Module = Module;
        return this;
    }
}