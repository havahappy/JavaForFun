package de.example.mybatis.repository.mapper;

import java.util.Set;

import de.example.mybatis.mapper.filter.MyBatisScanFilter;
import de.example.mybatis.model.Ad;


public interface AdCustomMapper extends MyBatisScanFilter {

	Set<Ad> selectAds();
}
