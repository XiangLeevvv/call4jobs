# Redis

#### 1. Nosql适合处理哪些数据

- 用户的个人信息
- 社交网络
- 地理位置
- 用户日志等

#### 2. 什么是Nosql

**Nosql = not only sql**，泛指非关系型数据库

#### 3. Nosql的特点

```
- 方便扩展（数据之间没有关系）

- 海量数据，高性能（每秒8万次）

- 多种数据类型（5种基础类型，3种特殊类型）

大数据时代：高并发、高可扩展、高性能（海量、多样、实时）

Nosql+RDBMS(Relational Database Management System)
```

#### 4. Nosql的四大分类

```
KV键值对：内容缓存，主要用于处理高并发访问，也可以用于一些日志系统（Redis）
文档型数据库：主要用于处理大量文档（MongoDb、CouchDB，MongoDB是一个介于关系型数据库和非关系型数据库中的中间产品，MongoDB是Nosql中功能最丰富，也是Nosql中最像关系型数据库的）
图形数据库：常用于社交网络和构建关系图谱（Neo4j）
列存储数据库：HBase、分布式文件系统
```

| **分类**                 |                  **Examples举例**                  |                         典型应用场景                         | 数据模型                                           |                             优点                             |                             缺点                             |
| ------------------------ | :------------------------------------------------: | :----------------------------------------------------------: | -------------------------------------------------- | :----------------------------------------------------------: | :----------------------------------------------------------: |
| **键值（key-value）[3]** | Tokyo Cabinet/Tyrant, Redis, Voldemort, Oracle BDB | 内容缓存，主要用于处理大量数据的高访问负载，也用于一些日志系统等等。[3] | Key 指向 Value 的键值对，通常用hash table来实现[3] |                          查找速度快                          |      数据无结构化，通常只被当作字符串或者二进制数据[3]       |
| **列存储数据库[3]**      |               Cassandra, HBase, Riak               |                       分布式的文件系统                       | 以列簇式存储，将同一列数据存在一起                 |         查找速度快，可扩展性强，更容易进行分布式扩展         |                         功能相对局限                         |
| **文档型数据库[3]**      |                  CouchDB, MongoDb                  | Web应用（与Key-Value类似，Value是结构化的，不同的是数据库能够了解Value的内容） | Key-Value对应的键值对，Value为结构化数据           | 数据结构要求不严格，表结构可变，不需要像关系型数据库一样需要预先定义表结构 |            查询性能不高，而且缺乏统一的查询语法。            |
| **图形(Graph)数据库[3]** |          Neo4J, InfoGrid, Infinite Graph           |           社交网络，推荐系统等。专注于构建关系图谱           | 图结构                                             |     利用图结构相关算法。比如最短路径寻址，N度关系查找等      | 很多时候需要对整个图做计算才能得出需要的信息，而且这种结构不太好做分布式的集群方案。[3] |

#### 5. Redis功能（Remote Dictionary Server，远程字典服务）

- 内存存储、持久化（rdb、aof）
- 高速缓存
- 简易消息队列
- 计时器、计数器
- 地图信息分析

#### 6. redis-benchmark性能测试

```bash
# 测试单机100个并发连接，10000个请求
redis-benchmark -h localhost -p 6379 -c 100 -n 10000

-h <hostname>：指定 Redis 服务器的主机名，默认为 localhost。
-p <port>：指定 Redis 服务器的端口号，默认为 6379。
-c <clients>：指定并发连接数，即同时向服务器发起的连接数。
-n <requests>：指定总请求数，即每个客户端向服务器发送的请求总数。
-d <size>：指定数据大小，即每个请求中包含的数据大小。
```

#### 7. 基础知识

	1. redis可以用作**数据库**、**缓存**和消息**中间件MQ**。
	1. redis默认有16个数据库，默认使用第0个。
	1. 基础命令：

