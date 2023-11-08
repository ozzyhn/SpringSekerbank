package com.bilgeadam.springrest.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bilgeadam.springrest.model.Konu;


public class KonuRepository {
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public List<Konu> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"KONU\" order by \"ID\" asc", BeanPropertyRowMapper.newInstance(Konu.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"KONU\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Konu getByID(long id)
	{
		Konu konu = null;
		String sql = "select * from \"public\".\"KONU\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		konu = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				BeanPropertyRowMapper.newInstance(Konu.class));
	
		return konu;
	}

	public boolean save(Konu knu)
	{
		String sql = "insert into \"public\".\"KONU\" (\"NAME\") values (:NAME)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", knu.getNAME());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Konu> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"KONU\" where \"NAME\" LIKE :NAME";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap,
				BeanPropertyRowMapper.newInstance(Konu.class));
	}

}
