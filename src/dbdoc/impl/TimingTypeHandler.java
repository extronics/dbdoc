package dbdoc.impl;

import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import dbdoc.reflect.Timing;

/**
 * @author jk
 * 
 */
public class TimingTypeHandler implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		String value = getter.getString();
		if (getter.wasNull()) {
			return null;
		}
		return Timing.valueOf(value);
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.VARCHAR);
		} else {
			setter.setString(((Timing) parameter).toString());
		}
	}

	@Override
	public Object valueOf(String s) {
		return s;
	}
}