```bash
# 切换编号为3的数据库
select 3
# 清除当前数据库
flushdb
# 清除所有数据库
FLUSHALL
# 查看当前数据库所有key
key *
# 判断key是否存在
EXIST 'key' 
# 移除key
move 'key' 'db' (move name 1)
# 设置key过期时间
EXPIRE 'key' 'secondes' (EXPIRE name 10)
# 查看key数据类型
type 'key'
```

	2. redis是单线程的，redis是基于内存操作，瓶颈是由**机器内存**和**网络带宽**决定的，所以**cpu不是redis的性能瓶颈**，redis没法通过多线程提升性能，所以使用单线程。

 3. redis为什么单线程还这么快？

    **基于内存的操作**

    ```
    redis将所有数据存储在内存中进行操作，速度快
    ```

    **单线程的原子操作**

    ```
    redis使用单线程处理操作可以保证操作原子性，避免了并发操作和锁机制，降低了资源的消耗
    ```

    **非阻塞的 I/O 模型**

    ```
    redis使用I/O多路复用模型，允许在单线程中同时处理多个客户端连接，而不必为每个连接创建一个线程
    ```

    **优化的数据结构和算法**

    ```
    redis优化了数据结构和算法。例如使用哈希表实现键值对存储；使用跳跃表实现有序集合，使得范围查询和有序操作的性能提升
    ```

#### 8. String基本类型

```bash
set 'key' 'value'
get 'key'
# 在某个key对应的值中追加字符串，如果key不存在则APPEND等于set
APPEND 'key' 'value'
# 自动加一/减一
incr 'key'
decr 'key'
# 设置增加/减少的步长
INCRBY 'key' '步长'
DECRBY 'key' '步长'
# 获取字符串子串
GETRANGE 'key' 'start' 'end' ([start, end])
-GETRANGE key 0 3 ({key: 'hello'})
-"hell"
-GETRANGE key 0 -1 (取出全部字符串)
# 替换字符串子串
SETRANGE 'key' 'offset' 'str' (从下标为offset的位置开始替换)
-SETRANGE key 1 oo ({key: 'hello'})
-hoolo
# 设置过期时间
setex 'key' 'seconds' 'value'
# key不存在时设置成功，如果存在设置失败不做任何操作
setnx 'key' 'value'
# 批量设置（原子性操作），当且仅当所有key都不存在时成功，如果有一个key已经存在，则所有key都设置失败
mset 'key1' 'val1' 'key2' 'val2' 'key3' 'val3'...
# 批量获取
mget 'key1' 'key2'...
# get后set，如果不存在则返回null，如果存在获取当前值并设置新值
getset 'key' 'value'
```

#### 9. List基本类型

- List可以理解成java的双端队列
- List中的元素是可以重复的

```bash
# 往列表头部添加元素
lpush 'list' 'value'
# 往列表尾部添加元素
rpush 'list' 'value'
# 获取区间元素
lrange 'start' 'end' ([start, end])
# 头部弹出
lpop 'list'
# 尾部弹出
rpop 'list'
# 根据下标取值
lindex 'list' 'index'
# 获取list长度
llen 'list'
# 移除指定的值
lrem 'list' 'count' 'value' (移除list中指定个数的value)
# list截断
ltrim 'list' 'start' 'end' (只保留[start, end]部分的list)
# 移除列表尾部元素并插入另一个list头部
rpoplpush 'source_list' 'target_list'
# 设置指定下标的值，下标不存在时报错，list不存在时也会报错
lset 'list' 'index' 'value'
# 在list指定值的前或后插入指定值
linsert 'list' before|after 'pivot' 'value' 
```

#### 10. Set基本类型

- set中的值是不可重复的
- set是无序的

```bash
# 添加元素
sadd 'key' 'value'
# 查看set所有元素
smembers 'key'
# 判断元素是否在set中
sismember 'key' 'value'
# 获取set元素的个数
scard 'key'
# 移除set指定元素
srem 'key' 'value'
# 随机选取set中指定数量的元素
srandmember 'key' 'count'
srandmember 'key' (随机选取一个元素)
# 随机删除set中指定数量的元素
spop 'key'
# set求交集、求并集、差集
sdiff 'key1' 'key2' (差集)
sinter 'key1' 'key2' （交集）
sunion 'key1' 'key2' (并集)
# 移动元素到另一set
smove 'source_set' 'destination_set' 'member'
```

