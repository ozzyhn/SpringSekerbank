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

import com.bilgeadam.springrest.model.DersOgrenci;
import com.bilgeadam.springrest.model.Ogretmen;

@Repository
public class DersOgrencıRepository
{
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(@Qualifier(value = "bizimjdbctemplate") JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<DersOgrenci> getAll()
	{
		return jdbcTemplate.query("select * from \"public\".\"DERS_OGR\" order by \"ID\" asc",
				BeanPropertyRowMapper.newInstance(DersOgrenci.class));
	}

	public boolean deleteByID(long id)
	{
		String sql = "delete from \"public\".\"DERS_OGR\" where \"ID\" = :ID";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ID", id);
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

	public DersOgrenci getByID(long id)
	{
		DersOgrenci dersogr = null;
		String sql = "select * from \"public\".\"DERS_OGR\" where \"ID\" = :ABUZIDDIN";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ABUZIDDIN", id);
		dersogr = namedParameterJdbcTemplate.queryForObject(sql, paramMap,
				BeanPropertyRowMapper.newInstance(DersOgrenci.class));
		return dersogr;
	}

	public boolean save(DersOgrenci dersogr)
	{
		String sql = "insert into \"public\".\"DERS_OGR\" (\"DEVAMSIZLIK\", \"OGR_ID\",\" NOTE\") values (:DEVAM, :OGRID, : NOTE)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("DEVAM", dersogr.getDEVAMSIZLIK());
		paramMap.put("OGRID", dersogr.getOGR_ID());
		paramMap.put("NOTE", dersogr.getNOTE());
		return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
	}

}