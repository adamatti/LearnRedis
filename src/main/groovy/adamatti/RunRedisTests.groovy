package adamatti

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component

import javax.annotation.Resource

@Slf4j
@Component
class RunRedisTests {
    @Autowired
    private RedisTemplate<String, String> template

    @Resource(name="redisTemplate")
    private ValueOperations valueOps

    public static void main(String [] args){
        ApplicationContext context = new GenericGroovyApplicationContext("classpath:spring.groovy")
        context.registerShutdownHook()
        context.getBean(RunRedisTests).test()
        log.info("All working fine")
    }

    private void test(){
        println valueOps.get("name")
        valueOps.set("name", "adamatti " + System.nanoTime())
    }
}
