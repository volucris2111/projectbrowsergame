package com.browsergame.project.cache;

import java.util.HashMap;
import java.util.Map;

import com.browsergame.project.cache.factory.CacheFactory;
import com.browsergame.project.field.datatransfer.Field;

public class Cache
{

	Map<String, Field> CacheMap = new HashMap<String, Field>();

	public Cache()
	{
		CacheFactory cacheFactory = new CacheFactory();
		this.CacheMap = cacheFactory.createCache();
	}

	public Map<String, Field> getCacheMap()
	{
		return this.CacheMap;
	}

	public void setCacheMap(Map<String, Field> cacheMap)
	{
		this.CacheMap = cacheMap;
	}

}
