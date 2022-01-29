# Blog V1.0

This is my blog built by Spring Boot 2.

The version is V1.0



## Admin System

### 获取菜单信息

- **url**: /api/admin/menus
- **param**：
  - roleName：用户的角色（如管理员）

### 用户管理

#### 用户列表

##### 用户角色下拉菜单

- **url**：/api/admin/userList/getRoleSelectList

##### 按条件查询用户信息

- **url**: /api/admin/userList/getUserByCondition

- **param**: 
  - roleName：用户角色
  - nickname：昵称
    - 可模糊查询



### 文章管理

