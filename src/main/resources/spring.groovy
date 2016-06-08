URI redisUri
if (System.env.REDISCLOUD_URL){
    redisUri = new URI(System.env.REDISCLOUD_URL)
}


beans {
    xmlns([context:"http://www.springframework.org/schema/context"])

    context."component-scan"("base-package":"adamatti")

    jedisConnFactory(org.springframework.data.redis.connection.jedis.JedisConnectionFactory){
        if (redisUri) {
            hostName = redisUri.host
            port = redisUri.port
            password = redisUri.userInfo.split(":", 2)[1]
        }
        usePool = true
    }

    redisTemplate(org.springframework.data.redis.core.RedisTemplate){
        connectionFactory = ref('jedisConnFactory')
    }
}
