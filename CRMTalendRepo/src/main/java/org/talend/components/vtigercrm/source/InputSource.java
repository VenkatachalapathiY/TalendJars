package org.talend.components.vtigercrm.source;

import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.talend.sdk.component.api.configuration.Option;
import org.talend.sdk.component.api.input.Producer;
import org.talend.sdk.component.api.meta.Documentation;
import org.talend.sdk.component.api.service.record.RecordBuilderFactory;
import com.vtiger.vtwsclib.WSClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.talend.components.vtigercrm.service.VtigercrmComponentsService;

@Documentation("TODO fill the documentation for this source")
public class InputSource implements Serializable {
	private final InputMapperConfiguration configuration;
	private final VtigercrmComponentsService service;
	private final RecordBuilderFactory builderFactory;
	// private BufferizedProducerSupport<JsonArray> bufferedReader;

	public InputSource(@Option("configuration") final InputMapperConfiguration configuration,
			final VtigercrmComponentsService service, final RecordBuilderFactory builderFactory) {
		this.configuration = configuration;
		this.service = service;
		this.builderFactory = builderFactory;
	}

	@PostConstruct
	public void init() {

		_current = 0;

		String module = configuration.getDataset().getModule();
		String URL = configuration.getDataset().getConnection().getVtigerCRM_URL();
		String username = configuration.getDataset().getConnection().getUsername();
		String access = configuration.getDataset().getConnection().getAccessKey();

		WSClient client = new WSClient(URL);
		boolean result = client.doLogin(username, access);
		if (result == false) {
			System.out.println("Login failed!");
			System.out.println(client.lastError());

		} else {
			queryResult = client.doQuery("SELECT * FROM " + module);
			if (client.hasError(queryResult)) {
				System.out.println("Query failed!" + client.lastError());
			} else {
				_limit = queryResult.size();

				Iterator resultIterator = queryResult.iterator();
				while (resultIterator.hasNext()) {
					list1 = (JSONObject) resultIterator.next();

					Iterator rowIterator = list1.keySet().iterator();
					// System.out.println("---");
					while (rowIterator.hasNext()) {
						Object key = rowIterator.next();
						Object val = list1.get(key);

					}
				}
			}
		}
	}

	@Producer
	public JSONObject next() {
		final JSONObject next;

		if (_current == _limit) {
			return null;

		} else {
			next = _createJson(_current++);
			return next;
		}

	}

	private JSONObject _createJson(int seed) {
		list1 = (JSONObject) queryResult.get(seed);
		return list1;
	}

	@PreDestroy
	public void release() {
		// this is the symmetric method of the init() one,
		// release potential connections you created or data you cached

		System.out.println("closed connection");
	}

	private int _limit;
	private int _current;
	private JSONObject list1;
	private JSONArray queryResult;
}