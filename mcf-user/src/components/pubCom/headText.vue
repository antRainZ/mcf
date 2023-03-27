<template>
  <div class="head f-14">
    {{content}}
  </div>
</template>

<script>
export default {
  props:['content']
}
</script>

<style lang="less" scoped>
.head{
  padding: 1.125rem 1.5rem;
  border-bottom: 1px solid hsla(0,0%,59.2%,.1);
}
</style>