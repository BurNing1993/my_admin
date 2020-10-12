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