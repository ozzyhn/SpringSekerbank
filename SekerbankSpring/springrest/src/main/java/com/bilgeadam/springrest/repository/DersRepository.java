package com.bilgeadam.springrest.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bilgeadam.springrest.model.Ders;
import com.bilgeadam.springrest.model.Ogretmen;

@Repository
public class DersRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Ders> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"DERS\" order by \"ID\" asc", 
				BeanPropertyRowMapper.newInstance(Ders.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"DERS\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Ders getByID(long id)
	{
		Ders ders = null;
		String sql = "select * from \"public\".\"DERS\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		ders = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				BeanPropertyRowMapper.newInstance(Ders.class));
		return ders;
	}

	public boolean save(Ders drs)
	{
		String sql = "insert into \"public\".\"DERS\" (\"KONU_ID\", \"OGRETMEN_ID\") values (:KONU, :OGRETMEN)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("KONU", drs.getKONU_ID());
		paramMap.put("OGRETMEN", drs.getOGRETMEN_ID());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

}