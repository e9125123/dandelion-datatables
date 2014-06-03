package com.github.dandelion.datatables.core.processor;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.github.dandelion.datatables.core.configuration.ColumnConfiguration;
import com.github.dandelion.datatables.core.configuration.ConfigToken;
import com.github.dandelion.datatables.core.configuration.TableConfig;
import com.github.dandelion.datatables.core.configuration.TableConfiguration;

public class StringBuilderProcessorTest {

	protected TableConfiguration tableConfiguration;
	protected ColumnConfiguration columnConfiguration;
	protected HttpServletRequest request;
	protected Map<ConfigToken<?>, Object> confToBeApplied;

	@Before
	public void setup() {
		MockServletContext mockServletContext = new MockServletContext();
		MockPageContext mockPageContext = new MockPageContext(mockServletContext);
		request = (HttpServletRequest) mockPageContext.getRequest();
		confToBeApplied = new HashMap<ConfigToken<?>, Object>();
		tableConfiguration = new TableConfiguration(confToBeApplied, request);
		columnConfiguration = new ColumnConfiguration();
	}

	@Test
	public void should_update_the_table_entry() throws Exception{
		Entry<ConfigToken<?>, Object> entry = new MapEntry<ConfigToken<?>, Object>(TableConfig.CSS_CLASS, "someString");
		ConfigurationProcessor processor = new StringBuilderProcessor();
		processor.process(entry, tableConfiguration);
		assertThat(entry.getValue().toString()).isEqualTo("someString");
	}
	
	@Test
	public void should_update_the_column_entry() throws Exception{
		Entry<ConfigToken<?>, Object> entry = new MapEntry<ConfigToken<?>, Object>(TableConfig.CSS_CLASS, "someString");
		ConfigurationProcessor processor = new StringBuilderProcessor();
		processor.process(entry, columnConfiguration, tableConfiguration);
		assertThat(entry.getValue().toString()).isEqualTo("someString");
	}
}