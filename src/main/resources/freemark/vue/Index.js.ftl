import Vue from 'vue'
import Router from 'vue-router'


/**
 * 管理边栏的地方
 */
Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'
//登陆界面
const Login = r => require.ensure([], () => r(require('@/views/login/index')), 'login')

<#list ftlVo.modules as module>
/* ${module.metaTitle} */
const ${module.component} = r => require.ensure([], () => r(require('${module.realPath}')), '${module.component}')
</#list>

export const constantRouterMap = [
  {
    path: '',
    component: Layout,
    redirect: '/homepage',
    name: 'HomePage',
    meta: {
      title: '配置管理'
    },
    noDropdown: true,
    children: [

<#list ftlVo.modules as module>
      {
        path: '${module.path}',
        component: ${module.component},
        name: '${module.component}',
        meta: {
          title: '${module.metaTitle}'
        },
        hidden: ${module.hidden}
      }
<#if module_has_next>,</#if>
</#list>
    ]
  },
  {
    path: '/login',
    component: Login,
    hidden: true
  }
]
export const routerMap = {
  "9901": {
    path: 'test/test',
    component: Login,
    name: 'UserMgt',
    meta: {
      title: '用户管理'
    }
  }
}
export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRouterMap
})
