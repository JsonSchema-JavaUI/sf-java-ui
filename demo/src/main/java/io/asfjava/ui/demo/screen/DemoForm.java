package io.asfjava.ui.demo.screen;

import java.io.Serializable;

import io.asfjava.ui.core.form.Index;
import io.asfjava.ui.core.form.Tab;
import io.asfjava.ui.core.form.TextField;

public class DemoForm implements Serializable {

	@Index(1)
	@TextField(title = "index 1")
	private String index1;

	@Tab(index = 2, title = "Tab2")
	@Index(0)
	@TextField(title = "index 2")
	private String index2;

	@Tab(index = 3, title = "Tab1")
	@Index(2)
	@TextField(title = "Tab1 f3 index 2")
	private String index3;

	@Tab(index = 3, title = "Tab1")
	@Index(1)
	@TextField(title = "Tab1 f4 index 1")
	private String index4;

	@Tab(index = 2, title = "Tab2")
	@TextField(title = "Tab2 f5 index 0")
	private String index5;

	private static final long serialVersionUID = -5073515619469444978L;

	public String getIndex1() {
		return index1;
	}

	public String getIndex2() {
		return index2;
	}

	public String getIndex4() {
		return index4;
	}

	public String getIndex5() {
		return index5;
	}

	public String getIndex3() {
		return index3;
	}

}
