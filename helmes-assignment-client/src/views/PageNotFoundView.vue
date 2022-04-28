<template>
  <div>
    <div class="page-not-found-background"></div>
    <div class="page-not-found-wrapper">
      <div class="page-not-found-block">
        <span class="title big">Something went wrong!</span>
        <span>Cannot find page :(</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PageNotFoundView"
};
</script>


<style lang="scss" scoped>

  .page-not-found-background {
    @include image-background("../assets/page-not-found-background.jpg");
  }

  .page-not-found-wrapper{
    @extend %background-wrapper;
  }

  .page-not-found-block {
    @extend %block;
  }

</style>

