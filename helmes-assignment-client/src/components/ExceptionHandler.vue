<template>
  <div v-if="isError">
    <div class="full-page-wrapper">
      <div class="message-box">
        <div @click="close" class="close">
          <img src="../assets/close-pink.svg" alt="close">
        </div>
        {{getError}}
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ExceptionHandler',
    computed: {
      isError() {
        return this.$store.state.exception.errorPool.length !== 0;
      },
      getError() {
        return this.$store.getters['exception/getError'];
      }
    },
    methods: {
      close(){
        this.$store.dispatch('exception/discardLastError');
      }
    }
  };
</script>

<style lang="scss" scoped>

  .full-page-wrapper {
    position: absolute;
    margin-top: $small-margin;
    padding: $small-padding $horizontal-padding;
    width: 100vw;
    z-index: 20000;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .message-box {
    @extend %rounded;
    position: relative;
    width: 100%;
    padding: ($large-padding+$logo-size-small) $horizontal-padding;
    background: $error-color;
  }

  .close {
    position: absolute;
    top: $standard-padding;
    right: $large-padding;
    width: $logo-size-small;
    height: $logo-size-small;
  }
</style>