#### 11. Hash基本类型

- Hash就是k-v中value是map类型（key-map）

```bash
hset 'key' 'field' 'value'
hget 'key' 'field'
hmset 'key' 'field1' 'value1' 'field2' 'value2'
hmget 'key' 'field1' 'field2'
hincrby 'key' 'field' 'increment'
hdecrby 'key' 'field' 'decrement'
hsetnx 'key' 'field' 'value'
hgetall 'key'
hdel 'key' 'field'
hlen 'key'
hexist 'key' 'field'
hkeys 'key'
hvals 'key'
```

#### 12. Zset基本类型

- zset底层的存储结构包括`ziplist`或`skiplist`，只有在以下两个条件满足时使用`ziplist`：

  - 有序集合保存的元素数量小于**128**个
  - 有序集合保存的所有元素长度都小于**64**字节

- `ziplist`编码的有序集合使用紧挨在一起的压缩列表节点来保存，**第一个节点保存`member`，第二个保存`score`**。**`ziplist`内的集合元素按score从小到大排序**，score较小的排在表头位置。

- `skiplist`是受**多层链表**的想法设计而来（下面是一个三层链表，每层链表的节点是上一层的一半，查询一个节点的时间是O(logn)）。`skiplist`为了避免这一问题，它不要求上下相邻两层链表之间的节点个数有严格的对应关系，而是为每个节点**随机选出**一个层数(level)。

  ![](/Users/chocolate/Documents/project/call4jobs/我的总结/多层链表.webp)

  ​							三层链表

  ![](/Users/chocolate/Documents/project/call4jobs/我的总结/skiplist.webp)

  ​							skiplist

- ```c
  // 字典的key保存元素的值，value保存元素的score
  // 跳跃表和哈希表的结合使用可以使查找单个member和范围查询都有非常高的效率
  typedef struct zset{
       //跳跃表
       zskiplist *zsl;
       //字典
       dict *dice;
  } zset;
  ```

- 有序集合，在set基础上增加了一个排序值

```bash
zadd 'key' 'score' 'value'
zrem 'key' 'value'
# 获取集合个数
zcard 'key'
zrange 'key' 'start' 'end'
# 生序排序
zrangebyscore 'key' 'min' 'max' 'withscores' 'limit'
-zrangebyscore zset 0 10000 (升序排列)
# 降序排序
zrevrangebyscore 'key' 'max' 'min'
# 获取区间中元素个数
zcount 'key' 'min' 'max'
```

#### 13. 事务

- redis事务本质上是一组命令的集合
- 一个事务中的所有命令都会被序列化
- 所有命令按照顺序执行
- redis事务不保证原子性和隔离性

redis事务执行顺序（命令入队时不会执行）：

- 开启事务 

  ```bash
  multi
  ```

- 命令入队

  ```bash
  如果命令本身有误，例如set写成sett，那么exec时会直接报错，所有的命令都不会执行
  如果命令本身无误，但是在exec时某条命令出错，那么只有出错的命令失败，其他正常的命令会成功执行
  ```

- 执行事务 

  ```bash
  exec
  ```

- 取消执行

  ```bash
  discard
  ```

#### 14. Springboot-redis

- 引入`spring-boot-starter-data-redis`包即可，这个包引用了`spring-data-redis`和`lettuce-core`，1.x版本的springboot底层采用`jedis`而不是`lettuce`。

- `jedis`问题时多线程操作不安全，需要使用`jedis pool`

- `lettuce`使用`netty`，实例可以在多个线程中共享，多线程访问也不会出现不安全的并发操作

#### 15. redis.config

