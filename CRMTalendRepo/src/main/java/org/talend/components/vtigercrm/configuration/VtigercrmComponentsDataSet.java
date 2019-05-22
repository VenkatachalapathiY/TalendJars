package org.talend.components.vtigercrm.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.action.Suggestable;
import org.talend.sdk.component.api.configuration.type.DataSet;
import org.talend.sdk.component.api.configuration.ui.layout.GridLayout;
import org.talend.sdk.component.api.configuration.ui.widget.Structure;
import org.talend.sdk.component.api.configuration.ui.widget.Structure.Type;
import org.talend.sdk.component.api.meta.Documentation;

@GridLayout({
		// the generated layout put one configuration entry per line,
		// customize it as much as needed
		@GridLayout.Row({ "dataStore" }), @GridLayout.Row({ "Module" }), @GridLayout.Row({ "fields" })

})

@DataSet
@Documentation("TODO fill the documentation for this configuration")
public class VtigercrmComponentsDataSet implements Serializable {

	//
	// fill the reusable configuration for input/output components
	// -> it must also enable to instantiate a source component without
	// additional *required* configuration
	// -> any input/output components must have a reference to a dataset to be
	// valid for Talend Platform (cloud)
	//

	@Option
	@Documentation("The connection of the configuration")
	private VtigercrmComponentsDataStore dataStore;

	@Option
	@Suggestable(value = "loadModules", parameters = { "dataStore" })
	@Documentation("module names are loaded using service")
	private String Module;

	public static final String PROPOSABLE_GET_TABLE_FIELDS = "GetTableFields";

	@Option
	@Structure(discoverSchema = "guessTableSchema", type = Type.IN)
	@Documentation(value = "List of field names to return in the response.")
	private List<String> fields = new ArrayList<>();

	public VtigercrmComponentsDataStore getConnection() {
		return dataStore;
	}

	public VtigercrmComponentsDataSet setConnection(VtigercrmComponentsDataStore dataStore) {
		this.dataStore = dataStore;
		return this;
	}

	public String getModule() {
		return Module;

	}

	public VtigercrmComponentsDataSet setModule(String Module) {
		this.Module = Module;
		return this;
	}

}