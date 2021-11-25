package com.witi.templatemethod.pattern;

import com.witi.factory.pattern.ConnectionFactory;
import com.witi.templatemethod.util.SqlTemplateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlTemplate<T> {

    private ConnectionFactory connectionFactory;

    public SqlTemplate(ConnectionFactory connection) {
        this.connectionFactory = connection;
    }

    protected abstract T adapterEntity(ResultSet rs) throws SQLException;

    public List<T> selectMany(String sql) {
        return selectMany(sql, null);
    }

    public List<T> selectMany(String sql, Object... params) {

        List<T> entities = new ArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = connectionFactory.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                ps = addParams(ps, params);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                entities.add(adapterEntity(rs));
            }

        } catch (Exception ex) {
            throw new SqlTemplateException("SqlTemplate couldn t execute " + sql, ex);
        } finally {
            closePrepareStatement(ps);
            closeConnection(connection);
            closeResultSet(rs);
        }
        return entities;
    }

    public T selectOne(String sql) {
        return selectOne(sql, null);
    }

    public T selectOne(String sql, Object... params) {

        T entity = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            connection = connectionFactory.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                ps = addParams(ps, params);
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                entity = adapterEntity(rs);
            }

        } catch (Exception ex) {
            throw new SqlTemplateException("SqlTemplate couldn't execute " + sql, ex);
        } finally {
            closePrepareStatement(ps);
            closeConnection(connection);
            closeResultSet(rs);
        }
        return entity;
    }

    public int update(String sql) {
        return update(sql, null);
    }

    public int update(String sql, Object... params) {

        int updated = 0;
        Connection connection = null;
        PreparedStatement ps = null;

        try {

            connection = connectionFactory.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                ps = addParams(ps, params);
            }
            updated =  ps.executeUpdate();

        } catch (Exception ex) {
            throw new SqlTemplateException("SqlTemplate couldn't execute " + sql, ex);
        } finally {
            closePrepareStatement(ps);
            closeConnection(connection);
        }
        return updated;
    }

    protected void closePrepareStatement(PreparedStatement ps) {
        try {
           if (ps != null) {
               ps.close();
           }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    protected void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    protected void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Agrega parametros a un preparedstatement dinamicamente identificando el
     * tipo de objeto y asignando su indice.
     *
     * @param params Ojetos a incluir en parametros de consulta, se agregan
     * sin importar la cantidad.
     * @throws SQLException
     */
    protected PreparedStatement addParams(PreparedStatement preparedStatement, Object... params) throws SQLException {
        int index = 1;
        for (Object param : params) {
            if (param instanceof String) {
                preparedStatement.setString(index, (String) param);
            } else if (param instanceof Integer) {
                preparedStatement.setInt(index, (int) param);
            } else if (param instanceof Long) {
                preparedStatement.setLong(index, (long) param);
            } else if (param instanceof Double) {
                preparedStatement.setDouble(index, (double) param);
            } else if (param instanceof java.util.Date) {
                java.util.Date time = (java.util.Date) param;
                preparedStatement.setTimestamp(index, new java.sql.Timestamp(time.getTime()));
            } else if (param instanceof Boolean) {
                preparedStatement.setBoolean(index, Boolean.parseBoolean(param.toString().trim()));
            } else if (param == null) {
                String expectedClassName = preparedStatement.getParameterMetaData().getParameterClassName(index);
                if (expectedClassName.equals("java.lang.String")) {
                    preparedStatement.setString(index, null);
                } else if (expectedClassName.equals("java.lang.Integer")) {
                    preparedStatement.setInt(index, 0);
                } else if (expectedClassName.equals("java.lang.Long")) {
                    preparedStatement.setLong(index, 0);
                } else if (expectedClassName.equals("java.lang.Double")) {
                    preparedStatement.setDouble(index, 0);
                } else if (expectedClassName.equals("java.lang.Boolean")) {
                    preparedStatement.setBoolean(index, false);
                } else if (expectedClassName.equals("java.util.Date")) {
                    preparedStatement.setTimestamp(index, null);
                } else {
                    String databaseType = preparedStatement.getParameterMetaData()
                            .getParameterTypeName(index);
                    throw new NullPointerException("param at index " + index
                            + " is null, ypu must to set "
                            + expectedClassName + " for " + databaseType + " type");
                }

            } else {
                throw new IllegalArgumentException(param.getClass().getName() + " not supported for PreparedStatement, parameter nº " + index);
            }
            index++;
        }
        return preparedStatement;
    }

}