```bash
# MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
# is reached. You can select one from the following behaviors:
#
# volatile-lru -> Evict using approximated LRU, only keys with an expire set.
# allkeys-lru -> Evict any key using approximated LRU.
# volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
# allkeys-lfu -> Evict any key using approximated LFU.
# volatile-random -> Remove a random key having an expire set.
# allkeys-random -> Remove a random key, any key.
# volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
# noeviction -> Don't evict anything, just return an error on write operations.
# volatile-lru：只对设置了过期时间的key执行lru
# allkeys-lru：删除lru的key
# volatile-random：随机删除有过期时间的key
# volatile-ttl：删除即将过期的key
# noeviction：永不过期，返回错误
```

#### 16. RDB（Redis DataBase）

- Redis默认的持久化方式是RDB也就是快照（Snapshotting）。在快照持久化中，Redis会将内存中的数据保存到磁盘上的一个文件（称为**快照文件**）中，这样在Redis重新启动时，可以通过加载这个快照文件将数据重新载入到内存中。

- rdb保存的文件是`dump.rdb`

- 触发机制：

  - save的条件满足触发


  ```bash
  # 触发规则
  save 'seconds' 'changed_key'
  -save 900 1 (900秒内至少有1个key发生变化则进行持久化)
  ```

  - 执行`flushall`、`flushdb`指令触发


  - 退出redis时触发

- 优点：
  - **适合大规模的数据恢复**
  - **高性能**，fork子进程确保主进程不进行任何IO操作
- 缺点：
  - 最后一次修改的数据可能丢失（redis宕机）
  - fork子进程会占用一定的内存空间

#### 17. AOF (Append-Only File)

- AOF持久化记录了对Redis服务器执行的所有**写操作命令**，这些命令会以追加的方式写入到一个文件中。在Redis重新启动时，可以通过重新执行这个文件中的命令来恢复数据。

- 写命令保存在`appendonly.aof`文件中，aof文件出错redis不可以连接，可以用redis自带的`redis-check-aof`工具修复`.aof`文件

  ```bash
  redis-check-aof --fix appendonly.aof
  ```

- 默认不开启AOF，需要在配置文件手动开启

  ```bash
  # 开启aof
  appendonly yes
  ```

- `AOF Rewrite` 是 Redis 中一个重要的功能，它用于**优化 AOF 文件的体积和性能**。AOF Rewrite 可以重新生成一个新的 AOF 文件，这个新文件包含了和原始文件相同的数据，但是使用更加**紧凑和高效的格式**，从而**减小文件体积并提升性能**。
- 优点：
  - **更好的灵活性：** AOF持久化允许用户选择不同的同步策略（如每个写命令、每秒同步等），这使得用户可以根据实际需求和对数据安全性的要求进行灵活配置，以达到更好的性能和可靠性。

- 缺点：
  - **文件体积可能较大：** AOF文件记录了每次写操作的命令，因此在一段时间内的大量写操作会导致AOF文件体积增长较快
  - **恢复速度可能较慢：** 当AOF文件体积较大时，在Redis重新启动时可能需要较长的时间来重新执行AOF文件中的命令以恢复数据

#### 18. redis集群

- 单台redis使用的内存不应超过**20GB**

- **每台redis默认自己都是master**

  ```bash
  # 查看本机的主从复制信息
  info replication
  ```

- Slave服务器配置主机

  ```bash
  # 配置文件（推荐）
  replicaof 'master_ip' 'master_port'
  # 命令配置
  slaveof 'host' 'port'
  ```

#### 19. 主从复制

- Redis 主从复制是一种用于创建 Redis 数据复制和备份的机制。它允许你创建一个主 Redis 服务器（Master）和一个或多个从 Redis 服务器（Slave）。主 Redis 服务器负责处理写操作和更新，而从 Redis 服务器则负责复制主服务器的数据，以便在需要时提供读取操作或者在主服务器故障时接管服务。
- 主机可以读和写，从机只能读不能写
- 作用：
  - **数据备份与灾难恢复：** 通过主从复制，可以将主服务器上的数据实时复制到一个或多个从服务器上。这样，如果主服务器发生故障或数据丢失，可以快速地将一个从服务器升级为新的主服务器，从而实现快速的灾难恢复和数据备份。
  - **读写分离提升读写性能：** 在主从复制中，主服务器负责处理写操作和更新，而从服务器可以用于处理读操作。这样可以有效地将读取操作分担到多个从服务器上，从而降低主服务器的读取压力，提高系统的整体读写性能。
  - **高可用性和负载均衡：** 通过将多个从服务器部署在不同的节点上，可以实现数据的冗余备份和故障转移。如果主服务器发生故障，可以快速地将其中一个从服务器升级为新的主服务器，从而保证系统的高可用性。此外，通过将读操作分发到不同的从服务器上，可以实现负载均衡，提高系统的整体性能和可扩展性。

