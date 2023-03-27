<template>
  <span class="Tag f-12 d-ib">{{content}}</span>
</template>

<script>
export default {
  props:['content']
}
</script>

<style lang="less" scoped>
.Tag{
  width:fit-content;
  padding: 0.0625rem 0.1875rem;
  color: #0caa3c;
  border: 1px solid #0caa3c;
  margin-left: 0.3125rem;
  margin-top: 0.3875rem;
  margin-bottom: 0.3875rem;
}
.Tag:first-child{
  margin-left: 0;
}
</style>