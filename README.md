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

##### 查询所有Tag

- **GET**

- **Url**: /api/admin/tag/getTag
- **Param**

##### 查询所有分类

- **GET**

- **Url**: /api/admin/category/getCategory
- **Param**