- Redis 主从复制的原理如下：
  1. **初始化同步：** 当从服务器连接到主服务器时，它发送一个同步命令（`sync`）给主服务器请求进行初始化同步。主服务器在收到同步命令后，会创建一个**后台进程**开始进行复制（**全量复制**）。
  2. **快照同步（如果需要）：** 如果从服务器的数据集为空，或者与主服务器的数据不一致，主服务器会发送一个快照给从服务器。这个快照包含了当前主服务器上的整个数据集。从服务器接收到快照后，会加载数据并将其存储在内存中。
  3. **增量同步：** 一旦从服务器的数据集已经与主服务器的数据集一致，主服务器会将接收到的写操作**实时**发送给从服务器。**这些写操作以命令的形式被发送**，并在从服务器上执行以保持数据的一致性。
  4. **心跳检测和断线重连：** 主服务器和从服务器之间会保持一个**持久的连接**。主服务器会定期发送**心跳消息**给从服务器以检测连接状态。如果连接断开，从服务器会尝试重新连接到主服务器，并重新开始同步过程。
  5. **复制偏移量和复制积压缓冲区：** 主服务器会维护一个复制偏移量来跟踪从服务器同步的进度。从服务器定期向主服务器发送复制偏移量，并根据偏移量向主服务器请求缺失的数据。主服务器还会维护一个复制积压缓冲区，用于保存最近一段时间内发送给从服务器的写操作，以便在从服务器重新连接时可以重放这些操作。

#### 20. 哨兵模式

- Redis 哨兵模式的基本原理：

  - **哨兵节点部署：** 在 Redis 哨兵模式中，通常会部署多个哨兵节点（`Sentinel`）。这些哨兵节点是**独立的 Redis 进程**，它们会**相互通信**，共同监视 Redis 主服务器和从服务器的运行状态。
  - **监视主服务器：** 哨兵节点会定期向主服务器发送 **PING 命令**来检测主服务器的运行状态。如果主服务器在一定时间内未响应，哨兵节点会将主服务器标记为下线状态。
  - **选举领袖哨兵：** 哨兵节点中会进行选举，选举出一个领袖哨兵（`Leader Sentinel`）。领袖哨兵负责监视其他哨兵节点和协调故障转移过程。如果领导者哨兵宕机，其他哨兵会重新进行选举以确定新的领导者。
  - **故障转移（`failover`）：** 当主服务器被标记为下线状态时，**领袖哨兵会根据一定的算法选择一个从服务器作为新的主服务器，并将其他从服务器切换到新的主服务器上**。此过程称为**故障转移**。
  - **更新客户端连接：** 一旦故障转移完成，**领袖哨兵会通知客户端新的主服务器的地址**，客户端会重新连接到新的主服务器上。
  - **持续监视：** 哨兵节点会持续监视主服务器和从服务器的运行状态，并在需要时执行故障转移。这样可以保证 Redis 服务的高可用性和稳定性。

  通过 Redis 哨兵模式，可以实现 Redis 服务的自动故障检测和故障转移，从而提高了 Redis 的**可用性**和**可靠性**。

- Sentinel 在选择新的主服务器时会综合考虑多个因素，包括从服务器的**健康状况**、**复制偏移量**、**优先级**等，以保证选择出的新主服务器具有最佳的性能和可用性。
  - 优先级较高的从服务器通常拥有更好的**硬件配置**或**网络环境**
  - Sentinel 会选择复制偏移量最大的从服务器作为新的主服务器。**复制偏移量最大的从服务器通常表示它的数据与主服务器的数据最接近**

