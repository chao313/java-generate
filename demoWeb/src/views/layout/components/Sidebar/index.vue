<template>
  <el-menu
    mode="vertical"
    :show-timeout="200"
    :default-active="$route.path"
    background-color="#2A3F54"
    text-color="#c5cfd8"
    active-text-color="#ffffff"
  >
    <sidebar-item :routes="permission_routers"></sidebar-item>
  </el-menu>
</template>

<script>
  import {mapGetters} from 'vuex'
  import SidebarItem from './SidebarItem'

  export default {
    components: {SidebarItem},
    computed: {
      ...mapGetters([
        'permission_routers'
      ]),
    }
  }
</script>
