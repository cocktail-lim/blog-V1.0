# Blog V1.0

This is my blog built by Spring Boot 2.

The version is V1.0

It is developed by Finn.



## Session

### Session过期跳转

- **GET**
- **Url**: /api/session/invalid
- **Param**



## Admin System

<font color="red" >*表示必须</font>

### 菜单展示模块

- **GET**

- **Url**: /api/admin/getMenus
- **Param**：
  - *roleName：用户的角色（必须，如管理员）

### 用户管理

#### 用户列表

##### 获取用户角色信息

- **GET**

- **Url**：/api/admin/role/getRoleSelectList

##### 根据用户角色和昵称分页查询用户列表

- **GET**

- **Url**: /api/admin/user/getUserList

- **Param**: 
  - current: 当前页，默认为1
  - size：一页显示个数，默认为5
  - roleName：用户角色
  - nickname：昵称
    - 可模糊查询



### 文章管理

#### 新增博客

##### 保存和修改文章

- **POST**
- **Url**: /api/admin/article/saveOrUpdateArticle
- **Param**
  - *Integer **id**: null为新增文章，非null为指定id的文章进行修改
  - *String **articleTitle**: 文章标题
  - String **articleContent**: 文章内容
  - String **articleCover**: 文章封面 url 链接
  - Integer **categoryId**: 分类ID
  - List **tagList**: 标签Id List，<u>要返回数组</u>
  - Boolean **isTop**: 是否置顶
  - Boolean **isDraft**: 保存草稿or发布文章



##### 查询所有Tag

- **GET**

- **Url**: /api/admin/tag/getTag
- **Param**



##### 查询所有分类

- **GET**

- **Url**: /api/admin/category/getCategory
- **Param**



### 文章列表

##### 根据文章标题分页查询文章列表

- **GET**

- **Url**: /api/admin/article/listArticleBackPage

- **Param**: 
  - current: 当前页，默认为1
  - size：一页显示个数，默认为10
  - articleTitle: 文章标题
    - 可模糊查询

- **Return**: 
  - List **articleList**
  - long **total**



##### 根据文章Id置顶文章

- **POST**

- **Url**: /api/admin/article/topArticleById

- **Param**: 
  - *Integer **articleId**:  文章id
  - *Boolean **isTop**：文章是否置顶



##### 获取后台文章总数(包括草稿)

- **GET**
- **Url**: /api/admin/article/countArticleBack
- **Param**
- **Return**: 
  - Long **totalArticle**





## Show Page

##### 根据文章标题分页查询文章列表

- **GET**

- **Url**: /api/article/listArticlePreviewPage

- **Param**: 
  - current: 当前页，默认为1
  - size：一页显示个数，默认为10
  - articleTitle: 文章标题
    - 可模糊查询

- **Return**: 
  - List **articleList**
    - `Integer id`
    - `String articleTitle`
    - `String articleCover`
    - `String categoryName`
    - `Boolean isTop`
    - `Date createTime`
    - `List<String> tagList`
  - long **total**



##### 根据文章Id查询文章内容

- **GET**

- **Url**: /api/article/showArticleContent

- **Param**: 
  - *Integer articleId 

- **Return**: 
  - List **articleList**
    - `Integer id`
    - `String articleTitle`
    - `String articleContent`
    - `String categoryName`
    - `Date createTime`
    - `Date updateTime`
    - `List<String> tagList`

