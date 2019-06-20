package xin.liujiajun.mybatis.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import xin.liujiajun.mybatis.model.GenderEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LiuJiaJun
 * @date 2018/9/27 10:31
 */
public class GenderEnumTypeHandler extends BaseTypeHandler<GenderEnum> {

    private Class<GenderEnum> gender;

    private final GenderEnum [] genderEnums;

    public GenderEnumTypeHandler(Class<GenderEnum> gender){
        this.gender = gender;
        this.genderEnums = null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GenderEnum genderEnum, JdbcType jdbcType) throws SQLException {
         preparedStatement.setInt(i,genderEnum.getCode());
    }

    @Override
    public GenderEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return GenderEnum.getEnum(resultSet.getInt(s));
    }

    @Override
    public GenderEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return GenderEnum.getEnum(resultSet.getInt(i));
    }

    @Override
    public GenderEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return GenderEnum.getEnum(callableStatement.getInt(i));
    }
}
