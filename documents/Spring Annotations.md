# 记录各种注解

@After -- AOP的注解，相应方法会在目标方法‌执行之后‌（无论成功或异常）
@AfterReturning -- AOP的注解，相应方法会在目标方法‌执行成功之后
@AfterThrowing -- AOP的注解，相应方法会在目标方法‌执行抛出异常之后
@Around -- AOP的注解，相应方法会在目标方法‌执行前和后被调用，无论成功或异常
@Aspect -- AOP的注解，声明一个切面
@Async -- 注解用于标记异步执行的方法。当您在配置类上添加@EnableAsync后，被@Async标记的方法将在调用时立即返回，其实际执行会交由TaskExecutor在后台线程中完成，这非常适合处理耗时操作以提高应用响应能力。需要注意的是，异步方法默认的返回值类型是void或Future。
@AutoConfigurationPackage -- @AutoConfigurationPackage是SpringBoot自动配置机制中的关键注解之一，它通常由@SpringBootApplication注解隐式引入。它的主要作用是记录注解所在包路径作为自动配置的基础包。SpringBoot会扫描该包及其子包下的组件（如@Component,@Service,@Repository等），并将它们注册到Spring容器中。
@Autowired -- @Autowired是Spring框架实现依赖注入（DI）的核心注解。它可以标注在构造器、Setter方法或属性字段上。Spring容器会自动查找匹配的Bean，并将其注入到目标位置，从而解耦组件间的依赖关系。
@Bean -- @Bean注解则用于显式定义单个Bean。它通常标注在配置类（被@Configuration注解的类）的方法上。该方法的返回值将被Spring容器注册为一个Bean，方法名默认作为该Bean的名称。当需要控制Bean的实例化过程，或者将第三方库的类纳入Spring管理时，@Bean注解非常有用。
@Before -- AOP的注解，相应方法会在目标方法‌执行之前
@Cacheable -- 用于声明方法的返回值是可缓存的。当方法被调用时，Spring会先检查缓存中是否已存在对应的键，如果存在则直接返回缓存值，否则执行方法并将结果存入缓存 @Cacheable(value = "users", condition = "#id > 10") / @Cacheable(value = "users", unless = "#result == null") / @Cacheable(value = "orders", key = "#userId + '-' + #orderType")
@CacheEvict -- 用于清除缓存数据，通常在更新或删除操作后使用，确保缓存与数据源保持一致 @CacheEvict(value = "products", key = "#id") / @CacheEvict(value = "products", allEntries = true) / @CacheEvict(value = "users", condition = "#user.id > 10")
@CachePut -- 总是执行方法，并使用返回值更新缓存，适用于需要同步缓存与数据源的场景
@Component -- 是一个通用 stereotype 注解，标记一个类为Spring容器管理的组件。Spring会自动检测带有此注解的类，并创建对应的bean实例，与controller，service，repository同一级概念
@ComponentScan -- 配置Spring在哪些包路径下扫描带有@Component及其派生注解的类，并将其注册为bean
@Conditional -- 是Spring条件化配置的基础注解，从Spring 4.0开始提供，用于根据特定条件决定是否向IoC容器中注册Bean。它可以标注在类或方法上，只有当其指定的所有Condition实现都返回true时，被注解的组件才会被创建
@CondtiionalOnClass -- 该注解用于检查系统中是否存在指定的类，只有在类路径下能找到这些类时，被注解的配置才会生效
@CondtiionalOnEnabledHealthIndicator -- 这是一个相对特殊的条件注解，专门用于控制健康检查指示器(Health Indicator)的启用状态
@ConditionalOnMisingBean -- 当容器中不存在指定类型的Bean时，被该注解标记的配置才会生效
@ConditionalOnProperty -- 此注解根据配置文件（如application.properties）中的属性值来决定是否启用配置
@ConditionalOnWebApplication -- 这是一个条件化注解，用于在Spring Boot应用中根据当前应用是否为Web应用来决定是否创建特定的Bean或配置类
@Configurable -- 这个注解主要用于AspectJ的编译时织入，它允许Spring容器管理那些并非由Spring直接创建的对象的依赖注入。例如，当使用new操作符实例化的对象也需要自动注入Spring管理的Bean时，可以在该类上添加@Configurable，并结合AspectJ配置实现
@Configuration -- 注解用于标记一个类为配置类，表明该类将定义Spring应用上下文中的Bean
@ConfigurationProperties -- 这个注解用于将外部配置文件（如application.properties或application.yml）中的属性批量绑定到Java对象上
@Controller -- 是Spring MVC框架中的核心注解，用于标记一个类作为Web请求的处理器
@CrossOrigin -- 注解用于启用和处理跨域资源共享（CORS）请求，它允许Web应用绕过浏览器的同源策略，安全地访问不同源的资源
@DependsOn -- 注解用于显式指定Spring Bean的初始化顺序
@EnableAspectJAutoProxy -- 注解用于在基于Java配置的Spring应用中启用对AspectJ切面的自动代理支持，Spring容器会自动扫描并处理标记有 @Aspect 的组件
@EnableAsync -- 注解用于开启Spring的异步方法执行功能，在配置类上添加此注解后，可以在任何Spring管理的Bean的方法上使用 @Async 注解来使其异步执行。这通常用于处理耗时操作，提升应用的响应性和吞吐量
@EnableAutoConfiguration -- 是Spring Boot自动配置机制的核心注解之一。它根据应用中引入的jar包依赖、已定义的Bean以及各种属性设置，自动推断并配置Spring应用所需的组件。这是Spring Boot“约定优于配置”理念的关键体现，极大地减少了手动配置的工作量
@EnableConfigurationProperties -- 注解用于启用配置属性绑定机制，它允许将外部配置文件中的属性值自动注入到对应的Java Bean中
@EnableLoadTimeWeaving -- 注解用于启用AspectJ的加载时织入功能，允许在类加载时动态地将切面代码织入到目标类中
@Entity -- 注解用于标记一个类为JPA实体，表示该类的实例将对应数据库中的一条记录
@EventListener -- 注解用于将普通方法标记为事件监听器‌，实现基于事件的松耦合通信机制
@ExceptionHandler -- 注解是Spring MVC异常处理机制的核心，提供声明式的方法来处理控制器抛出的异常
@GetMapping -- 注解是Spring MVC中用于处理HTTP GET请求的专用注解，它是@RequestMapping(method = RequestMethod.GET)的简写形式，专门用于简化RESTful API的开发
@HandlesTypes -- 注解用于在Servlet容器启动时指定需要处理的接口类型，它是Servlet 3.0+规范中ServletContainerInitializer机制的重要组成部分
@Import -- 注解允许在配置类中导入其他配置类或组件，实现Spring配置的模块化和动态化
@Indexed -- 是Spring 5.0引入的性能优化利器‌，通过在编译时生成组件索引来大幅提升大型应用的启动速度
@Inject -- 注解是JSR-330标准定义的依赖注入注解，提供了与Spring原生@Autowired类似的功能，但遵循Java标准规范
@Lazy -- 注解用于控制Bean的初始化时机，默认情况下Spring容器启动时会立即创建所有单例Bean。使用@Lazy可以延迟Bean的创建，直到第一次被请求时才进行初始化
@Mapper -- 是MyBatis框架中的注解，用于标记接口作为数据映射器。被标注的接口会被MyBatis扫描并生成代理实现类
@MapperScan -- 用于在Spring配置类中指定MyBatis映射器接口的扫描路径，通常用在@Configuration类上
@ModelAttribute -- 在Spring MVC中用于将请求参数绑定到模型对象，或在方法级别将返回值添加到模型
@NotNull -- 用于确保被标注的字段或参数不为null，是Spring数据验证体系中的基础注解。它通常与@Valid或@Validated注解配合使用，在Controller层进行参数校验
@Order -- 定义Spring组件在集合中的执行顺序，数值越小优先级越高
@PathVariable -- 用于从URL路径中提取变量值并绑定到方法参数上，是构建RESTful API的核心工具
@Pointcut -- 用于定义AOP的切入点，即指定在哪些方法执行时植入增强逻辑@Pointcut("execution(* com.example.service.*.*(..))")
@PostConstruct -- 标记在依赖注入完成后执行的方法，用于完成资源初始化等操作
@PostMapping -- 专门用于映射HTTP POST请求到处理器方法。它是@RequestMapping(method = RequestMethod.POST)的简写形式，常用于处理表单提交或创建新资源的请求
@PreDestroy -- 是JSR-250规范中的生命周期注解，用于标记在Bean实例被容器销毁之前需要执行的方法。这个方法通常用于释放资源，例如关闭数据库连接、清理缓存或执行其他清理任务
@Primary -- 注解的核心作用在于指定“首选”Bean‌。当 Spring 容器中存在多个相同类型的 Bean 候选者，导致自动装配出现歧义时，@Primary 会标记其中一个 Bean 作为默认选择，从而消除歧义。这就像一个团队在多个方案中投票选出一个“主推方案”。
@Profile -- 注解用于根据特定环境条件来注册Bean。通过指定一个或多个Profile名称，可以控制Bean在哪些环境下被激活。例如，@Profile("dev")表示该Bean仅在"dev"Profile激活时才会被创建，这在区分开发、测试和生产环境配置时非常有用
@PropertySource -- 用于声明外部属性文件的位置，并将这些属性加载到Spring的Environment中，@PropertySource("classpath:config-prod.properties")读取一个具体的配置文件
@Qualifier -- 当Spring容器中存在多个相同类型的Bean时，@Qualifier注解用于指定具体要注入哪个Bean
@Repository -- Spring框架中专门用于标记数据访问层(DAO)组件的注解
@RequestBody -- 用于将HTTP请求体中的内容绑定到控制器方法的参数上
@RequestMapping -- @RequestMapping是Spring MVC中用于建立URL到控制器方法映射的核心注解
@RequestParam -- 用于从HTTP请求的查询参数中获取值，并将其绑定到控制器方法的参数上
@Resource -- @Resource是Java EE标准定义的依赖注入注解，在Spring框架中得到了完整支持。该注解支持按名称(name)和按类型(type)两种注入方式
@ResponseBody -- @ResponseBody注解主要用于控制层方法，其核心功能是将方法的返回值直接写入HTTP响应体中。该注解的实现依赖于Spring MVC的HttpMessageConverters机制，能够自动将对象转换为JSON格式。
@RestController -- @RestController是Spring 4.0引入的组合注解，相当于@Controller + @ResponseBody的结合体。使用该注解的类会被自动识别为控制器，且所有方法的返回值都会直接作为HTTP响应体内容。特别适合构建RESTful API服务，但无法返回JSP或HTML页面视图。
@Scope
@Service -- Spring框架中专门用于标记业务逻辑层组件的注解
@ServletComponentScan -- 注解用于扫描并注册Servlet、Filter和Listener组件
@SonTransactional
@SpringBootApplication
@SpringBootConfiguration
@Timed
@Transactional
@Valid
@Value
@WebFilter
@WebListener
@WebServlet