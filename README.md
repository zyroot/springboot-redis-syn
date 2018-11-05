# springboot-redis-syn
redis 分布式锁 工具类
     1,引入依赖
     <!--redis依赖-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
    
    2.导入reds封装工具类  并注入到要用场景
    
     @Autowired
    private RedisSynUtil redisSynUtil;
    
    3.调用方法
    
    实例：
     @ResponseBody
    @RequestMapping("/test.do")
    public String  test(){

        //设置延迟时间
        long l = System.currentTimeMillis() + Long.valueOf(10);

        //上锁
        if(!redisSynUtil.lock("1",String.valueOf(l))){
            //排队中
            System.out.println("抛出异常，人太多了，换个姿势再试试");
        }

        //业务逻辑
        System.out.println("呵呵");

        //解锁
        redisSynUtil.unlock("1",String.valueOf(l));

        return  "成功";
    }
