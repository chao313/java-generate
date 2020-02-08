const getters = {
  token: state => state.user.token,
  userInfo: state => state.user.userInfo,
  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters,
  permission: state => state.permission.permission,
}
export default getters
