package org.talend.components.vtigercrm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.talend.components.vtigercrm.configuration.VtigercrmComponentsDataSet;
import org.talend.components.vtigercrm.configuration.VtigercrmComponentsDataStore;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.record.Schema;
import org.talend.sdk.component.api.service.Service;
import org.talend.sdk.component.api.service.completion.SuggestionValues;
import org.talend.sdk.component.api.service.completion.SuggestionValues.Item;
import org.talend.sdk.component.api.service.completion.Suggestions;
import org.talend.sdk.component.api.service.healthcheck.HealthCheck;
import org.talend.sdk.component.api.service.healthcheck.HealthCheckStatus;
import org.talend.sdk.component.api.service.healthcheck.HealthCheckStatus.Status;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;
import org.talend.sdk.component.api.service.schema.DiscoverSchema;

import com.vtiger.vtwsclib.WSClient;

@Service
public class VtigercrmComponentsService {

	// you can put logic here you can reuse in components
	@HealthCheck()
	public HealthCheckStatus healthCheck(@Option("datastore") VtigercrmComponentsDataStore datastore) {
		WSClient client = new WSClient(datastore.getVtigerCRM_URL());

		if (client.doLogin(datastore.getUsername(), datastore.getAccessKey())) {
			// often add an exception message mapping or equivalent
			return new HealthCheckStatus(Status.OK, "Connection  successfull....");

		} else {
			return new HealthCheckStatus(Status.KO, "Connection failed,Please check..");

		}
	}

	@Suggestions("loadModules")
	public SuggestionValues loadSalesforceModules(
			@Option("VtigercrmComponentsDataSet") final VtigercrmComponentsDataStore dataStore) {

		try {
			WSClient client = new WSClient(dataStore.getVtigerCRM_URL());
			boolean result = client.doLogin(dataStore.getUsername(), dataStore.getAccessKey());
			ArrayList<Item> a2 = new ArrayList<>();
			if (result == false) {
				System.out.println("Login failed!");
				System.out.println(client.lastError());
			} else {

				Map types = client.doListTypes();
				Iterator iterator = types.keySet().iterator();
				while (iterator.hasNext()) {
					Object key = iterator.next();
					Map moduleInfo = (Map) types.get(key);
					String s1 = moduleInfo.get("name").toString();
					a2.add(new Item(s1, s1));
					System.out.println("result " + s1);

				}
			}

			return new SuggestionValues(false, a2);

		} catch (final Exception unexpected) {
			// catch all exceptions for this ui label to return empty list
			unexpected.printStackTrace();
		}

		return new SuggestionValues();

	}

	@DiscoverSchema(value = "guessTableSchema")
	public Schema guessTableSchema(@Option("dataset") final VtigercrmComponentsDataSet dataset,
			final RecordBuilderFactory factory, final Module module) {
		final Schema.Entry.Builder entryBuilder = factory.newEntryBuilder();
		final Schema.Builder schemaBuilder = factory
				.newSchemaBuilder(org.talend.sdk.component.api.record.Schema.Type.RECORD);

		ArrayList list = module.getFields(dataset);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				schemaBuilder.withEntry(entryBuilder.withName(list.get(i).toString()).withType(Schema.Type.STRING)
						.withNullable(true).build());
			}
			return schemaBuilder.build();

		} else {
			return schemaBuilder.build();
		}

	}
}
