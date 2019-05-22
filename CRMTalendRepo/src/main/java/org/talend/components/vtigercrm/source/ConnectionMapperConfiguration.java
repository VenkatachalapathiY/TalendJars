package org.talend.components.vtigercrm.source;

import java.io.Serializable;

import org.talend.components.vtigercrm.configuration.VtigercrmComponentsDataSet;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.meta.Documentation;

@Documentation("TODO fill the documentation for this configuration")
public class ConnectionMapperConfiguration implements Serializable {
	@Option
	@Documentation("TODO fill the documentation for this parameter")
	private VtigercrmComponentsDataSet dataset;

	public VtigercrmComponentsDataSet getDataset() {
		return dataset;
	}

	public ConnectionMapperConfiguration setDataset(VtigercrmComponentsDataSet dataset) {
		this.dataset = dataset;
		return this;
	}

}