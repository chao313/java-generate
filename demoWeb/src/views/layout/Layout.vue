<template>
  <div>
    <!--:class="classObj"-->
    <div class="app-wrapper" :class="{hideSidebar:sidebar.opened}">
      <div class="sidebar-wrapper">
        <sidebar class="sidebar-container"></sidebar>
      </div>
      <div class="main-container">
        <Head-top></Head-top>
        <App-main/>
      </div>
    </div>
  </div>
</template>

<script>
  import {HeadTop, Sidebar, AppMain} from './components'

  export default {
    name: 'layout',
    components: {
      HeadTop,
      Sidebar,
      AppMain,
    },
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar
      },
    },
    methods: {}
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/style/mixin.scss";

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .app-wrapper {
    @include clearfix;
    position: absolute;
    background: #F7F7F7;
    height: 100%;
    width: 100%;
    top: 0;
    bottom: 0;
    .sidebar-wrapper {
      width: 230px;
      position: fixed;
      top: 0;
      bottom: 0;
      left: 0;
      z-index: 10;
      overflow: hidden;
    }
    &.hideSidebar {
      .sidebar-wrapper {
        transform: translate(-185px, 0);
        .sidebar-container {
          transform: translate(185px, 0);
        }
      }
      & {
        margin-left: 45px;
      }
    }
    .sidebar-container {
      position: absolute;
      top: 0px;
      bottom: 0;
      left: 0;
      right: 0px;
      overflow-y: hidden;
      min-height: 100%;
      color: #fff;
    }
    .main-container {
      position: relative;
      top: 0px;
      min-height: 100%;
      margin-left: 230px;
      background: #f7f7f7;
    }
  }
</style>
