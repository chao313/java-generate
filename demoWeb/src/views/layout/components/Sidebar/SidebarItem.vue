<template>
  <div class="menu-wrapper">
    <!--<h5 class="site_title">后台辅助系统</h5>-->

    <div class="profile mt10 clearfix">
      <div class="profile_pic">
        <img src="../../../../assets/images/logo.png" alt="..." class="img-circle">
      </div>
      <div class="profile_info">
        <span>{{userInfo.userName}}</span>
        <h2>{{userInfo.roadOrgName}}</h2>
      </div>
    </div>
    <div class="menu-router">
      <template v-for="item in routes" v-if="!item.hidden && item.children">
        <router-link v-if="hasOneShowingChildren(item.children) && !item.children[0].children"
                     :to="item.path+'/'+item.children[0].path" :key="item.children[0].name">
          <el-menu-item :index="item.path+'/'+item.children[0].path">
            <span v-if="item.children[0].meta && item.children[0].meta.title">{{item.children[0].meta.title}}</span>
          </el-menu-item>
        </router-link>
        <el-submenu v-else :index="item.name || item.path" :key="item.name">
          <template slot="title">
            <span v-if="item.meta && item.meta.title" slot="title">{{item.meta.title}}</span>
          </template>
          <template v-for="child in item.children" v-if="!child.hidden">
            <sidebar-item :is-nest="true" class="nest-menu" v-if="child.children && child.children.length > 0"
                          :routes="[child]" :key="child.path"></sidebar-item>
            <router-link v-else :to="item.path + '/'+child.path" :key="child.name">
              <el-menu-item :index="item.path + '/'+child.path">
                <span v-if="child.meta && child.meta.title" slot="title">{{child.meta.title}}</span>
              </el-menu-item>
            </router-link>
          </template>
        </el-submenu>
      </template>
    </div>

  </div>
</template>
<script>
  export default {
    name: 'SidebarItem',
    props: {
      routes: {
        type: Array
      }
    },
    computed: {
      userInfo() {
        return this.$store.state.user.userInfo
      },
    },
    methods: {
      hasOneShowingChildren(children) {
        const showingChildren = children.filter(item => {
          return !item.hidden
        })
        if (showingChildren.length === 1) {
          return true
        }
        return false
      },

    }
  }
</script>
<style rel="stylesheet/scss" lang="scss">
  $menuBg: #304156;
  $subMenuBg: #283244;
  $menuHover: #3c5869;
  .el-submenu .el-menu-item {
    background-color: $subMenuBg !important;
    /*border-left:solid 3px #13b3ce;*/
    &:hover {
      color: #fff !important;
      background-color: $menuHover !important;
    }
  }

  .el-icon-arrow-down:before {
    content: "\E603";
    color: #fff;
    font-weight: bold;
  }

  .menu-wrapper {
    width: 230px;
    position: fixed;
    top: 0px;
    bottom: 0;
    left: 0;
    z-index: 10;
    background: #2A3F54;
    overflow-y: auto;
  }

  .menu-router {
    position: relative;
    top: 20px;
    border-top: solid 1px #3c5869;
  }
</style>
