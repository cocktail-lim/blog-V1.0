# Blog V1.0

This is my blog built by Spring Boot 2.

The version is V1.0

It is developed by Finn.



## Admin System

<font color="red" >*表示必须</font>

### 获取菜单信息

- **url**: /api/admin/getMenus
- **param**：
  - *roleName：用户的角色（如管理员）

### 用户管理

#### 用户列表

##### 获取用户角色下拉菜单

- **url**：/api/admin/userList/getRoleSelectList

##### 按条件查询所有用户信息

- **url**: /api/admin/userList/getUserList

- **param**: 
  - current: 当前页，默认为1
  - size：一页显示个数，默认为5
  - roleName：用户角色
  - nickname：昵称
    - 可模糊查询



### 文章管理

