<template>
  <div class="app-wrapper">
    <!-- <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside" /> -->
    <!-- 左侧栏 -->
    <!-- <sidebar class="sidebar-container" /> -->


    <div class="main-container">
      <!-- 顶部栏 -->
      <el-scrollbar wrap-class="scrollbar-wrapper">
        <el-menu
          :default-active="activeMenu"
          :background-color="variables.menuBg"
          :text-color="variables.menuText"
          :unique-opened="false"
          :active-text-color="variables.menuActiveText"
          :collapse-transition="false"
          mode="horizontal"
          style="display: flex;width: 100vw;"
        >
          <sidebar-item v-for="route in routes" :key="route.path" :item="route" :base-path="route.path" style="padding-left: 150px;" />
          
        <navbar style="position:absolute;right: 0px;"/>
        </el-menu>
        
      </el-scrollbar>
      
      <!-- 主内容 -->
      <app-main />
      
    </div>
  </div>
</template>

<script>
import { Navbar, AppMain } from './components'
import ResizeMixin from './mixin/ResizeHandler'
import SidebarItem from './components/Sidebar/SidebarItem.vue'
import { mapGetters } from 'vuex'
import Logo from './components/Sidebar/Logo.vue'
import variables from '@/styles/variables.scss'

export default {
  data() {
      return {
        activeIndex: '1',
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    name: 'Layout',
    components: {
      Navbar,
      // Sidebar,
      SidebarItem, Logo,
      AppMain
    },
    mixins: [ResizeMixin],
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar
      },
      device() {
        return this.$store.state.app.device
      },
      fixedHeader() {
        return this.$store.state.settings.fixedHeader
      }

      ////////////////////
      ,
      ...mapGetters([
      'sidebar'
      ]),
      routes() {
        return this.$router.options.routes
      },
      activeMenu() {
        const route = this.$route
        const { meta, path } = route
        // if set path, the sidebar will highlight the path you set
        if (meta.activeMenu) {
          return meta.activeMenu
        }
        return path
      },
      showLogo() {
      
        return true;
      },
      variables() {
        return variables
      },
      isCollapse() {
        return !this.sidebar.opened
      }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";
  @import "~@/styles/variables.scss";
  

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar{
      position: fixed;
      top: 0;
    }
  }
  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$sideBarWidth});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px)
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