#### 21. 缓存穿透、缓存击穿、缓存雪崩

- 缓存穿透：访问一个缓存中**不存在**的值

  - ##### 布隆过滤器

  - ##### 设置空值缓存

- 缓存击穿：一个或多个**热点数据**在缓存过期或者缓存被清除，造成大量访问落到数据库

  - ##### 热点数据不过期

  - **使用分布式锁：** 如果系统是分布式的，可以使用分布式锁来保证只有一个请求去访问后端服务，并且避免多个请求同时访问后端服务的情况。

- 缓存雪崩：**缓存宕机**或者**多个key同时失效**
  - **缓存服务器高可用：** 部署多个缓存服务器并且进行负载均衡，当某个缓存服务器宕机时，请求可以自动切换到其他可用的缓存服务器上。
  - **限流和降级：** 对于并发量过大的请求，可以使用限流和降级的策略来限制请求的并发量，从而减轻后端服务的压力。

#### 22. 布隆过滤器

布隆过滤器（Bloom Filter）是一种空间效率非常高的概率型数据结构，用于判断一个元素是否可能存在于一个集合中，其主要应用场景是在大规模数据集中进行高效的成员查询操作。

布隆过滤器的基本原理是使用一个位数组（Bit Array）以及多个哈希函数。当一个元素被加入到布隆过滤器时，通过多个哈希函数将其映射到位数组中的多个位置，并将这些位置的值设置为1。当查询一个元素是否存在于布隆过滤器时，同样通过多个哈希函数将其映射到位数组中的相应位置，并检查这些位置的值是否都为1。如果所有位置的值都为1，则说明该元素可能存在于集合中，否则可以确定该元素一定不存在于集合中。

**误判率（False Positive）：** 布隆过滤器存在一定的误判率，即有可能判断一个元素存在于集合中，但实际上该元素并不存在。这是因为布隆过滤器的位数组在存储**多个元素的哈希值时会存在冲突**，从而导致不同元素的哈希值可能被映射到相同的位上，进而导致误判。误判率主要受到位数组大小和哈希函数数量的影响，可以通过调整这些参数来降低误判率，但会增加布隆过滤器的空间复杂度和查询时间。

**哈希函数选择：** 哈希函数的选择对布隆过滤器的性能影响较大。合适的哈希函数应该能够均匀分布哈希值，减少冲突，从而降低误判率。选择不合适的哈希函数可能会导致误判率增加。

#### 23. 如何保持mysql和redis之间的数据一致性

1. 先删除redis中的缓存再更新数据库，更新数据库成功后休眠一段时间后再删除一次redis中的缓存。（**最终一致性**）

   问题：在删除缓存后，更新数据库成功之前，可能会有其他线程拿到数据库中的旧数据并存到redis中。

   问题：为什么只删除不修改缓存呢？

   因为删除操作比更新更简单，效率更高。

2. 先更新数据库再删除redis中的对应缓存。（更推荐）

   删除缓存失败怎么办？

   删除重试机制，使用MQ实现异步删除。可以将这个删除过程独立出来用一个单独的应用（比如cannal）进行执行。

3. **定时同步（Scheduled Synchronization）：** 定期（例如每隔一段时间）从数据库中读取数据，并将更新后的数据同步到 Redis 中。这种方法可以减少对数据库的读取次数，降低系统负载，但也会增加数据同步的延迟。

4. **增量同步（Incremental Synchronization）：** **监听数据库的变更事件**（如数据库日志或消息队列），并根据变更事件实时地更新 Redis 中的数据。这种方法可以实现实时同步，并且可以减少对数据库的读取次数，但实现起来较为复杂。

5. **使用消息队列（Message Queue）：** **将数据库中的变更事件发送到消息队列**，然后消费者从消息队列中获取事件并将数据同步到 Redis 中。这种方法可以实现**异步同步**，并且可以解耦数据库和 Redis 之间的依赖关系，提高系统的可扩展性和灵活性。
