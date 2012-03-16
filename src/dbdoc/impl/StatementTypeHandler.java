package dbdoc.impl;

import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import dbdoc.reflect.StatementType;

/**
 * @author jk
 * 
 */
public class StatementTypeHandler implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		String value = getter.getString();
		if (getter.wasNull()) {
			return null;
		}
		return StatementType.valueOf(value);
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.VARCHAR);
		} else {
			setter.setString(((StatementType) parameter).toString());
		}
	}

	@Override
	public Object valueOf(String s) {
		return s;
	}
}
