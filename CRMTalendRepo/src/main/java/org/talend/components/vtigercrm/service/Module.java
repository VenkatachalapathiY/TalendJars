package org.talend.components.vtigercrm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.talend.components.vtigercrm.configuration.VtigercrmComponentsDataSet;
import org.talend.components.vtigercrm.configuration.VtigercrmComponentsDataStore;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.service.Service;
import org.talend.sdk.component.api.service.completion.DynamicValues;
import org.talend.sdk.component.api.service.completion.Values;
import org.talend.sdk.component.api.service.completion.Values.Item;

import com.vtiger.vtwsclib.WSClient;

@Service
public class Module {
	public ArrayList getFields(@Option("dataset") final VtigercrmComponentsDataSet dataset) {
		ArrayList list = new ArrayList();
		WSClient client = new WSClient(dataset.getConnection().getVtigerCRM_URL());
		boolean result = client.doLogin(dataset.getConnection().getUsername(), dataset.getConnection().getAccessKey());
		if (result == false) {
			System.out.println("Login failed!");
			System.out.println(client.lastError());
		} else {
			JSONArray queryResult = client.doQuery("SELECT * FROM " + dataset.getModule());

			list = (ArrayList) client.getResultColumns(queryResult);
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i));
			}
			return list;

		}
		return list;
	}

}