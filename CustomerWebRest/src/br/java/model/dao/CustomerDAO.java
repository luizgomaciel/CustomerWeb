package br.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.java.model.bean.Customer;

@Repository("dbQuery")
public class CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateMysql;

	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomers() {
		List<Customer> customers = null;

		String sql = "SELECT ID, RAZAO, NOME, CPFCNPJ, TELEFONE, STATS FROM CUSTOMER ";

		try {
			customers = (List<Customer>) jdbcTemplateMysql.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					return ps;
				}
			}, new RowMapper() {

				@Override
				public List<Customer> mapRow(ResultSet rs, int arg1) throws SQLException {
					List<Customer> cust = new ArrayList<Customer>();
					Customer c;
					while (rs.next()) {
						c = new Customer();
						c.setId(rs.getLong("ID"));
						c.setNome(rs.getString("NOME"));
						c.setRazao(rs.getString("RAZAO"));
						c.setCpfcnpj(rs.getString("CPFCNPJ"));
						c.setStats(rs.getString("STATS"));
						c.setTelefone("TELEFONE");
						cust.add(c);
					}

					return cust;
				}
			});

		} catch (Exception e) {
		}
		return customers;
	}

	@SuppressWarnings("unchecked")
	public void includeCustomer(Customer customer) {

		try {

			StringBuilder query = new StringBuilder();
			query.append(" INSERT INTO CUSTOMER (RAZAO, NOME, CPFCNPJ, TELEFONE) VALUES (?,?,?,?) ");

			jdbcTemplateMysql.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

					PreparedStatement psst = connection.prepareStatement(query.toString());
					psst.setString(1, customer.getRazao());
					psst.setString(2, customer.getNome());
					psst.setString(3, customer.getCpfcnpj());
					psst.setString(4, customer.getTelefone());
					return psst;

				}
			});

		} catch (Exception e) {
		}
	}

}
