package config.msg;

import java.util.Date;
import java.util.ListResourceBundle;

public class ClassConsMsg extends ListResourceBundle {
	private static final Object[][] contents = { 
				{ "Name", "Name_default"}, 
				{ "Birthday", new Date()} 
			};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}