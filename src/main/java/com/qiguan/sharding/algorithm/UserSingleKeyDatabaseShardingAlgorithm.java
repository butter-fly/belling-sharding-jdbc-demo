package com.qiguan.sharding.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

/**
 *  分库计算取模
 * 
 * @author Administrator
 *
 */
public final class UserSingleKeyDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

	/**
	 * sql 中关键字 匹配符为 between的时候，表的路由函数
	 */
	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		Range<Integer> range = (Range<Integer>) shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
			for (String each : availableTargetNames) {
				if (each.endsWith(i % 2 + "")) {
					result.add(each);
				}
			}
		}
		return result; 
	}

	/**
	 * sql 中关键字 匹配符为 = 的时候，表的路由函数
	 */
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		for (String each : availableTargetNames) {
			if (each.endsWith(shardingValue.getValue() % 2 + "")) {
				return each;
			}
		}
		throw new IllegalArgumentException();  
	}

	/**
	 * sql 中关键字 匹配符为 in 的时候，表的路由函数
	 */
	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (Integer value : shardingValue.getValues()) {
			for (String tableName : availableTargetNames) {
				if (tableName.endsWith(value % 2 + "")) {
					result.add(tableName);
				}
			}
		}
		return result;
	}
}
