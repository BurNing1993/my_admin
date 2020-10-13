# my_admin

## IDEA

### 启动参数

```sh
-Dserver.port=8888
```

## JPA

### [Entity常用注解](https://www.jianshu.com/p/38d247f02724)

- @Entity  @Table(name = "t_user")

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class User {}
```

- @Id(主键) @GeneratedValue(strategy= GenerationType.AUTO)

```java
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
private Long userId;
```

-  @CreatedDate @LastModifiedDate

```java
// 配合 @EntityListeners(AuditingEntityListener.class) 使用
@CreatedDate
private Timestamp createTime;

@LastModifiedDate
private Timestamp updateTime;
```

- @Column

```java
@Column(name = "username",nullable = false,unique = true,length = 64)
private String username;
```

### [分页查询](https://www.jianshu.com/p/e7882c4f29b6)

> ***Repository extends PagingAndSortingRepository<T,ID> 实现分页排序功能
> ***Repository extends JpaSpecificationExecutor<T> 实现查询功能

```java
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
}
```

```java
public interface UserService {
    Page<User> getUserPage(User user, Pageable pageable);
}
```

```java
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDO> getUserPage(UserDO userDO, Pageable pageable) {
        Specification<UserDO> userDOSpecification =new Specification<UserDO>() {
            @Override
            public Predicate toPredicate(Root<UserDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.like(root.get("nickname"),"%"+userDO.getNickname()+"%");
                return p1;
            }
        };
        return userRepository.findAll(userDOSpecification,pageable);
    }
}
```

```java
@RestController
public class UserDemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public Page<UserDO> getUserPage(@RequestParam(value = "nickname") String nickname,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        UserDO userDO = new UserDO();
        userDO.setNickname(nickname);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "userId");
        return userService.getUserPage(userDO, pageable);
    }
}
```

### JPA 初始化数据

<https://blog.csdn.net/u012326462/article/details/82080812>

## JWT

```xml
<!--pom.xml-->
<!--JWT-->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.11.0</version>
</dependency>
```

## [参数校验](https://gitee.com/huangxunhui/unifiedParamCheck)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### 常用注解


| 注解                                           | 运行时检查                                                   |
| ---------------------------------------------- | ------------------------------------------------------------ |
| @AssertFalse                                   | 被注解的元素必须为false                                      |
| @AssertTrue                                    | 被注解的元素必须为true                                       |
| @DecimalMax(value)                             | 被注解的元素必须为一个数字，其值必须小于等于指定的最小值     |
| @DecimalMin(Value)                             | 被注解的元素必须为一个数字，其值必须大于等于指定的最小值     |
| @Digits(integer=, fraction=)                   | 被注解的元素必须为一个数字，其值必须在可接受的范围内         |
| @Future                                        | 被注解的元素必须是日期，检查给定的日期是否比现在晚           |
| @Max(value)                                    | 被注解的元素必须为一个数字，其值必须小于等于指定的最小值     |
| @Min(value)                                    | 被注解的元素必须为一个数字，其值必须大于等于指定的最小值     |
| @NotNull                                       | 被注解的元素必须不为null                                     |
| @Null                                          | 被注解的元素必须为null                                       |
| @Past(java.util.Date/Calendar)                 | 被注解的元素必须过去的日期，检查标注对象中的值表示的日期比当前早 |
| @Pattern(regex=, flag=)                        | 被注解的元素必须符合正则表达式，检查该字符串是否能够在match指定的情况下被regex定义的正则表达式匹配 |
| @Size(min=, max=)                              | 被注解的元素必须在制定的范围(数据类型:String, Collection, Map and arrays) |
| @Valid                                         | 递归的对关联对象进行校验, 如果关联对象是个集合或者数组, 那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验 |
| @CreditCardNumber                              | 对信用卡号进行一个大致的验证                                 |
| @Email                                         | 被注释的元素必须是电子邮箱地址                               |
| @Length(min=, max=)                            | 被注解的对象必须是字符串的大小必须在制定的范围内             |
| @NotBlank                                      | 被注解的对象必须为字符串，不能为空，检查时会将空格忽略       |
| @NotEmpty                                      | 被注释的对象必须为空(数据:String,Collection,Map,arrays)      |
| @Range(min=, max=)                             | 被注释的元素必须在合适的范围内 (数据：BigDecimal, BigInteger, String, byte, short, int, long and 原始类型的包装类 ) |
| @URL(protocol=, host=, port=, regexp=, flags=) | 被注解的对象必须是字符串，检查是否是一个有效的URL，如果提供了protocol，host等，则该URL还需满足提供的条件 |

