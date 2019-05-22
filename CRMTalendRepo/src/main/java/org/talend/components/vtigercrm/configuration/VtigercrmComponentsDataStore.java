package org.talend.components.vtigercrm.configuration;

import java.io.Serializable;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.configuration.action.Checkable;
import org.talend.sdk.component.api.configuration.type.DataStore;
import org.talend.sdk.component.api.configuration.ui.widget.Credential;
import org.talend.sdk.component.api.meta.Documentation;

@DataStore
@Checkable()
@Documentation("TODO fill the documentation for this configuration")
public class VtigercrmComponentsDataStore implements Serializable {
	// fill the data store/connection configuration

	@Option
	@Documentation("TODO fill the documentation for this parameter")
	private String VtigerCRM_URL;

	@Option
	@Documentation("TODO fill the documentation for this parameter")
	private String Username;

	@Option
	@Credential
	@Documentation("TODO fill the documentation for this parameter")
	private String AccessKey;

	public VtigercrmComponentsDataStore() {
		// TODO Auto-generated constructor stub
	}

	public VtigercrmComponentsDataStore(String VtigerCRM_URL, String Username, String AccessKey) {
		this.VtigerCRM_URL = VtigerCRM_URL;
		this.Username = Username;
		this.AccessKey = AccessKey;
		// TODO Auto-generated constructor stub
	}

	public String getVtigerCRM_URL() {
		return VtigerCRM_URL;
	}

	public VtigercrmComponentsDataStore setVtigerCRM_URL(String VtigerCRM_URL) {
		this.VtigerCRM_URL = VtigerCRM_URL;
		return this;
	}

	public String getUsername() {
		return Username;
	}

	public VtigercrmComponentsDataStore setUsername(String Username) {
		this.Username = Username;
		return this;
	}

	public String getAccessKey() {
		return AccessKey;
	}

	public VtigercrmComponentsDataStore setAccessKey(String AccessKey) {
		this.AccessKey = AccessKey;
		return this;
	}

}