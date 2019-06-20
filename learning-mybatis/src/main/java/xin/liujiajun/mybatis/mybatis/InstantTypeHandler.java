package xin.liujiajun.mybatis.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 10:44
 */
public class InstantTypeHandler extends BaseTypeHandler<Instant> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Instant instant, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i,instant == null ?  0L : instant.getEpochSecond());
    }

    @Override
    public Instant getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Instant.ofEpochSecond(resultSet.getLong(s));
    }

    @Override
    public Instant getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Instant.ofEpochSecond(resultSet.getLong(i));
    }

    @Override
    public Instant getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Instant.ofEpochSecond(callableStatement.getLong(i));
    }
}
