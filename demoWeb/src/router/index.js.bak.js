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

/* 首页  */
const HomePage = r => require.ensure([], () => r(require('@/views/homePage/index')), 'homePage')
/* 注册配置管理 */
const ConfigRegistrations = r => require.ensure([], () => r(require('@/views/configRegistrations/configRegistrations')), 'configRegistrations')
const ConfigRegistrationsEdit = r => require.ensure([], () => r(require('@/views/configRegistrations/edit')), 'configRegistrationsEdit')

/* zipManager */
const ZipManagerList = r => require.ensure([], () => r(require('@/views/zipManager/list')), 'zipManagerList')
const ZipManagerEdit = r => require.ensure([], () => r(require('@/views/zipManager/edit')), 'zipManagerEdit')

/* zipManager */
const LocationZipManagerList = r => require.ensure([], () => r(require('@/views/locationZipManager/list')), 'locationZipManagerList')
const LocationZipManagerEdit = r => require.ensure([], () => r(require('@/views/locationZipManager/edit')), 'locationZipManagerEdit')

/* properties管理  */
const PropertiesEdit = r => require.ensure([], () => r(require('@/views/properties/edit')), 'propertiesEdit')


/* originalConf */
const OriginalConfEdit = r => require.ensure([], () => r(require('@/views/originalConf/edit')), 'originalConfEdit')


/* extractor_conf  */
const ExtractorConfList = r => require.ensure([], () => r(require('@/views/extractorConf/list')), 'extractorConfList')
const ExtractorConfEdit = r => require.ensure([], () => r(require('@/views/extractorConf/edit')), 'extractorConfEdit')

/* structConf  */
const StructConfEdit = r => require.ensure([], () => r(require('@/views/structConf/edit')), 'structConfEdit')
/* tableRelation */
const TableRelationEdit = r => require.ensure([], () => r(require('@/views/tableRelation/edit')), 'tableRelationEdit')
const TableRelationList = r => require.ensure([], () => r(require('@/views/tableRelation/list')), 'tableRelationList')
/* fieldExtractorConf */
const FieldExtractorConfList = r => require.ensure([], () => r(require('@/views/fieldExtractorConf/list')), 'fieldExtractorConfList')
const FieldExtractorConfEdit = r => require.ensure([], () => r(require('@/views/fieldExtractorConf/edit')), 'fieldExtractorConfEdit')
/** **/
const KeyWordConfEdit = r => require.ensure([], () => r(require('@/views/keyWordConf/edit')), 'keyWordConfEdit')
const KeyWordConfList = r => require.ensure([], () => r(require('@/views/keyWordConf/list')), 'keyWordConfList')

/* test */
const test = r => require.ensure([], () => r(require('@/views/test/test')), 'test')

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
      {
        path: 'configRegistrations',
        component: ConfigRegistrations,
        name: 'configRegistrations',
        meta: {
          title: '注册配置'
        }
      }, {
        path: 'configRegistrationsEdit',
        component: ConfigRegistrationsEdit,
        name: 'configRegistrationsEdit',
        meta: {
          title: '注册配置编辑'
        },
        hidden: true
      }, {
        path: 'zipManagerList',
        component: ZipManagerList,
        name: 'zipManagerList',
        meta: {
          title: 'zipManagerList配置编辑'
        },
        hidden: true
      }, {
        path: 'zipManagerEdit',
        component: ZipManagerEdit,
        name: 'zipManagerEdit',
        meta: {
          title: 'zipManagerEdit配置编辑'
        },
        hidden: true
      }, {
        path: 'locationZipManagerList',
        component: LocationZipManagerList,
        name: 'locationZipManagerList',
        meta: {
          title: 'locationZipManagerList配置编辑'
        },
        hidden: true
      }, {
        path: 'locationZipManagerEdit',
        component: LocationZipManagerEdit,
        name: 'locationZipManagerEdit',
        meta: {
          title: 'locationZipManagerEdit配置编辑'
        },
        hidden: true
      }
      , {
        path: 'originalConfEdit',
        component: OriginalConfEdit,
        name: 'originalConfEdit',
        meta: {
          title: 'originalConf配置编辑'
        },
        hidden: true
      }, {
        path: 'propertiesEdit',
        component: PropertiesEdit,
        name: 'propertiesEdit',
        meta: {
          title: 'properties配置编辑'
        },
        hidden: true
      }, {
        path: 'extractorConfEdit',
        component: ExtractorConfEdit,
        name: 'extractorConfEdit',
        meta: {
          title: 'extractorConfEdit配置编辑'
        },
        hidden: true
      }, {
        path: 'extractorConfList',
        component: ExtractorConfList,
        name: 'extractorConfList',
        meta: {
          title: 'extractorConfList配置编辑'
        },
        hidden: true
      }, {
        path: 'structConfEdit',
        component: StructConfEdit,
        name: 'structConfEdit',
        meta: {
          title: 'structConfEdit 配置编辑'
        },
        hidden: true
      }, {
        path: 'tableRelationEdit',
        component: TableRelationEdit,
        name: 'tableRelationEdit',
        meta: {
          title: 'tableRelationConfEdit 配置编辑'
        },
        hidden: true
      }, {
        path: 'tableRelationList',
        component: TableRelationList,
        name: 'tableRelationList',
        meta: {
          title: 'tableRelationList 列表'
        },
        hidden: true
      }, {
        path: 'keyWordConfEdit',
        component: KeyWordConfEdit,
        name: 'keyWordConfEdit',
        meta: {
          title: 'keyWordConfConfEdit 配置编辑'
        },
        hidden: true
      }, {
        path: 'keyWordConfList',
        component: KeyWordConfList,
        name: 'keyWordConfList',
        meta: {
          title: 'keyWordConfList 列表'
        },
        hidden: true
      },
      {
        path: 'fieldExtractorConfEdit',
        component: FieldExtractorConfEdit,
        name: 'fieldExtractorConfEdit',
        meta: {
          title: 'fieldExtractorConf 配置编辑'
        },
        hidden: true
      }, {
        path: 'fieldExtractorConfList',
        component: FieldExtractorConfList,
        name: 'fieldExtractorConfList',
        meta: {
          title: 'fieldExtractorConfList 列表'
        },
        hidden: true
      }
    ]
  },
  {
    path: '/login',
    component: Login,
    hidden: true
  },
  {
    path: '/test',
    component: test,
    hidden: true
  }
]
export const routerMap = {
  "9901": {
    path: 'test/test',
    component: test,
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
