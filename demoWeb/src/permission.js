//过滤请求，类似java的filter
import router from '@/router'
import store from '@/store'
import {getToken} from '@/utils/auth'


/**
 * 主界面上的允许 配置白名单 不需要登录就能访问的界面
 */
const whiteList = ['/login', '/forgetPsw', '/test', '/homepage', '/configRegistrations', '/configRegistrations/edit']; // 白名单 允许防问的页面

router.beforeEach((to, from, next) => {
  if (store.getters.token) {
    if (to.path === '/login') {
      next({
        path: '/'
      })
      //NProgress.done()
    } else {
      if (!store.getters.permission) {
        store.dispatch('GenerateRoutes').then(() => {
          router.addRoutes(store.getters.addRouters) // 动态添加可访问路由表
          next({
            ...to,
            replace: true
          })
        })
      } else {
        next();
      }

    }
  } else {
    // if (whiteList.indexOf(to.path) !== -1) {
    //
    //   next()
    // } else {
    //   next('/login');
    //   //NProgress.done();
    // }

    // if (whiteList.indexOf(to.path) !== -1) {

    next()
    // } else {
    //   next('/login');
    //   NProgress.done();
    // }
  }
})

router.afterEach(() => {
  //NProgress.done() // finish progress bar
})
