<template>
  <div class="mybutton c-fff cur-po">
    {{val}}
  </div>
</template>

<script>
export default {
  props:['size','val']
}
</script>

<style lang="less" scoped>
  .mybutton{
    width: 11.5rem;
    height: 3rem;
    line-height: 3rem;
    font-size: 0.875rem;
    text-align: center;
    background-color: #ff4d4d;
    border-radius: 0.125rem;
  }
  .mybutton:hover{
    background-color: #ff4d4d;
  }
</style>