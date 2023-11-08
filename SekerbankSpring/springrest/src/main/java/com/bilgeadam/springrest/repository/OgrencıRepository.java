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

import com.bilgeadam.springrest.model.Ogrenci;
import com.bilgeadam.springrest.model.Ogretmen;

@Repository
public class OgrencıRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Ogrenci> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"OGRENCİ\" order by \"ID\" asc",
				BeanPropertyRowMapper.newInstance(Ogrenci.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"OGRENCİ\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public Ogrenci getByID(long id)
	{
		Ogrenci ogrenci = null;
		String sql = "select * from \"public\".\"OGRENCİ\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		ogrenci = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
		// ---------------------------------
		/*
		 * ResultSetExtractor<Ogretmen> rse = new ResultSetExtractor<Ogretmen>() {
		 * 
		 * @Override public Ogretmen extractData(ResultSet result) throws SQLException,
		 * DataAccessException { return new Ogretmen(result.getInt("ID"),
		 * result.getString("NAME"), result.getBoolean("IS_GICIK")); } };
		 * namedParameterJdbcTemplate.query(sql, paramMap, rse);
		 */
		// ---------------------------------
		// Incorrect column count: expected 1, actual 3
		// namedParameterJdbcTemplate.queryForObject(sql, paramMap, Ogretmen.class);
		return ogrenci;
	}

	public boolean save(Ogrenci ogr)
	{
		String sql = "insert into \"public\".\"OGRENCİ\" (\"NAME\", \"OGR_NUMBER\") values (:NAME, :OGR_NUMBER,:YEAR )";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", ogr.getNAME());
		paramMap.put("OGR_NUMBER", ogr.getOGR_NUMBER());
		paramMap.put("YEAR", ogr.getYEAR());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public List<Ogretmen> getAllLike(String name)
	{
		String sql = "select * from \"public\".\"OGRETMEN\" where \"NAME\" LIKE :NAME";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("NAME", "%" + name + "%"); // % işareti parameter içersinde olacak
		return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogretmen.class));
	}

//	public boolean update(long id, Ogretmen ogr) throws SQLException
//	{
//	}
}