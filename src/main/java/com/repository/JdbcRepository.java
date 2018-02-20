package com.repository;

import com.entity.Card;
import com.entity.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcRepository {
	@Autowired
    JdbcTemplate jdbcTemplate;

	class RowsRowMapper implements RowMapper<Row> {
		@Override
		public Row mapRow(ResultSet rs, int rowNum) throws SQLException {
			Row row = new Row();
			row.setId(rs.getInt("id"));
			row.setName(rs.getString("name"));
			row.setPhone(rs.getString("phone"));
			return row;
		}
	}

	class NameRowMapper implements RowMapper<String> {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("name");
		}
	}

	public List<Card> findAll() {
		List<String> names = jdbcTemplate.query("select distinct name from row", new NameRowMapper());
		List<Card> cards = new ArrayList<>();
		for (String name : names) {
			Card card = findByName(name);
			cards.add(card);
		}
		return cards;
	}

	public Card findByName(String name) {
		List<Row> rows = jdbcTemplate.query("select * from row where name='" + name + "'", new RowsRowMapper());
		if (rows.size() == 0)
			return null;

		StringBuilder sb = new StringBuilder();
		for (Row row : rows) {
			sb.append( (sb.length()>0 ? "; " : "") + row.getPhone() );
		}
		return new Card(name, sb.toString());
	}

	public int deleteRowsByName(String name) {
		return jdbcTemplate.update("delete from row where name=?", new Object[] { name });
	}

	public int insert(Card card) {
		List<String> phones = Arrays.asList(card.getPhones().split("\\s*;\\s*"));
		int[] results = jdbcTemplate.batchUpdate("replace into row (name, phone) "
						+ "values(?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
						preparedStatement.setString(1, card.getName());
						preparedStatement.setString(2, phones.get(i));
					}

					@Override
					public int getBatchSize() {
						return phones.size();
					}
				}
		);
		if (results.length==0) return 0;
		for (int i : results) {
			if (i!=1) return 0;
		}
		return 1;
	}

	// Update is done via insert because it is actually a 'replace into'
	public int update(Card card) {
		return insert(card);
	}

}
