<template>
  <div id="app">
    <ExceptionHandler/>
    <router-view class="router"/>
  </div>
</template>

<style lang="scss">
#app {
  color: #2c3e50;
  position: relative;
}

.router {
  @extend %full-page;
  padding-top: 100px;
  position: fixed;
  top: 0;
}
</style>
<script>
import ExceptionHandler from "@/components/ExceptionHandler";
import '@/assets/styles/common/helmesassignment.scss';

export default {
  components: {
    ExceptionHandler
  }
};
</script>