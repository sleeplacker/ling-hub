package clients.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

/**
 * Redission客户端使用
 *
 * RedissionPractices/clients.redission.RedissioinTest.java
 *
 * author lingang
 *
 * createTime 2021-07-08 16:56:22 
 *
 */
public class RedissioinTest {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSentinelServers().setMasterName("mymaster")
				// 可以用"rediss://"来启用SSL连接
				.addSentinelAddress("redis://127.0.0.1:26379", "redis://127.0.0.1:26380", "redis://127.0.0.1:26381");
		config.setCodec(StringCodec.INSTANCE);// 设置序列号方式
		RedissonClient redisson = Redisson.create(config);
		System.out.println(redisson.getBucket("aa").get());
		redisson.shutdown();
	}
}
